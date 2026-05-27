package br.edu.ifgoiano.jogo.mapper;

import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.enums.TipoItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Converte linhas do ResultSet em entidades {@link Item}.
 */
public class ItemMapper {

    private ItemMapper() {}

    /**
     * Monta um {@link Item} a partir do ResultSet posicionado em uma linha
     * de {@code SELECT * FROM item}.
     */
    public static Item mapear(ResultSet rs) throws SQLException {
        Item i = new Item();
        i.setId(rs.getInt("id"));
        i.setNome(rs.getString("nome"));
        i.setDescricao(rs.getString("descricao"));
        i.setPreco(rs.getInt("preco"));
        i.setConsumivel(rs.getInt("consumivel") == 1);
        i.setRaro(rs.getInt("raro") == 1);
        i.setQuantidade(rs.getInt("quantidade"));
        i.setBonusVida(rs.getInt("bonus_vida"));
        i.setBonusAtaque(rs.getInt("bonus_ataque"));
        i.setBonusDefesa(rs.getInt("bonus_defesa"));

        String tipo = rs.getString("tipo");
        if (tipo != null) {
            try { i.setTipo(TipoItem.valueOf(tipo)); }
            catch (IllegalArgumentException ignored) {}
        }
        return i;
    }
}
