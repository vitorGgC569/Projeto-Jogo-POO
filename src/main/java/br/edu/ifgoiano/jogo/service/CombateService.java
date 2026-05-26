package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.core.Combate;
import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.enums.AcaoInimigo;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;

import java.util.ArrayList;

/**
 * Controla o andamento de turnos, compras e descarte de cartas, e acoes dos inimigos no combate.
 */
public class CombateService {

    /** Inicializa um novo combate, embaralha o baralho e compra as cartas iniciais. */
    public Combate iniciarCombate(Jogador jogador, Inimigo inimigo) {
        Combate combate = new Combate(jogador, inimigo);
        jogador.getBaralho().embaralhar();
        jogador.setEnergia(jogador.getEnergiaMaxima());
        comprarCartas(jogador, 5);
        inimigo.definirProximaAcao();
        return combate;
    }

    /** Compra a quantidade indicada de cartas do baralho para a mao. */
    private void comprarCartas(Jogador jogador, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (jogador.getBaralho().estaVazio()) {
                reembaralharDescarte(jogador);
            }
            if (!jogador.getBaralho().estaVazio() && !jogador.getMao().estaCheia()) {
                Carta carta = jogador.getBaralho().comprar();
                if (carta != null) jogador.getMao().adicionarCarta(carta);
            }
        }
    }

    /** Reembaralha o descarte de volta para o baralho quando este esvazia. */
    private void reembaralharDescarte(Jogador jogador) {
        if (jogador.getPilhaDescarte().getCartasDescartadas() != null) {
            for (Carta c : jogador.getPilhaDescarte().getCartasDescartadas()) {
                jogador.getBaralho().adicionarCarta(c);
            }
            jogador.getPilhaDescarte().setCartasDescartadas(new ArrayList<>());
            jogador.getBaralho().embaralhar();
        }
    }

    /** Usa uma carta contra um alvo, gasta energia e move carta para descarte. */
    public boolean jogarCarta(Combate combate, Carta carta, Danificavel alvo) {
        Jogador jogador = combate.getJogador();
        if (jogador.getEnergia() < carta.getCusto()) return false;
        if (!jogador.getMao().removerCarta(carta)) return false;

        jogador.setEnergia(jogador.getEnergia() - carta.getCusto());
        carta.usar(combate.getContexto(), alvo);
        jogador.getPilhaDescarte().getCartasDescartadas().add(carta);
        return true;
    }

    /** Finaliza o turno do jogador: descarta mao, reseta energia, passa turno. */
    public void finalizarTurnoJogador(Combate combate) {
        Jogador jogador = combate.getJogador();
        // descarta cartas restantes na mao
        for (Carta c : new ArrayList<>(jogador.getMao().getCartas())) {
            jogador.getPilhaDescarte().getCartasDescartadas().add(c);
        }
        jogador.getMao().getCartas().clear();
        jogador.setEnergia(jogador.getEnergiaMaxima());
        combate.getTurno().avancar();
        executarTurnoInimigo(combate);
    }

    /** Executa a acao do inimigo e avanca para o proximo turno. */
    public void executarTurnoInimigo(Combate combate) {
        Inimigo inimigo = combate.getInimigo();
        Jogador jogador = combate.getJogador();

        if (!inimigo.estaVivo()) return;

        AcaoInimigo acao = inimigo.getProximaAcao();
        if (acao == null) acao = AcaoInimigo.ATAQUE;

        switch (acao) {
            case ATAQUE:
                jogador.receberDano(inimigo.getAtaque());
                break;
            case ATAQUE_CRITICO:
                jogador.receberDano((int)(inimigo.getAtaque() * 1.5));
                break;
            case DEFESA:
                inimigo.ganharEscudo(inimigo.getDefesa());
                break;
            case BUFF:
                inimigo.setAtaque(inimigo.getAtaque() + 2);
                break;
            case CURA:
                inimigo.curar((int)(inimigo.getVidaMaxima() * 0.1));
                break;
        }

        combate.getTurno().avancar();
        inimigo.definirProximaAcao();
        comprarCartas(jogador, 3);
    }

    public boolean combateFinalizado(Combate combate) {
        return combate.estaFinalizado();
    }
}
