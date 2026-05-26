package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por aplicar um multiplicador ao dano basico baseado no contexto.
 */
public class EfeitoDanoMultiplicado extends EfeitoBase implements Efeito {
    private double multiplicador;
    private int danoBase;

    public EfeitoDanoMultiplicado(double multiplicador, int danoBase,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano,nome,descricao,valor,duracao,permanente);
        this.multiplicador = multiplicador;
        this.danoBase = danoBase;
    }

    public double getMultiplicador() {
        return multiplicador;
    }
    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }
    public int getDanoBase() {
        return danoBase;
    }
    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }
}
