package br.edu.ifgoiano.jogo.interfaces;

import br.edu.ifgoiano.jogo.core.ContextoCombate;

/**
 * Contrato para itens ou cartas que podem ser ativados pelo jogador durante o combate ou a exploração.
 * Qualquer objeto que precise produzir um efeito ao ser utilizado deve implementar esta interface,
 * como poções, relíquias e cartas da mão do jogador.
 */
public interface Usavel {

    /**
     * Ativa o objeto, aplicando seus efeitos sobre o alvo indicado.
     *
     * @param contexto informações do combate em andamento (jogador, inimigo e estado atual)
     * @param alvo     entidade sobre a qual o efeito será aplicado
     */
    void usar(ContextoCombate contexto, Danificavel alvo);
}
