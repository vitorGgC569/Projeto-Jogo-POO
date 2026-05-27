package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.Direcao;

/**
 * Representa o protagonista controlado pelo jogador no jogo.
 * Além dos atributos de combate herdados de Personagem, o jogador possui
 * recursos próprios como ouro, chaves, baralho de cartas, mão de cartas,
 * pilha de descarte e carteira de moedas.
 */
public class Jogador extends Personagem {

    private Direcao direcao = Direcao.NORTE;
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

    /**
     * Cria um jogador vazio sem nenhum atributo definido.
     * A direção inicial é configurada como NORTE por padrão.
     */
    public Jogador(){}

    /**
     * Cria um jogador com todos os atributos de progresso e recursos inicializados.
     *
     * @param ouro a quantidade inicial de ouro
     * @param chaves a quantidade inicial de chaves
     * @param pontuacao a pontuação inicial do jogador
     * @param salasExploradas o número de salas já exploradas
     * @param inimigosDerrotados o número de inimigos já derrotados
     * @param cartasJogadas o número total de cartas já jogadas
     * @param moedasColetadas o número total de moedas coletadas
     * @param pocoes a quantidade de poções disponíveis
     * @param nivelMasmorra o nível atual da masmorra em que o jogador está
     * @param baralho o baralho de cartas do jogador
     * @param mao a mão de cartas ativas do jogador
     * @param pilhaDescarte a pilha de cartas já descartadas
     * @param carteira a carteira com as moedas do jogador
     */
    public Jogador(int ouro, int chaves, int pontuacao,int salasExploradas,int inimigosDerrotados,int cartasJogadas,int moedasColetadas,int pocoes, int nivelMasmorra, Baralho baralho, Mao mao, PilhaDescarte pilhaDescarte, Carteira carteira) {
        this.ouro = ouro;
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

    /**
     * Retorna a quantidade atual de ouro do jogador.
     *
     * @return a quantidade de ouro
     */
    public int getOuro() {
        return ouro;
    }

    /**
     * Define a quantidade de ouro do jogador.
     *
     * @param ouro o novo valor de ouro
     */
    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    /**
     * Retorna a quantidade atual de chaves do jogador.
     *
     * @return a quantidade de chaves
     */
    public int getChaves() {
        return chaves;
    }

    /**
     * Define a quantidade de chaves do jogador.
     *
     * @param chaves o novo valor de chaves
     */
    public void setChaves(int chaves) {
        this.chaves = chaves;
    }

    /**
     * Retorna a pontuação atual do jogador.
     *
     * @return a pontuação
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define a pontuação do jogador.
     *
     * @param pontuacao o novo valor de pontuação
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna o número de salas exploradas pelo jogador.
     *
     * @return o total de salas exploradas
     */
    public int getSalasExploradas() {
        return salasExploradas;
    }

    /**
     * Define o número de salas exploradas pelo jogador.
     *
     * @param salasExploradas o novo total de salas exploradas
     */
    public void setSalasExploradas(int salasExploradas) {
        this.salasExploradas = salasExploradas;
    }

    /**
     * Retorna o número de inimigos derrotados pelo jogador.
     *
     * @return o total de inimigos derrotados
     */
    public int getInimigosDerrotados() {
        return inimigosDerrotados;
    }

    /**
     * Define o número de inimigos derrotados pelo jogador.
     *
     * @param inimigosDerrotados o novo total de inimigos derrotados
     */
    public void setInimigosDerrotados(int inimigosDerrotados) {
        this.inimigosDerrotados = inimigosDerrotados;
    }

    /**
     * Retorna o número total de cartas jogadas pelo jogador.
     *
     * @return o total de cartas jogadas
     */
    public int getCartasJogadas() {
        return cartasJogadas;
    }

    /**
     * Define o número total de cartas jogadas pelo jogador.
     *
     * @param cartasJogadas o novo total de cartas jogadas
     */
    public void setCartasJogadas(int cartasJogadas) {
        this.cartasJogadas = cartasJogadas;
    }

    /**
     * Retorna o número total de moedas coletadas pelo jogador durante a partida.
     *
     * @return o total de moedas coletadas
     */
    public int getMoedasColetadas() {
        return moedasColetadas;
    }

    /**
     * Define o número total de moedas coletadas pelo jogador.
     *
     * @param moedasColetadas o novo total de moedas coletadas
     */
    public void setMoedasColetadas(int moedasColetadas) {
        this.moedasColetadas = moedasColetadas;
    }

    /**
     * Retorna a quantidade de poções disponíveis para o jogador.
     *
     * @return a quantidade de poções
     */
    public int getPocoes() {
        return pocoes;
    }

    /**
     * Define a quantidade de poções disponíveis para o jogador.
     *
     * @param pocoes o novo valor de poções
     */
    public void setPocoes(int pocoes) {
        this.pocoes = pocoes;
    }

    /**
     * Retorna o nível atual da masmorra em que o jogador se encontra.
     *
     * @return o nível da masmorra
     */
    public int getNivelMasmorra() {
        return nivelMasmorra;
    }

    /**
     * Define o nível da masmorra em que o jogador se encontra.
     *
     * @param nivelMasmorra o novo nível da masmorra
     */
    public void setNivelMasmorra(int nivelMasmorra) {
        this.nivelMasmorra = nivelMasmorra;
    }

    /**
     * Retorna o baralho de cartas do jogador.
     *
     * @return o baralho do jogador
     */
    public Baralho getBaralho() {
        return baralho;
    }

    /**
     * Define o baralho de cartas do jogador.
     *
     * @param baralho o novo baralho
     */
    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    /**
     * Retorna a mão de cartas ativas do jogador no turno atual.
     *
     * @return a mão do jogador
     */
    public Mao getMao() {
        return mao;
    }

    /**
     * Define a mão de cartas do jogador.
     *
     * @param mao a nova mão de cartas
     */
    public void setMao(Mao mao) {
        this.mao = mao;
    }

    /**
     * Retorna a pilha de cartas descartadas do jogador.
     *
     * @return a pilha de descarte
     */
    public PilhaDescarte getPilhaDescarte() {
        return pilhaDescarte;
    }

    /**
     * Define a pilha de cartas descartadas do jogador.
     *
     * @param pilhaDescarte a nova pilha de descarte
     */
    public void setPilhaDescarte(PilhaDescarte pilhaDescarte) {
        this.pilhaDescarte = pilhaDescarte;
    }

    /**
     * Retorna a carteira com as moedas do jogador.
     *
     * @return a carteira do jogador
     */
    public Carteira getCarteira() {
        return carteira;
    }

    /**
     * Define a carteira do jogador.
     *
     * @param carteira a nova carteira
     */
    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    /**
     * Retorna a direção para a qual o jogador está voltado na masmorra.
     *
     * @return a direção atual do jogador
     */
    public Direcao getDirecao() {
        return direcao;
    }

    /**
     * Define a direção para a qual o jogador está voltado na masmorra.
     *
     * @param direcao a nova direção do jogador
     */
    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
