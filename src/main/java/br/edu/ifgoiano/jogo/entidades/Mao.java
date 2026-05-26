package br.edu.ifgoiano.jogo.entidades;

import java.util.List;

/**
 * Representa as cartas ativas na mao do jogador durante seu turno no combate.
 */
public class Mao {
    private int limiteCartas;
    private List<Carta> mao;

    public Mao() {
    }
    public Mao(List<Carta> cartas, int limiteCartas) {
        this.mao = cartas;
        this.limiteCartas = limiteCartas;
    }
    public List<Carta> getCartas() {
        return mao;
    }
    public void setCartas(List<Carta> cartas) {
        this.mao = cartas;
    }
    public int getLimiteCartas() {
        return limiteCartas;
    }
    public void setLimiteCartas(int limiteCartas) {
        this.limiteCartas = limiteCartas;
    }

    public boolean estaCheia() {
        return this.mao != null && this.mao.size() >= this.limiteCartas;
    }

    public void adicionarCarta(Carta carta) {
        if (this.mao != null && !estaCheia()) this.mao.add(carta);
    }

    public boolean removerCarta(Carta carta) {
        return this.mao != null && this.mao.remove(carta);
    }
}
