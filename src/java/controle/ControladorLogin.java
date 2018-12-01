/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.UsuarioEntidade;
import entidade.controller.UsuarioEntidadeJpaController;

/**
 *
 * @author julio
 */
public class ControladorLogin {

    public UsuarioEntidade atenticaUsuario(String login, String senha) {

        UsuarioEntidadeJpaController uc = new UsuarioEntidadeJpaController();
        return uc.login(login, senha);
    }
}
