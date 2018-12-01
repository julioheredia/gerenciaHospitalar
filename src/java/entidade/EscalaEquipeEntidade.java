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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "escala_equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EscalaEquipeEntidade.findAll", query = "SELECT e FROM EscalaEquipeEntidade e"),
    @NamedQuery(name = "EscalaEquipeEntidade.findById", query = "SELECT e FROM EscalaEquipeEntidade e WHERE e.id = :id"),
    //@NamedQuery(name = "EscalaEquipeEntidade.findByIdEquipe", query = "SELECT e FROM EscalaEquipeEntidade e WHERE e.idEquipe = :idEquipe"),
    @NamedQuery(name = "EscalaEquipeEntidade.findByHorario", query = "SELECT e FROM EscalaEquipeEntidade e WHERE e.horario = :horario"),
    @NamedQuery(name = "EscalaEquipeEntidade.findByIntervalo", query = "SELECT e FROM EscalaEquipeEntidade e WHERE e.intervalo = :intervalo")})
public class EscalaEquipeEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    /*
     @Basic(optional = false)
     @Column(name = "id_equipe")
     private String idEquipe;
     */
    @Column(name = "horario")
    @Temporal(TemporalType.TIME)
    private Date horario;
    @Column(name = "intervalo")
    private Integer intervalo;
    @OneToOne
    @JoinColumn(name = "id_equipe", referencedColumnName = "id")
    private EquipeEntidade equipe;

    public EscalaEquipeEntidade() {
    }

    public EscalaEquipeEntidade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public EquipeEntidade getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeEntidade equipe) {
        this.equipe = equipe;
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
        if (!(object instanceof EscalaEquipeEntidade)) {
            return false;
        }
        EscalaEquipeEntidade other = (EscalaEquipeEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.EscalaEquipeEntidade[ id=" + id + " ]";
    }
}
