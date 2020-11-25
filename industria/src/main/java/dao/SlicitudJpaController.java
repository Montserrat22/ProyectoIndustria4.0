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
import entidades.Procesoseis;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Procesoocho;
import entidades.Procesouno;
import entidades.Procesosiete;
import entidades.Procesocuatro;
import entidades.Procesodos;
import entidades.Procesocinco;
import entidades.Procesotres;
import entidades.Slicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jaker
 */
public class SlicitudJpaController implements Serializable {

    public SlicitudJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Slicitud slicitud) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (slicitud.getProcesoseisCollection() == null) {
            slicitud.setProcesoseisCollection(new ArrayList<Procesoseis>());
        }
        if (slicitud.getProcesoochoCollection() == null) {
            slicitud.setProcesoochoCollection(new ArrayList<Procesoocho>());
        }
        if (slicitud.getProcesounoCollection() == null) {
            slicitud.setProcesounoCollection(new ArrayList<Procesouno>());
        }
        if (slicitud.getProcesosieteCollection() == null) {
            slicitud.setProcesosieteCollection(new ArrayList<Procesosiete>());
        }
        if (slicitud.getProcesocuatroCollection() == null) {
            slicitud.setProcesocuatroCollection(new ArrayList<Procesocuatro>());
        }
        if (slicitud.getProcesodosCollection() == null) {
            slicitud.setProcesodosCollection(new ArrayList<Procesodos>());
        }
        if (slicitud.getProcesocincoCollection() == null) {
            slicitud.setProcesocincoCollection(new ArrayList<Procesocinco>());
        }
        if (slicitud.getProcesotresCollection() == null) {
            slicitud.setProcesotresCollection(new ArrayList<Procesotres>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Procesoseis> attachedProcesoseisCollection = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionProcesoseisToAttach : slicitud.getProcesoseisCollection()) {
                procesoseisCollectionProcesoseisToAttach = em.getReference(procesoseisCollectionProcesoseisToAttach.getClass(), procesoseisCollectionProcesoseisToAttach.getId());
                attachedProcesoseisCollection.add(procesoseisCollectionProcesoseisToAttach);
            }
            slicitud.setProcesoseisCollection(attachedProcesoseisCollection);
            Collection<Procesoocho> attachedProcesoochoCollection = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionProcesoochoToAttach : slicitud.getProcesoochoCollection()) {
                procesoochoCollectionProcesoochoToAttach = em.getReference(procesoochoCollectionProcesoochoToAttach.getClass(), procesoochoCollectionProcesoochoToAttach.getId());
                attachedProcesoochoCollection.add(procesoochoCollectionProcesoochoToAttach);
            }
            slicitud.setProcesoochoCollection(attachedProcesoochoCollection);
            Collection<Procesouno> attachedProcesounoCollection = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionProcesounoToAttach : slicitud.getProcesounoCollection()) {
                procesounoCollectionProcesounoToAttach = em.getReference(procesounoCollectionProcesounoToAttach.getClass(), procesounoCollectionProcesounoToAttach.getId());
                attachedProcesounoCollection.add(procesounoCollectionProcesounoToAttach);
            }
            slicitud.setProcesounoCollection(attachedProcesounoCollection);
            Collection<Procesosiete> attachedProcesosieteCollection = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionProcesosieteToAttach : slicitud.getProcesosieteCollection()) {
                procesosieteCollectionProcesosieteToAttach = em.getReference(procesosieteCollectionProcesosieteToAttach.getClass(), procesosieteCollectionProcesosieteToAttach.getId());
                attachedProcesosieteCollection.add(procesosieteCollectionProcesosieteToAttach);
            }
            slicitud.setProcesosieteCollection(attachedProcesosieteCollection);
            Collection<Procesocuatro> attachedProcesocuatroCollection = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionProcesocuatroToAttach : slicitud.getProcesocuatroCollection()) {
                procesocuatroCollectionProcesocuatroToAttach = em.getReference(procesocuatroCollectionProcesocuatroToAttach.getClass(), procesocuatroCollectionProcesocuatroToAttach.getId());
                attachedProcesocuatroCollection.add(procesocuatroCollectionProcesocuatroToAttach);
            }
            slicitud.setProcesocuatroCollection(attachedProcesocuatroCollection);
            Collection<Procesodos> attachedProcesodosCollection = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionProcesodosToAttach : slicitud.getProcesodosCollection()) {
                procesodosCollectionProcesodosToAttach = em.getReference(procesodosCollectionProcesodosToAttach.getClass(), procesodosCollectionProcesodosToAttach.getId());
                attachedProcesodosCollection.add(procesodosCollectionProcesodosToAttach);
            }
            slicitud.setProcesodosCollection(attachedProcesodosCollection);
            Collection<Procesocinco> attachedProcesocincoCollection = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionProcesocincoToAttach : slicitud.getProcesocincoCollection()) {
                procesocincoCollectionProcesocincoToAttach = em.getReference(procesocincoCollectionProcesocincoToAttach.getClass(), procesocincoCollectionProcesocincoToAttach.getId());
                attachedProcesocincoCollection.add(procesocincoCollectionProcesocincoToAttach);
            }
            slicitud.setProcesocincoCollection(attachedProcesocincoCollection);
            Collection<Procesotres> attachedProcesotresCollection = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionProcesotresToAttach : slicitud.getProcesotresCollection()) {
                procesotresCollectionProcesotresToAttach = em.getReference(procesotresCollectionProcesotresToAttach.getClass(), procesotresCollectionProcesotresToAttach.getId());
                attachedProcesotresCollection.add(procesotresCollectionProcesotresToAttach);
            }
            slicitud.setProcesotresCollection(attachedProcesotresCollection);
            em.persist(slicitud);
            for (Procesoseis procesoseisCollectionProcesoseis : slicitud.getProcesoseisCollection()) {
                Slicitud oldIdSolicitudOfProcesoseisCollectionProcesoseis = procesoseisCollectionProcesoseis.getIdSolicitud();
                procesoseisCollectionProcesoseis.setIdSolicitud(slicitud);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
                if (oldIdSolicitudOfProcesoseisCollectionProcesoseis != null) {
                    oldIdSolicitudOfProcesoseisCollectionProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionProcesoseis);
                    oldIdSolicitudOfProcesoseisCollectionProcesoseis = em.merge(oldIdSolicitudOfProcesoseisCollectionProcesoseis);
                }
            }
            for (Procesoocho procesoochoCollectionProcesoocho : slicitud.getProcesoochoCollection()) {
                Slicitud oldIdSolicitudOfProcesoochoCollectionProcesoocho = procesoochoCollectionProcesoocho.getIdSolicitud();
                procesoochoCollectionProcesoocho.setIdSolicitud(slicitud);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
                if (oldIdSolicitudOfProcesoochoCollectionProcesoocho != null) {
                    oldIdSolicitudOfProcesoochoCollectionProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionProcesoocho);
                    oldIdSolicitudOfProcesoochoCollectionProcesoocho = em.merge(oldIdSolicitudOfProcesoochoCollectionProcesoocho);
                }
            }
            for (Procesouno procesounoCollectionProcesouno : slicitud.getProcesounoCollection()) {
                Slicitud oldIdSolicitudOfProcesounoCollectionProcesouno = procesounoCollectionProcesouno.getIdSolicitud();
                procesounoCollectionProcesouno.setIdSolicitud(slicitud);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
                if (oldIdSolicitudOfProcesounoCollectionProcesouno != null) {
                    oldIdSolicitudOfProcesounoCollectionProcesouno.getProcesounoCollection().remove(procesounoCollectionProcesouno);
                    oldIdSolicitudOfProcesounoCollectionProcesouno = em.merge(oldIdSolicitudOfProcesounoCollectionProcesouno);
                }
            }
            for (Procesosiete procesosieteCollectionProcesosiete : slicitud.getProcesosieteCollection()) {
                Slicitud oldIdSolicitudOfProcesosieteCollectionProcesosiete = procesosieteCollectionProcesosiete.getIdSolicitud();
                procesosieteCollectionProcesosiete.setIdSolicitud(slicitud);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
                if (oldIdSolicitudOfProcesosieteCollectionProcesosiete != null) {
                    oldIdSolicitudOfProcesosieteCollectionProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionProcesosiete);
                    oldIdSolicitudOfProcesosieteCollectionProcesosiete = em.merge(oldIdSolicitudOfProcesosieteCollectionProcesosiete);
                }
            }
            for (Procesocuatro procesocuatroCollectionProcesocuatro : slicitud.getProcesocuatroCollection()) {
                Slicitud oldIdSolicitudOfProcesocuatroCollectionProcesocuatro = procesocuatroCollectionProcesocuatro.getIdSolicitud();
                procesocuatroCollectionProcesocuatro.setIdSolicitud(slicitud);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
                if (oldIdSolicitudOfProcesocuatroCollectionProcesocuatro != null) {
                    oldIdSolicitudOfProcesocuatroCollectionProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionProcesocuatro);
                    oldIdSolicitudOfProcesocuatroCollectionProcesocuatro = em.merge(oldIdSolicitudOfProcesocuatroCollectionProcesocuatro);
                }
            }
            for (Procesodos procesodosCollectionProcesodos : slicitud.getProcesodosCollection()) {
                Slicitud oldIdSolicitudOfProcesodosCollectionProcesodos = procesodosCollectionProcesodos.getIdSolicitud();
                procesodosCollectionProcesodos.setIdSolicitud(slicitud);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
                if (oldIdSolicitudOfProcesodosCollectionProcesodos != null) {
                    oldIdSolicitudOfProcesodosCollectionProcesodos.getProcesodosCollection().remove(procesodosCollectionProcesodos);
                    oldIdSolicitudOfProcesodosCollectionProcesodos = em.merge(oldIdSolicitudOfProcesodosCollectionProcesodos);
                }
            }
            for (Procesocinco procesocincoCollectionProcesocinco : slicitud.getProcesocincoCollection()) {
                Slicitud oldIdSolicitudOfProcesocincoCollectionProcesocinco = procesocincoCollectionProcesocinco.getIdSolicitud();
                procesocincoCollectionProcesocinco.setIdSolicitud(slicitud);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
                if (oldIdSolicitudOfProcesocincoCollectionProcesocinco != null) {
                    oldIdSolicitudOfProcesocincoCollectionProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionProcesocinco);
                    oldIdSolicitudOfProcesocincoCollectionProcesocinco = em.merge(oldIdSolicitudOfProcesocincoCollectionProcesocinco);
                }
            }
            for (Procesotres procesotresCollectionProcesotres : slicitud.getProcesotresCollection()) {
                Slicitud oldIdSolicitudOfProcesotresCollectionProcesotres = procesotresCollectionProcesotres.getIdSolicitud();
                procesotresCollectionProcesotres.setIdSolicitud(slicitud);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
                if (oldIdSolicitudOfProcesotresCollectionProcesotres != null) {
                    oldIdSolicitudOfProcesotresCollectionProcesotres.getProcesotresCollection().remove(procesotresCollectionProcesotres);
                    oldIdSolicitudOfProcesotresCollectionProcesotres = em.merge(oldIdSolicitudOfProcesotresCollectionProcesotres);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSlicitud(slicitud.getId()) != null) {
                throw new PreexistingEntityException("Slicitud " + slicitud + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Slicitud slicitud) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Slicitud persistentSlicitud = em.find(Slicitud.class, slicitud.getId());
            Collection<Procesoseis> procesoseisCollectionOld = persistentSlicitud.getProcesoseisCollection();
            Collection<Procesoseis> procesoseisCollectionNew = slicitud.getProcesoseisCollection();
            Collection<Procesoocho> procesoochoCollectionOld = persistentSlicitud.getProcesoochoCollection();
            Collection<Procesoocho> procesoochoCollectionNew = slicitud.getProcesoochoCollection();
            Collection<Procesouno> procesounoCollectionOld = persistentSlicitud.getProcesounoCollection();
            Collection<Procesouno> procesounoCollectionNew = slicitud.getProcesounoCollection();
            Collection<Procesosiete> procesosieteCollectionOld = persistentSlicitud.getProcesosieteCollection();
            Collection<Procesosiete> procesosieteCollectionNew = slicitud.getProcesosieteCollection();
            Collection<Procesocuatro> procesocuatroCollectionOld = persistentSlicitud.getProcesocuatroCollection();
            Collection<Procesocuatro> procesocuatroCollectionNew = slicitud.getProcesocuatroCollection();
            Collection<Procesodos> procesodosCollectionOld = persistentSlicitud.getProcesodosCollection();
            Collection<Procesodos> procesodosCollectionNew = slicitud.getProcesodosCollection();
            Collection<Procesocinco> procesocincoCollectionOld = persistentSlicitud.getProcesocincoCollection();
            Collection<Procesocinco> procesocincoCollectionNew = slicitud.getProcesocincoCollection();
            Collection<Procesotres> procesotresCollectionOld = persistentSlicitud.getProcesotresCollection();
            Collection<Procesotres> procesotresCollectionNew = slicitud.getProcesotresCollection();
            Collection<Procesoseis> attachedProcesoseisCollectionNew = new ArrayList<Procesoseis>();
            for (Procesoseis procesoseisCollectionNewProcesoseisToAttach : procesoseisCollectionNew) {
                procesoseisCollectionNewProcesoseisToAttach = em.getReference(procesoseisCollectionNewProcesoseisToAttach.getClass(), procesoseisCollectionNewProcesoseisToAttach.getId());
                attachedProcesoseisCollectionNew.add(procesoseisCollectionNewProcesoseisToAttach);
            }
            procesoseisCollectionNew = attachedProcesoseisCollectionNew;
            slicitud.setProcesoseisCollection(procesoseisCollectionNew);
            Collection<Procesoocho> attachedProcesoochoCollectionNew = new ArrayList<Procesoocho>();
            for (Procesoocho procesoochoCollectionNewProcesoochoToAttach : procesoochoCollectionNew) {
                procesoochoCollectionNewProcesoochoToAttach = em.getReference(procesoochoCollectionNewProcesoochoToAttach.getClass(), procesoochoCollectionNewProcesoochoToAttach.getId());
                attachedProcesoochoCollectionNew.add(procesoochoCollectionNewProcesoochoToAttach);
            }
            procesoochoCollectionNew = attachedProcesoochoCollectionNew;
            slicitud.setProcesoochoCollection(procesoochoCollectionNew);
            Collection<Procesouno> attachedProcesounoCollectionNew = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionNewProcesounoToAttach : procesounoCollectionNew) {
                procesounoCollectionNewProcesounoToAttach = em.getReference(procesounoCollectionNewProcesounoToAttach.getClass(), procesounoCollectionNewProcesounoToAttach.getId());
                attachedProcesounoCollectionNew.add(procesounoCollectionNewProcesounoToAttach);
            }
            procesounoCollectionNew = attachedProcesounoCollectionNew;
            slicitud.setProcesounoCollection(procesounoCollectionNew);
            Collection<Procesosiete> attachedProcesosieteCollectionNew = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionNewProcesosieteToAttach : procesosieteCollectionNew) {
                procesosieteCollectionNewProcesosieteToAttach = em.getReference(procesosieteCollectionNewProcesosieteToAttach.getClass(), procesosieteCollectionNewProcesosieteToAttach.getId());
                attachedProcesosieteCollectionNew.add(procesosieteCollectionNewProcesosieteToAttach);
            }
            procesosieteCollectionNew = attachedProcesosieteCollectionNew;
            slicitud.setProcesosieteCollection(procesosieteCollectionNew);
            Collection<Procesocuatro> attachedProcesocuatroCollectionNew = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionNewProcesocuatroToAttach : procesocuatroCollectionNew) {
                procesocuatroCollectionNewProcesocuatroToAttach = em.getReference(procesocuatroCollectionNewProcesocuatroToAttach.getClass(), procesocuatroCollectionNewProcesocuatroToAttach.getId());
                attachedProcesocuatroCollectionNew.add(procesocuatroCollectionNewProcesocuatroToAttach);
            }
            procesocuatroCollectionNew = attachedProcesocuatroCollectionNew;
            slicitud.setProcesocuatroCollection(procesocuatroCollectionNew);
            Collection<Procesodos> attachedProcesodosCollectionNew = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionNewProcesodosToAttach : procesodosCollectionNew) {
                procesodosCollectionNewProcesodosToAttach = em.getReference(procesodosCollectionNewProcesodosToAttach.getClass(), procesodosCollectionNewProcesodosToAttach.getId());
                attachedProcesodosCollectionNew.add(procesodosCollectionNewProcesodosToAttach);
            }
            procesodosCollectionNew = attachedProcesodosCollectionNew;
            slicitud.setProcesodosCollection(procesodosCollectionNew);
            Collection<Procesocinco> attachedProcesocincoCollectionNew = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionNewProcesocincoToAttach : procesocincoCollectionNew) {
                procesocincoCollectionNewProcesocincoToAttach = em.getReference(procesocincoCollectionNewProcesocincoToAttach.getClass(), procesocincoCollectionNewProcesocincoToAttach.getId());
                attachedProcesocincoCollectionNew.add(procesocincoCollectionNewProcesocincoToAttach);
            }
            procesocincoCollectionNew = attachedProcesocincoCollectionNew;
            slicitud.setProcesocincoCollection(procesocincoCollectionNew);
            Collection<Procesotres> attachedProcesotresCollectionNew = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionNewProcesotresToAttach : procesotresCollectionNew) {
                procesotresCollectionNewProcesotresToAttach = em.getReference(procesotresCollectionNewProcesotresToAttach.getClass(), procesotresCollectionNewProcesotresToAttach.getId());
                attachedProcesotresCollectionNew.add(procesotresCollectionNewProcesotresToAttach);
            }
            procesotresCollectionNew = attachedProcesotresCollectionNew;
            slicitud.setProcesotresCollection(procesotresCollectionNew);
            slicitud = em.merge(slicitud);
            for (Procesoseis procesoseisCollectionOldProcesoseis : procesoseisCollectionOld) {
                if (!procesoseisCollectionNew.contains(procesoseisCollectionOldProcesoseis)) {
                    procesoseisCollectionOldProcesoseis.setIdSolicitud(null);
                    procesoseisCollectionOldProcesoseis = em.merge(procesoseisCollectionOldProcesoseis);
                }
            }
            for (Procesoseis procesoseisCollectionNewProcesoseis : procesoseisCollectionNew) {
                if (!procesoseisCollectionOld.contains(procesoseisCollectionNewProcesoseis)) {
                    Slicitud oldIdSolicitudOfProcesoseisCollectionNewProcesoseis = procesoseisCollectionNewProcesoseis.getIdSolicitud();
                    procesoseisCollectionNewProcesoseis.setIdSolicitud(slicitud);
                    procesoseisCollectionNewProcesoseis = em.merge(procesoseisCollectionNewProcesoseis);
                    if (oldIdSolicitudOfProcesoseisCollectionNewProcesoseis != null && !oldIdSolicitudOfProcesoseisCollectionNewProcesoseis.equals(slicitud)) {
                        oldIdSolicitudOfProcesoseisCollectionNewProcesoseis.getProcesoseisCollection().remove(procesoseisCollectionNewProcesoseis);
                        oldIdSolicitudOfProcesoseisCollectionNewProcesoseis = em.merge(oldIdSolicitudOfProcesoseisCollectionNewProcesoseis);
                    }
                }
            }
            for (Procesoocho procesoochoCollectionOldProcesoocho : procesoochoCollectionOld) {
                if (!procesoochoCollectionNew.contains(procesoochoCollectionOldProcesoocho)) {
                    procesoochoCollectionOldProcesoocho.setIdSolicitud(null);
                    procesoochoCollectionOldProcesoocho = em.merge(procesoochoCollectionOldProcesoocho);
                }
            }
            for (Procesoocho procesoochoCollectionNewProcesoocho : procesoochoCollectionNew) {
                if (!procesoochoCollectionOld.contains(procesoochoCollectionNewProcesoocho)) {
                    Slicitud oldIdSolicitudOfProcesoochoCollectionNewProcesoocho = procesoochoCollectionNewProcesoocho.getIdSolicitud();
                    procesoochoCollectionNewProcesoocho.setIdSolicitud(slicitud);
                    procesoochoCollectionNewProcesoocho = em.merge(procesoochoCollectionNewProcesoocho);
                    if (oldIdSolicitudOfProcesoochoCollectionNewProcesoocho != null && !oldIdSolicitudOfProcesoochoCollectionNewProcesoocho.equals(slicitud)) {
                        oldIdSolicitudOfProcesoochoCollectionNewProcesoocho.getProcesoochoCollection().remove(procesoochoCollectionNewProcesoocho);
                        oldIdSolicitudOfProcesoochoCollectionNewProcesoocho = em.merge(oldIdSolicitudOfProcesoochoCollectionNewProcesoocho);
                    }
                }
            }
            for (Procesouno procesounoCollectionOldProcesouno : procesounoCollectionOld) {
                if (!procesounoCollectionNew.contains(procesounoCollectionOldProcesouno)) {
                    procesounoCollectionOldProcesouno.setIdSolicitud(null);
                    procesounoCollectionOldProcesouno = em.merge(procesounoCollectionOldProcesouno);
                }
            }
            for (Procesouno procesounoCollectionNewProcesouno : procesounoCollectionNew) {
                if (!procesounoCollectionOld.contains(procesounoCollectionNewProcesouno)) {
                    Slicitud oldIdSolicitudOfProcesounoCollectionNewProcesouno = procesounoCollectionNewProcesouno.getIdSolicitud();
                    procesounoCollectionNewProcesouno.setIdSolicitud(slicitud);
                    procesounoCollectionNewProcesouno = em.merge(procesounoCollectionNewProcesouno);
                    if (oldIdSolicitudOfProcesounoCollectionNewProcesouno != null && !oldIdSolicitudOfProcesounoCollectionNewProcesouno.equals(slicitud)) {
                        oldIdSolicitudOfProcesounoCollectionNewProcesouno.getProcesounoCollection().remove(procesounoCollectionNewProcesouno);
                        oldIdSolicitudOfProcesounoCollectionNewProcesouno = em.merge(oldIdSolicitudOfProcesounoCollectionNewProcesouno);
                    }
                }
            }
            for (Procesosiete procesosieteCollectionOldProcesosiete : procesosieteCollectionOld) {
                if (!procesosieteCollectionNew.contains(procesosieteCollectionOldProcesosiete)) {
                    procesosieteCollectionOldProcesosiete.setIdSolicitud(null);
                    procesosieteCollectionOldProcesosiete = em.merge(procesosieteCollectionOldProcesosiete);
                }
            }
            for (Procesosiete procesosieteCollectionNewProcesosiete : procesosieteCollectionNew) {
                if (!procesosieteCollectionOld.contains(procesosieteCollectionNewProcesosiete)) {
                    Slicitud oldIdSolicitudOfProcesosieteCollectionNewProcesosiete = procesosieteCollectionNewProcesosiete.getIdSolicitud();
                    procesosieteCollectionNewProcesosiete.setIdSolicitud(slicitud);
                    procesosieteCollectionNewProcesosiete = em.merge(procesosieteCollectionNewProcesosiete);
                    if (oldIdSolicitudOfProcesosieteCollectionNewProcesosiete != null && !oldIdSolicitudOfProcesosieteCollectionNewProcesosiete.equals(slicitud)) {
                        oldIdSolicitudOfProcesosieteCollectionNewProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionNewProcesosiete);
                        oldIdSolicitudOfProcesosieteCollectionNewProcesosiete = em.merge(oldIdSolicitudOfProcesosieteCollectionNewProcesosiete);
                    }
                }
            }
            for (Procesocuatro procesocuatroCollectionOldProcesocuatro : procesocuatroCollectionOld) {
                if (!procesocuatroCollectionNew.contains(procesocuatroCollectionOldProcesocuatro)) {
                    procesocuatroCollectionOldProcesocuatro.setIdSolicitud(null);
                    procesocuatroCollectionOldProcesocuatro = em.merge(procesocuatroCollectionOldProcesocuatro);
                }
            }
            for (Procesocuatro procesocuatroCollectionNewProcesocuatro : procesocuatroCollectionNew) {
                if (!procesocuatroCollectionOld.contains(procesocuatroCollectionNewProcesocuatro)) {
                    Slicitud oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro = procesocuatroCollectionNewProcesocuatro.getIdSolicitud();
                    procesocuatroCollectionNewProcesocuatro.setIdSolicitud(slicitud);
                    procesocuatroCollectionNewProcesocuatro = em.merge(procesocuatroCollectionNewProcesocuatro);
                    if (oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro != null && !oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro.equals(slicitud)) {
                        oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionNewProcesocuatro);
                        oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro = em.merge(oldIdSolicitudOfProcesocuatroCollectionNewProcesocuatro);
                    }
                }
            }
            for (Procesodos procesodosCollectionOldProcesodos : procesodosCollectionOld) {
                if (!procesodosCollectionNew.contains(procesodosCollectionOldProcesodos)) {
                    procesodosCollectionOldProcesodos.setIdSolicitud(null);
                    procesodosCollectionOldProcesodos = em.merge(procesodosCollectionOldProcesodos);
                }
            }
            for (Procesodos procesodosCollectionNewProcesodos : procesodosCollectionNew) {
                if (!procesodosCollectionOld.contains(procesodosCollectionNewProcesodos)) {
                    Slicitud oldIdSolicitudOfProcesodosCollectionNewProcesodos = procesodosCollectionNewProcesodos.getIdSolicitud();
                    procesodosCollectionNewProcesodos.setIdSolicitud(slicitud);
                    procesodosCollectionNewProcesodos = em.merge(procesodosCollectionNewProcesodos);
                    if (oldIdSolicitudOfProcesodosCollectionNewProcesodos != null && !oldIdSolicitudOfProcesodosCollectionNewProcesodos.equals(slicitud)) {
                        oldIdSolicitudOfProcesodosCollectionNewProcesodos.getProcesodosCollection().remove(procesodosCollectionNewProcesodos);
                        oldIdSolicitudOfProcesodosCollectionNewProcesodos = em.merge(oldIdSolicitudOfProcesodosCollectionNewProcesodos);
                    }
                }
            }
            for (Procesocinco procesocincoCollectionOldProcesocinco : procesocincoCollectionOld) {
                if (!procesocincoCollectionNew.contains(procesocincoCollectionOldProcesocinco)) {
                    procesocincoCollectionOldProcesocinco.setIdSolicitud(null);
                    procesocincoCollectionOldProcesocinco = em.merge(procesocincoCollectionOldProcesocinco);
                }
            }
            for (Procesocinco procesocincoCollectionNewProcesocinco : procesocincoCollectionNew) {
                if (!procesocincoCollectionOld.contains(procesocincoCollectionNewProcesocinco)) {
                    Slicitud oldIdSolicitudOfProcesocincoCollectionNewProcesocinco = procesocincoCollectionNewProcesocinco.getIdSolicitud();
                    procesocincoCollectionNewProcesocinco.setIdSolicitud(slicitud);
                    procesocincoCollectionNewProcesocinco = em.merge(procesocincoCollectionNewProcesocinco);
                    if (oldIdSolicitudOfProcesocincoCollectionNewProcesocinco != null && !oldIdSolicitudOfProcesocincoCollectionNewProcesocinco.equals(slicitud)) {
                        oldIdSolicitudOfProcesocincoCollectionNewProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionNewProcesocinco);
                        oldIdSolicitudOfProcesocincoCollectionNewProcesocinco = em.merge(oldIdSolicitudOfProcesocincoCollectionNewProcesocinco);
                    }
                }
            }
            for (Procesotres procesotresCollectionOldProcesotres : procesotresCollectionOld) {
                if (!procesotresCollectionNew.contains(procesotresCollectionOldProcesotres)) {
                    procesotresCollectionOldProcesotres.setIdSolicitud(null);
                    procesotresCollectionOldProcesotres = em.merge(procesotresCollectionOldProcesotres);
                }
            }
            for (Procesotres procesotresCollectionNewProcesotres : procesotresCollectionNew) {
                if (!procesotresCollectionOld.contains(procesotresCollectionNewProcesotres)) {
                    Slicitud oldIdSolicitudOfProcesotresCollectionNewProcesotres = procesotresCollectionNewProcesotres.getIdSolicitud();
                    procesotresCollectionNewProcesotres.setIdSolicitud(slicitud);
                    procesotresCollectionNewProcesotres = em.merge(procesotresCollectionNewProcesotres);
                    if (oldIdSolicitudOfProcesotresCollectionNewProcesotres != null && !oldIdSolicitudOfProcesotresCollectionNewProcesotres.equals(slicitud)) {
                        oldIdSolicitudOfProcesotresCollectionNewProcesotres.getProcesotresCollection().remove(procesotresCollectionNewProcesotres);
                        oldIdSolicitudOfProcesotresCollectionNewProcesotres = em.merge(oldIdSolicitudOfProcesotresCollectionNewProcesotres);
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
                Short id = slicitud.getId();
                if (findSlicitud(id) == null) {
                    throw new NonexistentEntityException("The slicitud with id " + id + " no longer exists.");
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
            Slicitud slicitud;
            try {
                slicitud = em.getReference(Slicitud.class, id);
                slicitud.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The slicitud with id " + id + " no longer exists.", enfe);
            }
            Collection<Procesoseis> procesoseisCollection = slicitud.getProcesoseisCollection();
            for (Procesoseis procesoseisCollectionProcesoseis : procesoseisCollection) {
                procesoseisCollectionProcesoseis.setIdSolicitud(null);
                procesoseisCollectionProcesoseis = em.merge(procesoseisCollectionProcesoseis);
            }
            Collection<Procesoocho> procesoochoCollection = slicitud.getProcesoochoCollection();
            for (Procesoocho procesoochoCollectionProcesoocho : procesoochoCollection) {
                procesoochoCollectionProcesoocho.setIdSolicitud(null);
                procesoochoCollectionProcesoocho = em.merge(procesoochoCollectionProcesoocho);
            }
            Collection<Procesouno> procesounoCollection = slicitud.getProcesounoCollection();
            for (Procesouno procesounoCollectionProcesouno : procesounoCollection) {
                procesounoCollectionProcesouno.setIdSolicitud(null);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
            }
            Collection<Procesosiete> procesosieteCollection = slicitud.getProcesosieteCollection();
            for (Procesosiete procesosieteCollectionProcesosiete : procesosieteCollection) {
                procesosieteCollectionProcesosiete.setIdSolicitud(null);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
            }
            Collection<Procesocuatro> procesocuatroCollection = slicitud.getProcesocuatroCollection();
            for (Procesocuatro procesocuatroCollectionProcesocuatro : procesocuatroCollection) {
                procesocuatroCollectionProcesocuatro.setIdSolicitud(null);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
            }
            Collection<Procesodos> procesodosCollection = slicitud.getProcesodosCollection();
            for (Procesodos procesodosCollectionProcesodos : procesodosCollection) {
                procesodosCollectionProcesodos.setIdSolicitud(null);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
            }
            Collection<Procesocinco> procesocincoCollection = slicitud.getProcesocincoCollection();
            for (Procesocinco procesocincoCollectionProcesocinco : procesocincoCollection) {
                procesocincoCollectionProcesocinco.setIdSolicitud(null);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
            }
            Collection<Procesotres> procesotresCollection = slicitud.getProcesotresCollection();
            for (Procesotres procesotresCollectionProcesotres : procesotresCollection) {
                procesotresCollectionProcesotres.setIdSolicitud(null);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
            }
            em.remove(slicitud);
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

    public List<Slicitud> findSlicitudEntities() {
        return findSlicitudEntities(true, -1, -1);
    }

    public List<Slicitud> findSlicitudEntities(int maxResults, int firstResult) {
        return findSlicitudEntities(false, maxResults, firstResult);
    }

    private List<Slicitud> findSlicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Slicitud.class));
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

    public Slicitud findSlicitud(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Slicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSlicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Slicitud> rt = cq.from(Slicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
