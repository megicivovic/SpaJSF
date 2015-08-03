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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Megi
 */
@Entity
@Table(name = "kompanija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kompanija.findAll", query = "SELECT k FROM Kompanija k"),
    @NamedQuery(name = "Kompanija.findByKompanijaID", query = "SELECT k FROM Kompanija k WHERE k.kompanijaID = :kompanijaID"),
    @NamedQuery(name = "Kompanija.findByPib", query = "SELECT k FROM Kompanija k WHERE k.pib = :pib"),
    @NamedQuery(name = "Kompanija.findByMaticniBroj", query = "SELECT k FROM Kompanija k WHERE k.maticniBroj = :maticniBroj"),
    @NamedQuery(name = "Kompanija.findByNaziv", query = "SELECT k FROM Kompanija k WHERE k.naziv = :naziv"),
    @NamedQuery(name = "Kompanija.findByZiroRacun", query = "SELECT k FROM Kompanija k WHERE k.ziroRacun = :ziroRacun"),
    @NamedQuery(name = "Kompanija.findByDatumOsnivanja", query = "SELECT k FROM Kompanija k WHERE k.datumOsnivanja = :datumOsnivanja"),
    @NamedQuery(name = "Kompanija.findByAdresa", query = "SELECT k FROM Kompanija k WHERE k.adresa = :adresa")})
public class Kompanija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kompanijaID")
    private Integer kompanijaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "pib")
    private String pib;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "maticniBroj")
    private String maticniBroj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ziroRacun")
    private String ziroRacun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumOsnivanja")
    @Temporal(TemporalType.DATE)
    private Date datumOsnivanja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adresa")
    private String adresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proizvodjac")
    private List<Preparat> preparatList;

    public Kompanija() {
    }

    public Kompanija(Integer kompanijaID) {
        this.kompanijaID = kompanijaID;
    }

    public Kompanija(Integer kompanijaID, String pib, String maticniBroj, String naziv, String ziroRacun, Date datumOsnivanja, String adresa) {
        this.kompanijaID = kompanijaID;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.ziroRacun = ziroRacun;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
    }

    public Integer getKompanijaID() {
        return kompanijaID;
    }

    public void setKompanijaID(Integer kompanijaID) {
        this.kompanijaID = kompanijaID;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZiroRacun() {
        return ziroRacun;
    }

    public void setZiroRacun(String ziroRacun) {
        this.ziroRacun = ziroRacun;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @XmlTransient
    public List<Preparat> getPreparatList() {
        return preparatList;
    }

    public void setPreparatList(List<Preparat> preparatList) {
        this.preparatList = preparatList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kompanijaID != null ? kompanijaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kompanija)) {
            return false;
        }
        Kompanija other = (Kompanija) object;
        if ((this.kompanijaID == null && other.kompanijaID != null) || (this.kompanijaID != null && !this.kompanijaID.equals(other.kompanijaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Kompanija[ kompanijaID=" + kompanijaID + " ]";
    }
    
}
