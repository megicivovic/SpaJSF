/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Megi
 */
@Entity
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z"),
    @NamedQuery(name = "Zaposleni.findByZaposleniID", query = "SELECT z FROM Zaposleni z WHERE z.zaposleniID = :zaposleniID"),
    @NamedQuery(name = "Zaposleni.findByImePrezime", query = "SELECT z FROM Zaposleni z WHERE z.imePrezime = :imePrezime"),
    @NamedQuery(name = "Zaposleni.findByStepenSS", query = "SELECT z FROM Zaposleni z WHERE z.stepenSS = :stepenSS"),
    @NamedQuery(name = "Zaposleni.findByDatumRodjenja", query = "SELECT z FROM Zaposleni z WHERE z.datumRodjenja = :datumRodjenja")})
public class Zaposleni implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zaposleniID")
    private Integer zaposleniID;
    @Size(max = 50)
    @Column(name = "imePrezime")
    private String imePrezime;
    @Size(max = 2)
    @Column(name = "stepenSS")
    private String stepenSS;
    @Column(name = "datumRodjenja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumRodjenja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zaposleni")
    private List<Raspored> rasporedList;

    public Zaposleni() {
    }

    public Zaposleni(Integer zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public Integer getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Integer zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getStepenSS() {
        return stepenSS;
    }

    public void setStepenSS(String stepenSS) {
        this.stepenSS = stepenSS;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @XmlTransient
    public List<Raspored> getRasporedList() {
        return rasporedList;
    }

    public void setRasporedList(List<Raspored> rasporedList) {
        this.rasporedList = rasporedList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zaposleniID != null ? zaposleniID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.zaposleniID == null && other.zaposleniID != null) || (this.zaposleniID != null && !this.zaposleniID.equals(other.zaposleniID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Zaposleni[ zaposleniID=" + zaposleniID + " ]";
    }
    
}
