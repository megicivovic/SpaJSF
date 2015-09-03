/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Kompanija;
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
public class KompanijaFacade extends AbstractFacade<Kompanija> {
    @PersistenceContext(unitName = "SpaCentarJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KompanijaFacade() {
        super(Kompanija.class);
    }

    public Kompanija findByProperty(int id) {
        Query query = em.createQuery("FROM Kompanija k where k.kompanijaID = :value1");
        query.setParameter("value1", id);   
        List<Kompanija> results = query.getResultList();
        if (results.size()==1) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
}
