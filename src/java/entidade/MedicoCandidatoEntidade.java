/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "medico_candidato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicoCandidatoEntidade.findAll", query = "SELECT m FROM MedicoCandidatoEntidade m"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findById", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.id = :id"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByNome", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.nome = :nome"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByEndereco", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.endereco = :endereco"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByHonorarioDesejado", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.honorarioDesejado = :honorarioDesejado"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByQualificacao", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.qualificacao = :qualificacao"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByTempoExperiencia", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.tempoExperiencia = :tempoExperiencia"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByDataNascimento", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByCrm", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.crm = :crm"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByEspecializacao", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.especializacao = :especializacao"),
    @NamedQuery(name = "MedicoCandidatoEntidade.findByReferenciasAnteriores", query = "SELECT m FROM MedicoCandidatoEntidade m WHERE m.referenciasAnteriores = :referenciasAnteriores")})
public class MedicoCandidatoEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "honorario_desejado")
    private float honorarioDesejado;
    @Basic(optional = false)
    @Column(name = "qualificacao")
    private String qualificacao;
    @Basic(optional = false)
    @Column(name = "tempo_experiencia")
    private int tempoExperiencia;
    @Basic(optional = false)
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @Column(name = "crm")
    private String crm;
    @Basic(optional = false)
    @Column(name = "especializacao")
    private String especializacao;
    @Basic(optional = false)
    @Column(name = "referencias_anteriores")
    private int referenciasAnteriores;

    public MedicoCandidatoEntidade() {
    }

    public MedicoCandidatoEntidade(Integer id) {
        this.id = id;
    }

    public MedicoCandidatoEntidade(Integer id, String nome, String endereco, float honorarioDesejado, String qualificacao, int tempoExperiencia, Date dataNascimento, String crm, String especializacao, int referenciasAnteriores) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.honorarioDesejado = honorarioDesejado;
        this.qualificacao = qualificacao;
        this.tempoExperiencia = tempoExperiencia;
        this.dataNascimento = dataNascimento;
        this.crm = crm;
        this.especializacao = especializacao;
        this.referenciasAnteriores = referenciasAnteriores;
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

    public float getHonorarioDesejado() {
        return honorarioDesejado;
    }

    public void setHonorarioDesejado(float honorarioDesejado) {
        this.honorarioDesejado = honorarioDesejado;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    public int getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(int tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public int getReferenciasAnteriores() {
        return referenciasAnteriores;
    }

    public void setReferenciasAnteriores(int referenciasAnteriores) {
        this.referenciasAnteriores = referenciasAnteriores;
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
        if (!(object instanceof MedicoCandidatoEntidade)) {
            return false;
        }
        MedicoCandidatoEntidade other = (MedicoCandidatoEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.MedicoCandidatoEntidade[ id=" + id + " ]";
    }
}
