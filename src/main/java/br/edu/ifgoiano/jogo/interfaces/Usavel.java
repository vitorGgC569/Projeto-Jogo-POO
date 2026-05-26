package br.edu.ifgoiano.jogo.interfaces;

import br.edu.ifgoiano.jogo.core.ContextoCombate;

/**
 * Define itens ou cartas que podem ser ativados em combate ou exploracao.
 */
public interface Usavel {
    void usar(ContextoCombate contexto, Danificavel alvo);
}
