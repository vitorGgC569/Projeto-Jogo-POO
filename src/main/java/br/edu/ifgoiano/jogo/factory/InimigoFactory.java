package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Chefe;
import br.edu.ifgoiano.jogo.entidades.Inimigo;

/**
 * Fabrica para instanciacao de inimigos e chefes.
 */
public class InimigoFactory {

    public static Inimigo criarEsqueleto() {
        Inimigo i = new Inimigo();
        i.setNome("Esqueleto");
        i.setVida(30);
        i.setVidaMaxima(30);
        i.setAtaque(6);
        i.setDefesa(2);
        i.setEscudo(0);
        i.setNivel(1);
        i.setRecompensaOuro(10);
        i.setRecompensaXp(15);
        i.setChanceCritico(10);
        i.setElite(false);
        i.setTipo("Esqueleto");
        return i;
    }

    public static Inimigo criarMorcego() {
        Inimigo i = new Inimigo();
        i.setNome("Morcego");
        i.setVida(18);
        i.setVidaMaxima(18);
        i.setAtaque(4);
        i.setDefesa(1);
        i.setEscudo(0);
        i.setNivel(1);
        i.setRecompensaOuro(6);
        i.setRecompensaXp(8);
        i.setChanceCritico(20);
        i.setElite(false);
        i.setTipo("Morcego");
        return i;
    }

    public static Inimigo criarEsqueletoElite() {
        Inimigo i = criarEsqueleto();
        i.setNome("Esqueleto Elite");
        i.setVida(60);
        i.setVidaMaxima(60);
        i.setAtaque(12);
        i.setDefesa(5);
        i.setElite(true);
        i.setRecompensaOuro(25);
        i.setRecompensaXp(40);
        return i;
    }

    public static Chefe criarChefe() {
        Chefe chefe = new Chefe();
        chefe.setNome("Rei dos Mortos");
        chefe.setVida(120);
        chefe.setVidaMaxima(120);
        chefe.setAtaque(18);
        chefe.setDefesa(8);
        chefe.setEscudo(0);
        chefe.setNivel(5);
        chefe.setRecompensaOuro(100);
        chefe.setRecompensaXp(200);
        chefe.setChanceCritico(25);
        chefe.setFase(1);
        chefe.setHabilidadeEspecial("Golpe das Trevas");
        chefe.setModoFuria(false);
        chefe.setTipo("Chefe");
        chefe.setElite(true);
        return chefe;
    }
}
