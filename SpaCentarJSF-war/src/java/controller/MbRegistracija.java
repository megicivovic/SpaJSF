/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Klijent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

        if (korisnik.getKorisnickaSifra().length() < 1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti lozinku!", ""));

            return "registracija";
        }

        Klijent ulogovaniKorisnik = (Klijent) klijentFacade.findByUsername(korisnik.getKorisnickoIme());
        if (ulogovaniKorisnik != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnik sa unetim korisničkim imenom već postoji!", ""));
            return "registracija";
        } else {
            klijentFacade.create(korisnik);
            return "login?faces-redirect=true&registracija=uspesna";
        }

    }

}
