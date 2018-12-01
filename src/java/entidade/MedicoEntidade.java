/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@DiscriminatorValue(value = "M")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicoEntidade.findByCrm", query = "SELECT m FROM MedicoEntidade m WHERE m.crm = :crm")})
public class MedicoEntidade  extends ProfissionalEntidade{

    @Basic(optional = false)
    @Column(name = "crm")
    private String crm;

    public MedicoEntidade() {
    }

    public MedicoEntidade(String crm) {
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
