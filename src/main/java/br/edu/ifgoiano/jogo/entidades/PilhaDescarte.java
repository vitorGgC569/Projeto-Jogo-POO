package br.edu.ifgoiano.jogo.entidades;

import java.util.List;

/**
 * Representa o monte de cartas descartadas que retornam ao deck quando este esvazia.
 */
public class PilhaDescarte {
    private List<Carta> cartasDescartadas;
    private int quantidadeCartas;

    public PilhaDescarte() {}

    public PilhaDescarte(List<Carta> cartasDescartadas) {
        this.cartasDescartadas = cartasDescartadas;
        this.quantidadeCartas = cartasDescartadas.size();
    }
    public List<Carta> getCartasDescartadas() {
        return cartasDescartadas;
    }
    public void setCartasDescartadas(List<Carta> cartasDescartadas) {
        this.cartasDescartadas = cartasDescartadas;
    }
    public int getQuantidadeCartas() {
        return quantidadeCartas;
    }
    public void setQuantidadeCartas(int quantidadeCartas) {
        this.quantidadeCartas = quantidadeCartas;
    }
}
