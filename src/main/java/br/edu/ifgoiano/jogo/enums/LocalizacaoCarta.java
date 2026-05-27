package br.edu.ifgoiano.jogo.enums;

/**
 * Localização de uma carta dentro do sistema de baralho do jogador.
 * Toda carta sempre está em exatamente um desses locais, e o sistema de
 * combate move cartas entre eles conforme as ações do jogador.
 */
public enum LocalizacaoCarta {

    /** A carta está no baralho, aguardando ser comprada. */
    BARALHO,

    /** A carta está na mão do jogador, disponível para ser jogada neste turno. */
    MAO,

    /** A carta foi jogada e está na pilha de descarte, podendo ser reembaralhada futuramente. */
    DESCARTE,

    /** A carta foi removida permanentemente do baralho e não voltará ao jogo. */
    REMOVIDA
}
