/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 */
@Entity
@Table(name = "setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SetorEntidade.findAll", query = "SELECT s FROM SetorEntidade s"),
    @NamedQuery(name = "SetorEntidade.findById", query = "SELECT s FROM SetorEntidade s WHERE s.id = :id"),
    @NamedQuery(name = "SetorEntidade.findByNome", query = "SELECT s FROM SetorEntidade s WHERE s.nome = :nome"),
    @NamedQuery(name = "SetorEntidade.findByAreaHospitalar", query = "SELECT s FROM SetorEntidade s WHERE s.areaHospitalar = :areaHospitalar")})
public class SetorEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(name = "area_hospitalar")
    private AreaHospitalar areaHospitalar;
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EnfermeiroEntidade> enfermeiroList;
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PatrimonioEntidade> patrimonioList;
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MedicamentoEntidade> medicamentoList;

    public SetorEntidade() {
    }

    public SetorEntidade(Integer id) {
        this.id = id;
    }

    public SetorEntidade(Integer id, String nome) {
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

    public AreaHospitalar getAreaHospitalar() {
        return areaHospitalar;
    }

    public void setAreaHospitalar(AreaHospitalar areaHospitalar) {
        this.areaHospitalar = areaHospitalar;
    }

    public List<EnfermeiroEntidade> getEnfermeiroList() {
        return enfermeiroList;
    }

    public void setEnfermeiroList(List<EnfermeiroEntidade> enfermeiroList) {
        this.enfermeiroList = enfermeiroList;
    }

    public List<PatrimonioEntidade> getPatrimonioList() {
        return patrimonioList;
    }

    public void setPatrimonioList(List<PatrimonioEntidade> patrimonioList) {
        this.patrimonioList = patrimonioList;
    }

    public List<MedicamentoEntidade> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<MedicamentoEntidade> medicamentoList) {
        this.medicamentoList = medicamentoList;
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
        if (!(object instanceof SetorEntidade)) {
            return false;
        }
        SetorEntidade other = (SetorEntidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.SetorEntidade[ id=" + id + " ]";
    }
}
