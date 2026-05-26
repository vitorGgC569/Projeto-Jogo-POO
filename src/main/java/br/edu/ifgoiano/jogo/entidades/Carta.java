package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.enums.RaridadeCarta;
import br.edu.ifgoiano.jogo.enums.TipoCarta;
import br.edu.ifgoiano.jogo.interfaces.Compravel;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;
import br.edu.ifgoiano.jogo.interfaces.Usavel;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma carta usada pelo jogador em combate.
 */
public class Carta implements Usavel, Compravel {
    private int id;
    private String nome;
    private String descricao;
    private int custo;
    private TipoCarta tipo;
    private RaridadeCarta raridade;
    private boolean aprimorada;
    private int valorVenda;
    private List<Efeito> efeitos;

    public Carta() {
        this.efeitos = new ArrayList<>();
    }

    public Carta(int id, String nome, String descricao, int custo,
                 TipoCarta tipo, RaridadeCarta raridade, boolean aprimorada, int valorVenda) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipo = tipo;
        this.raridade = raridade;
        this.aprimorada = aprimorada;
        this.valorVenda = valorVenda;
        this.efeitos = new ArrayList<>();
    }

    @Override
    public void usar(ContextoCombate contexto, Danificavel alvo) {
        for (Efeito efeito : this.efeitos) {
            efeito.aplicar(contexto, alvo);
        }
    }

    @Override
    public int getPreco() {
        return this.valorVenda;
    }

    public void adicionarEfeito(Efeito efeito) {
        this.efeitos.add(efeito);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getCusto() { return custo; }
    public void setCusto(int custo) { this.custo = custo; }

    public TipoCarta getTipo() { return tipo; }
    public void setTipo(TipoCarta tipo) { this.tipo = tipo; }

    public RaridadeCarta getRaridade() { return raridade; }
    public void setRaridade(RaridadeCarta raridade) { this.raridade = raridade; }

    public boolean isAprimorada() { return aprimorada; }
    public void setAprimorada(boolean aprimorada) { this.aprimorada = aprimorada; }

    public int getValorVenda() { return valorVenda; }
    public void setValorVenda(int valorVenda) { this.valorVenda = valorVenda; }

    public List<Efeito> getEfeitos() { return efeitos; }
    public void setEfeitos(List<Efeito> efeitos) { this.efeitos = efeitos; }
}
