package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por realizar a compra de novas cartas para a mao.
 */
public class EfeitoCompraCarta extends EfeitoBase implements Efeito {
    private int quantidadeCompra;

    public EfeitoCompraCarta(){}

    public EfeitoCompraCarta(int quantidadeCompra,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano, nome, descricao, valor, duracao, permanente);
        this.quantidadeCompra = quantidadeCompra;
    }
    public int getQuantidadeCompra() {
        return quantidadeCompra;
    }
    public void setQuantidadeCompra(int quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }
}
