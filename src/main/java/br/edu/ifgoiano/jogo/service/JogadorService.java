package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.entidades.Baralho;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Carteira;
import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Mao;
import br.edu.ifgoiano.jogo.entidades.PilhaDescarte;

import java.util.ArrayList;

/**
 * Controla as regras de negocio do jogador, gerenciando vida, itens, moedas e cartas.
 */
public class JogadorService {

    /** Cria e retorna um novo Jogador com atributos iniciais padrao. */
    public Jogador criarJogador(String nome) {
        Jogador jogador = new Jogador();
        jogador.setNome(nome);
        jogador.setVida(80);
        jogador.setVidaMaxima(80);
        jogador.setEnergia(3);
        jogador.setEnergiaMaxima(3);
        jogador.setAtaque(5);
        jogador.setDefesa(2);
        jogador.setEscudo(0);
        jogador.setNivel(1);
        jogador.setXp(0);
        jogador.setOuro(0);
        jogador.setNivelMasmorra(1);

        jogador.setBaralho(new Baralho(40, 0, false, new ArrayList<>()));
        jogador.setMao(new Mao(new ArrayList<>(), 10));
        jogador.setPilhaDescarte(new PilhaDescarte(new ArrayList<>()));
        jogador.setCarteira(new Carteira(0, 0, 0));
        return jogador;
    }

    public void adicionarCarta(Jogador jogador, Carta carta) {
        jogador.getBaralho().adicionarCarta(carta);
    }

    public boolean gastarMoedas(Jogador jogador, int valor) {
        return jogador.getCarteira().gastar(valor);
    }

    public void ganharMoedas(Jogador jogador, int valor) {
        jogador.getCarteira().adicionar(valor);
    }

    public void ganharXp(Jogador jogador, int xp) {
        jogador.setXp(jogador.getXp() + xp);
    }
}
