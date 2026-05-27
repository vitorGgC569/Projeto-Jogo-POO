package br.edu.ifgoiano.jogo.enums;

/**
 * Categorias de cartas de combate disponíveis no baralho do jogador.
 * O tipo determina o comportamento geral da carta e como ela interage
 * com outros efeitos e mecânicas do jogo.
 */
public enum TipoCarta {

    /** Carta de ataque — causa dano direto ao inimigo. */
    ATAQUE,

    /** Carta de defesa — gera escudo ou reduz o dano recebido no turno. */
    DEFESA,

    /** Carta de cura — restaura pontos de vida do jogador. */
    CURA,

    /** Carta especial — possui efeito único que não se encaixa nas categorias básicas. */
    ESPECIAL,

    /** Carta de poder — concede bônus permanentes ou efeitos que persistem por vários turnos. */
    PODER
}
