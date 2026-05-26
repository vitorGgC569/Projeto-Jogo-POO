package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por modificar a quantidade de moedas do jogador.
 */
public class EfeitoMoeda extends EfeitoBase implements Efeito {
    private int moedasGanhos;

    public EfeitoMoeda(int moedasGanhos,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano, nome, descricao, valor, duracao, permanente);
        this.moedasGanhos = moedasGanhos;
    }
    public int getMoedasGanhos() {
        return moedasGanhos;
    }
    public void setMoedasGanhos(int moedasGanhos) {
        this.moedasGanhos = moedasGanhos;
    }
}
