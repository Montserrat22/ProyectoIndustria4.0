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
import entidades.Proceso;
import entidades.Procesocinco;
import entidades.Procesocuatro;
import entidades.Procesodos;
import entidades.Procesoocho;
import entidades.Procesoseis;
import entidades.Procesosiete;
import entidades.Procesotres;
import entidades.Procesouno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jaker
 */
public class ProcesoJpaController implements Serializable {

    public ProcesoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proceso proceso) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro idCarro = proceso.getIdCarro();
            if (idCarro != null) {
                idCarro = em.getReference(idCarro.getClass(), idCarro.getId());
                proceso.setIdCarro(idCarro);
            }
            Procesocinco pcinco = proceso.getPcinco();
            if (pcinco != null) {
                pcinco = em.getReference(pcinco.getClass(), pcinco.getId());
                proceso.setPcinco(pcinco);
            }
            Procesocuatro pcuatro = proceso.getPcuatro();
            if (pcuatro != null) {
                pcuatro = em.getReference(pcuatro.getClass(), pcuatro.getId());
                proceso.setPcuatro(pcuatro);
            }
            Procesodos pdos = proceso.getPdos();
            if (pdos != null) {
                pdos = em.getReference(pdos.getClass(), pdos.getId());
                proceso.setPdos(pdos);
            }
            Procesoocho pocho = proceso.getPocho();
            if (pocho != null) {
                pocho = em.getReference(pocho.getClass(), pocho.getId());
                proceso.setPocho(pocho);
            }
            Procesoseis pseis = proceso.getPseis();
            if (pseis != null) {
                pseis = em.getReference(pseis.getClass(), pseis.getId());
                proceso.setPseis(pseis);
            }
            Procesosiete psiete = proceso.getPsiete();
            if (psiete != null) {
                psiete = em.getReference(psiete.getClass(), psiete.getId());
                proceso.setPsiete(psiete);
            }
            Procesotres ptres = proceso.getPtres();
            if (ptres != null) {
                ptres = em.getReference(ptres.getClass(), ptres.getId());
                proceso.setPtres(ptres);
            }
            Procesouno puno = proceso.getPuno();
            if (puno != null) {
                puno = em.getReference(puno.getClass(), puno.getId());
                proceso.setPuno(puno);
            }
            em.persist(proceso);
            if (idCarro != null) {
                idCarro.getProcesoCollection().add(proceso);
                idCarro = em.merge(idCarro);
            }
            if (pcinco != null) {
                pcinco.getProcesoCollection().add(proceso);
                pcinco = em.merge(pcinco);
            }
            if (pcuatro != null) {
                pcuatro.getProcesoCollection().add(proceso);
                pcuatro = em.merge(pcuatro);
            }
            if (pdos != null) {
                pdos.getProcesoCollection().add(proceso);
                pdos = em.merge(pdos);
            }
            if (pocho != null) {
                pocho.getProcesoCollection().add(proceso);
                pocho = em.merge(pocho);
            }
            if (pseis != null) {
                pseis.getProcesoCollection().add(proceso);
                pseis = em.merge(pseis);
            }
            if (psiete != null) {
                psiete.getProcesoCollection().add(proceso);
                psiete = em.merge(psiete);
            }
            if (ptres != null) {
                ptres.getProcesoCollection().add(proceso);
                ptres = em.merge(ptres);
            }
            if (puno != null) {
                puno.getProcesoCollection().add(proceso);
                puno = em.merge(puno);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProceso(proceso.getId()) != null) {
                throw new PreexistingEntityException("Proceso " + proceso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getId());
            Carro idCarroOld = persistentProceso.getIdCarro();
            Carro idCarroNew = proceso.getIdCarro();
            Procesocinco pcincoOld = persistentProceso.getPcinco();
            Procesocinco pcincoNew = proceso.getPcinco();
            Procesocuatro pcuatroOld = persistentProceso.getPcuatro();
            Procesocuatro pcuatroNew = proceso.getPcuatro();
            Procesodos pdosOld = persistentProceso.getPdos();
            Procesodos pdosNew = proceso.getPdos();
            Procesoocho pochoOld = persistentProceso.getPocho();
            Procesoocho pochoNew = proceso.getPocho();
            Procesoseis pseisOld = persistentProceso.getPseis();
            Procesoseis pseisNew = proceso.getPseis();
            Procesosiete psieteOld = persistentProceso.getPsiete();
            Procesosiete psieteNew = proceso.getPsiete();
            Procesotres ptresOld = persistentProceso.getPtres();
            Procesotres ptresNew = proceso.getPtres();
            Procesouno punoOld = persistentProceso.getPuno();
            Procesouno punoNew = proceso.getPuno();
            if (idCarroNew != null) {
                idCarroNew = em.getReference(idCarroNew.getClass(), idCarroNew.getId());
                proceso.setIdCarro(idCarroNew);
            }
            if (pcincoNew != null) {
                pcincoNew = em.getReference(pcincoNew.getClass(), pcincoNew.getId());
                proceso.setPcinco(pcincoNew);
            }
            if (pcuatroNew != null) {
                pcuatroNew = em.getReference(pcuatroNew.getClass(), pcuatroNew.getId());
                proceso.setPcuatro(pcuatroNew);
            }
            if (pdosNew != null) {
                pdosNew = em.getReference(pdosNew.getClass(), pdosNew.getId());
                proceso.setPdos(pdosNew);
            }
            if (pochoNew != null) {
                pochoNew = em.getReference(pochoNew.getClass(), pochoNew.getId());
                proceso.setPocho(pochoNew);
            }
            if (pseisNew != null) {
                pseisNew = em.getReference(pseisNew.getClass(), pseisNew.getId());
                proceso.setPseis(pseisNew);
            }
            if (psieteNew != null) {
                psieteNew = em.getReference(psieteNew.getClass(), psieteNew.getId());
                proceso.setPsiete(psieteNew);
            }
            if (ptresNew != null) {
                ptresNew = em.getReference(ptresNew.getClass(), ptresNew.getId());
                proceso.setPtres(ptresNew);
            }
            if (punoNew != null) {
                punoNew = em.getReference(punoNew.getClass(), punoNew.getId());
                proceso.setPuno(punoNew);
            }
            proceso = em.merge(proceso);
            if (idCarroOld != null && !idCarroOld.equals(idCarroNew)) {
                idCarroOld.getProcesoCollection().remove(proceso);
                idCarroOld = em.merge(idCarroOld);
            }
            if (idCarroNew != null && !idCarroNew.equals(idCarroOld)) {
                idCarroNew.getProcesoCollection().add(proceso);
                idCarroNew = em.merge(idCarroNew);
            }
            if (pcincoOld != null && !pcincoOld.equals(pcincoNew)) {
                pcincoOld.getProcesoCollection().remove(proceso);
                pcincoOld = em.merge(pcincoOld);
            }
            if (pcincoNew != null && !pcincoNew.equals(pcincoOld)) {
                pcincoNew.getProcesoCollection().add(proceso);
                pcincoNew = em.merge(pcincoNew);
            }
            if (pcuatroOld != null && !pcuatroOld.equals(pcuatroNew)) {
                pcuatroOld.getProcesoCollection().remove(proceso);
                pcuatroOld = em.merge(pcuatroOld);
            }
            if (pcuatroNew != null && !pcuatroNew.equals(pcuatroOld)) {
                pcuatroNew.getProcesoCollection().add(proceso);
                pcuatroNew = em.merge(pcuatroNew);
            }
            if (pdosOld != null && !pdosOld.equals(pdosNew)) {
                pdosOld.getProcesoCollection().remove(proceso);
                pdosOld = em.merge(pdosOld);
            }
            if (pdosNew != null && !pdosNew.equals(pdosOld)) {
                pdosNew.getProcesoCollection().add(proceso);
                pdosNew = em.merge(pdosNew);
            }
            if (pochoOld != null && !pochoOld.equals(pochoNew)) {
                pochoOld.getProcesoCollection().remove(proceso);
                pochoOld = em.merge(pochoOld);
            }
            if (pochoNew != null && !pochoNew.equals(pochoOld)) {
                pochoNew.getProcesoCollection().add(proceso);
                pochoNew = em.merge(pochoNew);
            }
            if (pseisOld != null && !pseisOld.equals(pseisNew)) {
                pseisOld.getProcesoCollection().remove(proceso);
                pseisOld = em.merge(pseisOld);
            }
            if (pseisNew != null && !pseisNew.equals(pseisOld)) {
                pseisNew.getProcesoCollection().add(proceso);
                pseisNew = em.merge(pseisNew);
            }
            if (psieteOld != null && !psieteOld.equals(psieteNew)) {
                psieteOld.getProcesoCollection().remove(proceso);
                psieteOld = em.merge(psieteOld);
            }
            if (psieteNew != null && !psieteNew.equals(psieteOld)) {
                psieteNew.getProcesoCollection().add(proceso);
                psieteNew = em.merge(psieteNew);
            }
            if (ptresOld != null && !ptresOld.equals(ptresNew)) {
                ptresOld.getProcesoCollection().remove(proceso);
                ptresOld = em.merge(ptresOld);
            }
            if (ptresNew != null && !ptresNew.equals(ptresOld)) {
                ptresNew.getProcesoCollection().add(proceso);
                ptresNew = em.merge(ptresNew);
            }
            if (punoOld != null && !punoOld.equals(punoNew)) {
                punoOld.getProcesoCollection().remove(proceso);
                punoOld = em.merge(punoOld);
            }
            if (punoNew != null && !punoNew.equals(punoOld)) {
                punoNew.getProcesoCollection().add(proceso);
                punoNew = em.merge(punoNew);
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
                Short id = proceso.getId();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            Carro idCarro = proceso.getIdCarro();
            if (idCarro != null) {
                idCarro.getProcesoCollection().remove(proceso);
                idCarro = em.merge(idCarro);
            }
            Procesocinco pcinco = proceso.getPcinco();
            if (pcinco != null) {
                pcinco.getProcesoCollection().remove(proceso);
                pcinco = em.merge(pcinco);
            }
            Procesocuatro pcuatro = proceso.getPcuatro();
            if (pcuatro != null) {
                pcuatro.getProcesoCollection().remove(proceso);
                pcuatro = em.merge(pcuatro);
            }
            Procesodos pdos = proceso.getPdos();
            if (pdos != null) {
                pdos.getProcesoCollection().remove(proceso);
                pdos = em.merge(pdos);
            }
            Procesoocho pocho = proceso.getPocho();
            if (pocho != null) {
                pocho.getProcesoCollection().remove(proceso);
                pocho = em.merge(pocho);
            }
            Procesoseis pseis = proceso.getPseis();
            if (pseis != null) {
                pseis.getProcesoCollection().remove(proceso);
                pseis = em.merge(pseis);
            }
            Procesosiete psiete = proceso.getPsiete();
            if (psiete != null) {
                psiete.getProcesoCollection().remove(proceso);
                psiete = em.merge(psiete);
            }
            Procesotres ptres = proceso.getPtres();
            if (ptres != null) {
                ptres.getProcesoCollection().remove(proceso);
                ptres = em.merge(ptres);
            }
            Procesouno puno = proceso.getPuno();
            if (puno != null) {
                puno.getProcesoCollection().remove(proceso);
                puno = em.merge(puno);
            }
            em.remove(proceso);
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

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
