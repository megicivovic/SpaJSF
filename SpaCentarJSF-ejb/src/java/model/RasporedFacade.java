/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Raspored;
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
public class RasporedFacade extends AbstractFacade<Raspored> {
    @PersistenceContext(unitName = "SpaCentarJSF-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RasporedFacade() {
        super(Raspored.class);
    }

    public Raspored findByProperty(int[] params) {
      Query query = em.createQuery("FROM Raspored r where r.tretman.tretmanID = :value1 and r.zaposleni.zaposleniID= :value2");
        query.setParameter("value1", params[0]);
        query.setParameter("value2", params[1]);
        List<Raspored> results = query.getResultList();
        if (results.size()==1) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    
}
