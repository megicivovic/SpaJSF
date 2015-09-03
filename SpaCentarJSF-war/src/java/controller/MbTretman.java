/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Preparat;
import entities.Tretman;
import java.io.Serializable;
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
public class MbTretman implements Serializable {

    @EJB
    private PreparatFacade preparatFacade;
    @EJB
    private TretmanFacade tretmanFacade;

    private static boolean izmena = false;

    private static Tretman trenutniTretman;

    @ManagedProperty(value = "#{mbPreparat}")
    private MbPreparat mbPreparat;

    public MbTretman() {

    }

    @PostConstruct
    public void inicijalizujPodatke() {
        if (!izmena) {
            trenutniTretman = mbPreparat.getTretman();
        }
    }

    public List<Tretman> findAll() {
        if (this.tretmanFacade.findAll() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da pronađe nijedan tretman", ""));
        }
        return this.tretmanFacade.findAll();

    }

    public String pokreniIzmenu(Tretman tretman) {

        setTrenutniTretman(tretman);
        izmena = true;

        return "unosTretmana";

    }

    public String sacuvajTretman() {

        try {
            double cena = 0;
            if (trenutniTretman.getPreparatList().size() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da kreira novi tretman: " + trenutniTretman.getOpis(), ""));

            } else {
                for (Preparat preparat : trenutniTretman.getPreparatList()) {
                    cena += preparat.getCena();
                }
                trenutniTretman.setCena(cena);
                if (izmena) {
                    tretmanFacade.edit(trenutniTretman);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tretman:" + trenutniTretman.getOpis() + " je uspešno izmenjen", ""));
                   
                    System.out.println("Tretman:" + trenutniTretman.getOpis() + " je uspesno izmenjen");
                    izmena = false;

                } else {

                    tretmanFacade.create(trenutniTretman);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tretman: " + trenutniTretman.getOpis() + " je uspešno kreiran", ""));
                   
                    System.out.println("Tretman:" + trenutniTretman.getOpis() + " je uspesno kreiran");

                }

            }

        } catch (Exception ex) {
            if (!izmena) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da kreira novi tretman. " + trenutniTretman.getOpis(), ex.getMessage()));
            } else {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nije moguće sačuvati podatke o tretmanu", ex.getMessage()));
            }
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
