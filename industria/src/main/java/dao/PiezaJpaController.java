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
import entidades.Inventario;
import entidades.Pieza;
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
public class PiezaJpaController implements Serializable {

    public PiezaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pieza pieza) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pieza.getInventarioCollection() == null) {
            pieza.setInventarioCollection(new ArrayList<Inventario>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Inventario> attachedInventarioCollection = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionInventarioToAttach : pieza.getInventarioCollection()) {
                inventarioCollectionInventarioToAttach = em.getReference(inventarioCollectionInventarioToAttach.getClass(), inventarioCollectionInventarioToAttach.getId());
                attachedInventarioCollection.add(inventarioCollectionInventarioToAttach);
            }
            pieza.setInventarioCollection(attachedInventarioCollection);
            em.persist(pieza);
            for (Inventario inventarioCollectionInventario : pieza.getInventarioCollection()) {
                Pieza oldIdPiezaOfInventarioCollectionInventario = inventarioCollectionInventario.getIdPieza();
                inventarioCollectionInventario.setIdPieza(pieza);
                inventarioCollectionInventario = em.merge(inventarioCollectionInventario);
                if (oldIdPiezaOfInventarioCollectionInventario != null) {
                    oldIdPiezaOfInventarioCollectionInventario.getInventarioCollection().remove(inventarioCollectionInventario);
                    oldIdPiezaOfInventarioCollectionInventario = em.merge(oldIdPiezaOfInventarioCollectionInventario);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPieza(pieza.getId()) != null) {
                throw new PreexistingEntityException("Pieza " + pieza + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pieza pieza) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pieza persistentPieza = em.find(Pieza.class, pieza.getId());
            Collection<Inventario> inventarioCollectionOld = persistentPieza.getInventarioCollection();
            Collection<Inventario> inventarioCollectionNew = pieza.getInventarioCollection();
            Collection<Inventario> attachedInventarioCollectionNew = new ArrayList<Inventario>();
            for (Inventario inventarioCollectionNewInventarioToAttach : inventarioCollectionNew) {
                inventarioCollectionNewInventarioToAttach = em.getReference(inventarioCollectionNewInventarioToAttach.getClass(), inventarioCollectionNewInventarioToAttach.getId());
                attachedInventarioCollectionNew.add(inventarioCollectionNewInventarioToAttach);
            }
            inventarioCollectionNew = attachedInventarioCollectionNew;
            pieza.setInventarioCollection(inventarioCollectionNew);
            pieza = em.merge(pieza);
            for (Inventario inventarioCollectionOldInventario : inventarioCollectionOld) {
                if (!inventarioCollectionNew.contains(inventarioCollectionOldInventario)) {
                    inventarioCollectionOldInventario.setIdPieza(null);
                    inventarioCollectionOldInventario = em.merge(inventarioCollectionOldInventario);
                }
            }
            for (Inventario inventarioCollectionNewInventario : inventarioCollectionNew) {
                if (!inventarioCollectionOld.contains(inventarioCollectionNewInventario)) {
                    Pieza oldIdPiezaOfInventarioCollectionNewInventario = inventarioCollectionNewInventario.getIdPieza();
                    inventarioCollectionNewInventario.setIdPieza(pieza);
                    inventarioCollectionNewInventario = em.merge(inventarioCollectionNewInventario);
                    if (oldIdPiezaOfInventarioCollectionNewInventario != null && !oldIdPiezaOfInventarioCollectionNewInventario.equals(pieza)) {
                        oldIdPiezaOfInventarioCollectionNewInventario.getInventarioCollection().remove(inventarioCollectionNewInventario);
                        oldIdPiezaOfInventarioCollectionNewInventario = em.merge(oldIdPiezaOfInventarioCollectionNewInventario);
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
                Short id = pieza.getId();
                if (findPieza(id) == null) {
                    throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.");
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
            Pieza pieza;
            try {
                pieza = em.getReference(Pieza.class, id);
                pieza.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pieza with id " + id + " no longer exists.", enfe);
            }
            Collection<Inventario> inventarioCollection = pieza.getInventarioCollection();
            for (Inventario inventarioCollectionInventario : inventarioCollection) {
                inventarioCollectionInventario.setIdPieza(null);
                inventarioCollectionInventario = em.merge(inventarioCollectionInventario);
            }
            em.remove(pieza);
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

    public List<Pieza> findPiezaEntities() {
        return findPiezaEntities(true, -1, -1);
    }

    public List<Pieza> findPiezaEntities(int maxResults, int firstResult) {
        return findPiezaEntities(false, maxResults, firstResult);
    }

    private List<Pieza> findPiezaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pieza.class));
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

    public Pieza findPieza(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pieza.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pieza> rt = cq.from(Pieza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
