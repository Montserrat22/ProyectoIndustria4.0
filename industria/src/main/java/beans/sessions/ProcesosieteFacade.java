/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.sessions;

import entidades.Procesosiete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaker
 */
@Stateless
public class ProcesosieteFacade extends AbstractFacade<Procesosiete> {

    @PersistenceContext(unitName = "com.mycompany_industria_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesosieteFacade() {
        super(Procesosiete.class);
    }
    
}
