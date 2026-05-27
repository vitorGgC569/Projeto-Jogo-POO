package br.edu.ifgoiano.jogo.enums;

/**
 * Raridade de uma carta no jogo, indicando o quão difícil é obtê-la e o quão poderosa ela tende a ser.
 * Cartas mais raras geralmente possuem efeitos mais fortes ou custos menores,
 * e são menos frequentes nas recompensas e lojas da masmorra.
 */
public enum RaridadeCarta {

    /** Carta comum — fácil de encontrar, efeitos básicos. */
    COMUM,

    /** Carta rara — encontrada com menos frequência, efeitos melhores que as comuns. */
    RARA,

    /** Carta épica — difícil de obter, efeitos poderosos. */
    EPICA,

    /** Carta lendária — extremamente rara, efeitos excepcionais que podem mudar o rumo do combate. */
    LENDARIA
}
