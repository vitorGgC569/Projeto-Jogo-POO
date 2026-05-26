package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

import java.awt.*;

/**
 * Efeito responsavel por causar dano a uma entidade combatente.
 */
public class EfeitoDano extends EfeitoBase implements Efeito {
    private int dano;
    private boolean ignoraDefesa;
    private boolean critico;

    public EfeitoDano(){}

    public EfeitoDano(int dano, String nome, String descricao, int valor, int duracao, boolean permanente,boolean ignoraDefesa,boolean critico){
        super(dano,nome,descricao,valor,duracao,permanente);
        this.dano = dano;
        this.ignoraDefesa = ignoraDefesa;
        this.critico = critico;
    }
    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }
    public boolean getIgnoraDefesa() {
        return ignoraDefesa;
    }
    public void setIgnoraDefesa(boolean ignoraDefesa) {
        this.ignoraDefesa = ignoraDefesa;
    }
    public boolean getCritico() {
        return critico;
    }
    public void setCritico(boolean critico) {
        this.critico = critico;
    }
}
