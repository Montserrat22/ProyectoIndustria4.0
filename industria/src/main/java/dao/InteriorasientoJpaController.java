/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.Interiorasiento;
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
public class InteriorasientoJpaController implements Serializable {

    public InteriorasientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interiorasiento interiorasiento) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (interiorasiento.getProcesoseisCollection() == null) {
            interiorasiento.setProcesoseisCollection(new ArrayList<Procesoseis>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Inventario idInventario = interiorasiento.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                interiorasiento.setIdInventario(idInventario);
            }
            Collection<Procesoseis> attachedProcesoseisCollection = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionProcesoseisToAttach : interiorasiento.getProcesoseisCollection()) {
                procesoseisCollectionProcesoseisToAttach = em.getReference(procesoseisCollectionProcesoseisToAttach.getClass(), procesoseisCollectionProcesoseisToAttach.getId());
                attachedProcesoseisCollection.add(procesoseisCollectionProcesoseisToAttach);
            }
            interiorasiento.setProcesoseisCollection(attachedProcesoseisCollection);
            em.persist(interiorasiento);
            if (idInventario != null) {
                idInventario.getInteriorasientoCollection().add(interiorasiento);
                idInventario = em.merge(idInventario);
            }
            for (Procesoseis procesoseisCollectionProcesoseis : interiorasiento.getProcesoseisCollection()) {
                Interiorasiento oldIasientoOfProcesoseisCollectionProcesoseis = procesoseisCollectionProcesoseis.getIasiento();
                procesoseisCollectionProcesoseis.setIasiento(interiorasiento);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
                if (oldIasientoOfProcesoseisCollectionProcesoseis != null) {
                    oldIasientoOfProcesoseisCollectionProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionProcesoseis);
                    oldIasientoOfProcesoseisCollectionProcesoseis = em.merge(oldIasientoOfProcesoseisCollectionProcesoseis);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInteriorasiento(interiorasiento.getId()) != null) {
                throw new PreexistingEntityException("Interiorasiento " + interiorasiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interiorasiento interiorasiento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Interiorasiento persistentInteriorasiento = em.find(Interiorasiento.class, interiorasiento.getId());
            Inventario idInventarioOld = persistentInteriorasiento.getIdInventario();
            Inventario idInventarioNew = interiorasiento.getIdInventario();
            Collection<Procesoseis> procesoseisCollectionOld = persistentInteriorasiento.getProcesoseisCollection();
            Collection<Procesoseis> procesoseisCollectionNew = interiorasiento.getProcesoseisCollection();
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                interiorasiento.setIdInventario(idInventarioNew);
            }
            Collection<Procesoseis> attachedProcesoseisCollectionNew = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionNewProcesoseisToAttach : procesoseisCollectionNew) {
                procesoseisCollectionNewProcesoseisToAttach = em.getReference(procesoseisCollectionNewProcesoseisToAttach.getClass(), procesoseisCollectionNewProcesoseisToAttach.getId());
                attachedProcesoseisCollectionNew.add(procesoseisCollectionNewProcesoseisToAttach);
            }
            procesoseisCollectionNew = attachedProcesoseisCollectionNew;
            interiorasiento.setProcesoseisCollection(procesoseisCollectionNew);
            interiorasiento = em.merge(interiorasiento);
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getInteriorasientoCollection().remove(interiorasiento);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getInteriorasientoCollection().add(interiorasiento);
                idInventarioNew = em.merge(idInventarioNew);
            }
            for (Procesoseis procesoseisCollectionOldProcesoseis : procesoseisCollectionOld) {
                if (!procesoseisCollectionNew.contains(procesoseisCollectionOldProcesoseis)) {
                    procesoseisCollectionOldProcesoseis.setIasiento(null);
                    procesoseisCollectionOldProcesoseis = em.merge(procesoseisCollectionOldProcesoseis);
                }
            }
            for (Procesoseis procesoseisCollectionNewProcesoseis : procesoseisCollectionNew) {
                if (!procesoseisCollectionOld.contains(procesoseisCollectionNewProcesoseis)) {
                    Interiorasiento oldIasientoOfProcesoseisCollectionNewProcesoseis = procesoseisCollectionNewProcesoseis.getIasiento();
                    procesoseisCollectionNewProcesoseis.setIasiento(interiorasiento);
                    procesoseisCollectionNewProcesoseis = em.merge(procesoseisCollectionNewProcesoseis);
                    if (oldIasientoOfProcesoseisCollectionNewProcesoseis != null && !oldIasientoOfProcesoseisCollectionNewProcesoseis.equals(interiorasiento)) {
                        oldIasientoOfProcesoseisCollectionNewProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionNewProcesoseis);
                        oldIasientoOfProcesoseisCollectionNewProcesoseis = em.merge(oldIasientoOfProcesoseisCollectionNewProcesoseis);
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
                Short id = interiorasiento.getId();
                if (findInteriorasiento(id) == null) {
                    throw new NonexistentEntityException("The interiorasiento with id " + id + " no longer exists.");
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
            Interiorasiento interiorasiento;
            try {
                interiorasiento = em.getReference(Interiorasiento.class, id);
                interiorasiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interiorasiento with id " + id + " no longer exists.", enfe);
            }
            Inventario idInventario = interiorasiento.getIdInventario();
            if (idInventario != null) {
                idInventario.getInteriorasientoCollection().remove(interiorasiento);
                idInventario = em.merge(idInventario);
            }
            Collection<Procesoseis> procesoseisCollection = interiorasiento.getProcesoseisCollection();
            for (Procesoseis procesoseisCollectionProcesoseis : procesoseisCollection) {
                procesoseisCollectionProcesoseis.setIasiento(null);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
            }
            em.remove(interiorasiento);
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

    public List<Interiorasiento> findInteriorasientoEntities() {
        return findInteriorasientoEntities(true, -1, -1);
    }

    public List<Interiorasiento> findInteriorasientoEntities(int maxResults, int firstResult) {
        return findInteriorasientoEntities(false, maxResults, firstResult);
    }

    private List<Interiorasiento> findInteriorasientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interiorasiento.class));
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

    public Interiorasiento findInteriorasiento(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interiorasiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getInteriorasientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interiorasiento> rt = cq.from(Interiorasiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
