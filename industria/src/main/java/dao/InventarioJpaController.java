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
import entidades.Pieza;
import entidades.Procesouno;
import java.util.ArrayList;
import java.util.Collection;
import entidades.Procesosiete;
import entidades.Procesocuatro;
import entidades.Interiorasiento;
import entidades.Interiorvolante;
import entidades.Procesodos;
import entidades.Procesocinco;
import entidades.Interiorfreno;
import entidades.Inventario;
import entidades.Procesotres;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jaker
 */
public class InventarioJpaController implements Serializable {

    public InventarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inventario inventario) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (inventario.getProcesounoCollection() == null) {
            inventario.setProcesounoCollection(new ArrayList<Procesouno>());
        }
        if (inventario.getProcesosieteCollection() == null) {
            inventario.setProcesosieteCollection(new ArrayList<Procesosiete>());
        }
        if (inventario.getProcesocuatroCollection() == null) {
            inventario.setProcesocuatroCollection(new ArrayList<Procesocuatro>());
        }
        if (inventario.getInteriorasientoCollection() == null) {
            inventario.setInteriorasientoCollection(new ArrayList<Interiorasiento>());
        }
        if (inventario.getInteriorvolanteCollection() == null) {
            inventario.setInteriorvolanteCollection(new ArrayList<Interiorvolante>());
        }
        if (inventario.getProcesodosCollection() == null) {
            inventario.setProcesodosCollection(new ArrayList<Procesodos>());
        }
        if (inventario.getProcesocincoCollection() == null) {
            inventario.setProcesocincoCollection(new ArrayList<Procesocinco>());
        }
        if (inventario.getInteriorfrenoCollection() == null) {
            inventario.setInteriorfrenoCollection(new ArrayList<Interiorfreno>());
        }
        if (inventario.getProcesotresCollection() == null) {
            inventario.setProcesotresCollection(new ArrayList<Procesotres>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pieza idPieza = inventario.getIdPieza();
            if (idPieza != null) {
                idPieza = em.getReference(idPieza.getClass(), idPieza.getId());
                inventario.setIdPieza(idPieza);
            }
            Collection<Procesouno> attachedProcesounoCollection = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionProcesounoToAttach : inventario.getProcesounoCollection()) {
                procesounoCollectionProcesounoToAttach = em.getReference(procesounoCollectionProcesounoToAttach.getClass(), procesounoCollectionProcesounoToAttach.getId());
                attachedProcesounoCollection.add(procesounoCollectionProcesounoToAttach);
            }
            inventario.setProcesounoCollection(attachedProcesounoCollection);
            Collection<Procesosiete> attachedProcesosieteCollection = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionProcesosieteToAttach : inventario.getProcesosieteCollection()) {
                procesosieteCollectionProcesosieteToAttach = em.getReference(procesosieteCollectionProcesosieteToAttach.getClass(), procesosieteCollectionProcesosieteToAttach.getId());
                attachedProcesosieteCollection.add(procesosieteCollectionProcesosieteToAttach);
            }
            inventario.setProcesosieteCollection(attachedProcesosieteCollection);
            Collection<Procesocuatro> attachedProcesocuatroCollection = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionProcesocuatroToAttach : inventario.getProcesocuatroCollection()) {
                procesocuatroCollectionProcesocuatroToAttach = em.getReference(procesocuatroCollectionProcesocuatroToAttach.getClass(), procesocuatroCollectionProcesocuatroToAttach.getId());
                attachedProcesocuatroCollection.add(procesocuatroCollectionProcesocuatroToAttach);
            }
            inventario.setProcesocuatroCollection(attachedProcesocuatroCollection);
            Collection<Interiorasiento> attachedInteriorasientoCollection = new ArrayList<Interiorasiento>();
            for (Interiorasiento interiorasientoCollectionInteriorasientoToAttach : inventario.getInteriorasientoCollection()) {
                interiorasientoCollectionInteriorasientoToAttach = em.getReference(interiorasientoCollectionInteriorasientoToAttach.getClass(), interiorasientoCollectionInteriorasientoToAttach.getId());
                attachedInteriorasientoCollection.add(interiorasientoCollectionInteriorasientoToAttach);
            }
            inventario.setInteriorasientoCollection(attachedInteriorasientoCollection);
            Collection<Interiorvolante> attachedInteriorvolanteCollection = new ArrayList<Interiorvolante>();
            for (Interiorvolante interiorvolanteCollectionInteriorvolanteToAttach : inventario.getInteriorvolanteCollection()) {
                interiorvolanteCollectionInteriorvolanteToAttach = em.getReference(interiorvolanteCollectionInteriorvolanteToAttach.getClass(), interiorvolanteCollectionInteriorvolanteToAttach.getId());
                attachedInteriorvolanteCollection.add(interiorvolanteCollectionInteriorvolanteToAttach);
            }
            inventario.setInteriorvolanteCollection(attachedInteriorvolanteCollection);
            Collection<Procesodos> attachedProcesodosCollection = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionProcesodosToAttach : inventario.getProcesodosCollection()) {
                procesodosCollectionProcesodosToAttach = em.getReference(procesodosCollectionProcesodosToAttach.getClass(), procesodosCollectionProcesodosToAttach.getId());
                attachedProcesodosCollection.add(procesodosCollectionProcesodosToAttach);
            }
            inventario.setProcesodosCollection(attachedProcesodosCollection);
            Collection<Procesocinco> attachedProcesocincoCollection = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionProcesocincoToAttach : inventario.getProcesocincoCollection()) {
                procesocincoCollectionProcesocincoToAttach = em.getReference(procesocincoCollectionProcesocincoToAttach.getClass(), procesocincoCollectionProcesocincoToAttach.getId());
                attachedProcesocincoCollection.add(procesocincoCollectionProcesocincoToAttach);
            }
            inventario.setProcesocincoCollection(attachedProcesocincoCollection);
            Collection<Interiorfreno> attachedInteriorfrenoCollection = new ArrayList<Interiorfreno>();
            for (Interiorfreno interiorfrenoCollectionInteriorfrenoToAttach : inventario.getInteriorfrenoCollection()) {
                interiorfrenoCollectionInteriorfrenoToAttach = em.getReference(interiorfrenoCollectionInteriorfrenoToAttach.getClass(), interiorfrenoCollectionInteriorfrenoToAttach.getId());
                attachedInteriorfrenoCollection.add(interiorfrenoCollectionInteriorfrenoToAttach);
            }
            inventario.setInteriorfrenoCollection(attachedInteriorfrenoCollection);
            Collection<Procesotres> attachedProcesotresCollection = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionProcesotresToAttach : inventario.getProcesotresCollection()) {
                procesotresCollectionProcesotresToAttach = em.getReference(procesotresCollectionProcesotresToAttach.getClass(), procesotresCollectionProcesotresToAttach.getId());
                attachedProcesotresCollection.add(procesotresCollectionProcesotresToAttach);
            }
            inventario.setProcesotresCollection(attachedProcesotresCollection);
            em.persist(inventario);
            if (idPieza != null) {
                idPieza.getInventarioCollection().add(inventario);
                idPieza = em.merge(idPieza);
            }
            for (Procesouno procesounoCollectionProcesouno : inventario.getProcesounoCollection()) {
                Inventario oldIdInventarioOfProcesounoCollectionProcesouno = procesounoCollectionProcesouno.getIdInventario();
                procesounoCollectionProcesouno.setIdInventario(inventario);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
                if (oldIdInventarioOfProcesounoCollectionProcesouno != null) {
                    oldIdInventarioOfProcesounoCollectionProcesouno.getProcesounoCollection().remove(procesounoCollectionProcesouno);
                    oldIdInventarioOfProcesounoCollectionProcesouno = em.merge(oldIdInventarioOfProcesounoCollectionProcesouno);
                }
            }
            for (Procesosiete procesosieteCollectionProcesosiete : inventario.getProcesosieteCollection()) {
                Inventario oldIdInventarioOfProcesosieteCollectionProcesosiete = procesosieteCollectionProcesosiete.getIdInventario();
                procesosieteCollectionProcesosiete.setIdInventario(inventario);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
                if (oldIdInventarioOfProcesosieteCollectionProcesosiete != null) {
                    oldIdInventarioOfProcesosieteCollectionProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionProcesosiete);
                    oldIdInventarioOfProcesosieteCollectionProcesosiete = em.merge(oldIdInventarioOfProcesosieteCollectionProcesosiete);
                }
            }
            for (Procesocuatro procesocuatroCollectionProcesocuatro : inventario.getProcesocuatroCollection()) {
                Inventario oldIdInventarioOfProcesocuatroCollectionProcesocuatro = procesocuatroCollectionProcesocuatro.getIdInventario();
                procesocuatroCollectionProcesocuatro.setIdInventario(inventario);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
                if (oldIdInventarioOfProcesocuatroCollectionProcesocuatro != null) {
                    oldIdInventarioOfProcesocuatroCollectionProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionProcesocuatro);
                    oldIdInventarioOfProcesocuatroCollectionProcesocuatro = em.merge(oldIdInventarioOfProcesocuatroCollectionProcesocuatro);
                }
            }
            for (Interiorasiento interiorasientoCollectionInteriorasiento : inventario.getInteriorasientoCollection()) {
                Inventario oldIdInventarioOfInteriorasientoCollectionInteriorasiento = interiorasientoCollectionInteriorasiento.getIdInventario();
                interiorasientoCollectionInteriorasiento.setIdInventario(inventario);
                interiorasientoCollectionInteriorasiento = em.merge(interiorasientoCollectionInteriorasiento);
                if (oldIdInventarioOfInteriorasientoCollectionInteriorasiento != null) {
                    oldIdInventarioOfInteriorasientoCollectionInteriorasiento.getInteriorasientoCollection().remove(interiorasientoCollectionInteriorasiento);
                    oldIdInventarioOfInteriorasientoCollectionInteriorasiento = em.merge(oldIdInventarioOfInteriorasientoCollectionInteriorasiento);
                }
            }
            for (Interiorvolante interiorvolanteCollectionInteriorvolante : inventario.getInteriorvolanteCollection()) {
                Inventario oldIdInventarioOfInteriorvolanteCollectionInteriorvolante = interiorvolanteCollectionInteriorvolante.getIdInventario();
                interiorvolanteCollectionInteriorvolante.setIdInventario(inventario);
                interiorvolanteCollectionInteriorvolante = em.merge(interiorvolanteCollectionInteriorvolante);
                if (oldIdInventarioOfInteriorvolanteCollectionInteriorvolante != null) {
                    oldIdInventarioOfInteriorvolanteCollectionInteriorvolante.getInteriorvolanteCollection().remove(interiorvolanteCollectionInteriorvolante);
                    oldIdInventarioOfInteriorvolanteCollectionInteriorvolante = em.merge(oldIdInventarioOfInteriorvolanteCollectionInteriorvolante);
                }
            }
            for (Procesodos procesodosCollectionProcesodos : inventario.getProcesodosCollection()) {
                Inventario oldIdInventarioOfProcesodosCollectionProcesodos = procesodosCollectionProcesodos.getIdInventario();
                procesodosCollectionProcesodos.setIdInventario(inventario);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
                if (oldIdInventarioOfProcesodosCollectionProcesodos != null) {
                    oldIdInventarioOfProcesodosCollectionProcesodos.getProcesodosCollection().remove(procesodosCollectionProcesodos);
                    oldIdInventarioOfProcesodosCollectionProcesodos = em.merge(oldIdInventarioOfProcesodosCollectionProcesodos);
                }
            }
            for (Procesocinco procesocincoCollectionProcesocinco : inventario.getProcesocincoCollection()) {
                Inventario oldIdInventarioOfProcesocincoCollectionProcesocinco = procesocincoCollectionProcesocinco.getIdInventario();
                procesocincoCollectionProcesocinco.setIdInventario(inventario);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
                if (oldIdInventarioOfProcesocincoCollectionProcesocinco != null) {
                    oldIdInventarioOfProcesocincoCollectionProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionProcesocinco);
                    oldIdInventarioOfProcesocincoCollectionProcesocinco = em.merge(oldIdInventarioOfProcesocincoCollectionProcesocinco);
                }
            }
            for (Interiorfreno interiorfrenoCollectionInteriorfreno : inventario.getInteriorfrenoCollection()) {
                Inventario oldIdInventarioOfInteriorfrenoCollectionInteriorfreno = interiorfrenoCollectionInteriorfreno.getIdInventario();
                interiorfrenoCollectionInteriorfreno.setIdInventario(inventario);
                interiorfrenoCollectionInteriorfreno = em.merge(interiorfrenoCollectionInteriorfreno);
                if (oldIdInventarioOfInteriorfrenoCollectionInteriorfreno != null) {
                    oldIdInventarioOfInteriorfrenoCollectionInteriorfreno.getInteriorfrenoCollection().remove(interiorfrenoCollectionInteriorfreno);
                    oldIdInventarioOfInteriorfrenoCollectionInteriorfreno = em.merge(oldIdInventarioOfInteriorfrenoCollectionInteriorfreno);
                }
            }
            for (Procesotres procesotresCollectionProcesotres : inventario.getProcesotresCollection()) {
                Inventario oldIdInventarioOfProcesotresCollectionProcesotres = procesotresCollectionProcesotres.getIdInventario();
                procesotresCollectionProcesotres.setIdInventario(inventario);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
                if (oldIdInventarioOfProcesotresCollectionProcesotres != null) {
                    oldIdInventarioOfProcesotresCollectionProcesotres.getProcesotresCollection().remove(procesotresCollectionProcesotres);
                    oldIdInventarioOfProcesotresCollectionProcesotres = em.merge(oldIdInventarioOfProcesotresCollectionProcesotres);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInventario(inventario.getId()) != null) {
                throw new PreexistingEntityException("Inventario " + inventario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inventario inventario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Inventario persistentInventario = em.find(Inventario.class, inventario.getId());
            Pieza idPiezaOld = persistentInventario.getIdPieza();
            Pieza idPiezaNew = inventario.getIdPieza();
            Collection<Procesouno> procesounoCollectionOld = persistentInventario.getProcesounoCollection();
            Collection<Procesouno> procesounoCollectionNew = inventario.getProcesounoCollection();
            Collection<Procesosiete> procesosieteCollectionOld = persistentInventario.getProcesosieteCollection();
            Collection<Procesosiete> procesosieteCollectionNew = inventario.getProcesosieteCollection();
            Collection<Procesocuatro> procesocuatroCollectionOld = persistentInventario.getProcesocuatroCollection();
            Collection<Procesocuatro> procesocuatroCollectionNew = inventario.getProcesocuatroCollection();
            Collection<Interiorasiento> interiorasientoCollectionOld = persistentInventario.getInteriorasientoCollection();
            Collection<Interiorasiento> interiorasientoCollectionNew = inventario.getInteriorasientoCollection();
            Collection<Interiorvolante> interiorvolanteCollectionOld = persistentInventario.getInteriorvolanteCollection();
            Collection<Interiorvolante> interiorvolanteCollectionNew = inventario.getInteriorvolanteCollection();
            Collection<Procesodos> procesodosCollectionOld = persistentInventario.getProcesodosCollection();
            Collection<Procesodos> procesodosCollectionNew = inventario.getProcesodosCollection();
            Collection<Procesocinco> procesocincoCollectionOld = persistentInventario.getProcesocincoCollection();
            Collection<Procesocinco> procesocincoCollectionNew = inventario.getProcesocincoCollection();
            Collection<Interiorfreno> interiorfrenoCollectionOld = persistentInventario.getInteriorfrenoCollection();
            Collection<Interiorfreno> interiorfrenoCollectionNew = inventario.getInteriorfrenoCollection();
            Collection<Procesotres> procesotresCollectionOld = persistentInventario.getProcesotresCollection();
            Collection<Procesotres> procesotresCollectionNew = inventario.getProcesotresCollection();
            if (idPiezaNew != null) {
                idPiezaNew = em.getReference(idPiezaNew.getClass(), idPiezaNew.getId());
                inventario.setIdPieza(idPiezaNew);
            }
            Collection<Procesouno> attachedProcesounoCollectionNew = new ArrayList<Procesouno>();
            for (Procesouno procesounoCollectionNewProcesounoToAttach : procesounoCollectionNew) {
                procesounoCollectionNewProcesounoToAttach = em.getReference(procesounoCollectionNewProcesounoToAttach.getClass(), procesounoCollectionNewProcesounoToAttach.getId());
                attachedProcesounoCollectionNew.add(procesounoCollectionNewProcesounoToAttach);
            }
            procesounoCollectionNew = attachedProcesounoCollectionNew;
            inventario.setProcesounoCollection(procesounoCollectionNew);
            Collection<Procesosiete> attachedProcesosieteCollectionNew = new ArrayList<Procesosiete>();
            for (Procesosiete procesosieteCollectionNewProcesosieteToAttach : procesosieteCollectionNew) {
                procesosieteCollectionNewProcesosieteToAttach = em.getReference(procesosieteCollectionNewProcesosieteToAttach.getClass(), procesosieteCollectionNewProcesosieteToAttach.getId());
                attachedProcesosieteCollectionNew.add(procesosieteCollectionNewProcesosieteToAttach);
            }
            procesosieteCollectionNew = attachedProcesosieteCollectionNew;
            inventario.setProcesosieteCollection(procesosieteCollectionNew);
            Collection<Procesocuatro> attachedProcesocuatroCollectionNew = new ArrayList<Procesocuatro>();
            for (Procesocuatro procesocuatroCollectionNewProcesocuatroToAttach : procesocuatroCollectionNew) {
                procesocuatroCollectionNewProcesocuatroToAttach = em.getReference(procesocuatroCollectionNewProcesocuatroToAttach.getClass(), procesocuatroCollectionNewProcesocuatroToAttach.getId());
                attachedProcesocuatroCollectionNew.add(procesocuatroCollectionNewProcesocuatroToAttach);
            }
            procesocuatroCollectionNew = attachedProcesocuatroCollectionNew;
            inventario.setProcesocuatroCollection(procesocuatroCollectionNew);
            Collection<Interiorasiento> attachedInteriorasientoCollectionNew = new ArrayList<Interiorasiento>();
            for (Interiorasiento interiorasientoCollectionNewInteriorasientoToAttach : interiorasientoCollectionNew) {
                interiorasientoCollectionNewInteriorasientoToAttach = em.getReference(interiorasientoCollectionNewInteriorasientoToAttach.getClass(), interiorasientoCollectionNewInteriorasientoToAttach.getId());
                attachedInteriorasientoCollectionNew.add(interiorasientoCollectionNewInteriorasientoToAttach);
            }
            interiorasientoCollectionNew = attachedInteriorasientoCollectionNew;
            inventario.setInteriorasientoCollection(interiorasientoCollectionNew);
            Collection<Interiorvolante> attachedInteriorvolanteCollectionNew = new ArrayList<Interiorvolante>();
            for (Interiorvolante interiorvolanteCollectionNewInteriorvolanteToAttach : interiorvolanteCollectionNew) {
                interiorvolanteCollectionNewInteriorvolanteToAttach = em.getReference(interiorvolanteCollectionNewInteriorvolanteToAttach.getClass(), interiorvolanteCollectionNewInteriorvolanteToAttach.getId());
                attachedInteriorvolanteCollectionNew.add(interiorvolanteCollectionNewInteriorvolanteToAttach);
            }
            interiorvolanteCollectionNew = attachedInteriorvolanteCollectionNew;
            inventario.setInteriorvolanteCollection(interiorvolanteCollectionNew);
            Collection<Procesodos> attachedProcesodosCollectionNew = new ArrayList<Procesodos>();
            for (Procesodos procesodosCollectionNewProcesodosToAttach : procesodosCollectionNew) {
                procesodosCollectionNewProcesodosToAttach = em.getReference(procesodosCollectionNewProcesodosToAttach.getClass(), procesodosCollectionNewProcesodosToAttach.getId());
                attachedProcesodosCollectionNew.add(procesodosCollectionNewProcesodosToAttach);
            }
            procesodosCollectionNew = attachedProcesodosCollectionNew;
            inventario.setProcesodosCollection(procesodosCollectionNew);
            Collection<Procesocinco> attachedProcesocincoCollectionNew = new ArrayList<Procesocinco>();
            for (Procesocinco procesocincoCollectionNewProcesocincoToAttach : procesocincoCollectionNew) {
                procesocincoCollectionNewProcesocincoToAttach = em.getReference(procesocincoCollectionNewProcesocincoToAttach.getClass(), procesocincoCollectionNewProcesocincoToAttach.getId());
                attachedProcesocincoCollectionNew.add(procesocincoCollectionNewProcesocincoToAttach);
            }
            procesocincoCollectionNew = attachedProcesocincoCollectionNew;
            inventario.setProcesocincoCollection(procesocincoCollectionNew);
            Collection<Interiorfreno> attachedInteriorfrenoCollectionNew = new ArrayList<Interiorfreno>();
            for (Interiorfreno interiorfrenoCollectionNewInteriorfrenoToAttach : interiorfrenoCollectionNew) {
                interiorfrenoCollectionNewInteriorfrenoToAttach = em.getReference(interiorfrenoCollectionNewInteriorfrenoToAttach.getClass(), interiorfrenoCollectionNewInteriorfrenoToAttach.getId());
                attachedInteriorfrenoCollectionNew.add(interiorfrenoCollectionNewInteriorfrenoToAttach);
            }
            interiorfrenoCollectionNew = attachedInteriorfrenoCollectionNew;
            inventario.setInteriorfrenoCollection(interiorfrenoCollectionNew);
            Collection<Procesotres> attachedProcesotresCollectionNew = new ArrayList<Procesotres>();
            for (Procesotres procesotresCollectionNewProcesotresToAttach : procesotresCollectionNew) {
                procesotresCollectionNewProcesotresToAttach = em.getReference(procesotresCollectionNewProcesotresToAttach.getClass(), procesotresCollectionNewProcesotresToAttach.getId());
                attachedProcesotresCollectionNew.add(procesotresCollectionNewProcesotresToAttach);
            }
            procesotresCollectionNew = attachedProcesotresCollectionNew;
            inventario.setProcesotresCollection(procesotresCollectionNew);
            inventario = em.merge(inventario);
            if (idPiezaOld != null && !idPiezaOld.equals(idPiezaNew)) {
                idPiezaOld.getInventarioCollection().remove(inventario);
                idPiezaOld = em.merge(idPiezaOld);
            }
            if (idPiezaNew != null && !idPiezaNew.equals(idPiezaOld)) {
                idPiezaNew.getInventarioCollection().add(inventario);
                idPiezaNew = em.merge(idPiezaNew);
            }
            for (Procesouno procesounoCollectionOldProcesouno : procesounoCollectionOld) {
                if (!procesounoCollectionNew.contains(procesounoCollectionOldProcesouno)) {
                    procesounoCollectionOldProcesouno.setIdInventario(null);
                    procesounoCollectionOldProcesouno = em.merge(procesounoCollectionOldProcesouno);
                }
            }
            for (Procesouno procesounoCollectionNewProcesouno : procesounoCollectionNew) {
                if (!procesounoCollectionOld.contains(procesounoCollectionNewProcesouno)) {
                    Inventario oldIdInventarioOfProcesounoCollectionNewProcesouno = procesounoCollectionNewProcesouno.getIdInventario();
                    procesounoCollectionNewProcesouno.setIdInventario(inventario);
                    procesounoCollectionNewProcesouno = em.merge(procesounoCollectionNewProcesouno);
                    if (oldIdInventarioOfProcesounoCollectionNewProcesouno != null && !oldIdInventarioOfProcesounoCollectionNewProcesouno.equals(inventario)) {
                        oldIdInventarioOfProcesounoCollectionNewProcesouno.getProcesounoCollection().remove(procesounoCollectionNewProcesouno);
                        oldIdInventarioOfProcesounoCollectionNewProcesouno = em.merge(oldIdInventarioOfProcesounoCollectionNewProcesouno);
                    }
                }
            }
            for (Procesosiete procesosieteCollectionOldProcesosiete : procesosieteCollectionOld) {
                if (!procesosieteCollectionNew.contains(procesosieteCollectionOldProcesosiete)) {
                    procesosieteCollectionOldProcesosiete.setIdInventario(null);
                    procesosieteCollectionOldProcesosiete = em.merge(procesosieteCollectionOldProcesosiete);
                }
            }
            for (Procesosiete procesosieteCollectionNewProcesosiete : procesosieteCollectionNew) {
                if (!procesosieteCollectionOld.contains(procesosieteCollectionNewProcesosiete)) {
                    Inventario oldIdInventarioOfProcesosieteCollectionNewProcesosiete = procesosieteCollectionNewProcesosiete.getIdInventario();
                    procesosieteCollectionNewProcesosiete.setIdInventario(inventario);
                    procesosieteCollectionNewProcesosiete = em.merge(procesosieteCollectionNewProcesosiete);
                    if (oldIdInventarioOfProcesosieteCollectionNewProcesosiete != null && !oldIdInventarioOfProcesosieteCollectionNewProcesosiete.equals(inventario)) {
                        oldIdInventarioOfProcesosieteCollectionNewProcesosiete.getProcesosieteCollection().remove(procesosieteCollectionNewProcesosiete);
                        oldIdInventarioOfProcesosieteCollectionNewProcesosiete = em.merge(oldIdInventarioOfProcesosieteCollectionNewProcesosiete);
                    }
                }
            }
            for (Procesocuatro procesocuatroCollectionOldProcesocuatro : procesocuatroCollectionOld) {
                if (!procesocuatroCollectionNew.contains(procesocuatroCollectionOldProcesocuatro)) {
                    procesocuatroCollectionOldProcesocuatro.setIdInventario(null);
                    procesocuatroCollectionOldProcesocuatro = em.merge(procesocuatroCollectionOldProcesocuatro);
                }
            }
            for (Procesocuatro procesocuatroCollectionNewProcesocuatro : procesocuatroCollectionNew) {
                if (!procesocuatroCollectionOld.contains(procesocuatroCollectionNewProcesocuatro)) {
                    Inventario oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro = procesocuatroCollectionNewProcesocuatro.getIdInventario();
                    procesocuatroCollectionNewProcesocuatro.setIdInventario(inventario);
                    procesocuatroCollectionNewProcesocuatro = em.merge(procesocuatroCollectionNewProcesocuatro);
                    if (oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro != null && !oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro.equals(inventario)) {
                        oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro.getProcesocuatroCollection().remove(procesocuatroCollectionNewProcesocuatro);
                        oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro = em.merge(oldIdInventarioOfProcesocuatroCollectionNewProcesocuatro);
                    }
                }
            }
            for (Interiorasiento interiorasientoCollectionOldInteriorasiento : interiorasientoCollectionOld) {
                if (!interiorasientoCollectionNew.contains(interiorasientoCollectionOldInteriorasiento)) {
                    interiorasientoCollectionOldInteriorasiento.setIdInventario(null);
                    interiorasientoCollectionOldInteriorasiento = em.merge(interiorasientoCollectionOldInteriorasiento);
                }
            }
            for (Interiorasiento interiorasientoCollectionNewInteriorasiento : interiorasientoCollectionNew) {
                if (!interiorasientoCollectionOld.contains(interiorasientoCollectionNewInteriorasiento)) {
                    Inventario oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento = interiorasientoCollectionNewInteriorasiento.getIdInventario();
                    interiorasientoCollectionNewInteriorasiento.setIdInventario(inventario);
                    interiorasientoCollectionNewInteriorasiento = em.merge(interiorasientoCollectionNewInteriorasiento);
                    if (oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento != null && !oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento.equals(inventario)) {
                        oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento.getInteriorasientoCollection().remove(interiorasientoCollectionNewInteriorasiento);
                        oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento = em.merge(oldIdInventarioOfInteriorasientoCollectionNewInteriorasiento);
                    }
                }
            }
            for (Interiorvolante interiorvolanteCollectionOldInteriorvolante : interiorvolanteCollectionOld) {
                if (!interiorvolanteCollectionNew.contains(interiorvolanteCollectionOldInteriorvolante)) {
                    interiorvolanteCollectionOldInteriorvolante.setIdInventario(null);
                    interiorvolanteCollectionOldInteriorvolante = em.merge(interiorvolanteCollectionOldInteriorvolante);
                }
            }
            for (Interiorvolante interiorvolanteCollectionNewInteriorvolante : interiorvolanteCollectionNew) {
                if (!interiorvolanteCollectionOld.contains(interiorvolanteCollectionNewInteriorvolante)) {
                    Inventario oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante = interiorvolanteCollectionNewInteriorvolante.getIdInventario();
                    interiorvolanteCollectionNewInteriorvolante.setIdInventario(inventario);
                    interiorvolanteCollectionNewInteriorvolante = em.merge(interiorvolanteCollectionNewInteriorvolante);
                    if (oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante != null && !oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante.equals(inventario)) {
                        oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante.getInteriorvolanteCollection().remove(interiorvolanteCollectionNewInteriorvolante);
                        oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante = em.merge(oldIdInventarioOfInteriorvolanteCollectionNewInteriorvolante);
                    }
                }
            }
            for (Procesodos procesodosCollectionOldProcesodos : procesodosCollectionOld) {
                if (!procesodosCollectionNew.contains(procesodosCollectionOldProcesodos)) {
                    procesodosCollectionOldProcesodos.setIdInventario(null);
                    procesodosCollectionOldProcesodos = em.merge(procesodosCollectionOldProcesodos);
                }
            }
            for (Procesodos procesodosCollectionNewProcesodos : procesodosCollectionNew) {
                if (!procesodosCollectionOld.contains(procesodosCollectionNewProcesodos)) {
                    Inventario oldIdInventarioOfProcesodosCollectionNewProcesodos = procesodosCollectionNewProcesodos.getIdInventario();
                    procesodosCollectionNewProcesodos.setIdInventario(inventario);
                    procesodosCollectionNewProcesodos = em.merge(procesodosCollectionNewProcesodos);
                    if (oldIdInventarioOfProcesodosCollectionNewProcesodos != null && !oldIdInventarioOfProcesodosCollectionNewProcesodos.equals(inventario)) {
                        oldIdInventarioOfProcesodosCollectionNewProcesodos.getProcesodosCollection().remove(procesodosCollectionNewProcesodos);
                        oldIdInventarioOfProcesodosCollectionNewProcesodos = em.merge(oldIdInventarioOfProcesodosCollectionNewProcesodos);
                    }
                }
            }
            for (Procesocinco procesocincoCollectionOldProcesocinco : procesocincoCollectionOld) {
                if (!procesocincoCollectionNew.contains(procesocincoCollectionOldProcesocinco)) {
                    procesocincoCollectionOldProcesocinco.setIdInventario(null);
                    procesocincoCollectionOldProcesocinco = em.merge(procesocincoCollectionOldProcesocinco);
                }
            }
            for (Procesocinco procesocincoCollectionNewProcesocinco : procesocincoCollectionNew) {
                if (!procesocincoCollectionOld.contains(procesocincoCollectionNewProcesocinco)) {
                    Inventario oldIdInventarioOfProcesocincoCollectionNewProcesocinco = procesocincoCollectionNewProcesocinco.getIdInventario();
                    procesocincoCollectionNewProcesocinco.setIdInventario(inventario);
                    procesocincoCollectionNewProcesocinco = em.merge(procesocincoCollectionNewProcesocinco);
                    if (oldIdInventarioOfProcesocincoCollectionNewProcesocinco != null && !oldIdInventarioOfProcesocincoCollectionNewProcesocinco.equals(inventario)) {
                        oldIdInventarioOfProcesocincoCollectionNewProcesocinco.getProcesocincoCollection().remove(procesocincoCollectionNewProcesocinco);
                        oldIdInventarioOfProcesocincoCollectionNewProcesocinco = em.merge(oldIdInventarioOfProcesocincoCollectionNewProcesocinco);
                    }
                }
            }
            for (Interiorfreno interiorfrenoCollectionOldInteriorfreno : interiorfrenoCollectionOld) {
                if (!interiorfrenoCollectionNew.contains(interiorfrenoCollectionOldInteriorfreno)) {
                    interiorfrenoCollectionOldInteriorfreno.setIdInventario(null);
                    interiorfrenoCollectionOldInteriorfreno = em.merge(interiorfrenoCollectionOldInteriorfreno);
                }
            }
            for (Interiorfreno interiorfrenoCollectionNewInteriorfreno : interiorfrenoCollectionNew) {
                if (!interiorfrenoCollectionOld.contains(interiorfrenoCollectionNewInteriorfreno)) {
                    Inventario oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno = interiorfrenoCollectionNewInteriorfreno.getIdInventario();
                    interiorfrenoCollectionNewInteriorfreno.setIdInventario(inventario);
                    interiorfrenoCollectionNewInteriorfreno = em.merge(interiorfrenoCollectionNewInteriorfreno);
                    if (oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno != null && !oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno.equals(inventario)) {
                        oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno.getInteriorfrenoCollection().remove(interiorfrenoCollectionNewInteriorfreno);
                        oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno = em.merge(oldIdInventarioOfInteriorfrenoCollectionNewInteriorfreno);
                    }
                }
            }
            for (Procesotres procesotresCollectionOldProcesotres : procesotresCollectionOld) {
                if (!procesotresCollectionNew.contains(procesotresCollectionOldProcesotres)) {
                    procesotresCollectionOldProcesotres.setIdInventario(null);
                    procesotresCollectionOldProcesotres = em.merge(procesotresCollectionOldProcesotres);
                }
            }
            for (Procesotres procesotresCollectionNewProcesotres : procesotresCollectionNew) {
                if (!procesotresCollectionOld.contains(procesotresCollectionNewProcesotres)) {
                    Inventario oldIdInventarioOfProcesotresCollectionNewProcesotres = procesotresCollectionNewProcesotres.getIdInventario();
                    procesotresCollectionNewProcesotres.setIdInventario(inventario);
                    procesotresCollectionNewProcesotres = em.merge(procesotresCollectionNewProcesotres);
                    if (oldIdInventarioOfProcesotresCollectionNewProcesotres != null && !oldIdInventarioOfProcesotresCollectionNewProcesotres.equals(inventario)) {
                        oldIdInventarioOfProcesotresCollectionNewProcesotres.getProcesotresCollection().remove(procesotresCollectionNewProcesotres);
                        oldIdInventarioOfProcesotresCollectionNewProcesotres = em.merge(oldIdInventarioOfProcesotresCollectionNewProcesotres);
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
                Short id = inventario.getId();
                if (findInventario(id) == null) {
                    throw new NonexistentEntityException("The inventario with id " + id + " no longer exists.");
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
            Inventario inventario;
            try {
                inventario = em.getReference(Inventario.class, id);
                inventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventario with id " + id + " no longer exists.", enfe);
            }
            Pieza idPieza = inventario.getIdPieza();
            if (idPieza != null) {
                idPieza.getInventarioCollection().remove(inventario);
                idPieza = em.merge(idPieza);
            }
            Collection<Procesouno> procesounoCollection = inventario.getProcesounoCollection();
            for (Procesouno procesounoCollectionProcesouno : procesounoCollection) {
                procesounoCollectionProcesouno.setIdInventario(null);
                procesounoCollectionProcesouno = em.merge(procesounoCollectionProcesouno);
            }
            Collection<Procesosiete> procesosieteCollection = inventario.getProcesosieteCollection();
            for (Procesosiete procesosieteCollectionProcesosiete : procesosieteCollection) {
                procesosieteCollectionProcesosiete.setIdInventario(null);
                procesosieteCollectionProcesosiete = em.merge(procesosieteCollectionProcesosiete);
            }
            Collection<Procesocuatro> procesocuatroCollection = inventario.getProcesocuatroCollection();
            for (Procesocuatro procesocuatroCollectionProcesocuatro : procesocuatroCollection) {
                procesocuatroCollectionProcesocuatro.setIdInventario(null);
                procesocuatroCollectionProcesocuatro = em.merge(procesocuatroCollectionProcesocuatro);
            }
            Collection<Interiorasiento> interiorasientoCollection = inventario.getInteriorasientoCollection();
            for (Interiorasiento interiorasientoCollectionInteriorasiento : interiorasientoCollection) {
                interiorasientoCollectionInteriorasiento.setIdInventario(null);
                interiorasientoCollectionInteriorasiento = em.merge(interiorasientoCollectionInteriorasiento);
            }
            Collection<Interiorvolante> interiorvolanteCollection = inventario.getInteriorvolanteCollection();
            for (Interiorvolante interiorvolanteCollectionInteriorvolante : interiorvolanteCollection) {
                interiorvolanteCollectionInteriorvolante.setIdInventario(null);
                interiorvolanteCollectionInteriorvolante = em.merge(interiorvolanteCollectionInteriorvolante);
            }
            Collection<Procesodos> procesodosCollection = inventario.getProcesodosCollection();
            for (Procesodos procesodosCollectionProcesodos : procesodosCollection) {
                procesodosCollectionProcesodos.setIdInventario(null);
                procesodosCollectionProcesodos = em.merge(procesodosCollectionProcesodos);
            }
            Collection<Procesocinco> procesocincoCollection = inventario.getProcesocincoCollection();
            for (Procesocinco procesocincoCollectionProcesocinco : procesocincoCollection) {
                procesocincoCollectionProcesocinco.setIdInventario(null);
                procesocincoCollectionProcesocinco = em.merge(procesocincoCollectionProcesocinco);
            }
            Collection<Interiorfreno> interiorfrenoCollection = inventario.getInteriorfrenoCollection();
            for (Interiorfreno interiorfrenoCollectionInteriorfreno : interiorfrenoCollection) {
                interiorfrenoCollectionInteriorfreno.setIdInventario(null);
                interiorfrenoCollectionInteriorfreno = em.merge(interiorfrenoCollectionInteriorfreno);
            }
            Collection<Procesotres> procesotresCollection = inventario.getProcesotresCollection();
            for (Procesotres procesotresCollectionProcesotres : procesotresCollection) {
                procesotresCollectionProcesotres.setIdInventario(null);
                procesotresCollectionProcesotres = em.merge(procesotresCollectionProcesotres);
            }
            em.remove(inventario);
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

    public List<Inventario> findInventarioEntities() {
        return findInventarioEntities(true, -1, -1);
    }

    public List<Inventario> findInventarioEntities(int maxResults, int firstResult) {
        return findInventarioEntities(false, maxResults, firstResult);
    }

    private List<Inventario> findInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inventario.class));
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

    public Inventario findInventario(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inventario> rt = cq.from(Inventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
