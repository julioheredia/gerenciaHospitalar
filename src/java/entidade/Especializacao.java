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
public enum Especializacao {

    ACUMPUTURA(1, "Acupuntura"),
    ALERGIA(2, "Alergia"),
    ANESTESIOLOGIA(3, "Anestesiologia"),
    ANGIOLOGIA(4, "Angiologia"),
    CANCEROLOGIA(5, "Cancerologia"),
    CARDIOLOGIA(6, "Cardiologia"),
    CIRURGIA_CARDIOVASCULAR(7, "Cirurgia Cardiovascular"),
    CIRURGIA_DA_MAO(8, "Cirurgia da Mão"),
    CIRURGIA_GERAL(9, "Cirurgia Geral"),
    CIRURGIA_PEDIARICA(10, "Cirurgia Pediátrica"),
    CIRURGIA_PLASTICA(11, "Cirurgia Plástica"),
    CIRURGIA_TORACICA(12, "Cirurgia Torácica"),
    CIRURGIA_VASCULAR(13, "Cirurgia Vascular"),
    CLINICA_MEDICA(14, "Clínica Médica"),
    COLOPROCTOLOGIA(15, "Coloproctologia"),
    DERMATOLOGIA(16, "Dermatologia"),
    ENDOCRINOLOGIA(17, "Endocrinologia"),
    ENDOSCOPIA(18, "Endoscopia"),
    GASTROENTEROLOGIA(19, "Gastroenterologia"),
    GENETICA_MEDICA(20, "Genética médica"),
    GERIATRIA(21, "Geriatria"),
    HOMEOPATIA(22, "Homeopatia"),
    INFECTOLOGIA(23, "Infectologia"),
    MASTOLOGIA(24, "Mastologia"),
    NEUROCIRURGIA(25, "Neurocirurgia"),
    NEUROLOGIA(26, "Neurologia"),
    NUTROLOGIA(27, "Nutrologia"),
    OFTALMOLOGIA(28, "Oftalmologia"),
    ORTOPEDIA(29, "Ortopedia"),
    OTORRINOLARINGOLOGIA(30, "Otorrinolaringologia"),
    PATOLOGIA(31, "Patologia"),
    PEDIATRIA(32, "Pediatrias"),
    PNEUMOLOGIA(33, "Pneumologia"),
    PSIQUIATRIA(34, "Psiquiatria"),
    RADIOLOGIA(35, "Radiologia"),
    RADIOTERAPIA(36, "Radioterapia"),
    REUMATOLOGIA(37, "Reumatologia"),
    UROLOGIA(38, "Urologia");
    private int id;
    private String nome;

    private Especializacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<Especializacao> getListEspecializacao() {
        return Arrays.asList(Especializacao.values());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
