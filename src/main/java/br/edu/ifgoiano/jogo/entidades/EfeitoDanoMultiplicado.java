package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por aplicar um multiplicador ao dano basico baseado no contexto.
 */
public class EfeitoDanoMultiplicado extends EfeitoBase implements Efeito {
    private double multiplicador;
    private int danoBase;

    public EfeitoDanoMultiplicado() {}

    public EfeitoDanoMultiplicado(int danoBase, double multiplicador) {
        this.danoBase = danoBase;
        this.multiplicador = multiplicador;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        int danoFinal = (int)(this.danoBase * this.multiplicador);
        alvo.receberDano(danoFinal);
    }

    public double getMultiplicador() { return multiplicador; }
    public void setMultiplicador(double multiplicador) { this.multiplicador = multiplicador; }
    public int getDanoBase() { return danoBase; }
    public void setDanoBase(int danoBase) { this.danoBase = danoBase; }
}
