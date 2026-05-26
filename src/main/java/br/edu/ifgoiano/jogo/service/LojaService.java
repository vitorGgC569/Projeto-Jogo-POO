package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Loja;

/**
 * Controla a geracao e a compra de mercadorias (cartas e itens) da loja.
 */
public class LojaService {

    private final JogadorService jogadorService = new JogadorService();

    /** Tenta comprar uma carta. Retorna true se a compra foi bem sucedida. */
    public boolean comprarCarta(Jogador jogador, Loja loja, Carta carta) {
        if (!loja.getCartasVenda().contains(carta)) return false;
        if (!jogador.getCarteira().gastar(carta.getPreco())) return false;
        loja.getCartasVenda().remove(carta);
        jogadorService.adicionarCarta(jogador, carta);
        return true;
    }

    /** Tenta comprar um item. Retorna true se a compra foi bem sucedida. */
    public boolean comprarItem(Jogador jogador, Loja loja, Item item) {
        if (!loja.getItens().contains(item)) return false;
        if (!jogador.getCarteira().gastar(item.getPreco())) return false;
        loja.getItens().remove(item);
        return true;
    }
}
