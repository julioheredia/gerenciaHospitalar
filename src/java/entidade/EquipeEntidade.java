/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipeEntidade.findAll", query = "SELECT e FROM EquipeEntidade e"),
    @NamedQuery(name = "EquipeEntidade.findById", query = "SELECT e FROM EquipeEntidade e WHERE e.id = :id"),
    @NamedQuery(name = "EquipeEntidade.findByNome", query = "SELECT e FROM EquipeEntidade e WHERE e.nome = :nome")})
public class EquipeEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProfissionalEntidade> profissionalList;
    @OneToOne(mappedBy = "equipe", optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private EscalaEquipeEntidade escala;

    public EquipeEntidade() {
    }

    public EquipeEntidade(Integer id) {
        this.id = id;
    }

    public EquipeEntidade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProfissionalEntidade> getProfissionalList() {
        return profissionalList;
    }

    public void setProfissionalList(List<ProfissionalEntidade> profissionalList) {
        this.profissionalList = profissionalList;
    }

    public EscalaEquipeEntidade getEscala() {
        return escala;
    }

    public void setEscala(EscalaEquipeEntidade escala) {
        this.escala = escala;
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
        if (!(object instanceof EquipeEntidade)) {
            return false;
        }
        EquipeEntidade other = (EquipeEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.EquipeEntidade[ id=" + id + " ]";
    }
}
