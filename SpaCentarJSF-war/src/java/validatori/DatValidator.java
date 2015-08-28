/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author student1
 */
@FacesValidator(value = "datVal")
public class DatValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            try {

                Date zakazanoVreme = (Date) value;

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String sVreme = sdf.format(value);
                Date vreme = sdf.parse(sVreme);

                sdf = new SimpleDateFormat("dd/MM/yyyy");          
                String sDan = sdf.format(value);
                Date dan= sdf.parse(sDan);

                Calendar c1 = new GregorianCalendar();
                c1.setTime(dan);

                Calendar c2 = new GregorianCalendar();
                c2.setTime(vreme);

                Calendar c3 = new GregorianCalendar();
                c3.setTime(new SimpleDateFormat("HH:mm").parse("12:00"));

                Date pocetakRadnogVremena = new Date(c1.get(GregorianCalendar.YEAR) - 1900, c1.get(GregorianCalendar.MONTH),
                        c1.get(GregorianCalendar.DAY_OF_MONTH), c3.get(GregorianCalendar.HOUR_OF_DAY), c3.get(GregorianCalendar.MINUTE));

                Calendar c4 = new GregorianCalendar();
                c4.setTime(new SimpleDateFormat("HH:mm").parse("20:00"));

                Date krajRadnogVremena = new Date(c1.get(GregorianCalendar.YEAR) - 1900, c1.get(GregorianCalendar.MONTH),
                        c1.get(GregorianCalendar.DAY_OF_MONTH), c4.get(GregorianCalendar.HOUR_OF_DAY), c4.get(GregorianCalendar.MINUTE));

                if (zakazanoVreme.before(pocetakRadnogVremena) || zakazanoVreme.after(krajRadnogVremena)) {
                    System.out.println("Validaciija");
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "fdsfsdfs", "Morate izabrati vreme u okviru našeg radnog vremena!"));
                
                }

            } catch (ParseException ex) {
                Logger.getLogger(DatValidator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
