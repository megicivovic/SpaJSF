/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Raspored;
import entities.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.RasporedFacade;
import model.RezervacijaFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
     private Date date10;
     
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
    
   
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public Date getDate10() {
        return date10;
    }

    public void setDate10(Date date10) {
        this.date10 = date10;
    }
    
}
