/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Klijent;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.KlijentFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@SessionScoped
public class MbKorisnik implements Serializable {

    @EJB
    private KlijentFacade klijentFacade;
    private Klijent korisnik;
    public boolean isLogged=false;

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
            korisnik = ulogovaniKorisnik;
           isLogged=true;

            if (ulogovaniKorisnik.getKorisnickoIme().equals("admin")) {
                return "index.xhtml?faces-redirect=true";
            } else {
                return "unosRezervacije.xhtml?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravno korisničko ime ili šifra!", ""));
            return "login.xhtml?faces-redirect=true";
        }
    }

}
