/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Klijent;
import entities.Rezervacija;
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
public class RezervacijaFacade extends AbstractFacade<Rezervacija> {
    @PersistenceContext(unitName = "SpaCentarJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RezervacijaFacade() {
        super(Rezervacija.class);
    }

  
    public List<Rezervacija> findByProperty(int[] params) {
         Query query = em.createQuery("FROM Rezervacija r where r.raspored.tretman.tretmanID = :value1 and r.raspored.zaposleni.zaposleniID= :value2");
        query.setParameter("value1", params[0]);
        query.setParameter("value2", params[1]);
        List<Rezervacija> results = query.getResultList();
        if (results.size()>0) {
            return results;
        } else {
            return null;
        }
        
    }
    public List<Rezervacija> findByProperty(int param) {
         Query query = em.createQuery("FROM Rezervacija r where r.zaposleniID= :value1");
        query.setParameter("value1", param);       
        List<Rezervacija> results = query.getResultList();
        if (results.size()>0) {
            return results;
        } else {
            return null;
        }
        
    }
    
}
