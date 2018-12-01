/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.UsuarioEntidade;
import entidade.controller.UsuarioEntidadeJpaController;
import entidade.controller.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ControladorUsuario {

    public Boolean createUsuario(UsuarioEntidade usuario) {

        Boolean resp = false;
        try {
            usuario.setAdmin(false);
            UsuarioEntidadeJpaController ucj = new UsuarioEntidadeJpaController();
            ucj.create(usuario);
            resp = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public Boolean removeUsuario(UsuarioEntidade usuario) {

        Boolean resp = false;
        try {
            UsuarioEntidadeJpaController ucj = new UsuarioEntidadeJpaController();
            ucj.destroy(usuario.getId());
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    public Boolean editUsuario(UsuarioEntidade usuario) {

        Boolean resp = false;
        try {
            UsuarioEntidadeJpaController ucj = new UsuarioEntidadeJpaController();
            ucj.edit(usuario);
            resp = true;

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }
}
