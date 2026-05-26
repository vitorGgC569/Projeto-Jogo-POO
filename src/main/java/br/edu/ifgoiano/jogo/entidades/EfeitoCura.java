package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Curavel;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por restaurar a vida atual de uma entidade.
 */
public class EfeitoCura extends EfeitoBase implements Efeito {
    private int cura;
    private boolean revive;

    public EfeitoCura() {}

    public EfeitoCura(int cura) {
        this.cura = cura;
        this.revive = false;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        if (alvo instanceof Curavel) {
            ((Curavel) alvo).curar(this.cura);
        }
    }

    public int getCura() { return cura; }
    public void setCura(int cura) { this.cura = cura; }
    public boolean isRevive() { return revive; }
    public void setRevive(boolean revive) { this.revive = revive; }
}
