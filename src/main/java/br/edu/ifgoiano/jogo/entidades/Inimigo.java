package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.AcaoInimigo;

/**
 * Representa os inimigos comuns enfrentados durante a exploracao da masmorra.
 */
public class Inimigo extends Personagem {
    private String tipo;
    private int recompensaOuro;
    private int recompensaXp;
    private boolean elite;
    private int chanceCritico;
    private int inteligencia;
    private AcaoInimigo proximaAcao;

    public Inimigo(){}

    public Inimigo(String tipo, int recompensaOuro, int recompensaXp, boolean elite, int chanceCritico, int inteligencia) {
        this.tipo = tipo;
        this.recompensaOuro = recompensaOuro;
        this.recompensaXp = recompensaXp;
        this.elite = elite;
        this.chanceCritico = chanceCritico;
        this.inteligencia = inteligencia;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getRecompensaOuro() {
        return recompensaOuro;
    }
    public void setRecompensaOuro(int recompensaOuro) {
        this.recompensaOuro = recompensaOuro;
    }
    public int getRecompensaXp() {
        return recompensaXp;
    }
    public void setRecompensaXp(int recompensaXp) {
        this.recompensaXp = recompensaXp;
    }
    public boolean isElite() {
        return elite;
    }
    public void setElite(boolean elite) {
        this.elite = elite;
    }
    public int getChanceCritico() {
        return chanceCritico;
    }
    public void setChanceCritico(int chanceCritico) {
        this.chanceCritico = chanceCritico;
    }
    public int getInteligencia() {
        return inteligencia;
    }
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public AcaoInimigo getProximaAcao() {
        return proximaAcao;
    }

    public void setProximaAcao(AcaoInimigo proximaAcao) {
        this.proximaAcao = proximaAcao;
    }

    /**
     * Define aleatoriamente a proxima acao do inimigo com base na sua inteligencia.
     */
    public void definirProximaAcao() {
        int roll = new java.util.Random().nextInt(100);
        if (roll < this.getChanceCritico()) {
            this.proximaAcao = AcaoInimigo.ATAQUE_CRITICO;
        } else if (roll < 60) {
            this.proximaAcao = AcaoInimigo.ATAQUE;
        } else if (roll < 75) {
            this.proximaAcao = AcaoInimigo.DEFESA;
        } else {
            this.proximaAcao = AcaoInimigo.BUFF;
        }
    }

    /**
     * Executa a acao definida pelo inimigo.
     */
    public void executarAcao() {
        if (this.proximaAcao != null) {
            switch (this.proximaAcao) {
                case ATAQUE:
                case ATAQUE_CRITICO:
                case DEFESA:
                case BUFF:
                    break;
            }
        }
    }
}
