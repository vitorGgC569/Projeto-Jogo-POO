package br.edu.ifgoiano.jogo.entidades;

import java.util.List;

/**
 * Representa a loja que disponibiliza itens e cartas para compra.
 */
public class Loja {
    private String nome;
    private int nivelLoja;
    private boolean aberta;
    private List<Item> itens;
    private List<Carta> cartasVenda;

    public Loja(){}

    public Loja(String nome, int nivelLoja, boolean aberta, List<Item> itens, List<Carta> cartasVenda) {
        this.nome = nome;
        this.nivelLoja = nivelLoja;
        this.aberta = aberta;
        this.itens = itens;
        this.cartasVenda = cartasVenda;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getNivelLoja() {
        return nivelLoja;
    }
    public void setNivelLoja(int nivelLoja) {
        this.nivelLoja = nivelLoja;
    }
    public boolean isAberta() {
        return aberta;
    }
    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }
    public List<Item> getItens() {
        return itens;
    }
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    public List<Carta> getCartasVenda() {
        return cartasVenda;
    }
    public void setCartasVenda(List<Carta> cartasVenda) {
        this.cartasVenda = cartasVenda;
    }
}
