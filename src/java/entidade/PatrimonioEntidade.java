/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "patrimonio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatrimonioEntidade.findAll", query = "SELECT p FROM PatrimonioEntidade p"),
    @NamedQuery(name = "PatrimonioEntidade.findById", query = "SELECT p FROM PatrimonioEntidade p WHERE p.id = :id"),
    //@NamedQuery(name = "PatrimonioEntidade.findByIdSetor", query = "SELECT p FROM PatrimonioEntidade p WHERE p.idSetor = :idSetor"),
    @NamedQuery(name = "PatrimonioEntidade.findByNome", query = "SELECT p FROM PatrimonioEntidade p WHERE p.nome = :nome"),
    @NamedQuery(name = "PatrimonioEntidade.findByDescricao", query = "SELECT p FROM PatrimonioEntidade p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PatrimonioEntidade.findByTipo", query = "SELECT p FROM PatrimonioEntidade p WHERE p.tipo = :tipo")})
public class PatrimonioEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    //@Basic(optional = false)
    //@Column(name = "id_setor")
    //private int idSetor;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPatrimonio tipo;
    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    private SetorEntidade setor;

    public PatrimonioEntidade() {
    }

    public PatrimonioEntidade(Integer id) {
        this.id = id;
    }

    public PatrimonioEntidade(Integer id, SetorEntidade setor, String nome) {
        this.id = id;
        this.setor = setor;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SetorEntidade getSetor() {
        return setor;
    }

    public void setSetor(SetorEntidade setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoPatrimonio getTipo() {
        return tipo;
    }

    public void setTipo(TipoPatrimonio tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof PatrimonioEntidade)) {
            return false;
        }
        PatrimonioEntidade other = (PatrimonioEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.PatrimonioEntidade[ id=" + id + " ]";
    }
}
