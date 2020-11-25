/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entidades.Carro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Procesoseis;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Procesoocho;
import entidades.Procesouno;
import entidades.Procesosiete;
import entidades.Proceso;
import entidades.Procesocuatro;
import entidades.Procesodos;
import entidades.Procesocinco;
import entidades.Procesotres;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jaker
 */
public class CarroJpaController implements Serializable {

    public CarroJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carro carro) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (carro.getProcesoseisCollection() == null) {
            carro.setProcesoseisCollection(new ArrayList<Procesoseis>());
        }
        if (carro.getProcesoochoCollection() == null) {
            carro.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        if (carro.getProcesounoCollection() == null) {
            carro.setProcesounoCollection(new ArrayList<Procesouno>());
        }
        if (carro.getProcesosieteCollection() == null) {
            carro.setProcesosieteCollection(new ArrayList<Procesosiete>());
        }
        if (carro.getProcesoCollection() == null) {
            carro.setProcesoCollection(new ArrayList<Proceso>());
        }
        if (carro.getProcesocuatroCollection() == null) {
            carro.setProcesocuatroCollection(new ArrayList<Procesocuatro>());
        }
        if (carro.getProcesodosCollection() == null) {
            carro.setProcesodosCollection(new ArrayList<Procesodos>());
        }
        if (carro.getProcesocincoCollection() == null) {
            carro.setProcesocincoCollection(new ArrayList<Procesocinco>());
        }
        if (carro.getProcesotresCollection() == null) {
            carro.setProcesotresCollection(new ArrayList<Procesotres>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoseis> attachedProcesoseisCollection = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionProcesoseisToAttach : carro.getProcesoseisCollection()) {
                procesoseisCollectionProcesoseisToAttach = em.getReference(procesoseisCollectionProcesoseisToAttach.getClass(), procesoseisCollectionProcesoseisToAttach.getId());
                attachedProcesoseisCollection.add(procesoseisCollectionProcesoseisToAttach);
            }
            carro.setProcesoseisCollection(attachedProcesoseisCollection);
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : carro.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            carro.setProcesoochoCollection(attachedProcesoochoCollection);
            Collection<Procesouno> attachedProcesounoCollection = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionProcesounoToAttach : carro.getProcesounoCollection()) {
                procesounoCollectionProcesounoToAttach = em.getReference(procesounoCollectionProcesounoToAttach.getClass(), procesounoCollectionProcesounoToAttach.getId());
                attachedProcesounoCollection.add(procesounoCollectionProcesounoToAttach);
            }
            carro.setProcesounoCollection(attachedProcesounoCollection);
            Collection<Procesosiete> attachedProcesosieteCollection = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionProcesosieteToAttach : carro.getProcesosieteCollection()) {
                procesosieteCollectionProcesosieteToAttach = em.getReference(procesosieteCollectionProcesosieteToAttach.getClass(), procesosieteCollectionProcesosieteToAttach.getId());
                attachedProcesosieteCollection.add(procesosieteCollectionProcesosieteToAttach);
            }
            carro.setProcesosieteCollection(attachedProcesosieteCollection);
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : carro.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getId());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            carro.setProcesoCollection(attachedProcesoCollection);
            Collection<Procesocuatro> attachedProcesocuatroCollection = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionProcesocuatroToAttach : carro.getProcesocuatroCollection()) {
                procesocuatroCollectionProcesocuatroToAttach = em.getReference(procesocuatroCollectionProcesocuatroToAttach.getClass(), procesocuatroCollectionProcesocuatroToAttach.getId());
                attachedProcesocuatroCollection.add(procesocuatroCollectionProcesocuatroToAttach);
            }
            carro.setProcesocuatroCollection(attachedProcesocuatroCollection);
            Collection<Procesodos> attachedProcesodosCollection = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionProcesodosToAttach : carro.getProcesodosCollection()) {
                procesodosCollectionProcesodosToAttach = em.getReference(procesodosCollectionProcesodosToAttach.getClass(), procesodosCollectionProcesodosToAttach.getId());
                attachedProcesodosCollection.add(procesodosCollectionProcesodosToAttach);
            }
            carro.setProcesodosCollection(attachedProcesodosCollection);
            Collection<Procesocinco> attachedProcesocincoCollection = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionProcesocincoToAttach : carro.getProcesocincoCollection()) {
                procesocincoCollectionProcesocincoToAttach = em.getReference(procesocincoCollectionProcesocincoToAttach.getClass(), procesocincoCollectionProcesocincoToAttach.getId());
                attachedProcesocincoCollection.add(procesocincoCollectionProcesocincoToAttach);
            }
            carro.setProcesocincoCollection(attachedProcesocincoCollection);
            Collection<Procesotres> attachedProcesotresCollection = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionProcesotresToAttach : carro.getProcesotresCollection()) {
                procesotresCollectionProcesotresToAttach = em.getReference(procesotresCollectionProcesotresToAttach.getClass(), procesotresCollectionProcesotresToAttach.getId());
                attachedProcesotresCollection.add(procesotresCollectionProcesotresToAttach);
            }
            carro.setProcesotresCollection(attachedProcesotresCollection);
            em.persist(carro);
            for (Procesoseis procesoseisCollectionProcesoseis : carro.getProcesoseisCollection()) {
                Carro oldIdCarroOfProcesoseisCollectionProcesoseis = procesoseisCollectionProcesoseis.getIdCarro();
                procesoseisCollectionProcesoseis.setIdCarro(carro);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
                if (oldIdCarroOfProcesoseisCollectionProcesoseis != null) {
                    oldIdCarroOfProcesoseisCollectionProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionProcesoseis);
                    oldIdCarroOfProcesoseisCollectionProcesoseis = em.merge(oldIdCarroOfProcesoseisCollectionProcesoseis);
                }
            }
            for (Procesoocho procesoochoCollectionProcesoocho : carro.getProcesoochoCollection()) {
                Carro oldIdCarroOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getIdCarro();
                procesoochoCollectionProcesoocho.setIdCarro(carro);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldIdCarroOfProcesoochoCollectionProcesoocho != null) {
                    oldIdCarroOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldIdCarroOfProcesoochoCollectionProcesoocho = em.merge(oldIdCarroOfProcesoochoCollectionProcesoocho);
                }
            }
            for (Procesouno procesounoCollectionProcesouno : carro.getProcesounoCollection()) {
                Carro oldIdCarroOfProcesounoCollectionProcesouno = procesounoCollectionProcesouno.getIdCarro();
                procesounoCollectionProcesouno.setIdCarro(carro);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
                if (oldIdCarroOfProcesounoCollectionProcesouno != null) {
                    oldIdCarroOfProcesounoCollectionProcesouno.getProcesounoCollection().remove(procesounoCollectionProcesouno);
                    oldIdCarroOfProcesounoCollectionProcesouno = em.merge(oldIdCarroOfProcesounoCollectionProcesouno);
                }
            }
            for (Procesosiete procesosieteCollectionProcesosiete : carro.getProcesosieteCollection()) {
                Carro oldIdCarroOfProcesosieteCollectionProcesosiete = procesosieteCollectionProcesosiete.getIdCarro();
                procesosieteCollectionProcesosiete.setIdCarro(carro);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
                if (oldIdCarroOfProcesosieteCollectionProcesosiete != null) {
                    oldIdCarroOfProcesosieteCollectionProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionProcesosiete);
                    oldIdCarroOfProcesosieteCollectionProcesosiete = em.merge(oldIdCarroOfProcesosieteCollectionProcesosiete);
                }
            }
            for (Proceso procesoCollectionProceso : carro.getProcesoCollection()) {
                Carro oldIdCarroOfProcesoCollectionProceso = procesoCollectionProceso.getIdCarro();
                procesoCollectionProceso.setIdCarro(carro);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldIdCarroOfProcesoCollectionProceso != null) {
                    oldIdCarroOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldIdCarroOfProcesoCollectionProceso = em.merge(oldIdCarroOfProcesoCollectionProceso);
                }
            }
            for (Procesocuatro procesocuatroCollectionProcesocuatro : carro.getProcesocuatroCollection()) {
                Carro oldIdCarroOfProcesocuatroCollectionProcesocuatro = procesocuatroCollectionProcesocuatro.getIdCarro();
                procesocuatroCollectionProcesocuatro.setIdCarro(carro);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
                if (oldIdCarroOfProcesocuatroCollectionProcesocuatro != null) {
                    oldIdCarroOfProcesocuatroCollectionProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionProcesocuatro);
                    oldIdCarroOfProcesocuatroCollectionProcesocuatro = em.merge(oldIdCarroOfProcesocuatroCollectionProcesocuatro);
                }
            }
            for (Procesodos procesodosCollectionProcesodos : carro.getProcesodosCollection()) {
                Carro oldIdCarroOfProcesodosCollectionProcesodos = procesodosCollectionProcesodos.getIdCarro();
                procesodosCollectionProcesodos.setIdCarro(carro);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
                if (oldIdCarroOfProcesodosCollectionProcesodos != null) {
                    oldIdCarroOfProcesodosCollectionProcesodos.getProcesodosCollection().remove(procesodosCollectionProcesodos);
                    oldIdCarroOfProcesodosCollectionProcesodos = em.merge(oldIdCarroOfProcesodosCollectionProcesodos);
                }
            }
            for (Procesocinco procesocincoCollectionProcesocinco : carro.getProcesocincoCollection()) {
                Carro oldIdCarroOfProcesocincoCollectionProcesocinco = procesocincoCollectionProcesocinco.getIdCarro();
                procesocincoCollectionProcesocinco.setIdCarro(carro);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
                if (oldIdCarroOfProcesocincoCollectionProcesocinco != null) {
                    oldIdCarroOfProcesocincoCollectionProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionProcesocinco);
                    oldIdCarroOfProcesocincoCollectionProcesocinco = em.merge(oldIdCarroOfProcesocincoCollectionProcesocinco);
                }
            }
            for (Procesotres procesotresCollectionProcesotres : carro.getProcesotresCollection()) {
                Carro oldIdCarroOfProcesotresCollectionProcesotres = procesotresCollectionProcesotres.getIdCarro();
                procesotresCollectionProcesotres.setIdCarro(carro);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
                if (oldIdCarroOfProcesotresCollectionProcesotres != null) {
                    oldIdCarroOfProcesotresCollectionProcesotres.getProcesotresCollection().remove(procesotresCollectionProcesotres);
                    oldIdCarroOfProcesotresCollectionProcesotres = em.merge(oldIdCarroOfProcesotresCollectionProcesotres);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCarro(carro.getId()) != null) {
                throw new PreexistingEntityException("Carro " + carro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carro carro) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Carro persistentCarro = em.find(Carro.class, carro.getId());
            Collection<Procesoseis> procesoseisCollectionOld = persistentCarro.getProcesoseisCollection();
            Collection<Procesoseis> procesoseisCollectionNew = carro.getProcesoseisCollection();
            Collection<Procesoocho> procesoochoCollectionOld = persistentCarro.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = carro.getProcesoochoCollection();
            Collection<Procesouno> procesounoCollectionOld = persistentCarro.getProcesounoCollection();
            Collection<Procesouno> procesounoCollectionNew = carro.getProcesounoCollection();
            Collection<Procesosiete> procesosieteCollectionOld = persistentCarro.getProcesosieteCollection();
            Collection<Procesosiete> procesosieteCollectionNew = carro.getProcesosieteCollection();
            Collection<Proceso> procesoCollectionOld = persistentCarro.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = carro.getProcesoCollection();
            Collection<Procesocuatro> procesocuatroCollectionOld = persistentCarro.getProcesocuatroCollection();
            Collection<Procesocuatro> procesocuatroCollectionNew = carro.getProcesocuatroCollection();
            Collection<Procesodos> procesodosCollectionOld = persistentCarro.getProcesodosCollection();
            Collection<Procesodos> procesodosCollectionNew = carro.getProcesodosCollection();
            Collection<Procesocinco> procesocincoCollectionOld = persistentCarro.getProcesocincoCollection();
            Collection<Procesocinco> procesocincoCollectionNew = carro.getProcesocincoCollection();
            Collection<Procesotres> procesotresCollectionOld = persistentCarro.getProcesotresCollection();
            Collection<Procesotres> procesotresCollectionNew = carro.getProcesotresCollection();
            Collection<Procesoseis> attachedProcesoseisCollectionNew = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionNewProcesoseisToAttach : procesoseisCollectionNew) {
                procesoseisCollectionNewProcesoseisToAttach = em.getReference(procesoseisCollectionNewProcesoseisToAttach.getClass(), procesoseisCollectionNewProcesoseisToAttach.getId());
                attachedProcesoseisCollectionNew.add(procesoseisCollectionNewProcesoseisToAttach);
            }
            procesoseisCollectionNew = attachedProcesoseisCollectionNew;
            carro.setProcesoseisCollection(procesoseisCollectionNew);
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            carro.setProcesoochoCollection(procesoochoCollectionNew);
            Collection<Procesouno> attachedProcesounoCollectionNew = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionNewProcesounoToAttach : procesounoCollectionNew) {
                procesounoCollectionNewProcesounoToAttach = em.getReference(procesounoCollectionNewProcesounoToAttach.getClass(), procesounoCollectionNewProcesounoToAttach.getId());
                attachedProcesounoCollectionNew.add(procesounoCollectionNewProcesounoToAttach);
            }
            procesounoCollectionNew = attachedProcesounoCollectionNew;
            carro.setProcesounoCollection(procesounoCollectionNew);
            Collection<Procesosiete> attachedProcesosieteCollectionNew = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionNewProcesosieteToAttach : procesosieteCollectionNew) {
                procesosieteCollectionNewProcesosieteToAttach = em.getReference(procesosieteCollectionNewProcesosieteToAttach.getClass(), procesosieteCollectionNewProcesosieteToAttach.getId());
                attachedProcesosieteCollectionNew.add(procesosieteCollectionNewProcesosieteToAttach);
            }
            procesosieteCollectionNew = attachedProcesosieteCollectionNew;
            carro.setProcesosieteCollection(procesosieteCollectionNew);
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getId());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            carro.setProcesoCollection(procesoCollectionNew);
            Collection<Procesocuatro> attachedProcesocuatroCollectionNew = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionNewProcesocuatroToAttach : procesocuatroCollectionNew) {
                procesocuatroCollectionNewProcesocuatroToAttach = em.getReference(procesocuatroCollectionNewProcesocuatroToAttach.getClass(), procesocuatroCollectionNewProcesocuatroToAttach.getId());
                attachedProcesocuatroCollectionNew.add(procesocuatroCollectionNewProcesocuatroToAttach);
            }
            procesocuatroCollectionNew = attachedProcesocuatroCollectionNew;
            carro.setProcesocuatroCollection(procesocuatroCollectionNew);
            Collection<Procesodos> attachedProcesodosCollectionNew = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionNewProcesodosToAttach : procesodosCollectionNew) {
                procesodosCollectionNewProcesodosToAttach = em.getReference(procesodosCollectionNewProcesodosToAttach.getClass(), procesodosCollectionNewProcesodosToAttach.getId());
                attachedProcesodosCollectionNew.add(procesodosCollectionNewProcesodosToAttach);
            }
            procesodosCollectionNew = attachedProcesodosCollectionNew;
            carro.setProcesodosCollection(procesodosCollectionNew);
            Collection<Procesocinco> attachedProcesocincoCollectionNew = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionNewProcesocincoToAttach : procesocincoCollectionNew) {
                procesocincoCollectionNewProcesocincoToAttach = em.getReference(procesocincoCollectionNewProcesocincoToAttach.getClass(), procesocincoCollectionNewProcesocincoToAttach.getId());
                attachedProcesocincoCollectionNew.add(procesocincoCollectionNewProcesocincoToAttach);
            }
            procesocincoCollectionNew = attachedProcesocincoCollectionNew;
            carro.setProcesocincoCollection(procesocincoCollectionNew);
            Collection<Procesotres> attachedProcesotresCollectionNew = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionNewProcesotresToAttach : procesotresCollectionNew) {
                procesotresCollectionNewProcesotresToAttach = em.getReference(procesotresCollectionNewProcesotresToAttach.getClass(), procesotresCollectionNewProcesotresToAttach.getId());
                attachedProcesotresCollectionNew.add(procesotresCollectionNewProcesotresToAttach);
            }
            procesotresCollectionNew = attachedProcesotresCollectionNew;
            carro.setProcesotresCollection(procesotresCollectionNew);
            carro = em.merge(carro);
            for (Procesoseis procesoseisCollectionOldProcesoseis : procesoseisCollectionOld) {
                if (!procesoseisCollectionNew.contains(procesoseisCollectionOldProcesoseis)) {
                    procesoseisCollectionOldProcesoseis.setIdCarro(null);
                    procesoseisCollectionOldProcesoseis = em.merge(procesoseisCollectionOldProcesoseis);
                }
            }
            for (Procesoseis procesoseisCollectionNewProcesoseis : procesoseisCollectionNew) {
                if (!procesoseisCollectionOld.contains(procesoseisCollectionNewProcesoseis)) {
                    Carro oldIdCarroOfProcesoseisCollectionNewProcesoseis = procesoseisCollectionNewProcesoseis.getIdCarro();
                    procesoseisCollectionNewProcesoseis.setIdCarro(carro);
                    procesoseisCollectionNewProcesoseis = em.merge(procesoseisCollectionNewProcesoseis);
                    if (oldIdCarroOfProcesoseisCollectionNewProcesoseis != null && !oldIdCarroOfProcesoseisCollectionNewProcesoseis.equals(carro)) {
                        oldIdCarroOfProcesoseisCollectionNewProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionNewProcesoseis);
                        oldIdCarroOfProcesoseisCollectionNewProcesoseis = em.merge(oldIdCarroOfProcesoseisCollectionNewProcesoseis);
                    }
                }
            }
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setIdCarro(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Carro oldIdCarroOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getIdCarro();
                    procesoochoCollectionNewProcesoocho.setIdCarro(carro);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldIdCarroOfProcesoochoCollectionNewProcesoocho != null && !oldIdCarroOfProcesoochoCollectionNewProcesoocho.equals(carro)) {
                        oldIdCarroOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldIdCarroOfProcesoochoCollectionNewProcesoocho = em.merge(oldIdCarroOfProcesoochoCollectionNewProcesoocho);
                    }
                }
            }
            for (Procesouno procesounoCollectionOldProcesouno : procesounoCollectionOld) {
                if (!procesounoCollectionNew.contains(procesounoCollectionOldProcesouno)) {
                    procesounoCollectionOldProcesouno.setIdCarro(null);
                    procesounoCollectionOldProcesouno = em.merge(procesounoCollectionOldProcesouno);
                }
            }
            for (Procesouno procesounoCollectionNewProcesouno : procesounoCollectionNew) {
                if (!procesounoCollectionOld.contains(procesounoCollectionNewProcesouno)) {
                    Carro oldIdCarroOfProcesounoCollectionNewProcesouno = procesounoCollectionNewProcesouno.getIdCarro();
                    procesounoCollectionNewProcesouno.setIdCarro(carro);
                    procesounoCollectionNewProcesouno = em.merge(procesounoCollectionNewProcesouno);
                    if (oldIdCarroOfProcesounoCollectionNewProcesouno != null && !oldIdCarroOfProcesounoCollectionNewProcesouno.equals(carro)) {
                        oldIdCarroOfProcesounoCollectionNewProcesouno.getProcesounoCollection().remove(procesounoCollectionNewProcesouno);
                        oldIdCarroOfProcesounoCollectionNewProcesouno = em.merge(oldIdCarroOfProcesounoCollectionNewProcesouno);
                    }
                }
            }
            for (Procesosiete procesosieteCollectionOldProcesosiete : procesosieteCollectionOld) {
                if (!procesosieteCollectionNew.contains(procesosieteCollectionOldProcesosiete)) {
                    procesosieteCollectionOldProcesosiete.setIdCarro(null);
                    procesosieteCollectionOldProcesosiete = em.merge(procesosieteCollectionOldProcesosiete);
                }
            }
            for (Procesosiete procesosieteCollectionNewProcesosiete : procesosieteCollectionNew) {
                if (!procesosieteCollectionOld.contains(procesosieteCollectionNewProcesosiete)) {
                    Carro oldIdCarroOfProcesosieteCollectionNewProcesosiete = procesosieteCollectionNewProcesosiete.getIdCarro();
                    procesosieteCollectionNewProcesosiete.setIdCarro(carro);
                    procesosieteCollectionNewProcesosiete = em.merge(procesosieteCollectionNewProcesosiete);
                    if (oldIdCarroOfProcesosieteCollectionNewProcesosiete != null && !oldIdCarroOfProcesosieteCollectionNewProcesosiete.equals(carro)) {
                        oldIdCarroOfProcesosieteCollectionNewProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionNewProcesosiete);
                        oldIdCarroOfProcesosieteCollectionNewProcesosiete = em.merge(oldIdCarroOfProcesosieteCollectionNewProcesosiete);
                    }
                }
            }
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    procesoCollectionOldProceso.setIdCarro(null);
                    procesoCollectionOldProceso = em.merge(procesoCollectionOldProceso);
                }
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Carro oldIdCarroOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getIdCarro();
                    procesoCollectionNewProceso.setIdCarro(carro);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldIdCarroOfProcesoCollectionNewProceso != null && !oldIdCarroOfProcesoCollectionNewProceso.equals(carro)) {
                        oldIdCarroOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldIdCarroOfProcesoCollectionNewProceso = em.merge(oldIdCarroOfProcesoCollectionNewProceso);
                    }
                }
            }
            for (Procesocuatro procesocuatroCollectionOldProcesocuatro : procesocuatroCollectionOld) {
                if (!procesocuatroCollectionNew.contains(procesocuatroCollectionOldProcesocuatro)) {
                    procesocuatroCollectionOldProcesocuatro.setIdCarro(null);
                    procesocuatroCollectionOldProcesocuatro = em.merge(procesocuatroCollectionOldProcesocuatro);
                }
            }
            for (Procesocuatro procesocuatroCollectionNewProcesocuatro : procesocuatroCollectionNew) {
                if (!procesocuatroCollectionOld.contains(procesocuatroCollectionNewProcesocuatro)) {
                    Carro oldIdCarroOfProcesocuatroCollectionNewProcesocuatro = procesocuatroCollectionNewProcesocuatro.getIdCarro();
                    procesocuatroCollectionNewProcesocuatro.setIdCarro(carro);
                    procesocuatroCollectionNewProcesocuatro = em.merge(procesocuatroCollectionNewProcesocuatro);
                    if (oldIdCarroOfProcesocuatroCollectionNewProcesocuatro != null && !oldIdCarroOfProcesocuatroCollectionNewProcesocuatro.equals(carro)) {
                        oldIdCarroOfProcesocuatroCollectionNewProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionNewProcesocuatro);
                        oldIdCarroOfProcesocuatroCollectionNewProcesocuatro = em.merge(oldIdCarroOfProcesocuatroCollectionNewProcesocuatro);
                    }
                }
            }
            for (Procesodos procesodosCollectionOldProcesodos : procesodosCollectionOld) {
                if (!procesodosCollectionNew.contains(procesodosCollectionOldProcesodos)) {
                    procesodosCollectionOldProcesodos.setIdCarro(null);
                    procesodosCollectionOldProcesodos = em.merge(procesodosCollectionOldProcesodos);
                }
            }
            for (Procesodos procesodosCollectionNewProcesodos : procesodosCollectionNew) {
                if (!procesodosCollectionOld.contains(procesodosCollectionNewProcesodos)) {
                    Carro oldIdCarroOfProcesodosCollectionNewProcesodos = procesodosCollectionNewProcesodos.getIdCarro();
                    procesodosCollectionNewProcesodos.setIdCarro(carro);
                    procesodosCollectionNewProcesodos = em.merge(procesodosCollectionNewProcesodos);
                    if (oldIdCarroOfProcesodosCollectionNewProcesodos != null && !oldIdCarroOfProcesodosCollectionNewProcesodos.equals(carro)) {
                        oldIdCarroOfProcesodosCollectionNewProcesodos.getProcesodosCollection().remove(procesodosCollectionNewProcesodos);
                        oldIdCarroOfProcesodosCollectionNewProcesodos = em.merge(oldIdCarroOfProcesodosCollectionNewProcesodos);
                    }
                }
            }
            for (Procesocinco procesocincoCollectionOldProcesocinco : procesocincoCollectionOld) {
                if (!procesocincoCollectionNew.contains(procesocincoCollectionOldProcesocinco)) {
                    procesocincoCollectionOldProcesocinco.setIdCarro(null);
                    procesocincoCollectionOldProcesocinco = em.merge(procesocincoCollectionOldProcesocinco);
                }
            }
            for (Procesocinco procesocincoCollectionNewProcesocinco : procesocincoCollectionNew) {
                if (!procesocincoCollectionOld.contains(procesocincoCollectionNewProcesocinco)) {
                    Carro oldIdCarroOfProcesocincoCollectionNewProcesocinco = procesocincoCollectionNewProcesocinco.getIdCarro();
                    procesocincoCollectionNewProcesocinco.setIdCarro(carro);
                    procesocincoCollectionNewProcesocinco = em.merge(procesocincoCollectionNewProcesocinco);
                    if (oldIdCarroOfProcesocincoCollectionNewProcesocinco != null && !oldIdCarroOfProcesocincoCollectionNewProcesocinco.equals(carro)) {
                        oldIdCarroOfProcesocincoCollectionNewProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionNewProcesocinco);
                        oldIdCarroOfProcesocincoCollectionNewProcesocinco = em.merge(oldIdCarroOfProcesocincoCollectionNewProcesocinco);
                    }
                }
            }
            for (Procesotres procesotresCollectionOldProcesotres : procesotresCollectionOld) {
                if (!procesotresCollectionNew.contains(procesotresCollectionOldProcesotres)) {
                    procesotresCollectionOldProcesotres.setIdCarro(null);
                    procesotresCollectionOldProcesotres = em.merge(procesotresCollectionOldProcesotres);
                }
            }
            for (Procesotres procesotresCollectionNewProcesotres : procesotresCollectionNew) {
                if (!procesotresCollectionOld.contains(procesotresCollectionNewProcesotres)) {
                    Carro oldIdCarroOfProcesotresCollectionNewProcesotres = procesotresCollectionNewProcesotres.getIdCarro();
                    procesotresCollectionNewProcesotres.setIdCarro(carro);
                    procesotresCollectionNewProcesotres = em.merge(procesotresCollectionNewProcesotres);
                    if (oldIdCarroOfProcesotresCollectionNewProcesotres != null && !oldIdCarroOfProcesotresCollectionNewProcesotres.equals(carro)) {
                        oldIdCarroOfProcesotresCollectionNewProcesotres.getProcesotresCollection().remove(procesotresCollectionNewProcesotres);
                        oldIdCarroOfProcesotresCollectionNewProcesotres = em.merge(oldIdCarroOfProcesotresCollectionNewProcesotres);
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
                Short id = carro.getId();
                if (findCarro(id) == null) {
                    throw new NonexistentEntityException("The carro with id " + id + " no longer exists.");
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
            Carro carro;
            try {
                carro = em.getReference(Carro.class, id);
                carro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carro with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoseis> procesoseisCollection = carro.getProcesoseisCollection();
            for (Procesoseis procesoseisCollectionProcesoseis : procesoseisCollection) {
                procesoseisCollectionProcesoseis.setIdCarro(null);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
            }
            Collection<Procesoocho> procesoochoCollection = carro.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setIdCarro(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            Collection<Procesouno> procesounoCollection = carro.getProcesounoCollection();
            for (Procesouno procesounoCollectionProcesouno : procesounoCollection) {
                procesounoCollectionProcesouno.setIdCarro(null);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
            }
            Collection<Procesosiete> procesosieteCollection = carro.getProcesosieteCollection();
            for (Procesosiete procesosieteCollectionProcesosiete : procesosieteCollection) {
                procesosieteCollectionProcesosiete.setIdCarro(null);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
            }
            Collection<Proceso> procesoCollection = carro.getProcesoCollection();
            for (Proceso procesoCollectionProceso : procesoCollection) {
                procesoCollectionProceso.setIdCarro(null);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
            }
            Collection<Procesocuatro> procesocuatroCollection = carro.getProcesocuatroCollection();
            for (Procesocuatro procesocuatroCollectionProcesocuatro : procesocuatroCollection) {
                procesocuatroCollectionProcesocuatro.setIdCarro(null);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
            }
            Collection<Procesodos> procesodosCollection = carro.getProcesodosCollection();
            for (Procesodos procesodosCollectionProcesodos : procesodosCollection) {
                procesodosCollectionProcesodos.setIdCarro(null);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
            }
            Collection<Procesocinco> procesocincoCollection = carro.getProcesocincoCollection();
            for (Procesocinco procesocincoCollectionProcesocinco : procesocincoCollection) {
                procesocincoCollectionProcesocinco.setIdCarro(null);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
            }
            Collection<Procesotres> procesotresCollection = carro.getProcesotresCollection();
            for (Procesotres procesotresCollectionProcesotres : procesotresCollection) {
                procesotresCollectionProcesotres.setIdCarro(null);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
            }
            em.remove(carro);
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

    public List<Carro> findCarroEntities() {
        return findCarroEntities(true, -1, -1);
    }

    public List<Carro> findCarroEntities(int maxResults, int firstResult) {
        return findCarroEntities(false, maxResults, firstResult);
    }

    private List<Carro> findCarroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carro.class));
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

    public Carro findCarro(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carro> rt = cq.from(Carro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
