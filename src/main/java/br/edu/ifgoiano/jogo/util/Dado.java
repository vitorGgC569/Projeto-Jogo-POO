package br.edu.ifgoiano.jogo.util;

/**
 * Utilitario para simulacao de rolagem de dados no jogo.
 */
public class Dado {

    private final int faces;

    /**
     * Construtor da classe Dado.
     *
     * @param faces numero de faces do dado
     */
    public Dado(int faces) {
        this.faces = faces;
    }

    /**
     * Rola o dado e retorna um valor entre 1 e o numero de faces.
     *
     * @return valor da rolagem
     */
    public int rolar() {
        return AleatorioUtil.entreInclusive(1, this.faces);
    }

    /**
     * Retorna o numero de faces do dado.
     *
     * @return numero de faces
     */
    public int getFaces() {
        return faces;
    }
}
