package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;

import java.util.List;

/**
 * Controla as regras de criacao, busca e ativacao de efeitos de cartas.
 */
public class CartaService {

    /** Aplica a carta contra um alvo dentro do contexto de combate. */
    public void aplicarCarta(Carta carta, ContextoCombate contexto, Danificavel alvo) {
        carta.usar(contexto, alvo);
    }

    /** Retorna a carta com o nome informado da lista, ou null se nao encontrar. */
    public Carta buscarPorNome(List<Carta> cartas, String nome) {
        return cartas.stream()
                .filter(c -> c.getNome() != null && c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
