/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Klijent;
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
public class KlijentFacade extends AbstractFacade<Klijent> {

    @PersistenceContext(unitName = "SpaCentarJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KlijentFacade() {
        super(Klijent.class);
    }

    @Override
    public Object findByProperty(String[] params) {
        Query query = em.createQuery("FROM Klijent k where k.korisnickoIme = :value1 and k.korisnickaSifra= :value2");
        query.setParameter("value1", params[0]);
        query.setParameter("value2", params[1]);
        List<Klijent> results = query.getResultList();
        if (results.size() == 1) {
            return results.get(0);
        } else {
            return null;
        }
    }

}
