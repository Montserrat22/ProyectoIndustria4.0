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
import entidades.Revisioncarroceria;
import entidades.Revisioninteriores;
import entidades.Revisionllantas;
import entidades.Revisionmotor;
import entidades.Revisionpintura;
import entidades.Revisionpuertas;
import entidades.Revisionrendimiento;
import entidades.Slicitud;
import entidades.Proceso;
import entidades.Procesoocho;
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
public class ProcesoochoJpaController implements Serializable {

    public ProcesoochoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesoocho procesoocho) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (procesoocho.getProcesoCollection() == null) {
            procesoocho.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = procesoocho.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                procesoocho.setIdCarro(idCarro);
            }
            Revisioncarroceria rcarroceria = procesoocho.getRcarroceria();
            if (rcarroceria != null) {
                rcarroceria = em.getReference(rcarroceria.getClass(), rcarroceria.getId());
                procesoocho.setRcarroceria(rcarroceria);
            }
            Revisioninteriores rinteriores = procesoocho.getRinteriores();
            if (rinteriores != null) {
                rinteriores = em.getReference(rinteriores.getClass(), rinteriores.getId());
                procesoocho.setRinteriores(rinteriores);
            }
            Revisionllantas rllantas = procesoocho.getRllantas();
            if (rllantas != null) {
                rllantas = em.getReference(rllantas.getClass(), rllantas.getId());
                procesoocho.setRllantas(rllantas);
            }
            Revisionmotor rmotor = procesoocho.getRmotor();
            if (rmotor != null) {
                rmotor = em.getReference(rmotor.getClass(), rmotor.getId());
                procesoocho.setRmotor(rmotor);
            }
            Revisionpintura rpintura = procesoocho.getRpintura();
            if (rpintura != null) {
                rpintura = em.getReference(rpintura.getClass(), rpintura.getId());
                procesoocho.setRpintura(rpintura);
            }
            Revisionpuertas rpuertas = procesoocho.getRpuertas();
            if (rpuertas != null) {
                rpuertas = em.getReference(rpuertas.getClass(), rpuertas.getId());
                procesoocho.setRpuertas(rpuertas);
            }
            Revisionrendimiento rrendimiento = procesoocho.getRrendimiento();
            if (rrendimiento != null) {
                rrendimiento = em.getReference(rrendimiento.getClass(), rrendimiento.getId());
                procesoocho.setRrendimiento(rrendimiento);
            }
            Slicitud idSolicitud = procesoocho.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getId());
                procesoocho.setIdSolicitud(idSolicitud);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : procesoocho.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            procesoocho.setProcesoCollection(attachedProcesoCollection);
            em.persist(procesoocho);
            if (idCarro != null) {
                idCarro.getProcesoochoCollection().add(procesoocho);
                idCarro = em.merge(idCarro);
            }
            if (rcarroceria != null) {
                rcarroceria.getProcesoochoCollection().add(procesoocho);
                rcarroceria = em.merge(rcarroceria);
            }
            if (rinteriores != null) {
                rinteriores.getProcesoochoCollection().add(procesoocho);
                rinteriores = em.merge(rinteriores);
            }
            if (rllantas != null) {
                rllantas.getProcesoochoCollection().add(procesoocho);
                rllantas = em.merge(rllantas);
            }
            if (rmotor != null) {
                rmotor.getProcesoochoCollection().add(procesoocho);
                rmotor = em.merge(rmotor);
            }
            if (rpintura != null) {
                rpintura.getProcesoochoCollection().add(procesoocho);
                rpintura = em.merge(rpintura);
            }
            if (rpuertas != null) {
                rpuertas.getProcesoochoCollection().add(procesoocho);
                rpuertas = em.merge(rpuertas);
            }
            if (rrendimiento != null) {
                rrendimiento.getProcesoochoCollection().add(procesoocho);
                rrendimiento = em.merge(rrendimiento);
            }
            if (idSolicitud != null) {
                idSolicitud.getProcesoochoCollection().add(procesoocho);
                idSolicitud = em.merge(idSolicitud);
            }
            for (Proceso procesoCollectionProceso : procesoocho.getProcesoCollection()) {
                Procesoocho oldPochoOfProcesoCollectionProceso = procesoCollectionProceso.getPocho();
                procesoCollectionProceso.setPocho(procesoocho);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldPochoOfProcesoCollectionProceso != null) {
                    oldPochoOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldPochoOfProcesoCollectionProceso = em.merge(oldPochoOfProcesoCollectionProceso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProcesoocho(procesoocho.getId()) != null) {
                throw new PreexistingEntityException("Procesoocho " + procesoocho + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesoocho procesoocho) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Procesoocho persistentProcesoocho = em.find(Procesoocho.class, procesoocho.getId());
            Carro idCarroOld = persistentProcesoocho.getIdCarro();
            Carro idCarroNew = procesoocho.getIdCarro();
            Revisioncarroceria rcarroceriaOld = persistentProcesoocho.getRcarroceria();
            Revisioncarroceria rcarroceriaNew = procesoocho.getRcarroceria();
            Revisioninteriores rinterioresOld = persistentProcesoocho.getRinteriores();
            Revisioninteriores rinterioresNew = procesoocho.getRinteriores();
            Revisionllantas rllantasOld = persistentProcesoocho.getRllantas();
            Revisionllantas rllantasNew = procesoocho.getRllantas();
            Revisionmotor rmotorOld = persistentProcesoocho.getRmotor();
            Revisionmotor rmotorNew = procesoocho.getRmotor();
            Revisionpintura rpinturaOld = persistentProcesoocho.getRpintura();
            Revisionpintura rpinturaNew = procesoocho.getRpintura();
            Revisionpuertas rpuertasOld = persistentProcesoocho.getRpuertas();
            Revisionpuertas rpuertasNew = procesoocho.getRpuertas();
            Revisionrendimiento rrendimientoOld = persistentProcesoocho.getRrendimiento();
            Revisionrendimiento rrendimientoNew = procesoocho.getRrendimiento();
            Slicitud idSolicitudOld = persistentProcesoocho.getIdSolicitud();
            Slicitud idSolicitudNew = procesoocho.getIdSolicitud();
            Collection<Proceso> procesoCollectionOld = persistentProcesoocho.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = procesoocho.getProcesoCollection();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                procesoocho.setIdCarro(idCarroNew);
            }
            if (rcarroceriaNew != null) {
                rcarroceriaNew = em.getReference(rcarroceriaNew.getClass(), rcarroceriaNew.getId());
                procesoocho.setRcarroceria(rcarroceriaNew);
            }
            if (rinterioresNew != null) {
                rinterioresNew = em.getReference(rinterioresNew.getClass(), rinterioresNew.getId());
                procesoocho.setRinteriores(rinterioresNew);
            }
            if (rllantasNew != null) {
                rllantasNew = em.getReference(rllantasNew.getClass(), rllantasNew.getId());
                procesoocho.setRllantas(rllantasNew);
            }
            if (rmotorNew != null) {
                rmotorNew = em.getReference(rmotorNew.getClass(), rmotorNew.getId());
                procesoocho.setRmotor(rmotorNew);
            }
            if (rpinturaNew != null) {
                rpinturaNew = em.getReference(rpinturaNew.getClass(), rpinturaNew.getId());
                procesoocho.setRpintura(rpinturaNew);
            }
            if (rpuertasNew != null) {
                rpuertasNew = em.getReference(rpuertasNew.getClass(), rpuertasNew.getId());
                procesoocho.setRpuertas(rpuertasNew);
            }
            if (rrendimientoNew != null) {
                rrendimientoNew = em.getReference(rrendimientoNew.getClass(), rrendimientoNew.getId());
                procesoocho.setRrendimiento(rrendimientoNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getId());
                procesoocho.setIdSolicitud(idSolicitudNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            procesoocho.setProcesoCollection(procesoCollectionNew);
            procesoocho = em.merge(procesoocho);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesoochoCollection().remove(procesoocho);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesoochoCollection().add(procesoocho);
                idCarroNew = em.merge(idCarroNew);
            }
            if (rcarroceriaOld != null && !rcarroceriaOld.equals(rcarroceriaNew)) {
                rcarroceriaOld.getProcesoochoCollection().remove(procesoocho);
                rcarroceriaOld = em.merge(rcarroceriaOld);
            }
            if (rcarroceriaNew != null && !rcarroceriaNew.equals(rcarroceriaOld)) {
                rcarroceriaNew.getProcesoochoCollection().add(procesoocho);
                rcarroceriaNew = em.merge(rcarroceriaNew);
            }
            if (rinterioresOld != null && !rinterioresOld.equals(rinterioresNew)) {
                rinterioresOld.getProcesoochoCollection().remove(procesoocho);
                rinterioresOld = em.merge(rinterioresOld);
            }
            if (rinterioresNew != null && !rinterioresNew.equals(rinterioresOld)) {
                rinterioresNew.getProcesoochoCollection().add(procesoocho);
                rinterioresNew = em.merge(rinterioresNew);
            }
            if (rllantasOld != null && !rllantasOld.equals(rllantasNew)) {
                rllantasOld.getProcesoochoCollection().remove(procesoocho);
                rllantasOld = em.merge(rllantasOld);
            }
            if (rllantasNew != null && !rllantasNew.equals(rllantasOld)) {
                rllantasNew.getProcesoochoCollection().add(procesoocho);
                rllantasNew = em.merge(rllantasNew);
            }
            if (rmotorOld != null && !rmotorOld.equals(rmotorNew)) {
                rmotorOld.getProcesoochoCollection().remove(procesoocho);
                rmotorOld = em.merge(rmotorOld);
            }
            if (rmotorNew != null && !rmotorNew.equals(rmotorOld)) {
                rmotorNew.getProcesoochoCollection().add(procesoocho);
                rmotorNew = em.merge(rmotorNew);
            }
            if (rpinturaOld != null && !rpinturaOld.equals(rpinturaNew)) {
                rpinturaOld.getProcesoochoCollection().remove(procesoocho);
                rpinturaOld = em.merge(rpinturaOld);
            }
            if (rpinturaNew != null && !rpinturaNew.equals(rpinturaOld)) {
                rpinturaNew.getProcesoochoCollection().add(procesoocho);
                rpinturaNew = em.merge(rpinturaNew);
            }
            if (rpuertasOld != null && !rpuertasOld.equals(rpuertasNew)) {
                rpuertasOld.getProcesoochoCollection().remove(procesoocho);
                rpuertasOld = em.merge(rpuertasOld);
            }
            if (rpuertasNew != null && !rpuertasNew.equals(rpuertasOld)) {
                rpuertasNew.getProcesoochoCollection().add(procesoocho);
                rpuertasNew = em.merge(rpuertasNew);
            }
            if (rrendimientoOld != null && !rrendimientoOld.equals(rrendimientoNew)) {
                rrendimientoOld.getProcesoochoCollection().remove(procesoocho);
                rrendimientoOld = em.merge(rrendimientoOld);
            }
            if (rrendimientoNew != null && !rrendimientoNew.equals(rrendimientoOld)) {
                rrendimientoNew.getProcesoochoCollection().add(procesoocho);
                rrendimientoNew = em.merge(rrendimientoNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getProcesoochoCollection().remove(procesoocho);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getProcesoochoCollection().add(procesoocho);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setPocho(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Procesoocho oldPochoOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getPocho();
                    procesoCollectionNewProceso.setPocho(procesoocho);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldPochoOfProcesoCollectionNewProceso != null && !oldPochoOfProcesoCollectionNewProceso.equals(procesoocho)) {
                        oldPochoOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldPochoOfProcesoCollectionNewProceso = em.merge(oldPochoOfProcesoCollectionNewProceso);
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
                Short id = procesoocho.getId();
                if (findProcesoocho(id) == null) {
                    throw new NonexistentEntityException("The procesoocho with id " + id + " no longer exists.");
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
            Procesoocho procesoocho;
            try {
                procesoocho = em.getReference(Procesoocho.class, id);
                procesoocho.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesoocho with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = procesoocho.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesoochoCollection().remove(procesoocho);
                idCarro = em.merge(idCarro);
            }
            Revisioncarroceria rcarroceria = procesoocho.getRcarroceria();
            if (rcarroceria != null) {
                rcarroceria.getProcesoochoCollection().remove(procesoocho);
                rcarroceria = em.merge(rcarroceria);
            }
            Revisioninteriores rinteriores = procesoocho.getRinteriores();
            if (rinteriores != null) {
                rinteriores.getProcesoochoCollection().remove(procesoocho);
                rinteriores = em.merge(rinteriores);
            }
            Revisionllantas rllantas = procesoocho.getRllantas();
            if (rllantas != null) {
                rllantas.getProcesoochoCollection().remove(procesoocho);
                rllantas = em.merge(rllantas);
            }
            Revisionmotor rmotor = procesoocho.getRmotor();
            if (rmotor != null) {
                rmotor.getProcesoochoCollection().remove(procesoocho);
                rmotor = em.merge(rmotor);
            }
            Revisionpintura rpintura = procesoocho.getRpintura();
            if (rpintura != null) {
                rpintura.getProcesoochoCollection().remove(procesoocho);
                rpintura = em.merge(rpintura);
            }
            Revisionpuertas rpuertas = procesoocho.getRpuertas();
            if (rpuertas != null) {
                rpuertas.getProcesoochoCollection().remove(procesoocho);
                rpuertas = em.merge(rpuertas);
            }
            Revisionrendimiento rrendimiento = procesoocho.getRrendimiento();
            if (rrendimiento != null) {
                rrendimiento.getProcesoochoCollection().remove(procesoocho);
                rrendimiento = em.merge(rrendimiento);
            }
            Slicitud idSolicitud = procesoocho.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getProcesoochoCollection().remove(procesoocho);
                idSolicitud = em.merge(idSolicitud);
            }
            Collection<Proceso> procesoCollection = procesoocho.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setPocho(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            em.remove(procesoocho);
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

    public List<Procesoocho> findProcesoochoEntities() {
        return findProcesoochoEntities(true, -1, -1);
    }

    public List<Procesoocho> findProcesoochoEntities(int maxResults, int firstResult) {
        return findProcesoochoEntities(false, maxResults, firstResult);
    }

    private List<Procesoocho> findProcesoochoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesoocho.class));
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

    public Procesoocho findProcesoocho(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesoocho.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoochoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesoocho> rt = cq.from(Procesoocho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
