package br.edu.ifgoiano.jogo;

import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.factory.InimigoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Personagem — dano, cura e escudo")
class PersonagemTest {

    private Inimigo alvo;

    @BeforeEach
    void setup() {
        alvo = InimigoFactory.criarEsqueleto(); // vida=30, vidaMaxima=30, escudo=0
    }

    @Test
    @DisplayName("receberDano reduz vida corretamente")
    void receberDanoReduzVida() {
        alvo.receberDano(10);
        assertEquals(20, alvo.getVida());
    }

    @Test
    @DisplayName("estaVivo retorna true enquanto vida > 0")
    void estaVivoComVidaPositiva() {
        alvo.receberDano(29);
        assertTrue(alvo.estaVivo());
    }

    // TODO: testar receberDano com escudo parcial (dano maior que escudo)
    // TODO: testar ganharEscudo acumula com escudo ja existente
    // TODO: testar curar com valor 0 nao muda vida
    // TODO: testar Chefe.verificarFuria() ativa com vida < 30% da vidaMaxima
    // TODO: testar Chefe.verificarFuria() nao ativa novamente se modoFuria ja for true
}
