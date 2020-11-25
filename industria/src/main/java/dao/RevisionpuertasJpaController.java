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
import entidades.Revisionpuertas;
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
public class RevisionpuertasJpaController implements Serializable {

    public RevisionpuertasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisionpuertas revisionpuertas) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisionpuertas.getProcesoochoCollection() == null) {
            revisionpuertas.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisionpuertas.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisionpuertas.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisionpuertas);
            for (Procesoocho procesoochoCollectionProcesoocho : revisionpuertas.getProcesoochoCollection()) {
                Revisionpuertas oldRpuertasOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRpuertas();
                procesoochoCollectionProcesoocho.setRpuertas(revisionpuertas);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRpuertasOfProcesoochoCollectionProcesoocho != null) {
                    oldRpuertasOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRpuertasOfProcesoochoCollectionProcesoocho = em.merge(oldRpuertasOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisionpuertas(revisionpuertas.getId()) != null) {
                throw new PreexistingEntityException("Revisionpuertas " + revisionpuertas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisionpuertas revisionpuertas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisionpuertas persistentRevisionpuertas = em.find(Revisionpuertas.class, revisionpuertas.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisionpuertas.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisionpuertas.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisionpuertas.setProcesoochoCollection(procesoochoCollectionNew);
            revisionpuertas = em.merge(revisionpuertas);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRpuertas(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisionpuertas oldRpuertasOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRpuertas();
                    procesoochoCollectionNewProcesoocho.setRpuertas(revisionpuertas);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRpuertasOfProcesoochoCollectionNewProcesoocho != null && !oldRpuertasOfProcesoochoCollectionNewProcesoocho.equals(revisionpuertas)) {
                        oldRpuertasOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRpuertasOfProcesoochoCollectionNewProcesoocho = em.merge(oldRpuertasOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisionpuertas.getId();
                if (findRevisionpuertas(id) == null) {
                    throw new NonexistentEntityException("The revisionpuertas with id " + id + " no longer exists.");
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
            Revisionpuertas revisionpuertas;
            try {
                revisionpuertas = em.getReference(Revisionpuertas.class, id);
                revisionpuertas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionpuertas with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisionpuertas.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRpuertas(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisionpuertas);
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

    public List<Revisionpuertas> findRevisionpuertasEntities() {
        return findRevisionpuertasEntities(true, -1, -1);
    }

    public List<Revisionpuertas> findRevisionpuertasEntities(int maxResults, int firstResult) {
        return findRevisionpuertasEntities(false, maxResults, firstResult);
    }

    private List<Revisionpuertas> findRevisionpuertasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisionpuertas.class));
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

    public Revisionpuertas findRevisionpuertas(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisionpuertas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionpuertasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisionpuertas> rt = cq.from(Revisionpuertas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
