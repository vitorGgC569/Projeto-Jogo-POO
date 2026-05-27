package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.EfeitoCura;
import br.edu.ifgoiano.jogo.entidades.EfeitoDano;
import br.edu.ifgoiano.jogo.entidades.EfeitoDanoMultiplicado;
import br.edu.ifgoiano.jogo.entidades.EfeitoEscudo;
import br.edu.ifgoiano.jogo.entidades.EfeitoMoeda;
import br.edu.ifgoiano.jogo.entidades.EfeitoPoder;
import br.edu.ifgoiano.jogo.enums.RaridadeCarta;
import br.edu.ifgoiano.jogo.enums.TipoCarta;
import br.edu.ifgoiano.jogo.util.AleatorioUtil;

/**
 * Fábrica responsável por criar as diferentes cartas do jogo, cada uma com seus efeitos configurados.
 */
public class CartaFactory {

    /**
     * Cria a carta Golpe, um ataque básico que causa dano ao inimigo.
     *
     * @return carta de ataque comum que causa 6 de dano
     */
    public static Carta criarGolpe() {
        Carta carta = new Carta(1, "Golpe", "Causa 6 de dano.", 1,
                TipoCarta.ATAQUE, RaridadeCarta.COMUM, false, 10);
        carta.adicionarEfeito(new EfeitoDano(6));
        return carta;
    }

    /**
     * Cria a carta Defender, que concede escudo ao jogador para absorver dano.
     *
     * @return carta de defesa comum que concede 5 de escudo
     */
    public static Carta criarDefesa() {
        Carta carta = new Carta(2, "Defender", "Ganha 5 de escudo.", 1,
                TipoCarta.DEFESA, RaridadeCarta.COMUM, false, 10);
        carta.adicionarEfeito(new EfeitoEscudo(5));
        return carta;
    }

    /**
     * Cria a carta Cura, que restaura pontos de vida do jogador.
     *
     * @return carta de cura comum que recupera 8 de vida
     */
    public static Carta criarCura() {
        Carta carta = new Carta(3, "Cura", "Recupera 8 de vida.", 1,
                TipoCarta.CURA, RaridadeCarta.COMUM, false, 15);
        carta.adicionarEfeito(new EfeitoCura(8));
        return carta;
    }

    /**
     * Cria a carta Golpe Crítico, um ataque poderoso de raridade rara que causa dano elevado.
     *
     * @return carta de ataque rara que causa 12 de dano crítico
     */
    public static Carta criarGolpeCritico() {
        Carta carta = new Carta(4, "Golpe Critico", "Causa 12 de dano.", 2,
                TipoCarta.ATAQUE, RaridadeCarta.RARA, false, 25);
        carta.adicionarEfeito(new EfeitoDano(12, false, true));
        return carta;
    }

    /**
     * Cria a carta Investida, que combina um ataque leve com ganho de escudo no mesmo turno.
     *
     * @return carta especial comum que causa 4 de dano e concede 3 de escudo
     */
    public static Carta criarInvestida() {
        Carta carta = new Carta(5, "Investida", "Causa 4 de dano e ganha 3 de escudo.", 1,
                TipoCarta.ESPECIAL, RaridadeCarta.COMUM, false, 15);
        carta.adicionarEfeito(new EfeitoDano(4));
        carta.adicionarEfeito(new EfeitoEscudo(3));
        return carta;
    }

    /**
     * Cria a carta Raiva, que aumenta permanentemente o ataque do jogador naquele combate.
     *
     * @return carta de poder rara que aumenta o ataque em 3
     */
    public static Carta criarRaiva() {
        Carta carta = new Carta(6, "Raiva", "Aumenta o ataque em 3.", 1,
                TipoCarta.PODER, RaridadeCarta.RARA, false, 20);
        carta.adicionarEfeito(new EfeitoPoder(3));
        return carta;
    }

    /**
     * Cria a carta Golpe Duplo, que aplica dano duas vezes seguidas ao inimigo.
     *
     * @return carta de ataque rara que causa 4 de dano duas vezes
     */
    public static Carta criarGolpeDuplo() {
        Carta carta = new Carta(7, "Golpe Duplo", "Causa 4 de dano duas vezes.", 2,
                TipoCarta.ATAQUE, RaridadeCarta.RARA, false, 30);
        carta.adicionarEfeito(new EfeitoDano(4));
        carta.adicionarEfeito(new EfeitoDano(4));
        return carta;
    }

    /**
     * Cria a carta Saque, que causa dano e ainda recompensa o jogador com moedas.
     *
     * @return carta especial rara que causa 3 de dano e concede 2 moedas
     */
    public static Carta criarSaque() {
        Carta carta = new Carta(8, "Saque", "Causa 3 de dano e ganha 2 moedas.", 1,
                TipoCarta.ESPECIAL, RaridadeCarta.RARA, false, 20);
        carta.adicionarEfeito(new EfeitoDano(3));
        carta.adicionarEfeito(new EfeitoMoeda(2));
        return carta;
    }

    /**
     * Cria e retorna uma carta escolhida aleatoriamente entre todas as disponíveis.
     *
     * @return uma carta aleatória do conjunto de cartas do jogo
     */
    public static Carta criarCartaAleatoria() {
        int escolha = AleatorioUtil.entreInclusive(1, 8);
        switch (escolha) {
            case 1: return criarGolpe();
            case 2: return criarDefesa();
            case 3: return criarCura();
            case 4: return criarGolpeCritico();
            case 5: return criarInvestida();
            case 6: return criarRaiva();
            case 7: return criarGolpeDuplo();
            default: return criarSaque();
        }
    }
}
