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
import entidades.Interiorasiento;
import entidades.Interiorfreno;
import entidades.Interiorvolante;
import entidades.Slicitud;
import entidades.Proceso;
import entidades.Procesoseis;
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
public class ProcesoseisJpaController implements Serializable {

    public ProcesoseisJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesoseis procesoseis) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesoseis.getProcesoCollection() == null) {
            procesoseis.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesoseis.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesoseis.setIdCarro(idCarro);
            }
            Interiorasiento iasiento = procesoseis.getIasiento();
            if (iasiento != null) {
                iasiento = em.getReference(iasiento.getClass(), iasiento.getId());
                procesoseis.setIasiento(iasiento);
            }
            Interiorfreno ifreno = procesoseis.getIfreno();
            if (ifreno != null) {
                ifreno = em.getReference(ifreno.getClass(), ifreno.getId());
                procesoseis.setIfreno(ifreno);
            }
            Interiorvolante ivolante = procesoseis.getIvolante();
            if (ivolante != null) {
                ivolante = em.getReference(ivolante.getClass(), ivolante.getId());
                procesoseis.setIvolante(ivolante);
            }
            Slicitud idSolicitud = procesoseis.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesoseis.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesoseis.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesoseis.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesoseis);
            if (idCarro != null) {
                idCarro.getProcesoseisCollection().add(procesoseis);
                idCarro = em.merge(idCarro);
            }
            if (iasiento != null) {
                iasiento.getProcesoseisCollection().add(procesoseis);
                iasiento = em.merge(iasiento);
            }
            if (ifreno != null) {
                ifreno.getProcesoseisCollection().add(procesoseis);
                ifreno = em.merge(ifreno);
            }
            if (ivolante != null) {
                ivolante.getProcesoseisCollection().add(procesoseis);
                ivolante = em.merge(ivolante);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesoseisCollection().add(procesoseis);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesoseis.getProcesoCollection()) {
                Procesoseis oldPseisOfProcesoCollectionProceso = procesoCollectionProceso.getPseis();
                procesoCollectionProceso.setPseis(procesoseis);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPseisOfProcesoCollectionProceso != null) {
                    oldPseisOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPseisOfProcesoCollectionProceso = em.merge(oldPseisOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesoseis(procesoseis.getId()) != null) {
                throw new PreexistingEntityException("Procesoseis " + procesoseis + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesoseis procesoseis) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesoseis persistentProcesoseis = em.find(Procesoseis.class, procesoseis.getId());
            Carro idCarroOld = persistentProcesoseis.getIdCarro();
            Carro idCarroNew = procesoseis.getIdCarro();
            Interiorasiento iasientoOld = persistentProcesoseis.getIasiento();
            Interiorasiento iasientoNew = procesoseis.getIasiento();
            Interiorfreno ifrenoOld = persistentProcesoseis.getIfreno();
            Interiorfreno ifrenoNew = procesoseis.getIfreno();
            Interiorvolante ivolanteOld = persistentProcesoseis.getIvolante();
            Interiorvolante ivolanteNew = procesoseis.getIvolante();
            Slicitud idSolicitudOld = persistentProcesoseis.getIdSolicitud();
            Slicitud idSolicitudNew = procesoseis.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesoseis.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesoseis.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesoseis.setIdCarro(idCarroNew);
            }
            if (iasientoNew != null) {
                iasientoNew = em.getReference(iasientoNew.getClass(), iasientoNew.getId());
                procesoseis.setIasiento(iasientoNew);
            }
            if (ifrenoNew != null) {
                ifrenoNew = em.getReference(ifrenoNew.getClass(), ifrenoNew.getId());
                procesoseis.setIfreno(ifrenoNew);
            }
            if (ivolanteNew != null) {
                ivolanteNew = em.getReference(ivolanteNew.getClass(), ivolanteNew.getId());
                procesoseis.setIvolante(ivolanteNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesoseis.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesoseis.setProcesoCollection(procesoCollectionNew);
            procesoseis = em.merge(procesoseis);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesoseisCollection().remove(procesoseis);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesoseisCollection().add(procesoseis);
                idCarroNew = em.merge(idCarroNew);
            }
            if (iasientoOld != null && !iasientoOld.equals(iasientoNew)) {
                iasientoOld.getProcesoseisCollection().remove(procesoseis);
                iasientoOld = em.merge(iasientoOld);
            }
            if (iasientoNew != null && !iasientoNew.equals(iasientoOld)) {
                iasientoNew.getProcesoseisCollection().add(procesoseis);
                iasientoNew = em.merge(iasientoNew);
            }
            if (ifrenoOld != null && !ifrenoOld.equals(ifrenoNew)) {
                ifrenoOld.getProcesoseisCollection().remove(procesoseis);
                ifrenoOld = em.merge(ifrenoOld);
            }
            if (ifrenoNew != null && !ifrenoNew.equals(ifrenoOld)) {
                ifrenoNew.getProcesoseisCollection().add(procesoseis);
                ifrenoNew = em.merge(ifrenoNew);
            }
            if (ivolanteOld != null && !ivolanteOld.equals(ivolanteNew)) {
                ivolanteOld.getProcesoseisCollection().remove(procesoseis);
                ivolanteOld = em.merge(ivolanteOld);
            }
            if (ivolanteNew != null && !ivolanteNew.equals(ivolanteOld)) {
                ivolanteNew.getProcesoseisCollection().add(procesoseis);
                ivolanteNew = em.merge(ivolanteNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesoseisCollection().remove(procesoseis);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesoseisCollection().add(procesoseis);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPseis(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesoseis oldPseisOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPseis();
                    procesoCollectionNewProceso.setPseis(procesoseis);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPseisOfProcesoCollectionNewProceso != null && !oldPseisOfProcesoCollectionNewProceso.equals(procesoseis)) {
                        oldPseisOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPseisOfProcesoCollectionNewProceso = em.merge(oldPseisOfProcesoCollectionNewProceso);
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
                Short id = procesoseis.getId();
                if (findProcesoseis(id) == null) {
                    throw new NonexistentEntityException("The procesoseis with id " + id + " no longer exists.");
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
            Procesoseis procesoseis;
            try {
                procesoseis = em.getReference(Procesoseis.class, id);
                procesoseis.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoseis with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesoseis.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesoseisCollection().remove(procesoseis);
                idCarro = em.merge(idCarro);
            }
            Interiorasiento iasiento = procesoseis.getIasiento();
            if (iasiento != null) {
                iasiento.getProcesoseisCollection().remove(procesoseis);
                iasiento = em.merge(iasiento);
            }
            Interiorfreno ifreno = procesoseis.getIfreno();
            if (ifreno != null) {
                ifreno.getProcesoseisCollection().remove(procesoseis);
                ifreno = em.merge(ifreno);
            }
            Interiorvolante ivolante = procesoseis.getIvolante();
            if (ivolante != null) {
                ivolante.getProcesoseisCollection().remove(procesoseis);
                ivolante = em.merge(ivolante);
            }
            Slicitud idSolicitud = procesoseis.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesoseisCollection().remove(procesoseis);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesoseis.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPseis(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesoseis);
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

    public List<Procesoseis> findProcesoseisEntities() {
        return findProcesoseisEntities(true, -1, -1);
    }

    public List<Procesoseis> findProcesoseisEntities(int maxResults, int firstResult) {
        return findProcesoseisEntities(false, maxResults, firstResult);
    }

    private List<Procesoseis> findProcesoseisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesoseis.class));
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

    public Procesoseis findProcesoseis(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesoseis.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoseisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesoseis> rt = cq.from(Procesoseis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
