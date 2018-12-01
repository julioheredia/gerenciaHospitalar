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
@Table(name = "convenio_saude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConvenioSaudeEntidade.findAll", query = "SELECT c FROM ConvenioSaudeEntidade c"),
    @NamedQuery(name = "ConvenioSaudeEntidade.findById", query = "SELECT c FROM ConvenioSaudeEntidade c WHERE c.id = :id"),
    @NamedQuery(name = "ConvenioSaudeEntidade.findByNome", query = "SELECT c FROM ConvenioSaudeEntidade c WHERE c.nome = :nome")})
public class ConvenioSaudeEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @ManyToMany
    @JoinTable(name = "rel_clinica_convenio", joinColumns = {
        @JoinColumn(name = "id_convenio")}, inverseJoinColumns = {
        @JoinColumn(name = "id_clinica")})
    private List<ClinicaEntidade> clinicaList;
    @ManyToMany
    @JoinTable(name = "rel_exame_convenio", joinColumns = {
        @JoinColumn(name = "id_convenio")}, inverseJoinColumns = {
        @JoinColumn(name = "id_exame")})
    private List<ExameEntidade> exameList;

    public ConvenioSaudeEntidade() {
    }

    public ConvenioSaudeEntidade(Integer id) {
        this.id = id;
    }

    public ConvenioSaudeEntidade(Integer id, String nome) {
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

    public List<ClinicaEntidade> getClinicaList() {
        return clinicaList;
    }

    public void setClinicaList(List<ClinicaEntidade> clinicaList) {
        this.clinicaList = clinicaList;
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
        if (!(object instanceof ConvenioSaudeEntidade)) {
            return false;
        }
        ConvenioSaudeEntidade other = (ConvenioSaudeEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ConvenioSaudeEntidade[ id=" + id + " ]";
    }
}
