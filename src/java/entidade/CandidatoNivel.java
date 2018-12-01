/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author julio
 */
public enum CandidatoNivel {

    PESSIMO(1, "Péssimo");
    private int id;
    private String nome;

    private CandidatoNivel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
