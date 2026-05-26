package br.edu.ifgoiano.jogo.core;

import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Inimigo;

/**
 * Armazena e fornece informacoes dinamicas sobre o combate para a execucao de efeitos.
 */
public class ContextoCombate {

    private Jogador jogador;
    private Inimigo inimigo;

    public ContextoCombate() {}

    public ContextoCombate(Jogador jogador, Inimigo inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    public Jogador getJogador() { return jogador; }
    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public Inimigo getInimigo() { return inimigo; }
    public void setInimigo(Inimigo inimigo) { this.inimigo = inimigo; }
}
