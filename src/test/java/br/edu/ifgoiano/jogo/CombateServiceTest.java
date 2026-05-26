package br.edu.ifgoiano.jogo;

import br.edu.ifgoiano.jogo.core.Combate;
import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.factory.CartaFactory;
import br.edu.ifgoiano.jogo.factory.InimigoFactory;
import br.edu.ifgoiano.jogo.service.CombateService;
import br.edu.ifgoiano.jogo.service.JogadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CombateService — fluxo de turnos")
class CombateServiceTest {

    private Jogador jogador;
    private Inimigo inimigo;
    private CombateService svc;
    private Combate combate;

    @BeforeEach
    void setup() {
        jogador = new JogadorService().criarJogador("Heroi");
        for (int i = 0; i < 10; i++) jogador.getBaralho().adicionarCarta(CartaFactory.criarGolpe());

        inimigo = InimigoFactory.criarMorcego(); // vida=18
        svc = new CombateService();
        combate = svc.iniciarCombate(jogador, inimigo);
    }

    @Test
    @DisplayName("iniciarCombate popula a mao do jogador")
    void iniciarCombatePopulaMao() {
        assertFalse(jogador.getMao().getCartas().isEmpty());
    }

    @Test
    @DisplayName("inimigo tem acao definida ao iniciar combate")
    void inimigoTemAcaoDefinida() {
        assertNotNull(inimigo.getProximaAcao());
    }


    // TODO: testar finalizarTurnoJogador — mao deve ser esvaziada para o descarte
    // TODO: testar finalizarTurnoJogador — energia do jogador deve resetar para energiaMaxima
    // TODO: testar finalizarTurnoJogador — turno.numero deve avancar apos turno do inimigo
    // TODO: testar reembaralhar — quando baralho esvazia, descarte vira baralho novamente
    // TODO: testar executarTurnoInimigo ATAQUE — jogador perde vida igual a ataque do inimigo
    // TODO: testar executarTurnoInimigo DEFESA — inimigo ganha escudo
    // TODO: testar executarTurnoInimigo CURA — inimigo recupera 10% da vidaMaxima
}
