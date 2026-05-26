package br.edu.ifgoiano.jogo;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.factory.CartaFactory;
import br.edu.ifgoiano.jogo.factory.InimigoFactory;
import br.edu.ifgoiano.jogo.service.JogadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carta e Efeitos — aplicar no contexto de combate")
class CartaEfeitosTest {

    private Jogador jogador;
    private Inimigo inimigo;
    private ContextoCombate ctx;

    @BeforeEach
    void setup() {
        jogador = new JogadorService().criarJogador("Heroi");
        inimigo = InimigoFactory.criarEsqueleto(); // vida=30
        ctx = new ContextoCombate(jogador, inimigo);
    }

    @Test
    @DisplayName("Golpe causa 6 de dano ao inimigo")
    void golpeCausaDano() {
        CartaFactory.criarGolpe().usar(ctx, inimigo);
        assertEquals(24, inimigo.getVida());
    }

    @Test
    @DisplayName("Defender concede escudo ao jogador")
    void defenderConcededEscudo() {
        int escudoAntes = jogador.getEscudo();
        CartaFactory.criarDefesa().usar(ctx, inimigo);
        assertEquals(escudoAntes + 5, jogador.getEscudo());
    }

    @Test
    @DisplayName("Cura restaura vida do jogador")
    void curaRestaurVida() {
        jogador.receberDano(20); // vida = 60
        CartaFactory.criarCura().usar(ctx, jogador);
        assertEquals(68, jogador.getVida()); // 60 + 8
    }

    // TODO: testar EfeitoCompraCarta — carta deve migrar do Baralho para a Mao
    // TODO: testar EfeitoPoder — ataque do jogador deve aumentar pelo valor de intensidade
    // TODO: testar EfeitoDanoMultiplicado — dano = danoBase * multiplicador arredondado para int
    // TODO: testar Investida — causa dano no inimigo E escudo no jogador simultaneamente
    // TODO: testar Golpe Duplo — aplica dano duas vezes (cada EfeitoDano independente)
}
