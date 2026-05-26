package br.edu.ifgoiano.jogo.core;

/**
 * Controla a alternancia de fases entre o jogador e o inimigo durante o combate.
 */
public class Turno {

    private int numero;
    private boolean turnoDoJogador;

    public Turno() {
        this.numero = 1;
        this.turnoDoJogador = true;
    }

    /** Avanca para o proximo turno, alternando entre jogador e inimigo. */
    public void avancar() {
        if (!this.turnoDoJogador) {
            this.numero++;
        }
        this.turnoDoJogador = !this.turnoDoJogador;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public boolean isTurnoDoJogador() { return turnoDoJogador; }
    public void setTurnoDoJogador(boolean turnoDoJogador) { this.turnoDoJogador = turnoDoJogador; }
}
