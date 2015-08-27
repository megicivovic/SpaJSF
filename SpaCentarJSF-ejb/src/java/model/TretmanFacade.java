/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Rezervacija;
import entities.Tretman;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Megi
 */
@Stateless
public class TretmanFacade extends AbstractFacade<Tretman> {
    @PersistenceContext(unitName = "SpaCentarJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TretmanFacade() {
        super(Tretman.class);
    }

    public Tretman findByProperty(int param) {
         Query query = em.createQuery("FROM Tretman t where t.tretmanID= :value1");
        query.setParameter("value1", param);       
        List<Tretman> results = query.getResultList();
        if (results.size()==1) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    
}
