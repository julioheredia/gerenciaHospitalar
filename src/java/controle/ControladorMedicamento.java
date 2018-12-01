/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.AreaHospitalar;
import entidade.MedicamentoEntidade;
import entidade.SetorEntidade;
import entidade.TipoMedicamento;
import entidade.controller.MedicamentoEntidadeJpaController;
import entidade.controller.SetorEntidadeJpaController;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.util.web.RespostaMedicamentoSetor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorMedicamento {

    public Boolean createMedicamento(MedicamentoEntidade medicamento) {
        Boolean resp = false;
        try {
            MedicamentoEntidadeJpaController mejc = new MedicamentoEntidadeJpaController();
            mejc.create(medicamento);
            resp = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public RespostaMedicamentoSetor vincularMedicamentoSetor(
            MedicamentoEntidade medicamento,
            SetorEntidade setor) {

        Boolean areaNaoCadastrada = false;
        Boolean remedioProibido = false;
        Boolean tipoRemedioProibido = false;
        String texto = "!!FALHA!!!";
        Boolean status = false;

        if (setor.getAreaHospitalar() == null) {
            areaNaoCadastrada = true;
            texto = "Setor não possui Área Hospitalar, vincule o setor a uma Área Hospitalar";
        } else {
            if (medicamento.getTipo().equals(TipoMedicamento.ANALGESICO)
                    && setor.getAreaHospitalar().equals(AreaHospitalar.AREAR_CRITICA)) {
                tipoRemedioProibido = true;
                texto = "Medicamento proibido na área - " + setor.getAreaHospitalar().getNome() + "";
            }
            if (medicamento.getTipo().equals(TipoMedicamento.ANTITERMICO)
                    && setor.getAreaHospitalar().equals(AreaHospitalar.AREAR_SEMICRITICA)) {
                tipoRemedioProibido = true;
                texto = "Medicamento proibido na área - " + setor.getAreaHospitalar().getNome() + "";
            }
        }

        if (!areaNaoCadastrada && !tipoRemedioProibido) {

            medicamento.setSetor(setor);
            setor.getMedicamentoList().add(medicamento);
            SetorEntidadeJpaController sejc = new SetorEntidadeJpaController();
            try {
                sejc.edit(setor);

                status = true;
                texto = "Solicitação de vincular medicamento à setor realizada com sucesso";
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new RespostaMedicamentoSetor(areaNaoCadastrada, remedioProibido, tipoRemedioProibido, texto, status);
    }
}
