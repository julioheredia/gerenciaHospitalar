/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.ExameEntidade;
import entidade.controller.ExameEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "exameConverter")
public class ExameEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ExameEntidade exame = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            ExameEntidadeJpaController ec = new ExameEntidadeJpaController();
            List<ExameEntidade> exameList = ec.findExameEntidadeEntities();

            for (ExameEntidade exameEntidade : exameList) {
                if (exameEntidade.getNome().equals(value)) {
                    exame = exameEntidade;
                }
            }
        }
        return exame;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((ExameEntidade) value).getNome());
        }
    }
}
