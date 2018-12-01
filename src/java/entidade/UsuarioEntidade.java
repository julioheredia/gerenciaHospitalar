/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEntidade.findAll", query = "SELECT u FROM UsuarioEntidade u"),
    @NamedQuery(name = "UsuarioEntidade.findById", query = "SELECT u FROM UsuarioEntidade u WHERE u.id = :id"),
    @NamedQuery(name = "UsuarioEntidade.findByLogin", query = "SELECT u FROM UsuarioEntidade u WHERE u.login = :login"),
    @NamedQuery(name = "UsuarioEntidade.findBySenha", query = "SELECT u FROM UsuarioEntidade u WHERE u.senha = :senha"),
    @NamedQuery(name = "UsuarioEntidade.findByEmail", query = "SELECT u FROM UsuarioEntidade u WHERE u.email = :email"),
    //@NamedQuery(name = "UsuarioEntidade.findByIdProfissional", query = "SELECT u FROM UsuarioEntidade u WHERE u.idProfissional = :idProfissional"),
    @NamedQuery(name = "UsuarioEntidade.findByAdmin", query = "SELECT u FROM UsuarioEntidade u WHERE u.admin = :admin")})
public class UsuarioEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    /*
     @Basic(optional = false)
     @Column(name = "id_profissional")
     private int idProfissional;
     */
    @Basic(optional = false)
    @Column(name = "admin")
    private Boolean admin;
    @OneToOne
    @JoinColumn(name = "id_profissional", referencedColumnName = "id")
    private ProfissionalEntidade profissional;

    public UsuarioEntidade() {
    }

    public UsuarioEntidade(Integer id) {
        this.id = id;
    }

    public UsuarioEntidade(Integer id, String login, String senha, String email, Boolean admin) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public ProfissionalEntidade getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalEntidade profissional) {
        this.profissional = profissional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntidade)) {
            return false;
        }
        UsuarioEntidade other = (UsuarioEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.UsuarioEntidade[ id=" + id + " ]";
    }
}
