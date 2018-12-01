/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.AreaHospitalar;
import entidade.PatrimonioEntidade;
import entidade.SetorEntidade;
import entidade.TipoMedicamento;
import entidade.TipoPatrimonio;
import entidade.controller.PatrimonioEntidadeJpaController;
import entidade.controller.SetorEntidadeJpaController;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.util.web.RespostaPatrimonioSetor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorPatrimonio {

    public RespostaPatrimonioSetor vincularMedicamentoSetor(
            PatrimonioEntidade patrimonio,
            SetorEntidade setor) {
        Boolean areaNaoCadastrada = false;
        Boolean tipoNaoCadastrado = false;
        Boolean patrimonioProibido = false;
        String texto = "!!FALHA!!!";
        Boolean status = false;

        if (setor.getAreaHospitalar() == null) {
            areaNaoCadastrada = true;
            texto = "Setor não possui Área Hospitalar, vincule o setor a uma Área Hospitalar";
        }
        if (patrimonio.getTipo() == null) {
            tipoNaoCadastrado = true;
            texto = "Patrimônio não possui Tipo, vincule o Patrimônio a um Tipo de Patrimônio";
        }

        if (areaNaoCadastrada && tipoNaoCadastrado) {
             texto = "Setor não possui Área Hospitalar, vincule o setor a uma Área Hospitalar"
                     + " & "
                     + "Patrimônio não possui Tipo, vincule o Patrimônio a um Tipo de Patrimônio";
        }
        
        if (!areaNaoCadastrada && !tipoNaoCadastrado) {
            if (patrimonio.getTipo().equals(TipoPatrimonio.EQUIPAMENTO_ESPECIALIZADO)
                    && setor.getAreaHospitalar().equals(AreaHospitalar.AREAR_SEMICRITICA)) {
                tipoNaoCadastrado = true;
                texto = "Patrimônio proibido na área - " + setor.getAreaHospitalar().getNome() + "";
            }
            if (patrimonio.getTipo().equals(TipoPatrimonio.EQUIPAMENTO_CIRURGICO)
                    && setor.getAreaHospitalar().equals(AreaHospitalar.AREAR_NAO_CRITICA)) {
                tipoNaoCadastrado = true;
                texto = "Patrimônio proibido na área - " + setor.getAreaHospitalar().getNome() + "";
            }

            if (patrimonio.getTipo().equals(TipoPatrimonio.EQUIPAMENTO_CLINICO)
                    && setor.getAreaHospitalar().equals(AreaHospitalar.AREAR_CRITICA)) {
                tipoNaoCadastrado = true;
                texto = "Patrimônio proibido na área - " + setor.getAreaHospitalar().getNome() + "";
            }
        }

        if (!areaNaoCadastrada && !patrimonioProibido && !tipoNaoCadastrado) {

            patrimonio.setSetor(setor);
            setor.getPatrimonioList().add(patrimonio);
            SetorEntidadeJpaController sejc = new SetorEntidadeJpaController();
            try {
                sejc.edit(setor);

                status = true;
                texto = "Solicitação de vincular patrimônio à setor realizada com sucesso";
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return new RespostaPatrimonioSetor(areaNaoCadastrada, tipoNaoCadastrado, patrimonioProibido, texto, status);
    }

    public Boolean createPatrimonio(PatrimonioEntidade patrimonio) {

        Boolean resp = false;
        try {
            PatrimonioEntidadeJpaController pc = new PatrimonioEntidadeJpaController();
            pc.create(patrimonio);
            resp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    public Boolean removePatrimonio(PatrimonioEntidade patrimonio) {

        Boolean resp = false;
        try {
            PatrimonioEntidadeJpaController pc = new PatrimonioEntidadeJpaController();
            pc.destroy(patrimonio.getId());
            resp = true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean editPatrimonio(PatrimonioEntidade patrimonio) {

        Boolean resp = false;
        try {
            PatrimonioEntidadeJpaController pc = new PatrimonioEntidadeJpaController();
            pc.edit(patrimonio);
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}
