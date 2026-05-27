package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.connection.ConnectionFactory;
import br.edu.ifgoiano.jogo.entidades.Item;
import br.edu.ifgoiano.jogo.exception.DatabaseException;
import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.mapper.ItemMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO completo para a entidade {@link Item}, com persistencia em SQLite via JDBC.
 */
public class ItemDAO implements GenericDAO<Item> {

    private static final String SELECT_ALL = "SELECT * FROM item ORDER BY id";
    private static final String SELECT_ID  = "SELECT * FROM item WHERE id = ?";
    private static final String INSERT     =
            "INSERT INTO item (nome, descricao, preco, consumivel, raro, quantidade, bonus_vida, bonus_ataque, bonus_defesa, tipo) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE     =
            "UPDATE item SET nome=?, descricao=?, preco=?, consumivel=?, raro=?, quantidade=?, bonus_vida=?, bonus_ataque=?, bonus_defesa=?, tipo=? WHERE id=?";
    private static final String DELETE     = "DELETE FROM item WHERE id=?";

    @Override
    public void salvar(Item i) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencher(ps, i);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) i.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao salvar item '" + i.getNome() + "'.", e);
        }
    }

    @Override
    public void atualizar(Item i) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            preencher(ps, i);
            ps.setInt(11, i.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar item id=" + i.getId(), e);
        }
    }

    @Override
    public void deletar(int id) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar item id=" + id, e);
        }
    }

    @Override
    public Optional<Item> buscarPorId(int id) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(SELECT_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(ItemMapper.mapear(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar item id=" + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> listarTodos() {
        List<Item> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {
            while (rs.next()) lista.add(ItemMapper.mapear(rs));
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao listar itens.", e);
        }
        return lista;
    }

    private void preencher(PreparedStatement ps, Item i) throws SQLException {
        ps.setString(1, i.getNome());
        ps.setString(2, i.getDescricao());
        ps.setInt   (3, i.getPreco());
        ps.setInt   (4, i.isConsumivel() ? 1 : 0);
        ps.setInt   (5, i.isRaro() ? 1 : 0);
        ps.setInt   (6, i.getQuantidade());
        ps.setInt   (7, i.getBonusVida());
        ps.setInt   (8, i.getBonusAtaque());
        ps.setInt   (9, i.getBonusDefesa());
        ps.setString(10, i.getTipo() != null ? i.getTipo().name() : null);
    }
}
