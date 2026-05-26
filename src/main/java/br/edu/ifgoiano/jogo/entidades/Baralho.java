package br.edu.ifgoiano.jogo.entidades;

import java.util.List;

/**
 * Representa o deck completo de cartas do jogador.
 */
public class Baralho {

    private int tamanhoMaximo;
    private int quantidadeCartas;
    private boolean embaralhado;
    private List<Carta> baralho;

    public Baralho() {}

    public Baralho(int tamanhoMaximo, int quantidadeCartas, boolean embaralhado, List<Carta> cartas) {
        this.tamanhoMaximo = tamanhoMaximo;
        this.quantidadeCartas = quantidadeCartas;
        this.embaralhado = embaralhado;
        this.baralho = cartas;
    }
    public int getTamanhoMaximo() {
        return tamanhoMaximo;
    }
    public void setTamanhoMaximo(int tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
    }
    public int getQuantidadeCartas() {
        return quantidadeCartas;
    }
    public void setQuantidadeCartas(int quantidadeCartas) {
        this.quantidadeCartas = quantidadeCartas;
    }
    public boolean isEmbaralhado() {
        return embaralhado;
    }
    public void setEmbaralhado(boolean embaralhado) {
        this.embaralhado = embaralhado;
    }
    public List<Carta> getCartas() {
        return baralho;
    }
    public void setCartas(List<Carta> cartas) {
        this.baralho = cartas;
    }

    public void embaralhar() {
        java.util.Collections.shuffle(this.baralho);
        this.embaralhado = true;
    }

    /**
     * Remove e retorna a primeira carta do topo do baralho.
     * @return a primeira carta, ou null se o baralho está vazio
     */
    public Carta comprar() {
        if (this.baralho == null || this.baralho.isEmpty()) return null;
        return this.baralho.remove(0);
    }

    public boolean estaVazio() {
        return this.baralho == null || this.baralho.isEmpty();
    }

    public void adicionarCarta(Carta carta) {
        if (this.baralho != null) this.baralho.add(carta);
    }
}
