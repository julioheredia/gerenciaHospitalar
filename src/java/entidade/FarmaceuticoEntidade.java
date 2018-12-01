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
@DiscriminatorValue(value = "F")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmaceuticoEntidade.findByCrf", query = "SELECT f FROM FarmaceuticoEntidade f WHERE f.crf = :crf"),
    @NamedQuery(name = "FarmaceuticoEntidade.findByLimiteMedicamento", query = "SELECT f FROM FarmaceuticoEntidade f WHERE f.limiteMedicamento = :limiteMedicamento")})
public class FarmaceuticoEntidade extends ProfissionalEntidade {

    @Basic(optional = false)
    @Column(name = "crf")
    private String crf;
    @Basic(optional = false)
    @Column(name = "limite_medicamento")
    private int limiteMedicamento;

    public FarmaceuticoEntidade() {
    }

    public FarmaceuticoEntidade(String crf, int limiteMedicamento) {
        this.crf = crf;
        this.limiteMedicamento = limiteMedicamento;
    }

    public String getCrf() {
        return crf;
    }

    public void setCrf(String crf) {
        this.crf = crf;
    }

    public int getLimiteMedicamento() {
        return limiteMedicamento;
    }

    public void setLimiteMedicamento(int limiteMedicamento) {
        this.limiteMedicamento = limiteMedicamento;
    }
}
