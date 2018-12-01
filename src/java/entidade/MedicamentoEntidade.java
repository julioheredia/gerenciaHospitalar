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
@Table(name = "medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoEntidade.findAll", query = "SELECT m FROM MedicamentoEntidade m"),
    @NamedQuery(name = "MedicamentoEntidade.findByCodigo", query = "SELECT m FROM MedicamentoEntidade m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MedicamentoEntidade.findByNome", query = "SELECT m FROM MedicamentoEntidade m WHERE m.nome = :nome"),
    @NamedQuery(name = "MedicamentoEntidade.findByTipo", query = "SELECT m FROM MedicamentoEntidade m WHERE m.tipo = :tipo"), //@NamedQuery(name = "MedicamentoEntidade.findByIdSetor", query = "SELECT m FROM MedicamentoEntidade m WHERE m.idSetor = :idSetor")
})
public class MedicamentoEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoMedicamento tipo;
    //@Column(name = "id_setor")
    //private Integer idSetor;
    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id")
    private SetorEntidade setor;

    public MedicamentoEntidade() {
    }

    public MedicamentoEntidade(Integer codigo) {
        this.codigo = codigo;
    }

    public MedicamentoEntidade(Integer codigo, String nome, TipoMedicamento tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoMedicamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMedicamento tipo) {
        this.tipo = tipo;
    }

    public SetorEntidade getSetor() {
        return setor;
    }

    public void setSetor(SetorEntidade setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoEntidade)) {
            return false;
        }
        MedicamentoEntidade other = (MedicamentoEntidade) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.MedicamentoEntidade[ codigo=" + codigo + " ]";
    }
}
