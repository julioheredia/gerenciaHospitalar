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
public enum TipoMedicamento {

    ANTIBIOTICO(1, "Antibiótico"),
    ANTINFLAMATORIO(2, "Antinflamatório"),
    ANTITERMICO(3, "Antitérmico"),
    ANALGESICO(4, "Analgésico");
    private int id;
    private String nome;

    private TipoMedicamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<TipoMedicamento> getListTipoMedicamento() {
        return Arrays.asList(TipoMedicamento.values());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
