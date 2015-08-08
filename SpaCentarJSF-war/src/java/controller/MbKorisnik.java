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
public class MbKorisnik {

    @EJB
    private KlijentFacade klijentFacade;
    private Klijent korisnik;

    public MbKorisnik() {
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

    public String ulogujSe() {
        String preusmerenje = null;
        String[] korisnickoIme = new String[2];
        korisnickoIme[0] = korisnik.getKorisnickoIme();
        korisnickoIme[1] = korisnik.getKorisnickaSifra();
        Klijent ulogovaniKorisnik = (Klijent) klijentFacade.findByProperty(korisnickoIme);
        if (ulogovaniKorisnik != null) {
            return "index?faces-redirect=true";
        }
        else return "login";
    }

}
