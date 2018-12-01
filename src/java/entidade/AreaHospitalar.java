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
public enum AreaHospitalar {

    AREAR_CRITICA(1, "Área crítica"),
    AREAR_SEMICRITICA(2, "Área semicrítica"),
    AREAR_NAO_CRITICA(3, "Área não crítica");
    private int id;
    private String nome;

    private AreaHospitalar(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<AreaHospitalar> getListAreaHospitalar() {
        return Arrays.asList(AreaHospitalar.values());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
