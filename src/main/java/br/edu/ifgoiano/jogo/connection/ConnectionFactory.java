package br.edu.ifgoiano.jogo.connection;

import br.edu.ifgoiano.jogo.config.DatabaseConfig;
import br.edu.ifgoiano.jogo.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Responsavel por estabelecer e gerenciar conexoes JDBC com o banco de dados.
 */
public class ConnectionFactory {

    private ConnectionFactory() {}

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(
                DatabaseConfig.URL,
                DatabaseConfig.USUARIO,
                DatabaseConfig.SENHA
            );
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao conectar ao banco de dados.", e);
        }
    }
}
