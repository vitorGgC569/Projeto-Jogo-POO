package br.edu.ifgoiano.jogo.enums;

/**
 * Tipos de efeitos que cartas ou itens podem produzir ao serem ativados.
 * Cada constante representa uma categoria de ação que o sistema de combate
 * sabe interpretar e resolver corretamente.
 */
public enum TipoEfeito {

    /** Causa dano direto a um alvo. */
    DANO,

    /** Adiciona pontos de escudo ao alvo, absorvendo parte do próximo dano recebido. */
    ESCUDO,

    /** Restaura pontos de vida do alvo. */
    CURA,

    /** Faz o jogador comprar uma carta adicional do baralho. */
    COMPRA,

    /** Concede moedas ao jogador para gastar na loja ou em custos de cartas. */
    MOEDA,

    /** Aumenta os pontos de poder do jogador, podendo potencializar outros efeitos. */
    PODER,

    /** Causa dano multiplicado, geralmente escalando com algum atributo ou condição de combate. */
    DANO_MULTIPLICADO
}
