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
import entidades.Revisionrendimiento;
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
public class RevisionrendimientoJpaController implements Serializable {

    public RevisionrendimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisionrendimiento revisionrendimiento) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisionrendimiento.getProcesoochoCollection() == null) {
            revisionrendimiento.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisionrendimiento.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisionrendimiento.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisionrendimiento);
            for (Procesoocho procesoochoCollectionProcesoocho : revisionrendimiento.getProcesoochoCollection()) {
                Revisionrendimiento oldRrendimientoOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRrendimiento();
                procesoochoCollectionProcesoocho.setRrendimiento(revisionrendimiento);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRrendimientoOfProcesoochoCollectionProcesoocho != null) {
                    oldRrendimientoOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRrendimientoOfProcesoochoCollectionProcesoocho = em.merge(oldRrendimientoOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisionrendimiento(revisionrendimiento.getId()) != null) {
                throw new PreexistingEntityException("Revisionrendimiento " + revisionrendimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisionrendimiento revisionrendimiento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisionrendimiento persistentRevisionrendimiento = em.find(Revisionrendimiento.class, revisionrendimiento.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisionrendimiento.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisionrendimiento.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisionrendimiento.setProcesoochoCollection(procesoochoCollectionNew);
            revisionrendimiento = em.merge(revisionrendimiento);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRrendimiento(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisionrendimiento oldRrendimientoOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRrendimiento();
                    procesoochoCollectionNewProcesoocho.setRrendimiento(revisionrendimiento);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRrendimientoOfProcesoochoCollectionNewProcesoocho != null && !oldRrendimientoOfProcesoochoCollectionNewProcesoocho.equals(revisionrendimiento)) {
                        oldRrendimientoOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRrendimientoOfProcesoochoCollectionNewProcesoocho = em.merge(oldRrendimientoOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisionrendimiento.getId();
                if (findRevisionrendimiento(id) == null) {
                    throw new NonexistentEntityException("The revisionrendimiento with id " + id + " no longer exists.");
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
            Revisionrendimiento revisionrendimiento;
            try {
                revisionrendimiento = em.getReference(Revisionrendimiento.class, id);
                revisionrendimiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionrendimiento with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisionrendimiento.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRrendimiento(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisionrendimiento);
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

    public List<Revisionrendimiento> findRevisionrendimientoEntities() {
        return findRevisionrendimientoEntities(true, -1, -1);
    }

    public List<Revisionrendimiento> findRevisionrendimientoEntities(int maxResults, int firstResult) {
        return findRevisionrendimientoEntities(false, maxResults, firstResult);
    }

    private List<Revisionrendimiento> findRevisionrendimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisionrendimiento.class));
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

    public Revisionrendimiento findRevisionrendimiento(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisionrendimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionrendimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisionrendimiento> rt = cq.from(Revisionrendimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
