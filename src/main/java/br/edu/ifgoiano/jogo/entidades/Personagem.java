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
    public Personagem(String nome,int vida,int vidaMaxima,int energia,int energiaMaxima,int ataque,int defesa,int escudo,int nivel,int xp){
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setvida(int vida) {
        this.vida = vida;
    }

    public int getvida() {
        return vida;
    }

    public void setvidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getvidaMaxima() {
        return vidaMaxima;
    }

    public void setenergia(int energia) {
        this.energia = energia;
    }

    public int getenergia() {
        return energia;
    }

    public void energiaMaxima(int energiaMaxima) {
        this.energiaMaxima = energiaMaxima;
    }

    public int getenergiaMaxima() {
        return energiaMaxima;
    }

    public void setataque(int ataque) {
        this.ataque = ataque;
    }

    public int getataque() {
        return ataque;
    }

    public void setdefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getdefesa() {
        return defesa;
    }

    public void setescudo(int escudo) {
        this.escudo = escudo;
    }

    public int getescudo() {
        return escudo;
    }

    public void setnivel(int nivel) {
        this.nivel = nivel;
    }

    public int getnivel() {
        return nivel;
    }

    public void setxp(int xp) {
        this.xp = xp;
    }

    public int getxp() {
        return xp;
    }
}