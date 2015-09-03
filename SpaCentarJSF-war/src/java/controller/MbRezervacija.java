/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Raspored;
import entities.Rezervacija;
import entities.Tretman;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.RasporedFacade;
import model.RezervacijaFacade;
import model.TretmanFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Megi
 */
@ManagedBean
@RequestScoped
public class MbRezervacija {

    @EJB
    private RasporedFacade rasporedFacade;

    @EJB
    private TretmanFacade tretmanFacade;

    @EJB
    private RezervacijaFacade rezervacijaFacade;
    @ManagedProperty("#{mbKorisnik}")
    private MbKorisnik mbKorisnik;

    private Rezervacija rezervacija;

    private List<Raspored> listaRasporeda;
    private List<Rezervacija> filtriranaListaRezervacija;
    private List<Rezervacija> listaRezervacija;
    private Date datum;
    private boolean raspolozivost = true;

    /**
     * Creates a new instance of MbRezervacija
     */
    public MbRezervacija() {
    }

    @PostConstruct
    public void inicijalizujPodatke() {
        listaRasporeda = rasporedFacade.findAll();
        listaRezervacija = rezervacijaFacade.findAll();
        filtriranaListaRezervacija = rezervacijaFacade.findAll();
        rezervacija = new Rezervacija();

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
        if (listaRasporeda==null){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da pronađe nijedan tretman!", ""));
        }
        return listaRasporeda;
    }

    public void setListaRasporeda(List<Raspored> listaRasporeda) {
        this.listaRasporeda = listaRasporeda;
    }

    public boolean filtrirajPoDatumu(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (value == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        try {
            Date d = sdf.parse(((String) value).trim());
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            String sDatum = sdf2.format(d);
            if (sDatum.contains(filterText)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MbRezervacija.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public void sacuvajRezervaciju() throws Exception {
        if (rezervacija.getRaspored() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate izabrati zaposlenog i tretman koji želite!", ""));
        } else {
            if (rezervacija.getVreme() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate izabrati datum tretmana!", ""));
            } else {
                if (mbKorisnik.getKorisnik() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste ulogovani!", ""));
                } else {
                    rezervacija.setKlijentID(mbKorisnik.getKorisnik());
                    int[] params = new int[2];
                    params[0] = rezervacija.getRaspored().getTretman().getTretmanID();
                    params[1] = rezervacija.getRaspored().getZaposleni().getZaposleniID();

                    List<Rezervacija> rezervacije = rezervacijaFacade.findByProperty(params);

                    Raspored raspored = rasporedFacade.findByProperty(params);
                    int brTermina = raspored.getBrojTermina();

                    if (rezervacije != null) {
                        if (rezervacije.size() >= brTermina) {
                            raspolozivost = false;
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zaposleni koga ste izabrali nema slobodnih termina!", ""));
                            //throw new Exception("Zaposleni koga ste izabrali nema slobodnih termina!");
                        }
                    }

                    List<Rezervacija> rezervacijeZaposlenog = rezervacijaFacade.findByProperty(params[1]);

                    if (rezervacijeZaposlenog != null) {
                        for (Rezervacija rezervacija1 : rezervacijeZaposlenog) {
                            //vreme zavrsetka tretmana
                            Tretman tretman = tretmanFacade.findByProperty(rezervacija.getRaspored().getTretman().getTretmanID());

                            Calendar vremePocetka = new GregorianCalendar();
                            vremePocetka.setTime(rezervacija1.getVreme());

                            Calendar vremeZavrsetka = new GregorianCalendar();
                            vremeZavrsetka.setTime(rezervacija1.getVreme());
                            vremeZavrsetka.add(Calendar.MINUTE, tretman.getTrajanjeUMin());

                            //trazeno vreme
                            Calendar trazenoVreme = new GregorianCalendar();
                            trazenoVreme.setTime(rezervacija.getVreme());

                            Calendar trazenoVremeZavrsetka = new GregorianCalendar();
                            trazenoVremeZavrsetka.setTime(rezervacija.getVreme());
                            trazenoVremeZavrsetka.add(Calendar.MINUTE, tretman.getTrajanjeUMin());

                            if (!(trazenoVreme.after(vremeZavrsetka) || trazenoVremeZavrsetka.before(vremePocetka))) {
                                raspolozivost = false;
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem ne može da zapamti rezervaciju", ""));
                                //throw new Exception("Sistem ne može da zapamti rezervaciju!");
                            }
                        }
                    }
                    if (raspolozivost) {
                        System.out.println("Ubacujem rezervaciju korisnika " + mbKorisnik.getKorisnik());
                        rezervacijaFacade.create(rezervacija);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je zapamtio rezervaciju!", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti rezervaciju!", ""));
                    }
                }
            }
        }
    }

    public TretmanFacade getTretmanFacade() {
        return tretmanFacade;
    }

    public void setTretmanFacade(TretmanFacade tretmanFacade) {
        this.tretmanFacade = tretmanFacade;
    }

    public Date getDate10() {
        return datum;
    }

    public void setDate10(Date date10) {
        this.datum = date10;
    }

    public List<Rezervacija> getListaRezervacija() {
        return listaRezervacija;
    }

    public void setListaRezervacija(List<Rezervacija> listaRezervacija) {
        this.listaRezervacija = listaRezervacija;
    }

    public MbKorisnik getMbKorisnik() {
        return mbKorisnik;
    }

    public void setMbKorisnik(MbKorisnik mbKorisnik) {
        this.mbKorisnik = mbKorisnik;
    }

    public List<Rezervacija> getFiltriranaListaRezervacija() {
        return filtriranaListaRezervacija;
    }

    public void setFiltriranaListaRezervacija(List<Rezervacija> filtriranaListaRezervacija) {
        this.filtriranaListaRezervacija = filtriranaListaRezervacija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
