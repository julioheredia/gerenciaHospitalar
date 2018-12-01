/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ClinicaEntidade;
import entidade.ConvenioSaudeEntidade;
import entidade.ExameEntidade;
import entidade.controller.ClinicaEntidadeJpaController;
import entidade.controller.ConvenioSaudeEntidadeJpaController;
import entidade.controller.ExameEntidadeJpaController;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author julio
 */
public class ControladorDualList {

    public DualListModel<ConvenioSaudeEntidade> getDualListPlanoOfClinica(ClinicaEntidade clinica) {

        List<ConvenioSaudeEntidade> convenioSelect = clinica.getConvenioList();

        ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
        List<ConvenioSaudeEntidade> convenioListAux = cc.findConvenioSaudeEntidadeEntities();

        List<ConvenioSaudeEntidade> convenioOpcao = new ArrayList<ConvenioSaudeEntidade>();

        for (ConvenioSaudeEntidade convenioSaudeEntidade : convenioListAux) {
            Boolean resp = false;

            for (ConvenioSaudeEntidade convenioSaude : convenioSelect) {
                if (convenioSaudeEntidade.equals(convenioSaude)) {
                    resp = true;
                }
            }
            if (!resp) {
                convenioOpcao.add(convenioSaudeEntidade);
            }
        }

        return new DualListModel<ConvenioSaudeEntidade>(convenioOpcao, convenioSelect);
    }

    public DualListModel<ExameEntidade> getDualListExameOfClinica(ClinicaEntidade clinica) {

        List<ExameEntidade> exameSelect = clinica.getExameList();

        ExameEntidadeJpaController ec = new ExameEntidadeJpaController();
        List<ExameEntidade> exameList = ec.findExameEntidadeEntities();

        List<ExameEntidade> exameOpcao = new ArrayList<ExameEntidade>();

        for (ExameEntidade exameEntidade : exameList) {
            Boolean resp = false;
            for (ExameEntidade exame : exameSelect) {
                if (exameEntidade.equals(exame)) {
                    resp = true;
                }
            }

            if (!resp) {
                exameOpcao.add(exameEntidade);
            }
        }
        return new DualListModel<ExameEntidade>(exameOpcao, exameSelect);
    }

    public DualListModel<ExameEntidade> getDualListExameOfPlanoDeSaude(ConvenioSaudeEntidade plano) {

        List<ExameEntidade> exameSelect = plano.getExameList();

        ExameEntidadeJpaController ec = new ExameEntidadeJpaController();
        List<ExameEntidade> exameList = ec.findExameEntidadeEntities();

        List<ExameEntidade> exameOpcao = new ArrayList<ExameEntidade>();

        for (ExameEntidade exameEntidade : exameList) {
            Boolean resp = false;
            for (ExameEntidade exame : exameSelect) {
                if (exameEntidade.equals(exame)) {
                    resp = true;
                }
            }

            if (!resp) {
                exameOpcao.add(exameEntidade);
            }
        }
        return new DualListModel<ExameEntidade>(exameOpcao, exameSelect);
    }

    public DualListModel<ClinicaEntidade> getDualListClinicaOfPlanoDeSaude(ConvenioSaudeEntidade plano) {

        ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
        List<ClinicaEntidade> listClinica = cc.findClinicaEntidadeEntities();

        List<ClinicaEntidade> clinicaSelect = plano.getClinicaList();

        List<ClinicaEntidade> clinicaOpcao = new ArrayList<ClinicaEntidade>();

        for (ClinicaEntidade clinicaEntidade : listClinica) {
            Boolean resp = false;
            for (ClinicaEntidade clinica : clinicaSelect) {
                if (clinicaEntidade.equals(clinica)) {
                    resp = true;
                }
            }

            if (!resp) {
                clinicaOpcao.add(clinicaEntidade);
            }
        }

        return new DualListModel<ClinicaEntidade>(clinicaOpcao, clinicaSelect);
    }
}
