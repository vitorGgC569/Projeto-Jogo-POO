package br.edu.ifgoiano.jogo.enums;

/**
 * Ações que um inimigo pode escolher realizar durante o seu turno de combate.
 * A inteligência artificial do inimigo seleciona uma dessas ações a cada rodada,
 * determinando como ele se comporta frente ao jogador.
 */
public enum AcaoInimigo {

    /** Ataque comum que causa dano normal ao jogador. */
    ATAQUE,

    /** Ataque poderoso que causa dano aumentado ou ignora parte da defesa do jogador. */
    ATAQUE_CRITICO,

    /** O inimigo se prepara para defender, reduzindo o dano recebido no próximo turno. */
    DEFESA,

    /** O inimigo aplica um bônus em si mesmo, como aumento de força ou velocidade. */
    BUFF,

    /** O inimigo recupera pontos de vida. */
    CURA
}
