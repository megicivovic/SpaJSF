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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
            korisnik = ulogovaniKorisnik;
           //  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("auth", korisnik);

            if (ulogovaniKorisnik.getKorisnickoIme().equals("admin")) {
                return "index?faces-redirect=true";
            } else {
                return "unosRezervacije";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neispravno korisničko ime ili šifra!", ""));
            return "login";
        }
    }

  
}
