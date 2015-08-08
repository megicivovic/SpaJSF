/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Raspored;
import entities.Rezervacija;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.RasporedFacade;
import model.RezervacijaFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@ViewScoped
public class MbRezervacija {

    
    @EJB
    private RasporedFacade rasporedFacade;
    
    @EJB
    private RezervacijaFacade rezervacijaFacade;
    
    private Rezervacija rezervacija;
    private List<Raspored> listaRasporeda;
    /**
     * Creates a new instance of MbRezervacija
     */
    public MbRezervacija() {
    }

    public RasporedFacade getRasporedFacade() {
        return rasporedFacade;
    }

    public void setRasporedFacade(RasporedFacade rasporedFacade) {
        this.rasporedFacade = rasporedFacade;
    }

    public RezervacijaFacade getRezervacijaFacade() {
        return rezervacijaFacade;
    }

    public void setRezervacijaFacade(RezervacijaFacade rezervacijaFacade) {
        this.rezervacijaFacade = rezervacijaFacade;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public List<Raspored> getListaRasporeda() {
        return listaRasporeda;
    }

    public void setListaRasporeda(List<Raspored> listaRasporeda) {
        this.listaRasporeda = listaRasporeda;
    }
    
    
    
}
