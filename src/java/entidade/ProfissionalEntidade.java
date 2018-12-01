/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "profissional_i")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "discriminator",
        discriminatorType = DiscriminatorType.STRING)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfissionalEntidade.findAll", query = "SELECT p FROM ProfissionalEntidade p"),
    @NamedQuery(name = "ProfissionalEntidade.findById", query = "SELECT p FROM ProfissionalEntidade p WHERE p.id = :id"),
    //@NamedQuery(name = "ProfissionalEntidade.findByIdEquipe", query = "SELECT p FROM ProfissionalEntidade p WHERE p.idEquipe = :idEquipe"),
    @NamedQuery(name = "ProfissionalEntidade.findByNome", query = "SELECT p FROM ProfissionalEntidade p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProfissionalEntidade.findByExperiencia", query = "SELECT p FROM ProfissionalEntidade p WHERE p.experiencia = :experiencia"),
    @NamedQuery(name = "ProfissionalEntidade.findByEndereco", query = "SELECT p FROM ProfissionalEntidade p WHERE p.endereco = :endereco"),
    @NamedQuery(name = "ProfissionalEntidade.findByHonorario", query = "SELECT p FROM ProfissionalEntidade p WHERE p.honorario = :honorario"),
    @NamedQuery(name = "ProfissionalEntidade.findByQualificacao", query = "SELECT p FROM ProfissionalEntidade p WHERE p.qualificacao = :qualificacao"),
    @NamedQuery(name = "ProfissionalEntidade.findByDiscriminator", query = "SELECT p FROM ProfissionalEntidade p WHERE p.discriminator = :discriminator"),
    @NamedQuery(name = "ProfissionalEntidade.findByDataEntradaEquipe", query = "SELECT p FROM ProfissionalEntidade p WHERE p.dataEntradaEquipe = :dataEntradaEquipe"),
    @NamedQuery(name = "ProfissionalEntidade.findByDataSaidaEquipe", query = "SELECT p FROM ProfissionalEntidade p WHERE p.dataSaidaEquipe = :dataSaidaEquipe")})
public abstract class ProfissionalEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    /*
     @Basic(optional = false)
     @Column(name = "id_equipe")
     private int idEquipe;
     */
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "experiencia")
    private String experiencia;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Basic(optional = false)
    @Column(name = "honorario")
    private float honorario;
    @Basic(optional = false)
    @Column(name = "qualificacao")
    private String qualificacao;
    @Basic(optional = false)
    @Column(name = "discriminator")
    private String discriminator;
    @Column(name = "data_entrada_equipe")
    @Temporal(TemporalType.DATE)
    private Date dataEntradaEquipe;
    @Column(name = "data_saida_equipe")
    @Temporal(TemporalType.DATE)
    private Date dataSaidaEquipe;
    @ManyToOne
    @JoinColumn(name = "id_equipe", referencedColumnName = "id")
    private EquipeEntidade equipe;

    public ProfissionalEntidade() {
    }

    public ProfissionalEntidade(Integer id) {
        this.id = id;
    }

    public ProfissionalEntidade(Integer id, String nome, String experiencia, String endereco, float honorario, String qualificacao) {
        this.id = id;

        this.nome = nome;
        this.experiencia = experiencia;
        this.endereco = endereco;
        this.honorario = honorario;
        this.qualificacao = qualificacao;
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

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public float getHonorario() {
        return honorario;
    }

    public void setHonorario(float honorario) {
        this.honorario = honorario;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public EquipeEntidade getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeEntidade equipe) {
        this.equipe = equipe;
    }

    public Date getDataEntradaEquipe() {
        return dataEntradaEquipe;
    }

    public void setDataEntradaEquipe(Date dataEntradaEquipe) {
        this.dataEntradaEquipe = dataEntradaEquipe;
    }

    public Date getDataSaidaEquipe() {
        return dataSaidaEquipe;
    }

    public void setDataSaidaEquipe(Date dataSaidaEquipe) {
        this.dataSaidaEquipe = dataSaidaEquipe;
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
        if (!(object instanceof ProfissionalEntidade)) {
            return false;
        }
        ProfissionalEntidade other = (ProfissionalEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ProfissionalEntidade[ id=" + id + " ]";
    }
}
