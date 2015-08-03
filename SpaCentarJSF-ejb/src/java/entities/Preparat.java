/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "preparat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preparat.findAll", query = "SELECT p FROM Preparat p"),
    @NamedQuery(name = "Preparat.findByPreparatID", query = "SELECT p FROM Preparat p WHERE p.preparatID = :preparatID"),
    @NamedQuery(name = "Preparat.findByNaziv", query = "SELECT p FROM Preparat p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Preparat.findByCena", query = "SELECT p FROM Preparat p WHERE p.cena = :cena")})
public class Preparat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "preparatID")
    private Integer preparatID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private double cena;
    @ManyToMany(mappedBy = "preparatList")
    private List<Tretman> tretmanList;
    @JoinColumn(name = "proizvodjac", referencedColumnName = "kompanijaID")
    @ManyToOne(optional = false)
    private Kompanija proizvodjac;

    public Preparat() {
    }

    public Preparat(Integer preparatID) {
        this.preparatID = preparatID;
    }

    public Preparat(Integer preparatID, String naziv, double cena) {
        this.preparatID = preparatID;
        this.naziv = naziv;
        this.cena = cena;
    }

    public Integer getPreparatID() {
        return preparatID;
    }

    public void setPreparatID(Integer preparatID) {
        this.preparatID = preparatID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @XmlTransient
    public List<Tretman> getTretmanList() {
        return tretmanList;
    }

    public void setTretmanList(List<Tretman> tretmanList) {
        this.tretmanList = tretmanList;
    }

    public Kompanija getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Kompanija proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preparatID != null ? preparatID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preparat)) {
            return false;
        }
        Preparat other = (Preparat) object;
        if ((this.preparatID == null && other.preparatID != null) || (this.preparatID != null && !this.preparatID.equals(other.preparatID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Preparat[ preparatID=" + preparatID + " ]";
    }
    
}
