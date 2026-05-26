package br.edu.ifgoiano.jogo;

import br.edu.ifgoiano.jogo.entidades.Carteira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Carteira — adicionar e gastar moedas")
class CarteiraTest {

    private Carteira carteira;

    @BeforeEach
    void setup() {
        carteira = new Carteira(0, 0, 0);
    }

    @Test
    @DisplayName("adicionar aumenta saldo")
    void adicionarAumentaSaldo() {
        carteira.adicionar(50);
        assertEquals(50, carteira.getMoedas());
    }

    @Test
    @DisplayName("gastar desconta saldo e retorna true")
    void gastarComSaldoSuficiente() {
        carteira.adicionar(100);
        boolean resultado = carteira.gastar(30);
        assertTrue(resultado);
        assertEquals(70, carteira.getMoedas());
    }

    // TODO: testar que adicionar(0) nao altera moedasTotaisGanhos
    // TODO: testar que gastar atualiza moedasGastas corretamente
    // TODO: testar gastar com valor exatamente igual ao saldo (deve retornar true)
}
