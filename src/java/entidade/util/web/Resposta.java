/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.util.web;

/**
 *
 * @author julio
 */
public class Resposta {

    private String texto;
    private Boolean status;

    public Resposta(String texto, Boolean status) {
        this.texto = texto;
        this.status = status;
    }

    public String getTexto() {
        return texto;
    }

    public Boolean getStatus() {
        return status;
    }
}
