package br.edu.ifgoiano.jogo.interfaces;

import br.edu.ifgoiano.jogo.core.ContextoCombate;

/**
 * Contrato para efeitos que podem ser desencadeados por cartas ou itens durante o combate.
 * Cada implementação define exatamente o que acontece quando o efeito é ativado,
 * como causar dano, curar, aplicar escudo ou comprar cartas.
 */
public interface Efeito {

    /**
     * Aplica o efeito sobre um alvo dentro do contexto de combate atual.
     *
     * @param contexto informações do combate em andamento (jogador, inimigo e estado atual)
     * @param alvo     entidade que receberá o efeito diretamente
     */
    void aplicar(ContextoCombate contexto, Danificavel alvo);
}
