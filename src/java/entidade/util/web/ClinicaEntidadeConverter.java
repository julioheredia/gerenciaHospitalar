/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.ClinicaEntidade;
import entidade.controller.ClinicaEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "clinicaConverter")
public class ClinicaEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ClinicaEntidade clinica = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
            List<ClinicaEntidade> clinicaList = cc.findClinicaEntidadeEntities();

            for (ClinicaEntidade clinicaEntidade : clinicaList) {
                if (clinicaEntidade.getNome().equals(value)) {
                    clinica = clinicaEntidade;
                }
            }
        }
        return clinica;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((ClinicaEntidade) value).getNome());
        }
    }
}
