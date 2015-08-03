/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Megi
 */
@Entity
@Table(name = "klijent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klijent.findAll", query = "SELECT k FROM Klijent k"),
    @NamedQuery(name = "Klijent.findByKlijentID", query = "SELECT k FROM Klijent k WHERE k.klijentID = :klijentID"),
    @NamedQuery(name = "Klijent.findByImePrezime", query = "SELECT k FROM Klijent k WHERE k.imePrezime = :imePrezime"),
    @NamedQuery(name = "Klijent.findByKorisnickoIme", query = "SELECT k FROM Klijent k WHERE k.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Klijent.findByKorisnickaSifra", query = "SELECT k FROM Klijent k WHERE k.korisnickaSifra = :korisnickaSifra")})
public class Klijent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "klijentID")
    private Integer klijentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "imePrezime")
    private String imePrezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "korisnickaSifra")
    private String korisnickaSifra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klijentID")
    private List<Rezervacija> rezervacijaList;

    public Klijent() {
    }

    public Klijent(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public Klijent(Integer klijentID, String imePrezime, String korisnickoIme, String korisnickaSifra) {
        this.klijentID = klijentID;
        this.imePrezime = imePrezime;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
    }

    public Integer getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
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
        hash += (klijentID != null ? klijentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klijent)) {
            return false;
        }
        Klijent other = (Klijent) object;
        if ((this.klijentID == null && other.klijentID != null) || (this.klijentID != null && !this.klijentID.equals(other.klijentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Klijent[ klijentID=" + klijentID + " ]";
    }
    
}
