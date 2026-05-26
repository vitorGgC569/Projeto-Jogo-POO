package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Item;

/**
 * Fabrica para instanciacao de itens consumiveis ou reliquias.
 */
public class ItemFactory {

    public static Item criarPocaoDeVida() {
        return new Item(1, "Pocao de Vida", "Recupera 20 de vida.", 50,
                true, false, 1, 20, 0, 0);
    }

    public static Item criarPocaoGrande() {
        return new Item(2, "Pocao Grande", "Recupera 50 de vida.", 100,
                true, false, 1, 50, 0, 0);
    }

    public static Item criarChave() {
        return new Item(3, "Chave Dourada", "Abre cofres especiais.", 80,
                true, true, 1, 0, 0, 0);
    }

    public static Item criarAmuleto() {
        return new Item(4, "Amuleto do Guerreiro", "Aumenta ataque em 3.", 120,
                false, true, 1, 0, 3, 0);
    }

    public static Item criarArmadura() {
        return new Item(5, "Armadura Leve", "Aumenta defesa em 2.", 100,
                false, false, 1, 0, 0, 2);
    }
}
