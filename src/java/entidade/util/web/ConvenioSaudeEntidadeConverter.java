/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.ConvenioSaudeEntidade;
import entidade.controller.ConvenioSaudeEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "planoConverter")
public class ConvenioSaudeEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ConvenioSaudeEntidade convenio = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
            List<ConvenioSaudeEntidade> convenioList = cc.findConvenioSaudeEntidadeEntities();

            for (ConvenioSaudeEntidade convenioEntidade : convenioList) {
                if (convenioEntidade.getNome().equals(value)) {
                    convenio = convenioEntidade;
                }
            }
        }
        return convenio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((ConvenioSaudeEntidade) value).getNome());
        }
    }
}
