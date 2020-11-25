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
import entidades.Carro;
import entidades.Inventario;
import entidades.Slicitud;
import entidades.Proceso;
import entidades.Procesodos;
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
public class ProcesodosJpaController implements Serializable {

    public ProcesodosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesodos procesodos) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesodos.getProcesoCollection() == null) {
            procesodos.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesodos.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesodos.setIdCarro(idCarro);
            }
            Inventario idInventario = procesodos.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesodos.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesodos.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesodos.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesodos.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesodos.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesodos);
            if (idCarro != null) {
                idCarro.getProcesodosCollection().add(procesodos);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesodosCollection().add(procesodos);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesodosCollection().add(procesodos);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesodos.getProcesoCollection()) {
                Procesodos oldPdosOfProcesoCollectionProceso = procesoCollectionProceso.getPdos();
                procesoCollectionProceso.setPdos(procesodos);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPdosOfProcesoCollectionProceso != null) {
                    oldPdosOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPdosOfProcesoCollectionProceso = em.merge(oldPdosOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesodos(procesodos.getId()) != null) {
                throw new PreexistingEntityException("Procesodos " + procesodos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesodos procesodos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesodos persistentProcesodos = em.find(Procesodos.class, procesodos.getId());
            Carro idCarroOld = persistentProcesodos.getIdCarro();
            Carro idCarroNew = procesodos.getIdCarro();
            Inventario idInventarioOld = persistentProcesodos.getIdInventario();
            Inventario idInventarioNew = procesodos.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesodos.getIdSolicitud();
            Slicitud idSolicitudNew = procesodos.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesodos.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesodos.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesodos.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesodos.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesodos.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesodos.setProcesoCollection(procesoCollectionNew);
            procesodos = em.merge(procesodos);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesodosCollection().remove(procesodos);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesodosCollection().add(procesodos);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesodosCollection().remove(procesodos);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesodosCollection().add(procesodos);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesodosCollection().remove(procesodos);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesodosCollection().add(procesodos);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPdos(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesodos oldPdosOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPdos();
                    procesoCollectionNewProceso.setPdos(procesodos);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPdosOfProcesoCollectionNewProceso != null && !oldPdosOfProcesoCollectionNewProceso.equals(procesodos)) {
                        oldPdosOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPdosOfProcesoCollectionNewProceso = em.merge(oldPdosOfProcesoCollectionNewProceso);
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
                Short id = procesodos.getId();
                if (findProcesodos(id) == null) {
                    throw new NonexistentEntityException("The procesodos with id " + id + " no longer exists.");
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
            Procesodos procesodos;
            try {
                procesodos = em.getReference(Procesodos.class, id);
                procesodos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesodos with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesodos.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesodosCollection().remove(procesodos);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesodos.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesodosCollection().remove(procesodos);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesodos.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesodosCollection().remove(procesodos);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesodos.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPdos(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesodos);
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

    public List<Procesodos> findProcesodosEntities() {
        return findProcesodosEntities(true, -1, -1);
    }

    public List<Procesodos> findProcesodosEntities(int maxResults, int firstResult) {
        return findProcesodosEntities(false, maxResults, firstResult);
    }

    private List<Procesodos> findProcesodosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesodos.class));
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

    public Procesodos findProcesodos(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesodos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesodosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesodos> rt = cq.from(Procesodos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
