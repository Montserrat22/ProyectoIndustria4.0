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
import entidades.Procesotres;
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
public class ProcesotresJpaController implements Serializable {

    public ProcesotresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesotres procesotres) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesotres.getProcesoCollection() == null) {
            procesotres.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesotres.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesotres.setIdCarro(idCarro);
            }
            Inventario idInventario = procesotres.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesotres.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesotres.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesotres.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesotres.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesotres.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesotres);
            if (idCarro != null) {
                idCarro.getProcesotresCollection().add(procesotres);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesotresCollection().add(procesotres);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesotresCollection().add(procesotres);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesotres.getProcesoCollection()) {
                Procesotres oldPtresOfProcesoCollectionProceso = procesoCollectionProceso.getPtres();
                procesoCollectionProceso.setPtres(procesotres);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPtresOfProcesoCollectionProceso != null) {
                    oldPtresOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPtresOfProcesoCollectionProceso = em.merge(oldPtresOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesotres(procesotres.getId()) != null) {
                throw new PreexistingEntityException("Procesotres " + procesotres + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesotres procesotres) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesotres persistentProcesotres = em.find(Procesotres.class, procesotres.getId());
            Carro idCarroOld = persistentProcesotres.getIdCarro();
            Carro idCarroNew = procesotres.getIdCarro();
            Inventario idInventarioOld = persistentProcesotres.getIdInventario();
            Inventario idInventarioNew = procesotres.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesotres.getIdSolicitud();
            Slicitud idSolicitudNew = procesotres.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesotres.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesotres.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesotres.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesotres.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesotres.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesotres.setProcesoCollection(procesoCollectionNew);
            procesotres = em.merge(procesotres);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesotresCollection().remove(procesotres);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesotresCollection().add(procesotres);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesotresCollection().remove(procesotres);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesotresCollection().add(procesotres);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesotresCollection().remove(procesotres);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesotresCollection().add(procesotres);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPtres(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesotres oldPtresOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPtres();
                    procesoCollectionNewProceso.setPtres(procesotres);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPtresOfProcesoCollectionNewProceso != null && !oldPtresOfProcesoCollectionNewProceso.equals(procesotres)) {
                        oldPtresOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPtresOfProcesoCollectionNewProceso = em.merge(oldPtresOfProcesoCollectionNewProceso);
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
                Short id = procesotres.getId();
                if (findProcesotres(id) == null) {
                    throw new NonexistentEntityException("The procesotres with id " + id + " no longer exists.");
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
            Procesotres procesotres;
            try {
                procesotres = em.getReference(Procesotres.class, id);
                procesotres.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesotres with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesotres.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesotresCollection().remove(procesotres);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesotres.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesotresCollection().remove(procesotres);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesotres.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesotresCollection().remove(procesotres);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesotres.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPtres(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesotres);
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

    public List<Procesotres> findProcesotresEntities() {
        return findProcesotresEntities(true, -1, -1);
    }

    public List<Procesotres> findProcesotresEntities(int maxResults, int firstResult) {
        return findProcesotresEntities(false, maxResults, firstResult);
    }

    private List<Procesotres> findProcesotresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesotres.class));
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

    public Procesotres findProcesotres(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesotres.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesotresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesotres> rt = cq.from(Procesotres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
