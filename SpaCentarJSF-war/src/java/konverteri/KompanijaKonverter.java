/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import entities.Kompanija;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.KompanijaFacade;

/**
 *
 * @author student1
 */
@FacesConverter(value = "kompanijaKNV")
public class KompanijaKonverter implements Converter {

    @EJB
    KompanijaFacade kf;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.isEmpty()) {

            int id = Integer.parseInt(value);
            Kompanija k = kf.findByProperty(id);

            System.out.println("kompanija konverter:" + k.getNaziv());
            return k;

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null && (value instanceof Kompanija)) {

            Kompanija m = (Kompanija) value;
            return m.getKompanijaID().toString();

        }

        return "";

    }

}
