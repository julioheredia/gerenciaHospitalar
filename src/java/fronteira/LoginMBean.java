/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import controle.ControladorLogin;
import entidade.UsuarioEntidade;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author julio
 */
@ManagedBean
@RequestScoped
public class LoginMBean {

    private String login;
    private String senha;

    public LoginMBean() {
    }

    public void logOut() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().responseReset();

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", null);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }

    public void autenticarUsuario() throws IOException {

        UsuarioEntidade usuario = null;
        if (!"".equals(login) && !"".equals(senha)) {

            ControladorLogin cl = new ControladorLogin();
            usuario = cl.atenticaUsuario(login, senha);
        }

        if (usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);

            FacesContext.getCurrentInstance().getExternalContext().dispatch("logado/inicial.jsf");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Usuário e/ou a senha incorreto"));
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
