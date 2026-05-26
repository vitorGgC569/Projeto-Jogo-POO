package br.edu.ifgoiano.jogo.factory;

import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.entidades.Loja;
import br.edu.ifgoiano.jogo.entidades.Sala;
import br.edu.ifgoiano.jogo.enums.TipoSala;
import br.edu.ifgoiano.jogo.util.AleatorioUtil;

import java.util.Arrays;

/**
 * Fabrica para instanciacao de salas de masmorra baseadas em seu tipo.
 */
public class SalaFactory {

    private static int proximoId = 1;

    public static Sala criarSalaCombate() {
        Inimigo inimigo = AleatorioUtil.chance(0.2)
                ? InimigoFactory.criarEsqueletoElite()
                : (AleatorioUtil.chance(0.5) ? InimigoFactory.criarEsqueleto() : InimigoFactory.criarMorcego());
        Sala sala = new Sala(proximoId++, "Sala de Combate", "Um inimigo aguarda.", false, 1, TipoSala.COMBATE);
        sala.setInimigo(inimigo);
        return sala;
    }

    public static Sala criarSalaLoja() {
        Loja loja = new Loja("Loja do Aventureiro", 1, true,
                Arrays.asList(ItemFactory.criarPocaoDeVida()),
                Arrays.asList(CartaFactory.criarGolpe(), CartaFactory.criarDefesa(), CartaFactory.criarCura()));
        Sala sala = new Sala(proximoId++, "Loja", "Um comerciante misterioso.", false, 0, TipoSala.LOJA);
        sala.setLoja(loja);
        return sala;
    }

    public static Sala criarSalaBau() {
        Item recompensa = AleatorioUtil.chance(0.5)
                ? ItemFactory.criarPocaoDeVida()
                : ItemFactory.criarChave();
        Sala sala = new Sala(proximoId++, "Bau do Tesouro", "Um bau misterioso.", false, 0, TipoSala.BAU);
        sala.setRecompensa(recompensa);
        return sala;
    }

    public static Sala criarSalaChefe() {
        Sala sala = new Sala(proximoId++, "Sala do Chefe", "O chefe aguarda.", false, 5, TipoSala.CHEFE);
        sala.setInimigo(InimigoFactory.criarChefe());
        return sala;
    }
}
