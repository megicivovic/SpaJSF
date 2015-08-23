/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Kompanija;
import entities.Preparat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.KompanijaFacade;
import model.PreparatFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@ViewScoped
public class MbPreparat {

    @EJB
    private KompanijaFacade kompanijaFacade;

    @EJB
    private PreparatFacade preparatFacade;

    private static boolean izmena = false;
    private Preparat preparat;
    private Preparat izabraniPreparat;
    private Preparat preparatZaBrisanje;
    List<Kompanija> listaProizvodjaca;
    List<Preparat> listaPreparata;

    public MbPreparat() {
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaProizvodjaca = kompanijaFacade.findAll();
        listaPreparata = preparatFacade.findAll();
    }

    public List<Preparat> getListaPreparata() {
        return listaPreparata;
    }

    public void setListaPreparata(List<Preparat> listaPreparata) {
        this.listaPreparata = listaPreparata;
    }

    public List<Kompanija> getListaProizvodjaca() {
        return listaProizvodjaca;
    }

    public void setListaProizvodjaca(List<Kompanija> listaProizvodjaca) {
        this.listaProizvodjaca = listaProizvodjaca;
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        MbPreparat.izmena = izmena;
    }

    public Preparat getPreparat() {
        return preparat;
    }

    public void setPreparat(Preparat preparat) {
        this.preparat = preparat;
    }

    public Preparat getIzabraniPreparat() {
        return izabraniPreparat;
    }

    public void setIzabraniPreparat(Preparat izabraniPreparat) {
        System.out.println("Uradio selekciju:" + izabraniPreparat.getNaziv());
        this.izabraniPreparat = izabraniPreparat;
    }

    
    
    
    
    public String sacuvajPreparat() {

        try {
            System.out.println("Preparat:" + preparat.getNaziv());
            preparatFacade.create(preparat);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspesno je sacuvan preparat!!!", "Preparat je sacuvan u bazi podataka"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preparat nije uspesno sacuvan!!!", ex.getMessage()));
        }
        return null;

    }

    public Preparat getPreparatZaBrisanje() {
        return preparatZaBrisanje;
    }

    public void setPreparatZaBrisanje(Preparat preparatZaBrisanje) {
        this.preparatZaBrisanje = preparatZaBrisanje;
    }

    

}
