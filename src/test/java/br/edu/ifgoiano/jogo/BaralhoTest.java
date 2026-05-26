package br.edu.ifgoiano.jogo;

import br.edu.ifgoiano.jogo.entidades.Baralho;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.factory.CartaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Baralho — comprar, embaralhar e estado")
class BaralhoTest {

    private Baralho baralho;

    @BeforeEach
    void setup() {
        baralho = new Baralho(10, 0, false, new ArrayList<>());
        baralho.adicionarCarta(CartaFactory.criarGolpe());
        baralho.adicionarCarta(CartaFactory.criarDefesa());
        baralho.adicionarCarta(CartaFactory.criarCura());
    }

    @Test
    @DisplayName("estaVazio retorna false com cartas")
    void naoEstaVazioComCartas() {
        assertFalse(baralho.estaVazio());
    }

    @Test
    @DisplayName("comprar retorna carta nao nula")
    void comprarRetornaCarta() {
        Carta carta = baralho.comprar();
        assertNotNull(carta);
    }


    // TODO: adicionarCarta aumenta o baralho
    // TODO: embaralhar marca flag embaralhado como true
    // TODO: testar que embaralhar muda a ordem das cartas (rodar N vezes e checar divergencia)
    // TODO: testar que adicionarCarta nao aceita null sem lancar NullPointerException
    // TODO: testar tamanhoMaximo e se adicionarCarta respeita o limite
}
