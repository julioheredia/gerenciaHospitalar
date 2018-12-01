/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.PatrimonioEntidade;
import entidade.controller.PatrimonioEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "patrimonioConverter")
public class PatrimonioEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        PatrimonioEntidade patrimonio = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            PatrimonioEntidadeJpaController cp = new PatrimonioEntidadeJpaController();
            List<PatrimonioEntidade> patrimonioList = cp.findPatrimonioEntidadeEntities();

            for (PatrimonioEntidade patrimonioEntidade : patrimonioList) {
                if (patrimonioEntidade.getNome().equals(value)) {
                    patrimonio = patrimonioEntidade;
                }
            }
        }
        return patrimonio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((PatrimonioEntidade) value).getNome());
        }
    }
}
