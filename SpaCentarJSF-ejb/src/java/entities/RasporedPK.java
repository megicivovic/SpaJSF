/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Megi
 */
@Embeddable
public class RasporedPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "zaposleniID")
    private int zaposleniID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tretmanID")
    private int tretmanID;

    public RasporedPK() {
    }

    public RasporedPK(int zaposleniID, int tretmanID) {
        this.zaposleniID = zaposleniID;
        this.tretmanID = tretmanID;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public int getTretmanID() {
        return tretmanID;
    }

    public void setTretmanID(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) zaposleniID;
        hash += (int) tretmanID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RasporedPK)) {
            return false;
        }
        RasporedPK other = (RasporedPK) object;
        if (this.zaposleniID != other.zaposleniID) {
            return false;
        }
        if (this.tretmanID != other.tretmanID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RasporedPK[ zaposleniID=" + zaposleniID + ", tretmanID=" + tretmanID + " ]";
    }
    
}
