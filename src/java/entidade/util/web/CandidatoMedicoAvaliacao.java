/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

import controle.ControladorClinica;
import entidade.ClinicaEntidade;
import entidade.Especializacao;
import entidade.MedicoCandidatoEntidade;
import entidade.Qualificacao;
import entidade.StatusCandidato;

/**
 *
 * @author julio
 */
public class CandidatoMedicoAvaliacao extends MedicoCandidatoEntidade {

    private ClinicaEntidade referenciasAnterioresCandidato;
    private Especializacao especializacaoCandidato;
    private Qualificacao qualificacaoCandidato;
    private StatusCandidato statusCandidato;

    public CandidatoMedicoAvaliacao() {
        super();
    }

    public CandidatoMedicoAvaliacao(MedicoCandidatoEntidade medicoCandidato) {
        super(medicoCandidato.getId(),
                medicoCandidato.getNome(),
                medicoCandidato.getNome(),
                medicoCandidato.getHonorarioDesejado(),
                medicoCandidato.getQualificacao(),
                medicoCandidato.getTempoExperiencia(),
                medicoCandidato.getDataNascimento(),
                medicoCandidato.getCrm(),
                medicoCandidato.getEspecializacao(),
                medicoCandidato.getReferenciasAnteriores());

        this.qualificacaoCandidato = Qualificacao.valueOf(super.getQualificacao());
        this.especializacaoCandidato = Especializacao.valueOf(super.getEspecializacao());

        ControladorClinica cc = new ControladorClinica();
        this.referenciasAnterioresCandidato = cc.getClinica(super.getReferenciasAnteriores());
    }

    public ClinicaEntidade getReferenciasAnterioresCandidato() {
        return referenciasAnterioresCandidato;
    }

    public Especializacao getEspecializacaoCandidato() {
        return especializacaoCandidato;
    }

    public Qualificacao getQualificacaoCandidato() {
        return qualificacaoCandidato;
    }

    public StatusCandidato getStatusCandidato() {
        return statusCandidato;
    }

    public void setStatusCandidato(StatusCandidato statusCandidato) {
        this.statusCandidato = statusCandidato;
    }

    @Override
    public String toString() {
        return "CandidatoMedicoAvaliacao herdado do candidado com id= " + super.getId() + " ";
    }
}
