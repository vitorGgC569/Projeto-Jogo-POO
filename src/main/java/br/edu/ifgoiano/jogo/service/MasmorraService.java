package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.entidades.Sala;
import br.edu.ifgoiano.jogo.entidades.Masmorra;
import br.edu.ifgoiano.jogo.enums.TipoSala;

/**
 * Controla a geracao e a navegacao pelas salas e eventos da masmorra.
 */
public class MasmorraService {

    /** Marca a sala como explorada. */
    public void explorarSala(Sala sala) {
        sala.setExplorada(true);
    }

    /** Verifica se a sala possui combate pendente. */
    public boolean possuiCombate(Sala sala) {
        return sala.getTipo() == TipoSala.COMBATE && sala.possuiInimigo();
    }

    /** Verifica se a sala e sala de chefe. */
    public boolean eSalaChefe(Sala sala) {
        return sala.getTipo() == TipoSala.CHEFE;
    }
}
