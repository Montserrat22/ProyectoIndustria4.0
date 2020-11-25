/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Procesoocho;
import entidades.Revisioninteriores;
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
public class RevisioninterioresJpaController implements Serializable {

    public RevisioninterioresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisioninteriores revisioninteriores) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisioninteriores.getProcesoochoCollection() == null) {
            revisioninteriores.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisioninteriores.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisioninteriores.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisioninteriores);
            for (Procesoocho procesoochoCollectionProcesoocho : revisioninteriores.getProcesoochoCollection()) {
                Revisioninteriores oldRinterioresOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRinteriores();
                procesoochoCollectionProcesoocho.setRinteriores(revisioninteriores);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRinterioresOfProcesoochoCollectionProcesoocho != null) {
                    oldRinterioresOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRinterioresOfProcesoochoCollectionProcesoocho = em.merge(oldRinterioresOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisioninteriores(revisioninteriores.getId()) != null) {
                throw new PreexistingEntityException("Revisioninteriores " + revisioninteriores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisioninteriores revisioninteriores) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisioninteriores persistentRevisioninteriores = em.find(Revisioninteriores.class, revisioninteriores.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisioninteriores.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisioninteriores.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisioninteriores.setProcesoochoCollection(procesoochoCollectionNew);
            revisioninteriores = em.merge(revisioninteriores);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRinteriores(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisioninteriores oldRinterioresOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRinteriores();
                    procesoochoCollectionNewProcesoocho.setRinteriores(revisioninteriores);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRinterioresOfProcesoochoCollectionNewProcesoocho != null && !oldRinterioresOfProcesoochoCollectionNewProcesoocho.equals(revisioninteriores)) {
                        oldRinterioresOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRinterioresOfProcesoochoCollectionNewProcesoocho = em.merge(oldRinterioresOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisioninteriores.getId();
                if (findRevisioninteriores(id) == null) {
                    throw new NonexistentEntityException("The revisioninteriores with id " + id + " no longer exists.");
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
            Revisioninteriores revisioninteriores;
            try {
                revisioninteriores = em.getReference(Revisioninteriores.class, id);
                revisioninteriores.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisioninteriores with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisioninteriores.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRinteriores(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisioninteriores);
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

    public List<Revisioninteriores> findRevisioninterioresEntities() {
        return findRevisioninterioresEntities(true, -1, -1);
    }

    public List<Revisioninteriores> findRevisioninterioresEntities(int maxResults, int firstResult) {
        return findRevisioninterioresEntities(false, maxResults, firstResult);
    }

    private List<Revisioninteriores> findRevisioninterioresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisioninteriores.class));
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

    public Revisioninteriores findRevisioninteriores(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisioninteriores.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisioninterioresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisioninteriores> rt = cq.from(Revisioninteriores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
