/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.ProfissionalEntidade;
import entidade.controller.ProfissionalEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "profissionalConverter")
public class ProfissionalEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ProfissionalEntidade profissional = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            ProfissionalEntidadeJpaController pc = new ProfissionalEntidadeJpaController();
            List<ProfissionalEntidade> profissionalList = pc.findProfissionalEntidadeEntities();

            for (ProfissionalEntidade profissionalEntidade : profissionalList) {
                if (profissionalEntidade.getNome().equals(value)) {
                    profissional = profissionalEntidade;
                }
            }
        }
        return profissional;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((ProfissionalEntidade) value).getNome());
        }
    }
}
