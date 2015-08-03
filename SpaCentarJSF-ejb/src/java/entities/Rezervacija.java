/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Megi
 */
@Entity
@Table(name = "rezervacija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r"),
    @NamedQuery(name = "Rezervacija.findByRezervacijaID", query = "SELECT r FROM Rezervacija r WHERE r.rezervacijaID = :rezervacijaID"),
    @NamedQuery(name = "Rezervacija.findByVreme", query = "SELECT r FROM Rezervacija r WHERE r.vreme = :vreme")})
public class Rezervacija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rezervacijaID")
    private Integer rezervacijaID;
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @JoinColumns({
        @JoinColumn(name = "zaposleniID", referencedColumnName = "zaposleniID"),
        @JoinColumn(name = "tretmanID", referencedColumnName = "tretmanID")})
    @ManyToOne(optional = false)
    private Raspored raspored;
      @JoinColumn(name = "klijentID", referencedColumnName = "klijentID")
    @ManyToOne(optional = false)
    private Klijent klijentID;

    public Rezervacija() {
    }

    public Rezervacija(Integer rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Integer getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(Integer rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public Raspored getRaspored() {
        return raspored;
    }

    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }

    
    public Klijent getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Klijent klijentID) {
        this.klijentID = klijentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rezervacijaID != null ? rezervacijaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.rezervacijaID == null && other.rezervacijaID != null) || (this.rezervacijaID != null && !this.rezervacijaID.equals(other.rezervacijaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rezervacija[ rezervacijaID=" + rezervacijaID + " ]";
    }
    
}
