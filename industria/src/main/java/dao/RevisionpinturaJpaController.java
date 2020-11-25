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
import entidades.Revisionpintura;
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
public class RevisionpinturaJpaController implements Serializable {

    public RevisionpinturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revisionpintura revisionpintura) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (revisionpintura.getProcesoochoCollection() == null) {
            revisionpintura.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : revisionpintura.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            revisionpintura.setProcesoochoCollection(attachedProcesoochoCollection);
            em.persist(revisionpintura);
            for (Procesoocho procesoochoCollectionProcesoocho : revisionpintura.getProcesoochoCollection()) {
                Revisionpintura oldRpinturaOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getRpintura();
                procesoochoCollectionProcesoocho.setRpintura(revisionpintura);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldRpinturaOfProcesoochoCollectionProcesoocho != null) {
                    oldRpinturaOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldRpinturaOfProcesoochoCollectionProcesoocho = em.merge(oldRpinturaOfProcesoochoCollectionProcesoocho);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRevisionpintura(revisionpintura.getId()) != null) {
                throw new PreexistingEntityException("Revisionpintura " + revisionpintura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Revisionpintura revisionpintura) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Revisionpintura persistentRevisionpintura = em.find(Revisionpintura.class, revisionpintura.getId());
            Collection<Procesoocho> procesoochoCollectionOld = persistentRevisionpintura.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = revisionpintura.getProcesoochoCollection();
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            revisionpintura.setProcesoochoCollection(procesoochoCollectionNew);
            revisionpintura = em.merge(revisionpintura);
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setRpintura(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Revisionpintura oldRpinturaOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getRpintura();
                    procesoochoCollectionNewProcesoocho.setRpintura(revisionpintura);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldRpinturaOfProcesoochoCollectionNewProcesoocho != null && !oldRpinturaOfProcesoochoCollectionNewProcesoocho.equals(revisionpintura)) {
                        oldRpinturaOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldRpinturaOfProcesoochoCollectionNewProcesoocho = em.merge(oldRpinturaOfProcesoochoCollectionNewProcesoocho);
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
                Short id = revisionpintura.getId();
                if (findRevisionpintura(id) == null) {
                    throw new NonexistentEntityException("The revisionpintura with id " + id + " no longer exists.");
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
            Revisionpintura revisionpintura;
            try {
                revisionpintura = em.getReference(Revisionpintura.class, id);
                revisionpintura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revisionpintura with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoocho> procesoochoCollection = revisionpintura.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setRpintura(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            em.remove(revisionpintura);
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

    public List<Revisionpintura> findRevisionpinturaEntities() {
        return findRevisionpinturaEntities(true, -1, -1);
    }

    public List<Revisionpintura> findRevisionpinturaEntities(int maxResults, int firstResult) {
        return findRevisionpinturaEntities(false, maxResults, firstResult);
    }

    private List<Revisionpintura> findRevisionpinturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revisionpintura.class));
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

    public Revisionpintura findRevisionpintura(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revisionpintura.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevisionpinturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revisionpintura> rt = cq.from(Revisionpintura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
