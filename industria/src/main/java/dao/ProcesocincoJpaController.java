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
import entidades.Procesocinco;
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
public class ProcesocincoJpaController implements Serializable {

    public ProcesocincoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesocinco procesocinco) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesocinco.getProcesoCollection() == null) {
            procesocinco.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesocinco.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesocinco.setIdCarro(idCarro);
            }
            Inventario idInventario = procesocinco.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesocinco.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesocinco.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesocinco.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesocinco.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesocinco.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesocinco);
            if (idCarro != null) {
                idCarro.getProcesocincoCollection().add(procesocinco);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesocincoCollection().add(procesocinco);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesocincoCollection().add(procesocinco);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesocinco.getProcesoCollection()) {
                Procesocinco oldPcincoOfProcesoCollectionProceso = procesoCollectionProceso.getPcinco();
                procesoCollectionProceso.setPcinco(procesocinco);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPcincoOfProcesoCollectionProceso != null) {
                    oldPcincoOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPcincoOfProcesoCollectionProceso = em.merge(oldPcincoOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesocinco(procesocinco.getId()) != null) {
                throw new PreexistingEntityException("Procesocinco " + procesocinco + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesocinco procesocinco) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesocinco persistentProcesocinco = em.find(Procesocinco.class, procesocinco.getId());
            Carro idCarroOld = persistentProcesocinco.getIdCarro();
            Carro idCarroNew = procesocinco.getIdCarro();
            Inventario idInventarioOld = persistentProcesocinco.getIdInventario();
            Inventario idInventarioNew = procesocinco.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesocinco.getIdSolicitud();
            Slicitud idSolicitudNew = procesocinco.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesocinco.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesocinco.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesocinco.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesocinco.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesocinco.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesocinco.setProcesoCollection(procesoCollectionNew);
            procesocinco = em.merge(procesocinco);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesocincoCollection().remove(procesocinco);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesocincoCollection().add(procesocinco);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesocincoCollection().remove(procesocinco);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesocincoCollection().add(procesocinco);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesocincoCollection().remove(procesocinco);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesocincoCollection().add(procesocinco);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPcinco(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesocinco oldPcincoOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPcinco();
                    procesoCollectionNewProceso.setPcinco(procesocinco);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPcincoOfProcesoCollectionNewProceso != null && !oldPcincoOfProcesoCollectionNewProceso.equals(procesocinco)) {
                        oldPcincoOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPcincoOfProcesoCollectionNewProceso = em.merge(oldPcincoOfProcesoCollectionNewProceso);
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
                Short id = procesocinco.getId();
                if (findProcesocinco(id) == null) {
                    throw new NonexistentEntityException("The procesocinco with id " + id + " no longer exists.");
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
            Procesocinco procesocinco;
            try {
                procesocinco = em.getReference(Procesocinco.class, id);
                procesocinco.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesocinco with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesocinco.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesocincoCollection().remove(procesocinco);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesocinco.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesocincoCollection().remove(procesocinco);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesocinco.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesocincoCollection().remove(procesocinco);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesocinco.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPcinco(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesocinco);
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

    public List<Procesocinco> findProcesocincoEntities() {
        return findProcesocincoEntities(true, -1, -1);
    }

    public List<Procesocinco> findProcesocincoEntities(int maxResults, int firstResult) {
        return findProcesocincoEntities(false, maxResults, firstResult);
    }

    private List<Procesocinco> findProcesocincoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesocinco.class));
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

    public Procesocinco findProcesocinco(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesocinco.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesocincoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesocinco> rt = cq.from(Procesocinco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
