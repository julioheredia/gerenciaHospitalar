/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.EnfermeiroEntidade;
import entidade.FarmaceuticoEntidade;
import entidade.MedicoEntidade;
import entidade.ProfissionalEntidade;
import entidade.SetorEntidade;
import entidade.controller.EnfermeiroEntidadeJpaController;
import entidade.controller.FarmaceuticoEntidadeJpaController;
import entidade.controller.MedicoEntidadeJpaController;
import entidade.controller.ProfissionalEntidadeJpaController;
import entidade.controller.exceptions.IllegalOrphanException;
import entidade.controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorProfissional {

    public List<ProfissionalEntidade> searchProfessional(
            String nome, Boolean searchNome,
            String type, Boolean searchType) {

        if (searchNome || searchType) {

            ProfissionalEntidadeJpaController pc = new ProfissionalEntidadeJpaController();
            return pc.searchProfessional(nome, searchNome, type, searchType);

        } else {
            ProfissionalEntidadeJpaController pc = new ProfissionalEntidadeJpaController();
            return pc.findProfissionalEntidadeEntities();
        }
    }

    public Boolean createProfissional(
            ProfissionalEntidade profissional,
            MedicoEntidade medico,
            EnfermeiroEntidade enfermeiro,
            FarmaceuticoEntidade farmaceutico,
            String tipo) {

        profissional.setDataSaidaEquipe(null);
        Boolean resp = false;

        if ("M".equals(tipo)) {

            profissional.setDiscriminator(tipo);

            String crm = medico.getCrm();

            medico = (MedicoEntidade) profissional;
            medico.setCrm(crm);

            MedicoEntidadeJpaController mjc = new MedicoEntidadeJpaController();
            mjc.create(medico);

            if (medico.getId() != null) {
                resp = true;
            }
        }
        if ("E".equals(tipo)) {

            profissional.setDiscriminator(tipo);
            String cre = enfermeiro.getCre();
            int tempoExperiencia = enfermeiro.getTempoExperiencia();
            SetorEntidade setor = enfermeiro.getSetor();

            enfermeiro = (EnfermeiroEntidade) profissional;
            enfermeiro.setCre(cre);
            enfermeiro.setSetor(setor);
            enfermeiro.setTempoExperiencia(tempoExperiencia);

            EnfermeiroEntidadeJpaController ec = new EnfermeiroEntidadeJpaController();
            ec.create(enfermeiro);

            if (enfermeiro.getId() != null) {
                resp = true;
            }
        }
        if ("F".equals(tipo)) {

            profissional.setDiscriminator(tipo);

            String crf = farmaceutico.getCrf();
            int limiteMedicamento = farmaceutico.getLimiteMedicamento();
            MedicoEntidadeJpaController mc = new MedicoEntidadeJpaController();
            farmaceutico = (FarmaceuticoEntidade) profissional;
            farmaceutico.setCrf(crf);
            farmaceutico.setLimiteMedicamento(limiteMedicamento);

            FarmaceuticoEntidadeJpaController fc = new FarmaceuticoEntidadeJpaController();
            fc.create(farmaceutico);

            if (farmaceutico.getId() != null) {
                resp = true;
            }
        }

        return resp;
    }

    public Boolean removeProfissional(ProfissionalEntidade profissional) {

        Boolean resp = false;
        ProfissionalEntidadeJpaController pc = new ProfissionalEntidadeJpaController();
        try {
            pc.destroy(profissional.getId());
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean editProfissional(
            ProfissionalEntidade profissional,
            MedicoEntidade medico,
            EnfermeiroEntidade enfermeiro,
            FarmaceuticoEntidade farmaceutico) {


        Boolean resp = false;

        if (profissional instanceof MedicoEntidade) {

            String crm = medico.getCrm();

            medico = (MedicoEntidade) profissional;
            medico.setCrm(crm);

            MedicoEntidadeJpaController mjc = new MedicoEntidadeJpaController();
            try {
                mjc.edit(medico);
                resp = true;

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (profissional instanceof EnfermeiroEntidade) {

            String cre = enfermeiro.getCre();
            int tempoExperiencia = enfermeiro.getTempoExperiencia();
            SetorEntidade setor = enfermeiro.getSetor();

            enfermeiro = (EnfermeiroEntidade) profissional;
            enfermeiro.setCre(cre);
            enfermeiro.setSetor(setor);
            enfermeiro.setTempoExperiencia(tempoExperiencia);

            EnfermeiroEntidadeJpaController ec = new EnfermeiroEntidadeJpaController();
            try {
                ec.edit(enfermeiro);
                resp = true;

            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (profissional instanceof FarmaceuticoEntidade) {

            String crf = farmaceutico.getCrf();
            int limiteMedicamento = farmaceutico.getLimiteMedicamento();

            farmaceutico = (FarmaceuticoEntidade) profissional;
            farmaceutico.setCrf(crf);
            farmaceutico.setLimiteMedicamento(limiteMedicamento);

            FarmaceuticoEntidadeJpaController fc = new FarmaceuticoEntidadeJpaController();
            try {
                fc.edit(farmaceutico);
                resp = true;

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorProfissional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resp;
    }

    public MedicoEntidade getMedico(Integer id) {
        MedicoEntidadeJpaController mjc = new MedicoEntidadeJpaController();
        return mjc.findMedicoEntidade(id);
    }

    public EnfermeiroEntidade getEnfermeiro(Integer id) {
        EnfermeiroEntidadeJpaController ec = new EnfermeiroEntidadeJpaController();
        return ec.findEnfermeiroEntidade(id);
    }

    public FarmaceuticoEntidade getFarmaceutico(Integer id) {
        FarmaceuticoEntidadeJpaController fc = new FarmaceuticoEntidadeJpaController();
        return fc.findFarmaceuticoEntidade(id);
    }
}
