/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.EquipeEntidade;
import entidade.controller.EquipeEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "equipeConverter")
public class EquipeEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        EquipeEntidade equipe = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            EquipeEntidadeJpaController ec = new EquipeEntidadeJpaController();
            List<EquipeEntidade> equipeList = ec.findEquipeEntidadeEntities();

            for (EquipeEntidade equipeEntidade : equipeList) {
                if (equipeEntidade.getNome().equals(value)) {
                    equipe = equipeEntidade;
                }
            }
        }
        return equipe;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((EquipeEntidade) value).getNome());
        }
    }
}
