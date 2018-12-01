/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author julio
 */
public enum Qualificacao {

    GRADUACAO(1, "Graduação"),
    ESPECIALIZAOCAO(2, "Especialização"),
    POS_GRADUACAO(3, "Pos graduação"),
    MESTRADO(4, "Mestrado"),
    DOUTORADO(5, "Doutorado"),
    RESIDENCIA(6, "Residência");
    private int id;
    private String nome;

    private Qualificacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<Qualificacao> getListQualificacao() {
        return Arrays.asList(Qualificacao.values());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
