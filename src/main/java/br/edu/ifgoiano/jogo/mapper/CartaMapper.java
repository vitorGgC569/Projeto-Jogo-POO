package br.edu.ifgoiano.jogo.mapper;

import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.EfeitoCura;
import br.edu.ifgoiano.jogo.entidades.EfeitoDano;
import br.edu.ifgoiano.jogo.entidades.EfeitoEscudo;
import br.edu.ifgoiano.jogo.entidades.EfeitoMoeda;
import br.edu.ifgoiano.jogo.entidades.EfeitoPoder;
import br.edu.ifgoiano.jogo.enums.RaridadeCarta;
import br.edu.ifgoiano.jogo.enums.TipoCarta;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Converte linhas do ResultSet em entidades {@link Carta} e instancia
 * o {@link Efeito} adequado segundo o tipo de efeito persistido.
 */
public class CartaMapper {

    private CartaMapper() {}

    /**
     * Monta uma {@link Carta} a partir do ResultSet posicionado em uma linha
     * de {@code SELECT * FROM carta}.
     */
    public static Carta mapear(ResultSet rs) throws SQLException {
        Carta c = new Carta();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        c.setDescricao(rs.getString("descricao"));
        c.setCusto(rs.getInt("custo"));
        c.setTipo(TipoCarta.valueOf(rs.getString("tipo")));
        c.setRaridade(RaridadeCarta.valueOf(rs.getString("raridade")));
        c.setAprimorada(rs.getInt("aprimorada") == 1);
        c.setValorVenda(rs.getInt("valor_venda"));
        return c;
    }

    /**
     * Instancia um {@link Efeito} concreto a partir do tipo e dos
     * parametros gravados na tabela carta_efeito.
     *
     * @param tipoEfeito codigo do efeito (DANO, CURA, ESCUDO, PODER, MOEDA)
     * @param valor      magnitude do efeito
     * @param extraBool  flag adicional (ex.: critico para DANO)
     * @return instancia do efeito ou {@code null} se o tipo for desconhecido
     */
    public static Efeito mapearEfeito(String tipoEfeito, int valor, boolean extraBool) {
        if (tipoEfeito == null) return null;
        return switch (tipoEfeito.toUpperCase()) {
            case "DANO"   -> new EfeitoDano(valor, false, extraBool);
            case "CURA"   -> new EfeitoCura(valor);
            case "ESCUDO" -> new EfeitoEscudo(valor);
            case "PODER"  -> new EfeitoPoder(valor);
            case "MOEDA"  -> new EfeitoMoeda(valor);
            default       -> null;
        };
    }
}
