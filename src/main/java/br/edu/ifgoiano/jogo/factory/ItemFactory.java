package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Item;

/**
 * Fábrica responsável por criar os diferentes itens consumíveis e relíquias disponíveis no jogo.
 */
public class ItemFactory {

    /**
     * Cria uma Poção de Vida, item consumível que restaura uma quantidade moderada de pontos de vida.
     *
     * @return item consumível que recupera 20 de vida
     */
    public static Item criarPocaoDeVida() {
        return new Item(1, "Pocao de Vida", "Recupera 20 de vida.", 50,
                true, false, 1, 20, 0, 0);
    }

    /**
     * Cria uma Poção Grande, item consumível que restaura uma quantidade elevada de pontos de vida.
     *
     * @return item consumível que recupera 50 de vida
     */
    public static Item criarPocaoGrande() {
        return new Item(2, "Pocao Grande", "Recupera 50 de vida.", 100,
                true, false, 1, 50, 0, 0);
    }

    /**
     * Cria uma Chave Dourada, item utilizado para abrir cofres especiais encontrados na masmorra.
     *
     * @return item de uso único que permite abrir cofres especiais
     */
    public static Item criarChave() {
        return new Item(3, "Chave Dourada", "Abre cofres especiais.", 80,
                true, true, 1, 0, 0, 0);
    }

    /**
     * Cria um Amuleto do Guerreiro, relíquia passiva que aumenta permanentemente o ataque do jogador.
     *
     * @return relíquia que aumenta o ataque em 3
     */
    public static Item criarAmuleto() {
        return new Item(4, "Amuleto do Guerreiro", "Aumenta ataque em 3.", 120,
                false, true, 1, 0, 3, 0);
    }

    /**
     * Cria uma Armadura Leve, item que aumenta permanentemente a defesa do jogador.
     *
     * @return item que aumenta a defesa em 2
     */
    public static Item criarArmadura() {
        return new Item(5, "Armadura Leve", "Aumenta defesa em 2.", 100,
                false, false, 1, 0, 0, 2);
    }
}
