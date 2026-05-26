package br.edu.ifgoiano.jogo.core;

import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Masmorra;

/**
 * Controla o loop e o fluxo geral do jogo.
 */
public class Jogo {

    private Jogador jogador;
    private Masmorra masmorra;
    private EstadoJogo estadoJogo;
    private Combate combateAtual;

    public Jogo() {
        this.estadoJogo = new EstadoJogo();
    }

    public Jogo(Jogador jogador, Masmorra masmorra) {
        this.jogador = jogador;
        this.masmorra = masmorra;
        this.estadoJogo = new EstadoJogo();
    }

    public Jogador getJogador() { return jogador; }
    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public Masmorra getMasmorra() { return masmorra; }
    public void setMasmorra(Masmorra masmorra) { this.masmorra = masmorra; }

    public EstadoJogo getEstadoJogo() { return estadoJogo; }
    public void setEstadoJogo(EstadoJogo estadoJogo) { this.estadoJogo = estadoJogo; }

    public Combate getCombateAtual() { return combateAtual; }
    public void setCombateAtual(Combate combateAtual) { this.combateAtual = combateAtual; }
}
