package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;

import java.util.List;

/**
 * Serviço responsável pelas regras de uso e busca de cartas durante o combate.
 */
public class CartaService {

    /**
     * Aplica os efeitos de uma carta sobre o alvo dentro do contexto de combate atual.
     *
     * @param carta   a carta a ser usada
     * @param contexto o contexto do combate em andamento, com informações do jogador e turno
     * @param alvo    o alvo que receberá os efeitos da carta (inimigo ou jogador)
     */
    public void aplicarCarta(Carta carta, ContextoCombate contexto, Danificavel alvo) {
        carta.usar(contexto, alvo);
    }

    /**
     * Busca uma carta pelo nome dentro de uma lista, ignorando diferenças de maiúsculas e minúsculas.
     *
     * @param cartas lista de cartas onde a busca será realizada
     * @param nome   nome da carta a ser encontrada
     * @return a carta encontrada, ou {@code null} caso nenhuma corresponda ao nome informado
     */
    public Carta buscarPorNome(List<Carta> cartas, String nome) {
        return cartas.stream()
                .filter(c -> c.getNome() != null && c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
