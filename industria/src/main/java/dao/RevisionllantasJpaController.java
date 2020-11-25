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
import entidades.Revisionllantas;
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
public class RevisionllantasJpaController implements Serializable {

    public RevisionllantasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisionllantas revisionllantas) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisionllantas.getProcesoochoCollection() == null) {
            revisionllantas.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisionllantas.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisionllantas.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisionllantas);
            for (Procesoocho procesoochoCollectionProcesoocho : revisionllantas.getProcesoochoCollection()) {
                Revisionllantas oldRllantasOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRllantas();
                procesoochoCollectionProcesoocho.setRllantas(revisionllantas);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRllantasOfProcesoochoCollectionProcesoocho != null) {
                    oldRllantasOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRllantasOfProcesoochoCollectionProcesoocho = em.merge(oldRllantasOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisionllantas(revisionllantas.getId()) != null) {
                throw new PreexistingEntityException("Revisionllantas " + revisionllantas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisionllantas revisionllantas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisionllantas persistentRevisionllantas = em.find(Revisionllantas.class, revisionllantas.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisionllantas.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisionllantas.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisionllantas.setProcesoochoCollection(procesoochoCollectionNew);
            revisionllantas = em.merge(revisionllantas);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRllantas(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisionllantas oldRllantasOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRllantas();
                    procesoochoCollectionNewProcesoocho.setRllantas(revisionllantas);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRllantasOfProcesoochoCollectionNewProcesoocho != null && !oldRllantasOfProcesoochoCollectionNewProcesoocho.equals(revisionllantas)) {
                        oldRllantasOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRllantasOfProcesoochoCollectionNewProcesoocho = em.merge(oldRllantasOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisionllantas.getId();
                if (findRevisionllantas(id) == null) {
                    throw new NonexistentEntityException("The revisionllantas with id " + id + " no longer exists.");
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
            Revisionllantas revisionllantas;
            try {
                revisionllantas = em.getReference(Revisionllantas.class, id);
                revisionllantas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionllantas with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisionllantas.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRllantas(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisionllantas);
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

    public List<Revisionllantas> findRevisionllantasEntities() {
        return findRevisionllantasEntities(true, -1, -1);
    }

    public List<Revisionllantas> findRevisionllantasEntities(int maxResults, int firstResult) {
        return findRevisionllantasEntities(false, maxResults, firstResult);
    }

    private List<Revisionllantas> findRevisionllantasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisionllantas.class));
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

    public Revisionllantas findRevisionllantas(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisionllantas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionllantasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisionllantas> rt = cq.from(Revisionllantas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
