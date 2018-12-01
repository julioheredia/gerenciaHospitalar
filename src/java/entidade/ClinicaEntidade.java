/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "clinica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClinicaEntidade.findAll", query = "SELECT c FROM ClinicaEntidade c"),
    @NamedQuery(name = "ClinicaEntidade.findById", query = "SELECT c FROM ClinicaEntidade c WHERE c.id = :id"),
    @NamedQuery(name = "ClinicaEntidade.findByNome", query = "SELECT c FROM ClinicaEntidade c WHERE c.nome = :nome"),
    @NamedQuery(name = "ClinicaEntidade.findByEndereco", query = "SELECT c FROM ClinicaEntidade c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "ClinicaEntidade.findByTelefone", query = "SELECT c FROM ClinicaEntidade c WHERE c.telefone = :telefone")})
public class ClinicaEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "telefone")
    private String telefone;
    @ManyToMany
    @JoinTable(name = "rel_clinica_convenio", joinColumns = {
        @JoinColumn(name = "id_clinica")}, inverseJoinColumns = {
        @JoinColumn(name = "id_convenio")})
    private List<ConvenioSaudeEntidade> convenioList;
    @ManyToMany
    @JoinTable(name = "rel_clinica_exame", joinColumns = {
        @JoinColumn(name = "id_clinica")}, inverseJoinColumns = {
        @JoinColumn(name = "id_exame")})
    private List<ExameEntidade> exameList;

    public ClinicaEntidade() {
    }

    public ClinicaEntidade(Integer id) {
        this.id = id;
    }

    public ClinicaEntidade(Integer id, String nome) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<ConvenioSaudeEntidade> getConvenioList() {
        return convenioList;
    }

    public void setConvenioList(List<ConvenioSaudeEntidade> convenioList) {
        this.convenioList = convenioList;
    }

    public List<ExameEntidade> getExameList() {
        return exameList;
    }

    public void setExameList(List<ExameEntidade> exameList) {
        this.exameList = exameList;
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
        if (!(object instanceof ClinicaEntidade)) {
            return false;
        }
        ClinicaEntidade other = (ClinicaEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ClinicaEntidade[ id=" + id + " ]";
    }
}
