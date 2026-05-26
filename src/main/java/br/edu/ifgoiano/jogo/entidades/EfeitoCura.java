package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por restaurar a vida atual de uma entidade.
 */
public class EfeitoCura extends EfeitoBase implements Efeito {
    private int cura;
    private boolean revive;

    public EfeitoCura(){}

    public EfeitoCura(int cura, boolean revive,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano, nome, descricao, valor, duracao, permanente);
        this.cura = cura;
        this.revive = revive;
    }

    public int getCura() {
        return cura;
    }
    public void setCura(int cura) {
        this.cura = cura;
    }
    public boolean isRevive() {
        return revive;
    }
    public void setRevive(boolean revive) {
        this.revive = revive;
    }
}
