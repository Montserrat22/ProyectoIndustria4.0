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
import entidades.Procesouno;
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
public class ProcesounoJpaController implements Serializable {

    public ProcesounoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesouno procesouno) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesouno.getProcesoCollection() == null) {
            procesouno.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesouno.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesouno.setIdCarro(idCarro);
            }
            Inventario idInventario = procesouno.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesouno.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesouno.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesouno.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesouno.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesouno.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesouno);
            if (idCarro != null) {
                idCarro.getProcesounoCollection().add(procesouno);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesounoCollection().add(procesouno);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesounoCollection().add(procesouno);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesouno.getProcesoCollection()) {
                Procesouno oldPunoOfProcesoCollectionProceso = procesoCollectionProceso.getPuno();
                procesoCollectionProceso.setPuno(procesouno);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPunoOfProcesoCollectionProceso != null) {
                    oldPunoOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPunoOfProcesoCollectionProceso = em.merge(oldPunoOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesouno(procesouno.getId()) != null) {
                throw new PreexistingEntityException("Procesouno " + procesouno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesouno procesouno) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesouno persistentProcesouno = em.find(Procesouno.class, procesouno.getId());
            Carro idCarroOld = persistentProcesouno.getIdCarro();
            Carro idCarroNew = procesouno.getIdCarro();
            Inventario idInventarioOld = persistentProcesouno.getIdInventario();
            Inventario idInventarioNew = procesouno.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesouno.getIdSolicitud();
            Slicitud idSolicitudNew = procesouno.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesouno.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesouno.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesouno.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesouno.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesouno.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesouno.setProcesoCollection(procesoCollectionNew);
            procesouno = em.merge(procesouno);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesounoCollection().remove(procesouno);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesounoCollection().add(procesouno);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesounoCollection().remove(procesouno);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesounoCollection().add(procesouno);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesounoCollection().remove(procesouno);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesounoCollection().add(procesouno);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPuno(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesouno oldPunoOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPuno();
                    procesoCollectionNewProceso.setPuno(procesouno);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPunoOfProcesoCollectionNewProceso != null && !oldPunoOfProcesoCollectionNewProceso.equals(procesouno)) {
                        oldPunoOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPunoOfProcesoCollectionNewProceso = em.merge(oldPunoOfProcesoCollectionNewProceso);
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
                Short id = procesouno.getId();
                if (findProcesouno(id) == null) {
                    throw new NonexistentEntityException("The procesouno with id " + id + " no longer exists.");
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
            Procesouno procesouno;
            try {
                procesouno = em.getReference(Procesouno.class, id);
                procesouno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesouno with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesouno.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesounoCollection().remove(procesouno);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesouno.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesounoCollection().remove(procesouno);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesouno.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesounoCollection().remove(procesouno);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesouno.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPuno(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesouno);
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

    public List<Procesouno> findProcesounoEntities() {
        return findProcesounoEntities(true, -1, -1);
    }

    public List<Procesouno> findProcesounoEntities(int maxResults, int firstResult) {
        return findProcesounoEntities(false, maxResults, firstResult);
    }

    private List<Procesouno> findProcesounoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesouno.class));
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

    public Procesouno findProcesouno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesouno.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesounoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesouno> rt = cq.from(Procesouno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
