package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por causar dano a uma entidade combatente.
 */
public class EfeitoDano extends EfeitoBase implements Efeito {
    private int dano;
    private boolean ignoraDefesa;
    private boolean critico;

    public EfeitoDano() {}

    public EfeitoDano(int dano) {
        this.dano = dano;
        this.ignoraDefesa = false;
        this.critico = false;
    }

    public EfeitoDano(int dano, boolean ignoraDefesa, boolean critico) {
        this.dano = dano;
        this.ignoraDefesa = ignoraDefesa;
        this.critico = critico;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        int danoFinal = this.critico ? (int)(this.dano * 1.5) : this.dano;
        alvo.receberDano(danoFinal);
    }

    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
    public boolean isIgnoraDefesa() { return ignoraDefesa; }
    public void setIgnoraDefesa(boolean ignoraDefesa) { this.ignoraDefesa = ignoraDefesa; }
    public boolean isCritico() { return critico; }
    public void setCritico(boolean critico) { this.critico = critico; }
}
