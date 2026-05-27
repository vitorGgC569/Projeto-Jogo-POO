package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.entidades.Loja;
import br.edu.ifgoiano.jogo.entidades.Sala;
import br.edu.ifgoiano.jogo.enums.TipoSala;
import br.edu.ifgoiano.jogo.util.AleatorioUtil;

import java.util.Arrays;

/**
 * Fábrica responsável por criar os diferentes tipos de salas da masmorra,
 * configurando inimigos, lojas e recompensas conforme o tipo de sala.
 */
public class SalaFactory {

    /** Contador interno usado para gerar identificadores únicos para cada sala criada. */
    private static int proximoId = 1;

    /**
     * Cria uma sala de combate com um inimigo sorteado aleatoriamente.
     * Há 20% de chance de ser um esqueleto elite, caso contrário é um esqueleto ou morcego comum.
     *
     * @return sala de combate com um inimigo configurado
     */
    public static Sala criarSalaCombate() {
        Inimigo inimigo = AleatorioUtil.chance(0.2)
                ? InimigoFactory.criarEsqueletoElite()
                : (AleatorioUtil.chance(0.5) ? InimigoFactory.criarEsqueleto() : InimigoFactory.criarMorcego());
        Sala sala = new Sala(proximoId++, "Sala de Combate", "Um inimigo aguarda.", false, 1, TipoSala.COMBATE);
        sala.setInimigo(inimigo);
        return sala;
    }

    /**
     * Cria uma sala de loja com um comerciante que oferece poções e cartas básicas para compra.
     *
     * @return sala de loja com itens e cartas disponíveis para venda
     */
    public static Sala criarSalaLoja() {
        Loja loja = new Loja("Loja do Aventureiro", 1, true,
                Arrays.asList(ItemFactory.criarPocaoDeVida()),
                Arrays.asList(CartaFactory.criarGolpe(), CartaFactory.criarDefesa(), CartaFactory.criarCura()));
        Sala sala = new Sala(proximoId++, "Loja", "Um comerciante misterioso.", false, 0, TipoSala.LOJA);
        sala.setLoja(loja);
        return sala;
    }

    /**
     * Cria uma sala de baú com uma recompensa sorteada aleatoriamente entre poção de vida ou chave.
     *
     * @return sala de baú com um item de recompensa definido
     */
    public static Sala criarSalaBau() {
        Item recompensa = AleatorioUtil.chance(0.5)
                ? ItemFactory.criarPocaoDeVida()
                : ItemFactory.criarChave();
        Sala sala = new Sala(proximoId++, "Bau do Tesouro", "Um bau misterioso.", false, 0, TipoSala.BAU);
        sala.setRecompensa(recompensa);
        return sala;
    }

    /**
     * Cria a sala do chefe com o inimigo principal da masmorra já posicionado.
     *
     * @return sala de chefe com o Rei dos Mortos configurado para o combate final
     */
    public static Sala criarSalaChefe() {
        Sala sala = new Sala(proximoId++, "Sala do Chefe", "O chefe aguarda.", false, 5, TipoSala.CHEFE);
        sala.setInimigo(InimigoFactory.criarChefe());
        return sala;
    }
}
