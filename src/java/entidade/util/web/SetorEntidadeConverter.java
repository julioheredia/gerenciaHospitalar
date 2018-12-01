/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.SetorEntidade;
import entidade.controller.SetorEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "setorConverter")
public class SetorEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        SetorEntidade setor = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            SetorEntidadeJpaController sc = new SetorEntidadeJpaController();
            List<SetorEntidade> setorList = sc.findSetorEntidadeEntities();

            for (SetorEntidade setorEntidade : setorList) {
                if (setorEntidade.getNome().equals(value)) {
                    setor = setorEntidade;
                }
            }
        }
        return setor;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((SetorEntidade) value).getNome());
        }
    }
}
