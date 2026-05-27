package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.connection.ConnectionFactory;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.exception.DatabaseException;
import br.edu.ifgoiano.jogo.interfaces.Efeito;
import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.mapper.CartaMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DAO completo para a entidade {@link Carta}, com persistencia em SQLite via JDBC.
 * Carrega tambem os efeitos associados na tabela carta_efeito.
 */
public class CartaDAO implements GenericDAO<Carta> {

    private static final String SELECT_ALL  = "SELECT * FROM carta ORDER BY id";
    private static final String SELECT_ID   = "SELECT * FROM carta WHERE id = ?";
    private static final String SELECT_NOME = "SELECT * FROM carta WHERE nome = ?";
    private static final String INSERT      =
            "INSERT INTO carta (nome, descricao, custo, tipo, raridade, aprimorada, valor_venda, arquivo_img) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE      =
            "UPDATE carta SET nome=?, descricao=?, custo=?, tipo=?, raridade=?, aprimorada=?, valor_venda=?, arquivo_img=? WHERE id=?";
    private static final String DELETE      = "DELETE FROM carta WHERE id=?";

    private static final String SELECT_EFEITOS_TODOS =
            "SELECT carta_id, tipo_efeito, valor, extra_bool FROM carta_efeito ORDER BY id";
    private static final String SELECT_EFEITOS_CARTA =
            "SELECT tipo_efeito, valor, extra_bool FROM carta_efeito WHERE carta_id = ? ORDER BY id";

    // =========================================================
    // CRUD
    // =========================================================

    @Override
    public void salvar(Carta c) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencher(ps, c);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) c.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao salvar carta '" + c.getNome() + "'.", e);
        }
    }

    @Override
    public void atualizar(Carta c) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            preencher(ps, c);
            ps.setInt(9, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao atualizar carta id=" + c.getId(), e);
        }
    }

    @Override
    public void deletar(int id) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao deletar carta id=" + id, e);
        }
    }

    @Override
    public Optional<Carta> buscarPorId(int id) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(SELECT_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Carta c = CartaMapper.mapear(rs);
                    carregarEfeitos(conn, c);
                    return Optional.of(c);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar carta id=" + id, e);
        }
        return Optional.empty();
    }

    /** Busca uma carta pelo nome exato. */
    public Optional<Carta> buscarPorNome(String nome) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(SELECT_NOME)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Carta c = CartaMapper.mapear(rs);
                    carregarEfeitos(conn, c);
                    return Optional.of(c);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar carta nome=" + nome, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Carta> listarTodos() {
        List<Carta> resultado = new ArrayList<>();
        Map<Integer, Carta> porId = new HashMap<>();

        try (Connection conn = ConnectionFactory.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                Carta c = CartaMapper.mapear(rs);
                resultado.add(c);
                porId.put(c.getId(), c);
            }

            // Carrega efeitos em uma unica query
            try (Statement st2 = conn.createStatement();
                 ResultSet er = st2.executeQuery(SELECT_EFEITOS_TODOS)) {
                while (er.next()) {
                    Carta dona = porId.get(er.getInt("carta_id"));
                    if (dona == null) continue;
                    Efeito ef = CartaMapper.mapearEfeito(
                            er.getString("tipo_efeito"),
                            er.getInt("valor"),
                            er.getInt("extra_bool") == 1);
                    if (ef != null) dona.adicionarEfeito(ef);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao listar cartas.", e);
        }
        return resultado;
    }

    /**
     * Carrega o nome do arquivo de imagem registrado para a carta.
     *
     * @return nome do arquivo (sem extensao) ou {@code null} se nao houver
     */
    public String buscarArquivoImg(int cartaId) {
        try (Connection conn = ConnectionFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement("SELECT arquivo_img FROM carta WHERE id=?")) {
            ps.setInt(1, cartaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString("arquivo_img");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao buscar arquivo_img da carta id=" + cartaId, e);
        }
        return null;
    }

    // =========================================================
    // Helpers
    // =========================================================

    private void preencher(PreparedStatement ps, Carta c) throws SQLException {
        ps.setString(1, c.getNome());
        ps.setString(2, c.getDescricao());
        ps.setInt   (3, c.getCusto());
        ps.setString(4, c.getTipo() != null ? c.getTipo().name() : "ATAQUE");
        ps.setString(5, c.getRaridade() != null ? c.getRaridade().name() : "COMUM");
        ps.setInt   (6, c.isAprimorada() ? 1 : 0);
        ps.setInt   (7, c.getValorVenda());
        ps.setString(8, null);
    }

    private void carregarEfeitos(Connection conn, Carta c) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SELECT_EFEITOS_CARTA)) {
            ps.setInt(1, c.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Efeito ef = CartaMapper.mapearEfeito(
                            rs.getString("tipo_efeito"),
                            rs.getInt("valor"),
                            rs.getInt("extra_bool") == 1);
                    if (ef != null) c.adicionarEfeito(ef);
                }
            }
        }
    }
}
