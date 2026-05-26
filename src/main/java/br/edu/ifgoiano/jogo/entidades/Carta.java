package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Usavel;
import br.edu.ifgoiano.jogo.interfaces.Compravel;

/**
 * Representa uma carta usada pelo jogador em combate.
 */
public class Carta implements Usavel, Compravel {
    private int id;
    private String nome;
    private String descricao;
    private int custoEnergia;
    private int dano;
    private int defesa;
    private int cura;
    private int raridade;
    private boolean aprimorada;
    private boolean area;
    private int valorVenda;

    public Carta() {}

    public Carta(int id, String nome, String descricao,int custoEnergia, int dano, int defesa, int cura, int raridade,boolean aprimorada, boolean area,int valorVenda) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.custoEnergia = custoEnergia;
        this.dano = dano;
        this.defesa = defesa;
        this.cura = cura;
        this.raridade = raridade;
        this.valorVenda = valorVenda;
        this.aprimorada = aprimorada;
        this.area = area;

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
    public int getCustoEnergia() {
        return custoEnergia;
    }
    public void setCustoEnergia(int custoEnergia) {
        this.custoEnergia = custoEnergia;
    }
    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }
    public int getDefesa() {
        return defesa;
    }
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    public int getCura() {
        return cura;
    }
    public void setCura(int cura) {
        this.cura = cura;
    }
    public int getRaridade() {
        return raridade;
    }
    public void setRaridade(int raridade) {
        this.raridade = raridade;
    }
    public boolean getAprimorada() {
        return aprimorada;
    }
    public void setAprimorada(boolean aprimorada) {
        this.aprimorada = aprimorada;
    }
    public boolean getArea() {
        return area;
    }
    public void setArea(boolean area) {
        this.area = area;
    }
    public int getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(int valorVenda) {
        this.valorVenda = valorVenda;
    }

}
