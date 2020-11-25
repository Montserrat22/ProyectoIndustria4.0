/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.sessions;

import entidades.Procesocuatro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaker
 */
@Stateless
public class ProcesocuatroFacade extends AbstractFacade<Procesocuatro> {

    @PersistenceContext(unitName = "com.mycompany_industria_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesocuatroFacade() {
        super(Procesocuatro.class);
    }
    
}
