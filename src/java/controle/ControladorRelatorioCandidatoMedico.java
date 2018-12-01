/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.MedicoCandidatoEntidade;
import entidade.Qualificacao;
import entidade.StatusCandidato;
import entidade.controller.MedicoCandidatoEntidadeJpaController;
import entidade.util.web.CandidatoMedicoAvaliacao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author julio
 */
public class ControladorRelatorioCandidatoMedico {

    public List<CandidatoMedicoAvaliacao> relatorioMedicoCandidatoImpl() {

        MedicoCandidatoEntidadeJpaController mcejc = new MedicoCandidatoEntidadeJpaController();
        List<MedicoCandidatoEntidade> listCandidatos = mcejc.findMedicoCandidatoEntidadeEntities();

        List<CandidatoMedicoAvaliacao> listAvaliacao = new ArrayList<CandidatoMedicoAvaliacao>();

        for (MedicoCandidatoEntidade medicoCandidato : listCandidatos) {
            CandidatoMedicoAvaliacao candidatoMedicoAvaliacao = new CandidatoMedicoAvaliacao(medicoCandidato);
            candidatoMedicoAvaliacao.setStatusCandidato(verificaStatusCandidato(medicoCandidato));
            listAvaliacao.add(candidatoMedicoAvaliacao);
        }

        return listAvaliacao;
    }

    private StatusCandidato verificaStatusCandidato(MedicoCandidatoEntidade medicoCandidato) {

        float mediaTempoExperiencia = this.verificadorTempoExperiencia(medicoCandidato.getTempoExperiencia());
        float mediaDataNascimento = this.verificadorDataNascimento(medicoCandidato.getDataNascimento());
        float mediaQualificacao = this.verificadorQualificacao(medicoCandidato.getQualificacao());
        float mediaReferenciasAnteriores = this.verificadorReferenciasAnteriores(medicoCandidato.getReferenciasAnteriores());

        float media = mediaTempoExperiencia + mediaDataNascimento + mediaQualificacao + mediaReferenciasAnteriores;
        media = (media / 4);
        int m = (int) media;

        return StatusCandidato.valueOf(m);
    }

    private float verificadorTempoExperiencia(int tempoExperiencia) {
        if (tempoExperiencia < 2) {
            return 1;
        }
        if (tempoExperiencia < 4) {
            return 2;
        }
        if (tempoExperiencia <= 5) {
            return 3;
        }
        if (tempoExperiencia > 5 && tempoExperiencia < 7) {
            return 4;
        }
        if (tempoExperiencia > 7) {
            return 5;
        }
        return 0;
    }

    private float verificadorDataNascimento(Date dataNascimento) {

        Calendar now = Calendar.getInstance();
        int ano_atual = Calendar.getInstance().get(Calendar.YEAR);
        now.setTime(dataNascimento);
        int aniversario = now.get(Calendar.YEAR);

        int idade = ano_atual - aniversario;

        if (idade > 60 && idade < 25) {
            return 1;
        }
        if (idade < 30) {
            return 2;
        }
        if (idade > 50) {
            return 3;
        }
        if (idade > 40) {
            return 4;
        }
        if (idade > 30 && idade < 40) {
            return 5;
        }
        return 0;
    }

    private float verificadorQualificacao(String qualificacao) {
        Qualificacao q = Qualificacao.valueOf(qualificacao);
        if (q.equals(Qualificacao.RESIDENCIA)) {
            return 1;
        }
        if (q.equals(Qualificacao.GRADUACAO)) {
            return 2;
        }
        if (q.equals(Qualificacao.POS_GRADUACAO) || q.equals(Qualificacao.ESPECIALIZAOCAO)) {
            return 3;
        }
        if (q.equals(Qualificacao.MESTRADO)) {
            return 4;
        }
        if (q.equals(Qualificacao.DOUTORADO)) {
            return 5;
        }
        return 0;
    }

    private float verificadorReferenciasAnteriores(int referenciasAnteriores) {
        if (referenciasAnteriores != 0) {
            return 5;
        } else {
            return 0;
        }
    }
}
