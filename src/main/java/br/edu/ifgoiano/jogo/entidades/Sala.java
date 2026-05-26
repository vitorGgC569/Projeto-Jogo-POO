package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa um compartimento especifico da masmorra com conteudo proprio.
 */
public class Sala {
    private int id;
    private String nome;
    private String descricao;
    private boolean explorada;
    private boolean possuiInimigo;
    private boolean possuiTesouro;
    private boolean possuiEvento;
    private boolean possuiLoja;
    private boolean salaChefe;
    private int dificuldade;

    public Sala() {}

    public Sala(int id, String nome, String descricao,boolean explorada, boolean possuiInimigo,boolean possuiTesouro, boolean possuiEvento, boolean possuiLoja, boolean salaChefe, int dificuldade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.explorada = explorada;
        this.possuiInimigo = possuiInimigo;
        this.possuiTesouro = possuiTesouro;
        this.possuiEvento = possuiEvento;
        this.possuiLoja = possuiLoja;
        this.salaChefe = salaChefe;
        this.dificuldade = dificuldade;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isExplorada() {
        return explorada;
    }
    public void setExplorada(boolean explorada) {
        this.explorada = explorada;
    }
    public boolean isPossuiInimigo() {
        return possuiInimigo;
    }
    public void setPossuiInimigo(boolean possuiInimigo) {
        this.possuiInimigo = possuiInimigo;
    }
    public boolean isPossuiTesouro() {
        return possuiTesouro;
    }
    public void setPossuiTesouro(boolean possuiTesouro) {
        this.possuiTesouro = possuiTesouro;
    }
    public boolean isPossuiEvento() {
        return possuiEvento;
    }
    public void setPossuiEvento(boolean possuiEvento) {
        this.possuiEvento = possuiEvento;
    }
    public boolean isPossuiLoja() {
        return possuiLoja;
    }
    public void setPossuiLoja(boolean possuiLoja) {
        this.possuiLoja = possuiLoja;
    }
    public boolean isSalaChefe() {
        return salaChefe;
    }
    public void setSalaChefe(boolean salaChefe) {
        this.salaChefe = salaChefe;
    }
    public int getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}
