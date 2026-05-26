package br.edu.ifgoiano.jogo.entidades;

/**
 * Controla os recursos financeiros e moedas obtidos pelo jogador.
 */
public class Carteira {
    private int moedas;
    private int moedasTotaisGanhos;
    private int moedasGastas;

    public Carteira() {}

    public Carteira(int moedas, int moedasTotaisGanhos,  int moedasGastas) {
        this.moedas = moedas;
        this.moedasTotaisGanhos = moedasTotaisGanhos;
        this.moedasGastas = moedasGastas;
    }

    public int getMoedas() {
        return moedas;
    }
    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }
    public int getMoedasTotaisGanhos() {
        return moedasTotaisGanhos;
    }
    public void setMoedasTotaisGanhos(int moedasTotaisGanhos) {
        this.moedasTotaisGanhos = moedasTotaisGanhos;
    }
    public int getMoedasGastas() {
        return moedasGastas;
    }
    public void setMoedasGastas(int moedasGastas) {
        this.moedasGastas = moedasGastas;
    }

    public void adicionar(int valor) {
        if (valor > 0) {
            this.moedas += valor;
            this.moedasTotaisGanhos += valor;
        }
    }

    public boolean gastar(int valor) {
        if (valor > 0 && this.moedas >= valor) {
            this.moedas -= valor;
            this.moedasGastas += valor;
            return true;
        }
        return false;
    }
}
