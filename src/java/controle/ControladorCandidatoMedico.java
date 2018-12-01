/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.MedicoCandidatoEntidade;
import entidade.MedicoEntidade;
import entidade.controller.MedicoCandidatoEntidadeJpaController;
import entidade.controller.MedicoEntidadeJpaController;
import entidade.util.web.CandidatoMedicoAvaliacao;
import java.util.List;

/**
 *
 * @author julio
 */
public class ControladorCandidatoMedico {

    public Boolean createMedicoCandidato(MedicoCandidatoEntidade medicoCandidato) {

        Boolean resp = false;
        try {
            MedicoCandidatoEntidadeJpaController mcejc = new MedicoCandidatoEntidadeJpaController();
            mcejc.create(medicoCandidato);
            resp = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public List<CandidatoMedicoAvaliacao> relatorioMedicoCandidato() {
        ControladorRelatorioCandidatoMedico c = new ControladorRelatorioCandidatoMedico();
        return c.relatorioMedicoCandidatoImpl();
    }

    public Boolean removeMedicoCandidato(MedicoCandidatoEntidade medicoCandidato) {
        Boolean resp = false;
        try {
            MedicoCandidatoEntidadeJpaController mcejc = new MedicoCandidatoEntidadeJpaController();
            mcejc.destroy(medicoCandidato.getId());
            resp = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public Boolean contrataMedicoCandidato(CandidatoMedicoAvaliacao candidato) {
        Boolean resp = false;
        try {
            MedicoEntidade medico = new MedicoEntidade();
            medico.setDiscriminator("M");
            medico.setCrm(candidato.getCrm());
            medico.setNome(candidato.getNome());
            medico.setEndereco(candidato.getEndereco());
            String experiencia = String.valueOf(candidato.getTempoExperiencia());
            medico.setExperiencia(experiencia);
            medico.setHonorario(candidato.getHonorarioDesejado());
            medico.setQualificacao(candidato.getQualificacao());

            MedicoEntidadeJpaController mjc = new MedicoEntidadeJpaController();
            mjc.create(medico);
            resp = true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }
}
