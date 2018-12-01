/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ConvenioSaudeEntidade;
import entidade.controller.ConvenioSaudeEntidadeJpaController;
import entidade.controller.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorPlanoSaude {

    public Boolean createConvenioSaude(ConvenioSaudeEntidade plano) {

        Boolean resp = false;
        ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
        cc.create(plano);

        if (plano.getId() != null) {
            resp = true;
        }
        return resp;
    }

    public Boolean removeConvenioSaude(ConvenioSaudeEntidade plano) {

        Boolean resp = false;
        ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
        try {
            cc.destroy(plano.getId());
            resp = true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPlanoSaude.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean editConvenioSaude(ConvenioSaudeEntidade plano) {

        Boolean resp = false;
        try {
            ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
            cc.edit(plano);
            resp = true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPlanoSaude.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPlanoSaude.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}
