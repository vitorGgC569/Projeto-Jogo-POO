package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.core.EstadoJogo;
import br.edu.ifgoiano.jogo.core.Jogo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Masmorra;

/**
 * Controla as regras de inicializacao, salvamento, carregamento e fluxo geral do jogo.
 */
public class JogoService {

    private final JogadorService jogadorService = new JogadorService();

    /** Cria e retorna um novo jogo com jogador e masmorra iniciais. */
    public Jogo novoJogo(String nomeJogador) {
        Jogador jogador = jogadorService.criarJogador(nomeJogador);
        Masmorra masmorra = new Masmorra();
        Jogo jogo = new Jogo(jogador, masmorra);
        jogo.getEstadoJogo().mudarPara(EstadoJogo.Estado.EXPLORANDO);
        return jogo;
    }

    public void mudarEstado(Jogo jogo, EstadoJogo.Estado estado) {
        jogo.getEstadoJogo().mudarPara(estado);
    }
}
