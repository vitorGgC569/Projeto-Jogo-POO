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

    /**
     * Ativa o modo furia quando a vida cai abaixo de 30% da vida maxima.
     */
    public void verificarFuria() {
        if (getVida() > 0 && getVidaMaxima() > 0) {
            double percentualVida = (double) getVida() / getVidaMaxima();
            if (percentualVida < 0.30 && !this.modoFuria) {
                this.modoFuria = true;
            }
        }
    }

    /**
     * Avanca para a proxima fase do chefe.
     */
    public void avancarFase() {
        this.fase++;
    }
}
