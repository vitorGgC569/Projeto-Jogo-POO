package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Curavel;

/**
 * Classe abstrata que serve de base para todos os personagens do jogo.
 * Define os atributos fundamentais de combate como vida, energia, ataque,
 * defesa e escudo, além dos comportamentos comuns a inimigos e ao jogador.
 */
public abstract class Personagem implements Danificavel, Curavel {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int energia;
    private int energiaMaxima;
    private int ataque;
    private int defesa;
    private int escudo;
    private int nivel;
    private int xp;

    /**
     * Cria um personagem vazio sem nenhum atributo definido.
     */
    public Personagem() {}

    /**
     * Cria um personagem com todos os atributos de combate configurados.
     *
     * @param nome o nome do personagem
     * @param vida a quantidade de vida inicial
     * @param vidaMaxima o limite máximo de vida
     * @param energia a quantidade de energia inicial
     * @param energiaMaxima o limite máximo de energia
     * @param ataque o valor de ataque base
     * @param defesa o valor de defesa base
     * @param escudo os pontos de escudo iniciais
     * @param nivel o nível atual do personagem
     * @param xp a experiência acumulada
     */
    public Personagem(String nome, int vida, int vidaMaxima, int energia, int energiaMaxima,
                      int ataque, int defesa, int escudo, int nivel, int xp) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.energia = energia;
        this.energiaMaxima = energiaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
        this.escudo = escudo;
        this.nivel = nivel;
        this.xp = xp;
    }

    /**
     * Aplica dano ao personagem, descontando primeiro do escudo e depois da vida.
     * Nem a vida nem o escudo ficam abaixo de zero.
     *
     * @param dano a quantidade de dano recebida
     */
    @Override
    public void receberDano(int dano) {
        int danoReal = Math.max(0, dano - this.escudo);
        this.escudo = Math.max(0, this.escudo - dano);
        this.vida = Math.max(0, this.vida - danoReal);
    }

    /**
     * Verifica se o personagem ainda está vivo, ou seja, se sua vida é maior que zero.
     *
     * @return {@code true} se o personagem estiver vivo, {@code false} caso contrário
     */
    @Override
    public boolean estaVivo() {
        return this.vida > 0;
    }

    /**
     * Restaura a vida do personagem em um determinado valor, sem ultrapassar a vida máxima.
     *
     * @param valor a quantidade de vida a ser recuperada
     */
    @Override
    public void curar(int valor) {
        this.vida = Math.min(this.vidaMaxima, this.vida + valor);
    }

    /**
     * Adiciona pontos de escudo ao personagem.
     * O escudo absorve dano antes que ele afete a vida.
     *
     * @param valor a quantidade de escudo a ser adicionada
     */
    public void ganharEscudo(int valor) {
        this.escudo += valor;
    }

    /**
     * Retorna o nome do personagem.
     *
     * @return o nome do personagem
     */
    public String getNome() { return nome; }

    /**
     * Define o nome do personagem.
     *
     * @param nome o novo nome do personagem
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Retorna a vida atual do personagem.
     *
     * @return a vida atual
     */
    public int getVida() { return vida; }

    /**
     * Define a vida atual do personagem.
     *
     * @param vida o novo valor de vida
     */
    public void setVida(int vida) { this.vida = vida; }

    /**
     * Retorna o limite máximo de vida do personagem.
     *
     * @return a vida máxima
     */
    public int getVidaMaxima() { return vidaMaxima; }

    /**
     * Define o limite máximo de vida do personagem.
     *
     * @param vidaMaxima o novo valor de vida máxima
     */
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

    /**
     * Retorna a energia atual do personagem.
     *
     * @return a energia atual
     */
    public int getEnergia() { return energia; }

    /**
     * Define a energia atual do personagem.
     *
     * @param energia o novo valor de energia
     */
    public void setEnergia(int energia) { this.energia = energia; }

    /**
     * Retorna o limite máximo de energia do personagem.
     *
     * @return a energia máxima
     */
    public int getEnergiaMaxima() { return energiaMaxima; }

    /**
     * Define o limite máximo de energia do personagem.
     *
     * @param energiaMaxima o novo valor de energia máxima
     */
    public void setEnergiaMaxima(int energiaMaxima) { this.energiaMaxima = energiaMaxima; }

    /**
     * Retorna o valor de ataque base do personagem.
     *
     * @return o valor de ataque
     */
    public int getAtaque() { return ataque; }

    /**
     * Define o valor de ataque base do personagem.
     *
     * @param ataque o novo valor de ataque
     */
    public void setAtaque(int ataque) { this.ataque = ataque; }

    /**
     * Retorna o valor de defesa do personagem.
     *
     * @return o valor de defesa
     */
    public int getDefesa() { return defesa; }

    /**
     * Define o valor de defesa do personagem.
     *
     * @param defesa o novo valor de defesa
     */
    public void setDefesa(int defesa) { this.defesa = defesa; }

    /**
     * Retorna os pontos de escudo atuais do personagem.
     *
     * @return os pontos de escudo
     */
    public int getEscudo() { return escudo; }

    /**
     * Define os pontos de escudo do personagem.
     *
     * @param escudo o novo valor de escudo
     */
    public void setEscudo(int escudo) { this.escudo = escudo; }

    /**
     * Retorna o nível atual do personagem.
     *
     * @return o nível atual
     */
    public int getNivel() { return nivel; }

    /**
     * Define o nível do personagem.
     *
     * @param nivel o novo nível
     */
    public void setNivel(int nivel) { this.nivel = nivel; }

    /**
     * Retorna a quantidade de experiência acumulada pelo personagem.
     *
     * @return os pontos de experiência
     */
    public int getXp() { return xp; }

    /**
     * Define a quantidade de experiência do personagem.
     *
     * @param xp os novos pontos de experiência
     */
    public void setXp(int xp) { this.xp = xp; }
}
