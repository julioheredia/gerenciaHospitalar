/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author julio
 */
public enum StatusCandidato {

    BAIXO(1, "Não Recomendado"),
    MEDIO_BAIXO(2, "Não Recomendado"),
    MEDIANO(3, "Recomendado"),
    MEDIO_ALTO(4, "Recomendado"),
    ALTO(5, "Recomendado nível de excelência");
    private int estrela;
    private String recomendacao;

    private StatusCandidato(int estrela, String recomendacao) {
        this.estrela = estrela;
        this.recomendacao = recomendacao;
    }

    public int getEstrela() {
        return estrela;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public static StatusCandidato valueOf(int estrela) {
        switch (estrela) {
            case 1:
                return BAIXO;
            case 2:
                return MEDIO_BAIXO;
            case 3:
                return MEDIANO;
            case 5:
                return MEDIO_ALTO;
            case 6:
                return ALTO;
            default:
                throw new IllegalArgumentException("Codigo '" + estrela + "' de Status nao suportado.");
        }
    }
}
