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
import entidades.Revisioncarroceria;
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
public class RevisioncarroceriaJpaController implements Serializable {

    public RevisioncarroceriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisioncarroceria revisioncarroceria) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisioncarroceria.getProcesoochoCollection() == null) {
            revisioncarroceria.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisioncarroceria.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisioncarroceria.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisioncarroceria);
            for (Procesoocho procesoochoCollectionProcesoocho : revisioncarroceria.getProcesoochoCollection()) {
                Revisioncarroceria oldRcarroceriaOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRcarroceria();
                procesoochoCollectionProcesoocho.setRcarroceria(revisioncarroceria);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRcarroceriaOfProcesoochoCollectionProcesoocho != null) {
                    oldRcarroceriaOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRcarroceriaOfProcesoochoCollectionProcesoocho = em.merge(oldRcarroceriaOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisioncarroceria(revisioncarroceria.getId()) != null) {
                throw new PreexistingEntityException("Revisioncarroceria " + revisioncarroceria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisioncarroceria revisioncarroceria) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisioncarroceria persistentRevisioncarroceria = em.find(Revisioncarroceria.class, revisioncarroceria.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisioncarroceria.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisioncarroceria.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisioncarroceria.setProcesoochoCollection(procesoochoCollectionNew);
            revisioncarroceria = em.merge(revisioncarroceria);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRcarroceria(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisioncarroceria oldRcarroceriaOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRcarroceria();
                    procesoochoCollectionNewProcesoocho.setRcarroceria(revisioncarroceria);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRcarroceriaOfProcesoochoCollectionNewProcesoocho != null && !oldRcarroceriaOfProcesoochoCollectionNewProcesoocho.equals(revisioncarroceria)) {
                        oldRcarroceriaOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRcarroceriaOfProcesoochoCollectionNewProcesoocho = em.merge(oldRcarroceriaOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisioncarroceria.getId();
                if (findRevisioncarroceria(id) == null) {
                    throw new NonexistentEntityException("The revisioncarroceria with id " + id + " no longer exists.");
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
            Revisioncarroceria revisioncarroceria;
            try {
                revisioncarroceria = em.getReference(Revisioncarroceria.class, id);
                revisioncarroceria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisioncarroceria with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisioncarroceria.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRcarroceria(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisioncarroceria);
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

    public List<Revisioncarroceria> findRevisioncarroceriaEntities() {
        return findRevisioncarroceriaEntities(true, -1, -1);
    }

    public List<Revisioncarroceria> findRevisioncarroceriaEntities(int maxResults, int firstResult) {
        return findRevisioncarroceriaEntities(false, maxResults, firstResult);
    }

    private List<Revisioncarroceria> findRevisioncarroceriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisioncarroceria.class));
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

    public Revisioncarroceria findRevisioncarroceria(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisioncarroceria.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisioncarroceriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisioncarroceria> rt = cq.from(Revisioncarroceria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
