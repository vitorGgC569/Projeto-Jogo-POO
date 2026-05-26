package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por modificar a quantidade de moedas do jogador.
 */
public class EfeitoMoeda extends EfeitoBase implements Efeito {
    private int moedasGanhos;

    public EfeitoMoeda() {}

    public EfeitoMoeda(int moedasGanhos) {
        this.moedasGanhos = moedasGanhos;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        if (contexto != null && contexto.getJogador() != null
                && contexto.getJogador().getCarteira() != null) {
            contexto.getJogador().getCarteira().adicionar(this.moedasGanhos);
        }
    }

    public int getMoedasGanhos() { return moedasGanhos; }
    public void setMoedasGanhos(int moedasGanhos) { this.moedasGanhos = moedasGanhos; }
}
