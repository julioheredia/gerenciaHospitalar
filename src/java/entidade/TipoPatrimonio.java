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
public enum TipoPatrimonio {

    EQUIPAMENTO_CLINICO(1, "Equipamento Clínico"),
    EQUIPAMENTO_CIRURGICO(2, "Equipamento Cirurgico"),
    EQUIPAMENTO_OPERACIONAL(3, "Equipamento Operacional"),
    EQUIPAMENTO_ESPECIALIZADO(4, "Equipamento Especializado"),
    MATERIAL_CONSUMO(5, "Material de Consumo"),
    MATERIAL_PERSISTENTE(6, "Material Persistente");
    private int id;
    private String nome;

    private TipoPatrimonio(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<TipoPatrimonio> getListTipoPatrimonio() {
        return Arrays.asList(TipoPatrimonio.values());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
