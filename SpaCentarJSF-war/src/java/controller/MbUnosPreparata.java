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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.KompanijaFacade;
import model.PreparatFacade;

/**
 *
 * @author Megi
 */
@ManagedBean
@RequestScoped
public class MbUnosPreparata {

    @EJB
    private KompanijaFacade kompanijaFacade;
    @EJB
    private PreparatFacade preparatFacade;

    private static Preparat preparat;

    private List<Kompanija> listaProizvodjaca;

    public MbUnosPreparata() {
        if (preparat != null) {
            System.out.println(preparat);
        } else {
            preparat = new Preparat();
        }
    }

    @PostConstruct
    public void inicijalizujPodatke() {

        listaProizvodjaca = kompanijaFacade.findAll();
        if (listaProizvodjaca == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da pronađe nijednu kompaniju.", ""));
        }
    }

    public String sacuvajPreparat() {

        try {
            System.out.println("Preparat:" + preparat.getNaziv());
            preparatFacade.create(preparat);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je zapamtio uneti preparat.", "Preparat je sacuvan u bazi podataka"));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti novi preparat.", ex.getMessage()));

        }
        return "unosPreparata";
    }

    public List<Kompanija> getListaProizvodjaca() {
        return listaProizvodjaca;
    }

    public void setListaProizvodjaca(List<Kompanija> listaProizvodjaca) {
        this.listaProizvodjaca = listaProizvodjaca;
    }

    public Preparat getPreparat() {
        return preparat;
    }

    public void setPreparat(Preparat preparat2) {
        preparat = preparat2;
    }

}
