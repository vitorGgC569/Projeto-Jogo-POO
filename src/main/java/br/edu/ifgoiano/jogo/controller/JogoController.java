package br.edu.ifgoiano.jogo.controller;

import br.edu.ifgoiano.jogo.core.Combate;
import br.edu.ifgoiano.jogo.core.EstadoJogo;
import br.edu.ifgoiano.jogo.core.Jogo;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.service.CombateService;
import br.edu.ifgoiano.jogo.service.JogoService;

/**
 * Intermedia a comunicacao entre a interface grafica (UI) e os servicos de regra de negocio.
 */
public class JogoController {

    private Jogo jogo;
    private final JogoService jogoService = new JogoService();
    private final CombateService combateService = new CombateService();

    public void iniciarNovoJogo(String nomeJogador) {
        this.jogo = jogoService.novoJogo(nomeJogador);
    }

    public void iniciarCombate(Inimigo inimigo) {
        Combate combate = combateService.iniciarCombate(jogo.getJogador(), inimigo);
        jogo.setCombateAtual(combate);
        jogoService.mudarEstado(jogo, EstadoJogo.Estado.EM_COMBATE);
    }

    public boolean jogarCarta(Carta carta, Danificavel alvo) {
        if (jogo.getCombateAtual() == null) return false;
        return combateService.jogarCarta(jogo.getCombateAtual(), carta, alvo);
    }

    public void finalizarTurno() {
        if (jogo.getCombateAtual() != null) {
            combateService.finalizarTurnoJogador(jogo.getCombateAtual());
        }
    }

    public boolean combateFinalizado() {
        return jogo.getCombateAtual() != null && combateService.combateFinalizado(jogo.getCombateAtual());
    }

    public Jogo getJogo() { return jogo; }
    public Jogador getJogador() { return jogo != null ? jogo.getJogador() : null; }
}
