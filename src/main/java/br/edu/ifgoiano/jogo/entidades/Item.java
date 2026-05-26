package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.enums.TipoItem;
import br.edu.ifgoiano.jogo.interfaces.Compravel;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Usavel;

/**
 * Representa um consumivel ou reliquia que pode ser usado pelo jogador.
 */
public class Item implements Usavel, Compravel {
    private int id;
    private String nome;
    private String descricao;
    private int preco;
    private boolean consumivel;
    private boolean raro;
    private int quantidade;
    private int bonusVida;
    private int bonusAtaque;
    private int bonusDefesa;
    private TipoItem tipo;

    public Item() {}

    public Item(int id, String nome, String descricao, int preco,boolean consumivel,boolean raro,int quantidade,int bonusVida,int bonusAtaque,int bonusDefesa ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.consumivel = consumivel;
        this.raro = raro;
        this.quantidade = quantidade;
        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
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
    @Override
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public void usar(ContextoCombate contexto, Danificavel alvo) {
        if (contexto != null && contexto.getJogador() != null) {
            contexto.getJogador().curar(this.bonusVida);
            contexto.getJogador().setAtaque(contexto.getJogador().getAtaque() + this.bonusAtaque);
            contexto.getJogador().setDefesa(contexto.getJogador().getDefesa() + this.bonusDefesa);
        }
    }
    public boolean isConsumivel() {
        return consumivel;
    }
    public void setConsumivel(boolean consumivel) {
        this.consumivel = consumivel;
    }
    public boolean isRaro() {
        return raro;
    }
    public void setRaro(boolean raro) {
        this.raro = raro;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getBonusVida() {
        return bonusVida;
    }
    public void setBonusVida(int bonusVida) {
        this.bonusVida = bonusVida;
    }
    public int getBonusAtaque() {
        return bonusAtaque;
    }
    public void setBonusAtaque(int bonusAtaque) {
        this.bonusAtaque = bonusAtaque;
    }
    public int getBonusDefesa() {
        return bonusDefesa;
    }
    public void setBonusDefesa(int bonusDefesa) {
        this.bonusDefesa = bonusDefesa;
    }

    public TipoItem getTipo() { return tipo; }
    public void setTipo(TipoItem tipo) { this.tipo = tipo; }
}
