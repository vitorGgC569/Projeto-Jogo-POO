package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

public abstract class EfeitoBase implements Efeito {
    private int id;
    private String nome;
    private String descricao;
    private int valor;
    private int duracao;
    private boolean permanente;

    public EfeitoBase() {}

    public EfeitoBase(int id, String nome, String descricao, int valor, int duracao, boolean permanente) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracao = duracao;
        this.permanente = permanente;
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
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public boolean isPermanente() {
        return permanente;
    }
    public void setPermanente(boolean permanente) {
        this.permanente = permanente;
    }
}
