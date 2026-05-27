package br.edu.ifgoiano.jogo.interfaces;

/**
 * Contrato para entidades que podem sofrer dano e ser derrotadas no jogo.
 * Jogadores, inimigos e qualquer ser que participe de combate devem implementar
 * esta interface para que ataques e efeitos de dano sejam aplicados a eles.
 */
public interface Danificavel {

    /**
     * Aplica dano à entidade, reduzindo seus pontos de vida.
     *
     * @param dano quantidade de dano a ser sofrida
     */
    void receberDano(int dano);

    /**
     * Verifica se a entidade ainda está viva (pontos de vida acima de zero).
     *
     * @return {@code true} se a entidade ainda está viva; {@code false} se foi derrotada
     */
    boolean estaVivo();
}
