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
 * Fabrica para instanciacao de cartas e associacao efeitos.
 */
public class CartaFactory {

    public static Carta criarGolpe() {
        Carta carta = new Carta(1, "Golpe", "Causa 6 de dano.", 1,
                TipoCarta.ATAQUE, RaridadeCarta.COMUM, false, 10);
        carta.adicionarEfeito(new EfeitoDano(6));
        return carta;
    }

    public static Carta criarDefesa() {
        Carta carta = new Carta(2, "Defender", "Ganha 5 de escudo.", 1,
                TipoCarta.DEFESA, RaridadeCarta.COMUM, false, 10);
        carta.adicionarEfeito(new EfeitoEscudo(5));
        return carta;
    }

    public static Carta criarCura() {
        Carta carta = new Carta(3, "Cura", "Recupera 8 de vida.", 1,
                TipoCarta.CURA, RaridadeCarta.COMUM, false, 15);
        carta.adicionarEfeito(new EfeitoCura(8));
        return carta;
    }

    public static Carta criarGolpeCritico() {
        Carta carta = new Carta(4, "Golpe Critico", "Causa 12 de dano.", 2,
                TipoCarta.ATAQUE, RaridadeCarta.RARA, false, 25);
        carta.adicionarEfeito(new EfeitoDano(12, false, true));
        return carta;
    }

    public static Carta criarInvestida() {
        Carta carta = new Carta(5, "Investida", "Causa 4 de dano e ganha 3 de escudo.", 1,
                TipoCarta.ESPECIAL, RaridadeCarta.COMUM, false, 15);
        carta.adicionarEfeito(new EfeitoDano(4));
        carta.adicionarEfeito(new EfeitoEscudo(3));
        return carta;
    }

    public static Carta criarRaiva() {
        Carta carta = new Carta(6, "Raiva", "Aumenta o ataque em 3.", 1,
                TipoCarta.PODER, RaridadeCarta.RARA, false, 20);
        carta.adicionarEfeito(new EfeitoPoder(3));
        return carta;
    }

    public static Carta criarGolpeDuplo() {
        Carta carta = new Carta(7, "Golpe Duplo", "Causa 4 de dano duas vezes.", 2,
                TipoCarta.ATAQUE, RaridadeCarta.RARA, false, 30);
        carta.adicionarEfeito(new EfeitoDano(4));
        carta.adicionarEfeito(new EfeitoDano(4));
        return carta;
    }

    public static Carta criarSaque() {
        Carta carta = new Carta(8, "Saque", "Causa 3 de dano e ganha 2 moedas.", 1,
                TipoCarta.ESPECIAL, RaridadeCarta.RARA, false, 20);
        carta.adicionarEfeito(new EfeitoDano(3));
        carta.adicionarEfeito(new EfeitoMoeda(2));
        return carta;
    }

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
