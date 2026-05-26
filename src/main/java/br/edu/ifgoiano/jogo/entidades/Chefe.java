package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa o inimigo final da masmorra com comportamento e atributos elevados.
 */
public class Chefe extends Inimigo {
    private int fase;
    private boolean modoFuria;
    private String habilidadeEspecial;

    public Chefe() {}

    public Chefe(int fase, String habilidadeEspecial,boolean modoFuria) {
        this.habilidadeEspecial = habilidadeEspecial;
        this.fase = fase;
        this.modoFuria = modoFuria;
    }
    public int getFase() {
        return fase;
    }
    public void setFase(int fase) {
        this.fase = fase;
    }
    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }
    public void setHabilidadeEspecial(String habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }
    public boolean isModoFuria() {
        return modoFuria;
    }
    public void setModoFuria(boolean modoFuria) {
        this.modoFuria = modoFuria;
    }
}
