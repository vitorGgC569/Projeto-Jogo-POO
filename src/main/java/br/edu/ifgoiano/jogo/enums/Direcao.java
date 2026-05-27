package br.edu.ifgoiano.jogo.enums;

/**
 * Direções cardinais que o jogador pode encarar ao se mover pela masmorra.
 * Cada constante representa um dos quatro pontos cardeais e fornece métodos
 * para calcular rotações e o deslocamento correspondente na grade do mapa.
 */
public enum Direcao {

    /** Direção Norte — move o jogador para cima no mapa (linha diminui). */
    NORTE,

    /** Direção Sul — move o jogador para baixo no mapa (linha aumenta). */
    SUL,

    /** Direção Leste — move o jogador para a direita no mapa (coluna aumenta). */
    LESTE,

    /** Direção Oeste — move o jogador para a esquerda no mapa (coluna diminui). */
    OESTE;

    /**
     * Retorna a direção à esquerda desta, como se o jogador virasse 90° para a esquerda.
     *
     * @return direção resultante ao virar para a esquerda
     */
    public Direcao viraEsquerda() {
        return switch (this) {
            case NORTE -> OESTE;
            case OESTE -> SUL;
            case SUL   -> LESTE;
            case LESTE -> NORTE;
        };
    }

    /**
     * Retorna a direção à direita desta, como se o jogador virasse 90° para a direita.
     *
     * @return direção resultante ao virar para a direita
     */
    public Direcao viraDireita() {
        return switch (this) {
            case NORTE -> LESTE;
            case LESTE -> SUL;
            case SUL   -> OESTE;
            case OESTE -> NORTE;
        };
    }

    /**
     * Retorna a variação de linha ao dar um passo nesta direção na grade do mapa.
     * Norte retorna -1 (sobe), Sul retorna +1 (desce), Leste e Oeste retornam 0.
     *
     * @return delta de linha: -1, 0 ou +1
     */
    public int getDeltaLinha() {
        return switch (this) {
            case NORTE -> -1;
            case SUL   ->  1;
            case LESTE, OESTE -> 0;
        };
    }

    /**
     * Retorna a variação de coluna ao dar um passo nesta direção na grade do mapa.
     * Leste retorna +1 (avança), Oeste retorna -1 (recua), Norte e Sul retornam 0.
     *
     * @return delta de coluna: -1, 0 ou +1
     */
    public int getDeltaColuna() {
        return switch (this) {
            case LESTE ->  1;
            case OESTE -> -1;
            case NORTE, SUL -> 0;
        };
    }
}
