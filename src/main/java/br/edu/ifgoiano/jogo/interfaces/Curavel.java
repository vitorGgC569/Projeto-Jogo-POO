package br.edu.ifgoiano.jogo.interfaces;

/**
 * Contrato para entidades que podem recuperar pontos de vida durante o jogo.
 * Entidades como o jogador ou aliados que precisam se curar devem implementar
 * esta interface para que poções e efeitos de cura funcionem corretamente.
 */
public interface Curavel {

    /**
     * Recupera pontos de vida na entidade.
     *
     * @param valor quantidade de pontos de vida a ser restaurada
     */
    void curar(int valor);
}
