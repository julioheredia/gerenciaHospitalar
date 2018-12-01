/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

/**
 *
 * @author julio
 */
public class RespostaPatrimonioSetor extends Resposta {

    private Boolean areaNaoCadastrada;
    private Boolean tipoNaoCadastrado;
    private Boolean patrimonioProibido;

    public RespostaPatrimonioSetor(Boolean areaNaoCadastrada, Boolean tipoNaoCadastrado, Boolean patrimonioProibido, String texto, Boolean status) {
        super(texto, status);
        this.areaNaoCadastrada = areaNaoCadastrada;
        this.tipoNaoCadastrado = tipoNaoCadastrado;
        this.patrimonioProibido = patrimonioProibido;
    }

    public Boolean getAreaNaoCadastrada() {
        return areaNaoCadastrada;
    }

    public Boolean getTipoNaoCadastrado() {
        return tipoNaoCadastrado;
    }

    public Boolean getPatrimonioProibido() {
        return patrimonioProibido;
    }
}
