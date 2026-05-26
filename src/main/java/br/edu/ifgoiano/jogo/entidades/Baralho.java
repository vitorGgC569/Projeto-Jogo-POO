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
    public List<Carta> getcartas() {
        return baralho;
    }
    public void setcartas(List<Carta> cartas) {
        this.baralho = cartas;
    }
}
