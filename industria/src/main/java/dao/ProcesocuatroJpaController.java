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
import entidades.Procesocuatro;
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
public class ProcesocuatroJpaController implements Serializable {

    public ProcesocuatroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesocuatro procesocuatro) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesocuatro.getProcesoCollection() == null) {
            procesocuatro.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesocuatro.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesocuatro.setIdCarro(idCarro);
            }
            Inventario idInventario = procesocuatro.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesocuatro.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesocuatro.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesocuatro.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesocuatro.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesocuatro.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesocuatro);
            if (idCarro != null) {
                idCarro.getProcesocuatroCollection().add(procesocuatro);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesocuatroCollection().add(procesocuatro);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesocuatroCollection().add(procesocuatro);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesocuatro.getProcesoCollection()) {
                Procesocuatro oldPcuatroOfProcesoCollectionProceso = procesoCollectionProceso.getPcuatro();
                procesoCollectionProceso.setPcuatro(procesocuatro);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPcuatroOfProcesoCollectionProceso != null) {
                    oldPcuatroOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPcuatroOfProcesoCollectionProceso = em.merge(oldPcuatroOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesocuatro(procesocuatro.getId()) != null) {
                throw new PreexistingEntityException("Procesocuatro " + procesocuatro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesocuatro procesocuatro) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesocuatro persistentProcesocuatro = em.find(Procesocuatro.class, procesocuatro.getId());
            Carro idCarroOld = persistentProcesocuatro.getIdCarro();
            Carro idCarroNew = procesocuatro.getIdCarro();
            Inventario idInventarioOld = persistentProcesocuatro.getIdInventario();
            Inventario idInventarioNew = procesocuatro.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesocuatro.getIdSolicitud();
            Slicitud idSolicitudNew = procesocuatro.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesocuatro.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesocuatro.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesocuatro.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesocuatro.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesocuatro.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesocuatro.setProcesoCollection(procesoCollectionNew);
            procesocuatro = em.merge(procesocuatro);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesocuatroCollection().remove(procesocuatro);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesocuatroCollection().add(procesocuatro);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesocuatroCollection().remove(procesocuatro);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesocuatroCollection().add(procesocuatro);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesocuatroCollection().remove(procesocuatro);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesocuatroCollection().add(procesocuatro);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPcuatro(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesocuatro oldPcuatroOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPcuatro();
                    procesoCollectionNewProceso.setPcuatro(procesocuatro);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPcuatroOfProcesoCollectionNewProceso != null && !oldPcuatroOfProcesoCollectionNewProceso.equals(procesocuatro)) {
                        oldPcuatroOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPcuatroOfProcesoCollectionNewProceso = em.merge(oldPcuatroOfProcesoCollectionNewProceso);
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
                Short id = procesocuatro.getId();
                if (findProcesocuatro(id) == null) {
                    throw new NonexistentEntityException("The procesocuatro with id " + id + " no longer exists.");
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
            Procesocuatro procesocuatro;
            try {
                procesocuatro = em.getReference(Procesocuatro.class, id);
                procesocuatro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesocuatro with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesocuatro.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesocuatroCollection().remove(procesocuatro);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesocuatro.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesocuatroCollection().remove(procesocuatro);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesocuatro.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesocuatroCollection().remove(procesocuatro);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesocuatro.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPcuatro(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesocuatro);
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

    public List<Procesocuatro> findProcesocuatroEntities() {
        return findProcesocuatroEntities(true, -1, -1);
    }

    public List<Procesocuatro> findProcesocuatroEntities(int maxResults, int firstResult) {
        return findProcesocuatroEntities(false, maxResults, firstResult);
    }

    private List<Procesocuatro> findProcesocuatroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesocuatro.class));
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

    public Procesocuatro findProcesocuatro(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesocuatro.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesocuatroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesocuatro> rt = cq.from(Procesocuatro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
