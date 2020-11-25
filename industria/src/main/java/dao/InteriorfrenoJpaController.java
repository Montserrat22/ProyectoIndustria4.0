/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.Interiorfreno;
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
public class InteriorfrenoJpaController implements Serializable {

    public InteriorfrenoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interiorfreno interiorfreno) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (interiorfreno.getProcesoseisCollection() == null) {
            interiorfreno.setProcesoseisCollection(new ArrayList<Procesoseis>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Inventario idInventario = interiorfreno.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                interiorfreno.setIdInventario(idInventario);
            }
            Collection<Procesoseis> attachedProcesoseisCollection = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionProcesoseisToAttach : interiorfreno.getProcesoseisCollection()) {
                procesoseisCollectionProcesoseisToAttach = em.getReference(procesoseisCollectionProcesoseisToAttach.getClass(), procesoseisCollectionProcesoseisToAttach.getId());
                attachedProcesoseisCollection.add(procesoseisCollectionProcesoseisToAttach);
            }
            interiorfreno.setProcesoseisCollection(attachedProcesoseisCollection);
            em.persist(interiorfreno);
            if (idInventario != null) {
                idInventario.getInteriorfrenoCollection().add(interiorfreno);
                idInventario = em.merge(idInventario);
            }
            for (Procesoseis procesoseisCollectionProcesoseis : interiorfreno.getProcesoseisCollection()) {
                Interiorfreno oldIfrenoOfProcesoseisCollectionProcesoseis = procesoseisCollectionProcesoseis.getIfreno();
                procesoseisCollectionProcesoseis.setIfreno(interiorfreno);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
                if (oldIfrenoOfProcesoseisCollectionProcesoseis != null) {
                    oldIfrenoOfProcesoseisCollectionProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionProcesoseis);
                    oldIfrenoOfProcesoseisCollectionProcesoseis = em.merge(oldIfrenoOfProcesoseisCollectionProcesoseis);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInteriorfreno(interiorfreno.getId()) != null) {
                throw new PreexistingEntityException("Interiorfreno " + interiorfreno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interiorfreno interiorfreno) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Interiorfreno persistentInteriorfreno = em.find(Interiorfreno.class, interiorfreno.getId());
            Inventario idInventarioOld = persistentInteriorfreno.getIdInventario();
            Inventario idInventarioNew = interiorfreno.getIdInventario();
            Collection<Procesoseis> procesoseisCollectionOld = persistentInteriorfreno.getProcesoseisCollection();
            Collection<Procesoseis> procesoseisCollectionNew = interiorfreno.getProcesoseisCollection();
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                interiorfreno.setIdInventario(idInventarioNew);
            }
            Collection<Procesoseis> attachedProcesoseisCollectionNew = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionNewProcesoseisToAttach : procesoseisCollectionNew) {
                procesoseisCollectionNewProcesoseisToAttach = em.getReference(procesoseisCollectionNewProcesoseisToAttach.getClass(), procesoseisCollectionNewProcesoseisToAttach.getId());
                attachedProcesoseisCollectionNew.add(procesoseisCollectionNewProcesoseisToAttach);
            }
            procesoseisCollectionNew = attachedProcesoseisCollectionNew;
            interiorfreno.setProcesoseisCollection(procesoseisCollectionNew);
            interiorfreno = em.merge(interiorfreno);
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getInteriorfrenoCollection().remove(interiorfreno);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getInteriorfrenoCollection().add(interiorfreno);
                idInventarioNew = em.merge(idInventarioNew);
            }
            for (Procesoseis procesoseisCollectionOldProcesoseis : procesoseisCollectionOld) {
                if (!procesoseisCollectionNew.contains(procesoseisCollectionOldProcesoseis)) {
                    procesoseisCollectionOldProcesoseis.setIfreno(null);
                    procesoseisCollectionOldProcesoseis = em.merge(procesoseisCollectionOldProcesoseis);
                }
            }
            for (Procesoseis procesoseisCollectionNewProcesoseis : procesoseisCollectionNew) {
                if (!procesoseisCollectionOld.contains(procesoseisCollectionNewProcesoseis)) {
                    Interiorfreno oldIfrenoOfProcesoseisCollectionNewProcesoseis = procesoseisCollectionNewProcesoseis.getIfreno();
                    procesoseisCollectionNewProcesoseis.setIfreno(interiorfreno);
                    procesoseisCollectionNewProcesoseis = em.merge(procesoseisCollectionNewProcesoseis);
                    if (oldIfrenoOfProcesoseisCollectionNewProcesoseis != null && !oldIfrenoOfProcesoseisCollectionNewProcesoseis.equals(interiorfreno)) {
                        oldIfrenoOfProcesoseisCollectionNewProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionNewProcesoseis);
                        oldIfrenoOfProcesoseisCollectionNewProcesoseis = em.merge(oldIfrenoOfProcesoseisCollectionNewProcesoseis);
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
                Short id = interiorfreno.getId();
                if (findInteriorfreno(id) == null) {
                    throw new NonexistentEntityException("The interiorfreno with id " + id + " no longer exists.");
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
            Interiorfreno interiorfreno;
            try {
                interiorfreno = em.getReference(Interiorfreno.class, id);
                interiorfreno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interiorfreno with id " + id + " no longer exists.", enfe);
            }
            Inventario idInventario = interiorfreno.getIdInventario();
            if (idInventario != null) {
                idInventario.getInteriorfrenoCollection().remove(interiorfreno);
                idInventario = em.merge(idInventario);
            }
            Collection<Procesoseis> procesoseisCollection = interiorfreno.getProcesoseisCollection();
            for (Procesoseis procesoseisCollectionProcesoseis : procesoseisCollection) {
                procesoseisCollectionProcesoseis.setIfreno(null);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
            }
            em.remove(interiorfreno);
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

    public List<Interiorfreno> findInteriorfrenoEntities() {
        return findInteriorfrenoEntities(true, -1, -1);
    }

    public List<Interiorfreno> findInteriorfrenoEntities(int maxResults, int firstResult) {
        return findInteriorfrenoEntities(false, maxResults, firstResult);
    }

    private List<Interiorfreno> findInteriorfrenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interiorfreno.class));
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

    public Interiorfreno findInteriorfreno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interiorfreno.class, id);
        } finally {
            em.close();
        }
    }

    public int getInteriorfrenoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interiorfreno> rt = cq.from(Interiorfreno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
