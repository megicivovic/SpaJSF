/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Preparat;
import entities.Tretman;
import javax.faces.bean.ManagedBean;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.PreparatFacade;
import model.TretmanFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@ViewScoped
public class MbTretman {

    @EJB
    private PreparatFacade preparatFacade;
    @EJB
    private TretmanFacade tretmanFacade;

    private boolean izmena = false;
    private Tretman tretman;
    private Tretman trenutniTretman;

    private List<Preparat> listaPreparata;

    @ManagedProperty(value = "#{mbPreparat}")
    private MbPreparat mbPreparat;

    public MbTretman() {
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        trenutniTretman = new Tretman();
    }

    public List<Tretman> findAll() {

        return this.tretmanFacade.findAll();
    }

    public String pokreniIzmenu(Tretman tretman) {

        this.trenutniTretman = tretman;
        izmena = true;

        return "unosTretmana";

    }

    public String sacuvajTretman() {

        try {
            double cena = 0;
            for (Preparat preparat : trenutniTretman.getPreparatList()) {
                cena += preparat.getCena();
            }
            trenutniTretman.setCena(cena);
            if (izmena) {
                tretmanFacade.edit(trenutniTretman);
                System.out.println("Tretman:" + trenutniTretman.getOpis() + " je uspesno izmenjen");

            } else {

                tretmanFacade.create(trenutniTretman);
                System.out.println("Tretman:" + trenutniTretman.getOpis() + " je uspesno kreiran");

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno je sacuvan tretman!!!", "Tretman je sacuvan u bazi podataka"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tretman nije uspesno sacuvan!!!", ex.getMessage()));
        }
        return null;

    }

    public void dodajPreparat() {
        boolean dodaj = true;
        System.out.println("dodajem stavku:" + mbPreparat.getIzabraniPreparat());

        if (mbPreparat.getIzabraniPreparat() == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali preparat", ""));
            return;
        }

        List<Preparat> listaPreparata = trenutniTretman.getPreparatList();
        for (int i = 0; i < listaPreparata.size(); i++) {
            if (listaPreparata.get(i).equals(mbPreparat.getIzabraniPreparat())) {
                dodaj = false;
            }
        }
        if (dodaj) {
            listaPreparata.add(mbPreparat.getIzabraniPreparat());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno dodat preparat tretmanu", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tretman već sadrži preparat", ""));
        }

        //mbPreparat.setIzabraniPreparat(null);
    }

    public void ukloniPreparat() {
        System.out.println("brisem stavku:" + mbPreparat.getPreparatZaBrisanje());

        if (mbPreparat.getPreparatZaBrisanje() == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali preparat za brisanje", ""));
            return;
        }
        trenutniTretman.getPreparatList().remove(mbPreparat.getPreparatZaBrisanje());

    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public Tretman getTretman() {
        return tretman;
    }

    public void setTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    public List<Preparat> getListaPreparata() {
        return listaPreparata;
    }

    public void setListaPreparata(List<Preparat> listaPreparata) {
        this.listaPreparata = listaPreparata;
    }

    public Tretman getTrenutniTretman() {
        return trenutniTretman;
    }

    public void setTrenutniTretman(Tretman trenutniTretman) {
        this.trenutniTretman = trenutniTretman;
    }

    public MbPreparat getMbPreparat() {
        return mbPreparat;
    }

    public void setMbPreparat(MbPreparat mbPreparat) {
        this.mbPreparat = mbPreparat;
    }

}
