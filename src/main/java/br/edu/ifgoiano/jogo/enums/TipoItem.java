package br.edu.ifgoiano.jogo.enums;

/**
 * Categorias de itens que o jogador pode encontrar ou comprar durante a aventura.
 * O tipo define como o item se comporta no inventário e quando pode ser utilizado.
 */
public enum TipoItem {

    /** Poção — item consumível que produz um efeito imediato ao ser usado, como curar vida. */
    POCAO,

    /** Relíquia — item passivo que concede bônus permanentes enquanto estiver no inventário. */
    RELIQUIA,

    /** Chave — item especial necessário para abrir portas ou baús trancados na masmorra. */
    CHAVE,

    /** Artefato — item poderoso com efeitos únicos, podendo ser ativo ou passivo. */
    ARTEFATO
}
