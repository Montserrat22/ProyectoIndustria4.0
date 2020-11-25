/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.Interiorvolante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Inventario;
import entidades.Procesoseis;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jaker
 */
public class InteriorvolanteJpaController implements Serializable {

    public InteriorvolanteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interiorvolante interiorvolante) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (interiorvolante.getProcesoseisCollection() == null) {
            interiorvolante.setProcesoseisCollection(new ArrayList<Procesoseis>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Inventario idInventario = interiorvolante.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                interiorvolante.setIdInventario(idInventario);
            }
            Collection<Procesoseis> attachedProcesoseisCollection = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionProcesoseisToAttach : interiorvolante.getProcesoseisCollection()) {
                procesoseisCollectionProcesoseisToAttach = em.getReference(procesoseisCollectionProcesoseisToAttach.getClass(), procesoseisCollectionProcesoseisToAttach.getId());
                attachedProcesoseisCollection.add(procesoseisCollectionProcesoseisToAttach);
            }
            interiorvolante.setProcesoseisCollection(attachedProcesoseisCollection);
            em.persist(interiorvolante);
            if (idInventario != null) {
                idInventario.getInteriorvolanteCollection().add(interiorvolante);
                idInventario = em.merge(idInventario);
            }
            for (Procesoseis procesoseisCollectionProcesoseis : interiorvolante.getProcesoseisCollection()) {
                Interiorvolante oldIvolanteOfProcesoseisCollectionProcesoseis = procesoseisCollectionProcesoseis.getIvolante();
                procesoseisCollectionProcesoseis.setIvolante(interiorvolante);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
                if (oldIvolanteOfProcesoseisCollectionProcesoseis != null) {
                    oldIvolanteOfProcesoseisCollectionProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionProcesoseis);
                    oldIvolanteOfProcesoseisCollectionProcesoseis = em.merge(oldIvolanteOfProcesoseisCollectionProcesoseis);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInteriorvolante(interiorvolante.getId()) != null) {
                throw new PreexistingEntityException("Interiorvolante " + interiorvolante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interiorvolante interiorvolante) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Interiorvolante persistentInteriorvolante = em.find(Interiorvolante.class, interiorvolante.getId());
            Inventario idInventarioOld = persistentInteriorvolante.getIdInventario();
            Inventario idInventarioNew = interiorvolante.getIdInventario();
            Collection<Procesoseis> procesoseisCollectionOld = persistentInteriorvolante.getProcesoseisCollection();
            Collection<Procesoseis> procesoseisCollectionNew = interiorvolante.getProcesoseisCollection();
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                interiorvolante.setIdInventario(idInventarioNew);
            }
            Collection<Procesoseis> attachedProcesoseisCollectionNew = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionNewProcesoseisToAttach : procesoseisCollectionNew) {
                procesoseisCollectionNewProcesoseisToAttach = em.getReference(procesoseisCollectionNewProcesoseisToAttach.getClass(), procesoseisCollectionNewProcesoseisToAttach.getId());
                attachedProcesoseisCollectionNew.add(procesoseisCollectionNewProcesoseisToAttach);
            }
            procesoseisCollectionNew = attachedProcesoseisCollectionNew;
            interiorvolante.setProcesoseisCollection(procesoseisCollectionNew);
            interiorvolante = em.merge(interiorvolante);
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getInteriorvolanteCollection().remove(interiorvolante);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getInteriorvolanteCollection().add(interiorvolante);
                idInventarioNew = em.merge(idInventarioNew);
            }
            for (Procesoseis procesoseisCollectionOldProcesoseis : procesoseisCollectionOld) {
                if (!procesoseisCollectionNew.contains(procesoseisCollectionOldProcesoseis)) {
                    procesoseisCollectionOldProcesoseis.setIvolante(null);
                    procesoseisCollectionOldProcesoseis = em.merge(procesoseisCollectionOldProcesoseis);
                }
            }
            for (Procesoseis procesoseisCollectionNewProcesoseis : procesoseisCollectionNew) {
                if (!procesoseisCollectionOld.contains(procesoseisCollectionNewProcesoseis)) {
                    Interiorvolante oldIvolanteOfProcesoseisCollectionNewProcesoseis = procesoseisCollectionNewProcesoseis.getIvolante();
                    procesoseisCollectionNewProcesoseis.setIvolante(interiorvolante);
                    procesoseisCollectionNewProcesoseis = em.merge(procesoseisCollectionNewProcesoseis);
                    if (oldIvolanteOfProcesoseisCollectionNewProcesoseis != null && !oldIvolanteOfProcesoseisCollectionNewProcesoseis.equals(interiorvolante)) {
                        oldIvolanteOfProcesoseisCollectionNewProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionNewProcesoseis);
                        oldIvolanteOfProcesoseisCollectionNewProcesoseis = em.merge(oldIvolanteOfProcesoseisCollectionNewProcesoseis);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = interiorvolante.getId();
                if (findInteriorvolante(id) == null) {
                    throw new NonexistentEntityException("The interiorvolante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Interiorvolante interiorvolante;
            try {
                interiorvolante = em.getReference(Interiorvolante.class, id);
                interiorvolante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interiorvolante with id " + id + " no longer exists.", enfe);
            }
            Inventario idInventario = interiorvolante.getIdInventario();
            if (idInventario != null) {
                idInventario.getInteriorvolanteCollection().remove(interiorvolante);
                idInventario = em.merge(idInventario);
            }
            Collection<Procesoseis> procesoseisCollection = interiorvolante.getProcesoseisCollection();
            for (Procesoseis procesoseisCollectionProcesoseis : procesoseisCollection) {
                procesoseisCollectionProcesoseis.setIvolante(null);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
            }
            em.remove(interiorvolante);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Interiorvolante> findInteriorvolanteEntities() {
        return findInteriorvolanteEntities(true, -1, -1);
    }

    public List<Interiorvolante> findInteriorvolanteEntities(int maxResults, int firstResult) {
        return findInteriorvolanteEntities(false, maxResults, firstResult);
    }

    private List<Interiorvolante> findInteriorvolanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interiorvolante.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Interiorvolante findInteriorvolante(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interiorvolante.class, id);
        } finally {
            em.close();
        }
    }

    public int getInteriorvolanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interiorvolante> rt = cq.from(Interiorvolante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
