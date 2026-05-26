package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa o protagonista controlado pelo jogador no jogo.
 */
public class Jogador extends Personagem {

    private int ouro;
    private int chaves;
    private int pontuacao;
    private int salasExploradas;
    private int inimigosDerrotados;
    private int cartasJogadas;
    private int moedasColetadas;
    private int pocoes;
    private int nivelMasmorra;
    private Baralho baralho;
    private Mao mao;
    private PilhaDescarte pilhaDescarte;
    private Carteira carteira;

    public Jogador(){}
    public Jogador(int ouro, int chaves, int pontuacao,int salasExploradas,int inimigosDerrotados,int cartasJogadas,int moedasColetadas,int pocoes, int nivelMasmorra, Baralho baralho, Mao mao, PilhaDescarte pilhaDescarte, Carteira carteira) {
        this.chaves = chaves;
        this.pontuacao = pontuacao;
        this.salasExploradas = salasExploradas;
        this.inimigosDerrotados = inimigosDerrotados;
        this.cartasJogadas = cartasJogadas;
        this.moedasColetadas = moedasColetadas;
        this.pocoes = pocoes;
        this.nivelMasmorra = nivelMasmorra;
        this.baralho = baralho;
        this.mao = mao;
        this.pilhaDescarte = pilhaDescarte;
        this.carteira = carteira;
    }
    public int getOuro() {
        return ouro;
    }
    public void setOuro(int ouro) {
        this.ouro = ouro;
    }
    public int getChaves() {
        return chaves;
    }
    public void setChaves(int chaves) {
        this.chaves = chaves;
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    public int getSalasExploradas() {
        return salasExploradas;
    }
    public void setSalasExploradas(int salasExploradas) {
        this.salasExploradas = salasExploradas;
    }
    public int getInimigosDerrotados() {
        return inimigosDerrotados;
    }
    public void setInimigosDerrotados(int inimigosDerrotados) {
        this.inimigosDerrotados = inimigosDerrotados;
    }
    public int getCartasJogadas() {
        return cartasJogadas;
    }
    public void setCartasJogadas(int cartasJogadas) {
        this.cartasJogadas = cartasJogadas;
    }
    public int getMoedasColetadas() {
        return moedasColetadas;
    }
    public void setMoedasColetadas(int moedasColetadas) {
        this.moedasColetadas = moedasColetadas;
    }
    public int getPocoes() {
        return pocoes;
    }
    public void setPocoes(int pocoes) {
        this.pocoes = pocoes;
    }
    public int getNivelMasmorra() {
        return nivelMasmorra;
    }
    public void setNivelMasmorra(int nivelMasmorra) {
        this.nivelMasmorra = nivelMasmorra;
    }
    public Baralho getBaralho() {
        return baralho;
    }
    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }
    public Mao getMao() {
        return mao;
    }
    public void setMao(Mao mao) {
        this.mao = mao;
    }
    public PilhaDescarte getPilhaDescarte() {
        return pilhaDescarte;
    }
    public void setPilhaDescarte(PilhaDescarte pilhaDescarte) {
        this.pilhaDescarte = pilhaDescarte;
    }
    public Carteira getCarteira() {
        return carteira;
    }
    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
}
