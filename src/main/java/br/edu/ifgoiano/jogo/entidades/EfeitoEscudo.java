package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por conceder pontos de escudo temporarios.
 */
public class EfeitoEscudo extends EfeitoBase implements Efeito {
    private int valorEscudo;

    public EfeitoEscudo() {}

    public EfeitoEscudo(int valorEscudo) {
        this.valorEscudo = valorEscudo;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        if (contexto != null && contexto.getJogador() != null) {
            contexto.getJogador().ganharEscudo(this.valorEscudo);
        }
    }

    public int getValorEscudo() { return valorEscudo; }
    public void setValorEscudo(int valorEscudo) { this.valorEscudo = valorEscudo; }
}
