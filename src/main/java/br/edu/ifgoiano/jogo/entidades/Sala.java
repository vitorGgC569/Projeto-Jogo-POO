package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.TipoSala;

/**
 * Representa um compartimento especifico da masmorra com conteudo proprio.
 */
public class Sala {
    private int id;
    private String nome;
    private String descricao;
    private boolean explorada;
    private int dificuldade;
    private TipoSala tipo;
    private Inimigo inimigo;
    private Loja loja;
    private Item recompensa;

    public Sala() {}

    public Sala(int id, String nome, String descricao, boolean explorada, int dificuldade, TipoSala tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.explorada = explorada;
        this.dificuldade = dificuldade;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isExplorada() { return explorada; }
    public void setExplorada(boolean explorada) { this.explorada = explorada; }

    public int getDificuldade() { return dificuldade; }
    public void setDificuldade(int dificuldade) { this.dificuldade = dificuldade; }

    public TipoSala getTipo() { return tipo; }
    public void setTipo(TipoSala tipo) { this.tipo = tipo; }

    public Inimigo getInimigo() { return inimigo; }
    public void setInimigo(Inimigo inimigo) { this.inimigo = inimigo; }

    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }

    public Item getRecompensa() { return recompensa; }
    public void setRecompensa(Item recompensa) { this.recompensa = recompensa; }

    public boolean possuiInimigo() { return this.inimigo != null; }
    public boolean possuiLoja() { return this.loja != null; }
    public boolean possuiRecompensa() { return this.recompensa != null; }
}
