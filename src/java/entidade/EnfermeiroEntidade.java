/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@Table(name = "profissional_i")
@DiscriminatorValue(value = "E")
@XmlRootElement
@NamedQueries({
    //@NamedQuery(name = "EnfermeiroEntidade.findByIdSetor", query = "SELECT e FROM EnfermeiroEntidade e WHERE e.idSetor = :idSetor"),
    @NamedQuery(name = "EnfermeiroEntidade.findByCre", query = "SELECT e FROM EnfermeiroEntidade e WHERE e.cre = :cre"),
    @NamedQuery(name = "EnfermeiroEntidade.findByTempoExperiencia", query = "SELECT e FROM EnfermeiroEntidade e WHERE e.tempoExperiencia = :tempoExperiencia")})
public class EnfermeiroEntidade extends ProfissionalEntidade {

    /*
     @Basic(optional = false)
     @Column(name = "id_setor")
     private int idSetor;
     */
    @Basic(optional = false)
    @Column(name = "cre")
    private String cre;
    @Basic(optional = false)
    @Column(name = "tempo_experiencia")
    private int tempoExperiencia;
    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    private SetorEntidade setor;

    public EnfermeiroEntidade() {
    }

    public EnfermeiroEntidade(String cre, int tempoExperiencia) {
        this.cre = cre;
        this.tempoExperiencia = tempoExperiencia;
    }

    public String getCre() {
        return cre;
    }

    public void setCre(String cre) {
        this.cre = cre;
    }

    public int getTempoExperiencia() {
        return tempoExperiencia;
    }

    public void setTempoExperiencia(int tempoExperiencia) {
        this.tempoExperiencia = tempoExperiencia;
    }

    public SetorEntidade getSetor() {
        return setor;
    }

    public void setSetor(SetorEntidade setor) {
        this.setor = setor;
    }
}
