/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tretman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tretman.findAll", query = "SELECT t FROM Tretman t"),
    @NamedQuery(name = "Tretman.findByTretmanID", query = "SELECT t FROM Tretman t WHERE t.tretmanID = :tretmanID"),
    @NamedQuery(name = "Tretman.findByOpis", query = "SELECT t FROM Tretman t WHERE t.opis = :opis"),
    @NamedQuery(name = "Tretman.findByCena", query = "SELECT t FROM Tretman t WHERE t.cena = :cena"),
    @NamedQuery(name = "Tretman.findByTrajanjeUMin", query = "SELECT t FROM Tretman t WHERE t.trajanjeUMin = :trajanjeUMin")})
public class Tretman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tretmanID")
    private Integer tretmanID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private double cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanjeUMin")
    private int trajanjeUMin;
    @JoinTable(name = "tretmanpreparati", joinColumns = {
        @JoinColumn(name = "tretmanID", referencedColumnName = "tretmanID")}, inverseJoinColumns = {
        @JoinColumn(name = "preparatID", referencedColumnName = "preparatID")})
    @ManyToMany
    private List<Preparat> preparatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tretman")
    private List<Raspored> rasporedList;

    public Tretman() {
        preparatList = new LinkedList<>();
        rasporedList = new LinkedList<>();
    }

    public Tretman(Integer tretmanID) {
        this.tretmanID = tretmanID;
    }

    public Tretman(Integer tretmanID, String opis, double cena, int trajanjeUMin) {
        this.tretmanID = tretmanID;
        this.opis = opis;
        this.cena = cena;
        this.trajanjeUMin = trajanjeUMin;
    }

    public Integer getTretmanID() {
        return tretmanID;
    }

    public void setTretmanID(Integer tretmanID) {
        this.tretmanID = tretmanID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getTrajanjeUMin() {
        return trajanjeUMin;
    }

    public void setTrajanjeUMin(int trajanjeUMin) {
        this.trajanjeUMin = trajanjeUMin;
    }

    @XmlTransient
    public List<Preparat> getPreparatList() {
        return preparatList;
    }

    public void setPreparatList(List<Preparat> preparatList) {
        this.preparatList = preparatList;
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
        hash += (tretmanID != null ? tretmanID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tretman)) {
            return false;
        }
        Tretman other = (Tretman) object;
        if ((this.tretmanID == null && other.tretmanID != null) || (this.tretmanID != null && !this.tretmanID.equals(other.tretmanID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tretman[ tretmanID=" + tretmanID + " ]";
    }

}
