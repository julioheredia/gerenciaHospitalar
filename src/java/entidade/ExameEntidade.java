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
@Table(name = "exame")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExameEntidade.findAll", query = "SELECT e FROM ExameEntidade e"),
    @NamedQuery(name = "ExameEntidade.findById", query = "SELECT e FROM ExameEntidade e WHERE e.id = :id"),
    @NamedQuery(name = "ExameEntidade.findByNome", query = "SELECT e FROM ExameEntidade e WHERE e.nome = :nome")})
public class ExameEntidade implements Serializable {

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
        @JoinColumn(name = "id_exame")}, inverseJoinColumns = {
        @JoinColumn(name = "id_convenio")})
    private List<ConvenioSaudeEntidade> convenioList;

    public ExameEntidade() {
    }

    public ExameEntidade(Integer id) {
        this.id = id;
    }

    public ExameEntidade(Integer id, String nome) {
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

    public List<ConvenioSaudeEntidade> getConvenioList() {
        return convenioList;
    }

    public void setConvenioList(List<ConvenioSaudeEntidade> convenioList) {
        this.convenioList = convenioList;
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
        if (!(object instanceof ExameEntidade)) {
            return false;
        }
        ExameEntidade other = (ExameEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ExameEntidade[ id=" + id + " ]";
    }
}
