package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa o inimigo final (chefe) de uma área da masmorra.
 * Estende o inimigo comum com atributos extras como fases de batalha,
 * modo fúria e uma habilidade especial exclusiva.
 */
public class Chefe extends Inimigo {
    private int fase;
    private boolean modoFuria;
    private String habilidadeEspecial;

    /**
     * Cria um chefe vazio sem nenhum atributo definido.
     */
    public Chefe() {}

    /**
     * Cria um chefe com fase inicial, habilidade especial e estado de fúria definidos.
     *
     * @param fase o número da fase atual do chefe (começa normalmente em 1)
     * @param habilidadeEspecial o nome ou descrição da habilidade especial do chefe
     * @param modoFuria indica se o chefe começa já no modo fúria
     */
    public Chefe(int fase, String habilidadeEspecial,boolean modoFuria) {
        this.habilidadeEspecial = habilidadeEspecial;
        this.fase = fase;
        this.modoFuria = modoFuria;
    }

    /**
     * Retorna a fase atual do chefe.
     *
     * @return o número da fase atual
     */
    public int getFase() {
        return fase;
    }

    /**
     * Define a fase atual do chefe.
     *
     * @param fase o novo número de fase
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * Retorna o nome ou descrição da habilidade especial do chefe.
     *
     * @return a habilidade especial
     */
    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    /**
     * Define a habilidade especial do chefe.
     *
     * @param habilidadeEspecial a nova habilidade especial
     */
    public void setHabilidadeEspecial(String habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    /**
     * Verifica se o chefe está no modo fúria.
     *
     * @return {@code true} se o modo fúria estiver ativo, {@code false} caso contrário
     */
    public boolean isModoFuria() {
        return modoFuria;
    }

    /**
     * Ativa ou desativa o modo fúria do chefe manualmente.
     *
     * @param modoFuria {@code true} para ativar o modo fúria
     */
    public void setModoFuria(boolean modoFuria) {
        this.modoFuria = modoFuria;
    }

    /**
     * Verifica automaticamente se o chefe deve entrar em modo fúria.
     * O modo fúria é ativado quando a vida cai abaixo de 30% da vida máxima
     * e ainda não estava ativo.
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
     * Avança o chefe para a próxima fase da batalha, incrementando o contador de fase.
     */
    public void avancarFase() {
        this.fase++;
    }
}
