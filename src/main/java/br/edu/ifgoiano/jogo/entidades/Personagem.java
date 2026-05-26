package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Curavel;

/**
 * Representa a classe abstrata base para qualquer personagem combatente no jogo.
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

    public Personagem() {}
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

    @Override
    public void receberDano(int dano) {
        int danoReal = Math.max(0, dano - this.escudo);
        this.escudo = Math.max(0, this.escudo - dano);
        this.vida = Math.max(0, this.vida - danoReal);
    }

    @Override
    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public void curar(int valor) {
        this.vida = Math.min(this.vidaMaxima, this.vida + valor);
    }

    public void ganharEscudo(int valor) {
        this.escudo += valor;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }

    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }

    public int getEnergiaMaxima() { return energiaMaxima; }
    public void setEnergiaMaxima(int energiaMaxima) { this.energiaMaxima = energiaMaxima; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public int getEscudo() { return escudo; }
    public void setEscudo(int escudo) { this.escudo = escudo; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
}