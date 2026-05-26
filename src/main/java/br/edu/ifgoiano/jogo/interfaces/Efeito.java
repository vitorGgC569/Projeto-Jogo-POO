package br.edu.ifgoiano.jogo.interfaces;

import br.edu.ifgoiano.jogo.core.ContextoCombate;

/**
 * Interface que define a execucao de efeitos de cartas e itens.
 */
public interface Efeito {
    void aplicar(ContextoCombate contexto, Danificavel alvo);
}
