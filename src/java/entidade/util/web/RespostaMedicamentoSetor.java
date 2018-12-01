/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

/**
 *
 * @author julio
 */
public class RespostaMedicamentoSetor extends Resposta {

    private Boolean areaNaoCadastrada;
    private Boolean remedioProibido;
    private Boolean tipoRemedioProibido;

    public RespostaMedicamentoSetor(Boolean areaNaoCadastrada, Boolean remedioProibido, Boolean tipoRemedioProibido, String texto, Boolean status) {
        super(texto, status);
        this.areaNaoCadastrada = areaNaoCadastrada;
        this.remedioProibido = remedioProibido;
        this.tipoRemedioProibido = tipoRemedioProibido;
    }

    public Boolean getAreaNaoCadastrada() {
        return areaNaoCadastrada;
    }

    public Boolean getRemedioProibido() {
        return remedioProibido;
    }

    public Boolean getTipoRemedioProibido() {
        return tipoRemedioProibido;
    }
}
