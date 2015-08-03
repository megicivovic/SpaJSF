/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Megi
 */
@Entity
@Table(name = "raspored")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Raspored.findAll", query = "SELECT r FROM Raspored r"),
    @NamedQuery(name = "Raspored.findByZaposleniID", query = "SELECT r FROM Raspored r WHERE r.rasporedPK.zaposleniID = :zaposleniID"),
    @NamedQuery(name = "Raspored.findByTretmanID", query = "SELECT r FROM Raspored r WHERE r.rasporedPK.tretmanID = :tretmanID"),
    @NamedQuery(name = "Raspored.findByBrojTermina", query = "SELECT r FROM Raspored r WHERE r.brojTermina = :brojTermina")})
public class Raspored implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RasporedPK rasporedPK;
    @Column(name = "brojTermina")
    private Integer brojTermina;
    @JoinColumn(name = "zaposleniID", referencedColumnName = "zaposleniID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Zaposleni zaposleni;
    @JoinColumn(name = "tretmanID", referencedColumnName = "tretmanID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tretman tretman;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raspored")
    private List<Rezervacija> rezervacijaList;

    public Raspored() {
    }

    public Raspored(RasporedPK rasporedPK) {
        this.rasporedPK = rasporedPK;
    }

    public Raspored(int zaposleniID, int tretmanID) {
        this.rasporedPK = new RasporedPK(zaposleniID, tretmanID);
    }

    public RasporedPK getRasporedPK() {
        return rasporedPK;
    }

    public void setRasporedPK(RasporedPK rasporedPK) {
        this.rasporedPK = rasporedPK;
    }

    public Integer getBrojTermina() {
        return brojTermina;
    }

    public void setBrojTermina(Integer brojTermina) {
        this.brojTermina = brojTermina;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Tretman getTretman() {
        return tretman;
    }

    public void setTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    @XmlTransient
    public List<Rezervacija> getRezervacijaList() {
        return rezervacijaList;
    }

    public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
        this.rezervacijaList = rezervacijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rasporedPK != null ? rasporedPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Raspored)) {
            return false;
        }
        Raspored other = (Raspored) object;
        if ((this.rasporedPK == null && other.rasporedPK != null) || (this.rasporedPK != null && !this.rasporedPK.equals(other.rasporedPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Raspored[ rasporedPK=" + rasporedPK + " ]";
    }
    
}
