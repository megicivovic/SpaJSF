/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Klijent;
import entities.Tretman;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.KlijentFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@SessionScoped
public class MbRegistracija {

    @EJB
    private KlijentFacade klijentFacade;
    private Klijent korisnik;
    private String poruka;

    public MbRegistracija() {
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        korisnik = new Klijent();
    }

    public KlijentFacade getKlijentFacade() {
        return klijentFacade;
    }

    public void setKlijentFacade(KlijentFacade klijentFacade) {
        this.klijentFacade = klijentFacade;
    }

    public Klijent getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Klijent korisnik) {
        this.korisnik = korisnik;
    }

    
    public String registrujSe() {

        Klijent ulogovaniKorisnik = (Klijent) klijentFacade.findByUsername(korisnik.getKorisnickoIme());
        if (ulogovaniKorisnik != null) {
            poruka="Neuspešna registracija!";
            return "registracija";
        } else {
            klijentFacade.create(korisnik);
            poruka="";
            return "login?faces-redirect=true";
        }

    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

}
