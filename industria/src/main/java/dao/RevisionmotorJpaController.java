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
import entidades.Revisionmotor;
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
public class RevisionmotorJpaController implements Serializable {

    public RevisionmotorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisionmotor revisionmotor) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisionmotor.getProcesoochoCollection() == null) {
            revisionmotor.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisionmotor.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisionmotor.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisionmotor);
            for (Procesoocho procesoochoCollectionProcesoocho : revisionmotor.getProcesoochoCollection()) {
                Revisionmotor oldRmotorOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRmotor();
                procesoochoCollectionProcesoocho.setRmotor(revisionmotor);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRmotorOfProcesoochoCollectionProcesoocho != null) {
                    oldRmotorOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRmotorOfProcesoochoCollectionProcesoocho = em.merge(oldRmotorOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisionmotor(revisionmotor.getId()) != null) {
                throw new PreexistingEntityException("Revisionmotor " + revisionmotor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisionmotor revisionmotor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisionmotor persistentRevisionmotor = em.find(Revisionmotor.class, revisionmotor.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisionmotor.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisionmotor.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisionmotor.setProcesoochoCollection(procesoochoCollectionNew);
            revisionmotor = em.merge(revisionmotor);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRmotor(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisionmotor oldRmotorOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRmotor();
                    procesoochoCollectionNewProcesoocho.setRmotor(revisionmotor);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRmotorOfProcesoochoCollectionNewProcesoocho != null && !oldRmotorOfProcesoochoCollectionNewProcesoocho.equals(revisionmotor)) {
                        oldRmotorOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRmotorOfProcesoochoCollectionNewProcesoocho = em.merge(oldRmotorOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisionmotor.getId();
                if (findRevisionmotor(id) == null) {
                    throw new NonexistentEntityException("The revisionmotor with id " + id + " no longer exists.");
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
            Revisionmotor revisionmotor;
            try {
                revisionmotor = em.getReference(Revisionmotor.class, id);
                revisionmotor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionmotor with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisionmotor.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRmotor(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisionmotor);
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

    public List<Revisionmotor> findRevisionmotorEntities() {
        return findRevisionmotorEntities(true, -1, -1);
    }

    public List<Revisionmotor> findRevisionmotorEntities(int maxResults, int firstResult) {
        return findRevisionmotorEntities(false, maxResults, firstResult);
    }

    private List<Revisionmotor> findRevisionmotorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisionmotor.class));
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

    public Revisionmotor findRevisionmotor(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisionmotor.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionmotorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisionmotor> rt = cq.from(Revisionmotor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
