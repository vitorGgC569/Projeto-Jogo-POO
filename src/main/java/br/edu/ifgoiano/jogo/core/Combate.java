package br.edu.ifgoiano.jogo.core;

import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;

/**
 * Controla os dados e a logica estrutural de uma sessao de combate ativa.
 */
public class Combate {

    private Jogador jogador;
    private Inimigo inimigo;
    private Turno turno;
    private boolean ativo;
    private ContextoCombate contexto;

    public Combate() {}

    public Combate(Jogador jogador, Inimigo inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.turno = new Turno();
        this.ativo = true;
        this.contexto = new ContextoCombate(jogador, inimigo);
    }

    /** Verifica se o combate terminou (jogador ou inimigo derrotado). */
    public boolean estaFinalizado() {
        return !jogador.estaVivo() || !inimigo.estaVivo();
    }

    /** Retorna true se o jogador venceu o combate. */
    public boolean jogadorVenceu() {
        return !inimigo.estaVivo() && jogador.estaVivo();
    }

    public Jogador getJogador() { return jogador; }
    public void setJogador(Jogador jogador) { this.jogador = jogador; }

    public Inimigo getInimigo() { return inimigo; }
    public void setInimigo(Inimigo inimigo) { this.inimigo = inimigo; }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public ContextoCombate getContexto() { return contexto; }
    public void setContexto(ContextoCombate contexto) { this.contexto = contexto; }
}
