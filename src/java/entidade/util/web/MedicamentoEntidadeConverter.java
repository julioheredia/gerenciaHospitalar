/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import entidade.MedicamentoEntidade;
import entidade.controller.MedicamentoEntidadeJpaController;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author julio
 */
@FacesConverter(value = "medicamentoConverter")
public class MedicamentoEntidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        MedicamentoEntidade medicamento = null;
        if (value.trim().equals("")) {
            return null;
        } else {

            MedicamentoEntidadeJpaController mc = new MedicamentoEntidadeJpaController();
            List<MedicamentoEntidade> medicamentoList = mc.findMedicamentoEntidadeEntities();

            for (MedicamentoEntidade medicamentoEntidade : medicamentoList) {
                if (medicamentoEntidade.getNome().equals(value)) {
                    medicamento = medicamentoEntidade;
                }
            }
        }
        return medicamento;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((MedicamentoEntidade) value).getNome());
        }
    }
}
