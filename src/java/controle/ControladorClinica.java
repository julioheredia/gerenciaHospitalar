/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ClinicaEntidade;
import entidade.controller.ClinicaEntidadeJpaController;
import entidade.controller.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorClinica {

    public Boolean createClinica(ClinicaEntidade clinica) {

        ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
        cc.create(clinica);

        if (clinica.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeClinica(ClinicaEntidade clinica) {

        Boolean resp = false;
        ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
        try {
            cc.destroy(clinica.getId());
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorClinica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean editClinica(ClinicaEntidade clinica) {

        Boolean resp = false;
        try {
            ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
            cc.edit(clinica);
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorClinica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorClinica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public ClinicaEntidade getClinica(int id) {
        ClinicaEntidadeJpaController cejc = new ClinicaEntidadeJpaController();
        return cejc.findClinicaEntidade(id);
    }
}
