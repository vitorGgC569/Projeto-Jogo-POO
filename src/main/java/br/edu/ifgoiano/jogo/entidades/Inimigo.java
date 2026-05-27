package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.AcaoInimigo;

/**
 * Representa um inimigo comum encontrado durante a exploração da masmorra.
 * Possui atributos de combate próprios, chance de acerto crítico e um sistema
 * de ações que determina seu comportamento a cada turno.
 */
public class Inimigo extends Personagem {
    private String tipo;
    private int recompensaOuro;
    private int recompensaXp;
    private boolean elite;
    private int chanceCritico;
    private int inteligencia;
    private AcaoInimigo proximaAcao;

    /**
     * Cria um inimigo vazio sem nenhum atributo definido.
     */
    public Inimigo(){}

    /**
     * Cria um inimigo com os atributos de identificação e combate definidos.
     *
     * @param tipo o tipo ou categoria do inimigo (ex.: "Goblin", "Esqueleto")
     * @param recompensaOuro a quantidade de ouro concedida ao derrotá-lo
     * @param recompensaXp a quantidade de experiência concedida ao derrotá-lo
     * @param elite indica se o inimigo é do tipo elite, com atributos elevados
     * @param chanceCritico a porcentagem de chance de executar um ataque crítico
     * @param inteligencia o nível de inteligência que influencia a escolha de ações
     */
    public Inimigo(String tipo, int recompensaOuro, int recompensaXp, boolean elite, int chanceCritico, int inteligencia) {
        this.tipo = tipo;
        this.recompensaOuro = recompensaOuro;
        this.recompensaXp = recompensaXp;
        this.elite = elite;
        this.chanceCritico = chanceCritico;
        this.inteligencia = inteligencia;
    }

    /**
     * Retorna o tipo do inimigo.
     *
     * @return o tipo do inimigo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do inimigo.
     *
     * @param tipo o novo tipo do inimigo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a quantidade de ouro que o inimigo concede ao ser derrotado.
     *
     * @return a recompensa em ouro
     */
    public int getRecompensaOuro() {
        return recompensaOuro;
    }

    /**
     * Define a quantidade de ouro que o inimigo concede ao ser derrotado.
     *
     * @param recompensaOuro o novo valor da recompensa em ouro
     */
    public void setRecompensaOuro(int recompensaOuro) {
        this.recompensaOuro = recompensaOuro;
    }

    /**
     * Retorna a quantidade de experiência que o inimigo concede ao ser derrotado.
     *
     * @return a recompensa em experiência
     */
    public int getRecompensaXp() {
        return recompensaXp;
    }

    /**
     * Define a quantidade de experiência que o inimigo concede ao ser derrotado.
     *
     * @param recompensaXp o novo valor da recompensa em experiência
     */
    public void setRecompensaXp(int recompensaXp) {
        this.recompensaXp = recompensaXp;
    }

    /**
     * Verifica se o inimigo é do tipo elite.
     *
     * @return {@code true} se for elite, {@code false} caso contrário
     */
    public boolean isElite() {
        return elite;
    }

    /**
     * Define se o inimigo é do tipo elite.
     *
     * @param elite {@code true} para torná-lo elite
     */
    public void setElite(boolean elite) {
        this.elite = elite;
    }

    /**
     * Retorna a porcentagem de chance de o inimigo realizar um ataque crítico.
     *
     * @return a chance de acerto crítico (0 a 100)
     */
    public int getChanceCritico() {
        return chanceCritico;
    }

    /**
     * Define a porcentagem de chance de o inimigo realizar um ataque crítico.
     *
     * @param chanceCritico a nova chance de crítico (0 a 100)
     */
    public void setChanceCritico(int chanceCritico) {
        this.chanceCritico = chanceCritico;
    }

    /**
     * Retorna o nível de inteligência do inimigo, que influencia suas escolhas de ação.
     *
     * @return o nível de inteligência
     */
    public int getInteligencia() {
        return inteligencia;
    }

    /**
     * Define o nível de inteligência do inimigo.
     *
     * @param inteligencia o novo nível de inteligência
     */
    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    /**
     * Retorna a ação que o inimigo executará no próximo turno.
     *
     * @return a próxima ação do inimigo
     */
    public AcaoInimigo getProximaAcao() {
        return proximaAcao;
    }

    /**
     * Define manualmente a ação que o inimigo executará no próximo turno.
     *
     * @param proximaAcao a ação a ser definida
     */
    public void setProximaAcao(AcaoInimigo proximaAcao) {
        this.proximaAcao = proximaAcao;
    }

    /**
     * Sorteia aleatoriamente a próxima ação do inimigo com base na sua chance de crítico.
     * Pode resultar em ataque normal, ataque crítico, defesa ou buff.
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
     * Executa a ação previamente definida para o turno atual do inimigo.
     * Caso nenhuma ação tenha sido definida, nada acontece.
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
