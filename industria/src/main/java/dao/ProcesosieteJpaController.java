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
import entidades.Procesosiete;
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
public class ProcesosieteJpaController implements Serializable {

    public ProcesosieteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesosiete procesosiete) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesosiete.getProcesoCollection() == null) {
            procesosiete.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesosiete.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesosiete.setIdCarro(idCarro);
            }
            Inventario idInventario = procesosiete.getIdInventario();
            if (idInventario != null) {
                idInventario = em.getReference(idInventario.getClass(), idInventario.getId());
                procesosiete.setIdInventario(idInventario);
            }
            Slicitud idSolicitud = procesosiete.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesosiete.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesosiete.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesosiete.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesosiete);
            if (idCarro != null) {
                idCarro.getProcesosieteCollection().add(procesosiete);
                idCarro = em.merge(idCarro);
            }
            if (idInventario != null) {
                idInventario.getProcesosieteCollection().add(procesosiete);
                idInventario = em.merge(idInventario);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesosieteCollection().add(procesosiete);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesosiete.getProcesoCollection()) {
                Procesosiete oldPsieteOfProcesoCollectionProceso = procesoCollectionProceso.getPsiete();
                procesoCollectionProceso.setPsiete(procesosiete);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPsieteOfProcesoCollectionProceso != null) {
                    oldPsieteOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPsieteOfProcesoCollectionProceso = em.merge(oldPsieteOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesosiete(procesosiete.getId()) != null) {
                throw new PreexistingEntityException("Procesosiete " + procesosiete + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesosiete procesosiete) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesosiete persistentProcesosiete = em.find(Procesosiete.class, procesosiete.getId());
            Carro idCarroOld = persistentProcesosiete.getIdCarro();
            Carro idCarroNew = procesosiete.getIdCarro();
            Inventario idInventarioOld = persistentProcesosiete.getIdInventario();
            Inventario idInventarioNew = procesosiete.getIdInventario();
            Slicitud idSolicitudOld = persistentProcesosiete.getIdSolicitud();
            Slicitud idSolicitudNew = procesosiete.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesosiete.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesosiete.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesosiete.setIdCarro(idCarroNew);
            }
            if (idInventarioNew != null) {
                idInventarioNew = em.getReference(idInventarioNew.getClass(), idInventarioNew.getId());
                procesosiete.setIdInventario(idInventarioNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesosiete.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesosiete.setProcesoCollection(procesoCollectionNew);
            procesosiete = em.merge(procesosiete);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesosieteCollection().remove(procesosiete);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesosieteCollection().add(procesosiete);
                idCarroNew = em.merge(idCarroNew);
            }
            if (idInventarioOld != null && !idInventarioOld.equals(idInventarioNew)) {
                idInventarioOld.getProcesosieteCollection().remove(procesosiete);
                idInventarioOld = em.merge(idInventarioOld);
            }
            if (idInventarioNew != null && !idInventarioNew.equals(idInventarioOld)) {
                idInventarioNew.getProcesosieteCollection().add(procesosiete);
                idInventarioNew = em.merge(idInventarioNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesosieteCollection().remove(procesosiete);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesosieteCollection().add(procesosiete);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPsiete(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesosiete oldPsieteOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPsiete();
                    procesoCollectionNewProceso.setPsiete(procesosiete);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPsieteOfProcesoCollectionNewProceso != null && !oldPsieteOfProcesoCollectionNewProceso.equals(procesosiete)) {
                        oldPsieteOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPsieteOfProcesoCollectionNewProceso = em.merge(oldPsieteOfProcesoCollectionNewProceso);
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
                Short id = procesosiete.getId();
                if (findProcesosiete(id) == null) {
                    throw new NonexistentEntityException("The procesosiete with id " + id + " no longer exists.");
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
            Procesosiete procesosiete;
            try {
                procesosiete = em.getReference(Procesosiete.class, id);
                procesosiete.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesosiete with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesosiete.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesosieteCollection().remove(procesosiete);
                idCarro = em.merge(idCarro);
            }
            Inventario idInventario = procesosiete.getIdInventario();
            if (idInventario != null) {
                idInventario.getProcesosieteCollection().remove(procesosiete);
                idInventario = em.merge(idInventario);
            }
            Slicitud idSolicitud = procesosiete.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesosieteCollection().remove(procesosiete);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesosiete.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPsiete(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesosiete);
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

    public List<Procesosiete> findProcesosieteEntities() {
        return findProcesosieteEntities(true, -1, -1);
    }

    public List<Procesosiete> findProcesosieteEntities(int maxResults, int firstResult) {
        return findProcesosieteEntities(false, maxResults, firstResult);
    }

    private List<Procesosiete> findProcesosieteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesosiete.class));
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

    public Procesosiete findProcesosiete(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesosiete.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesosieteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesosiete> rt = cq.from(Procesosiete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
