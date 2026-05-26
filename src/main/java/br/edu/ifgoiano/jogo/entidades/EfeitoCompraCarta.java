package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por realizar a compra de novas cartas para a mao.
 */
public class EfeitoCompraCarta extends EfeitoBase implements Efeito {
    private int quantidadeCompra;

    public EfeitoCompraCarta() {}

    public EfeitoCompraCarta(int quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        if (contexto == null || contexto.getJogador() == null) return;
        for (int i = 0; i < quantidadeCompra; i++) {
            Carta carta = contexto.getJogador().getBaralho().comprar();
            if (carta != null) {
                contexto.getJogador().getMao().adicionarCarta(carta);
            }
        }
    }

    public int getQuantidadeCompra() { return quantidadeCompra; }
    public void setQuantidadeCompra(int q) { this.quantidadeCompra = q; }
}
