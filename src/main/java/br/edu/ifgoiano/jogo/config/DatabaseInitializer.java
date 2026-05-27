package br.edu.ifgoiano.jogo.config;

import br.edu.ifgoiano.jogo.connection.ConnectionFactory;
import br.edu.ifgoiano.jogo.exception.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cria a estrutura do banco de dados usada pelo jogo.
 */
public class DatabaseInitializer {

    private DatabaseInitializer() {}

    public static void inicializar() {
        try (Connection conexao = ConnectionFactory.getConexao();
             Statement statement = conexao.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON");

            for (String sql : COMANDOS_CRIACAO) {
                statement.execute(sql);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Erro ao inicializar o banco de dados.", e);
        }
    }

    private static final String[] COMANDOS_CRIACAO = {
        """
        CREATE TABLE IF NOT EXISTS personagem (
            id_personagem INTEGER PRIMARY KEY AUTOINCREMENT,
            nome VARCHAR(100) NOT NULL,
            vida INTEGER NOT NULL,
            vida_maxima INTEGER NOT NULL,
            energia INTEGER NOT NULL,
            energia_maxima INTEGER NOT NULL,
            ataque INTEGER NOT NULL,
            defesa INTEGER NOT NULL,
            escudo INTEGER NOT NULL,
            nivel INTEGER NOT NULL,
            xp INTEGER NOT NULL
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS jogador (
            id_jogador INTEGER PRIMARY KEY AUTOINCREMENT,
            id_personagem INTEGER NOT NULL UNIQUE,
            direcao VARCHAR(10) NOT NULL CHECK (direcao IN ('NORTE', 'SUL', 'LESTE', 'OESTE')),
            ouro INTEGER NOT NULL,
            chaves INTEGER NOT NULL,
            pontuacao INTEGER NOT NULL,
            salas_exploradas INTEGER NOT NULL,
            inimigos_derrotados INTEGER NOT NULL,
            cartas_jogadas INTEGER NOT NULL,
            moedas_coletadas INTEGER NOT NULL,
            pocoes INTEGER NOT NULL,
            nivel_masmorra INTEGER NOT NULL,
            FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS carteira (
            id_carteira INTEGER PRIMARY KEY AUTOINCREMENT,
            id_jogador INTEGER NOT NULL UNIQUE,
            moedas INTEGER NOT NULL,
            moedas_totais_ganhas INTEGER NOT NULL,
            moedas_gastas INTEGER NOT NULL,
            FOREIGN KEY (id_jogador) REFERENCES jogador(id_jogador) ON DELETE CASCADE
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS inimigo (
            id_inimigo INTEGER PRIMARY KEY AUTOINCREMENT,
            id_personagem INTEGER NOT NULL UNIQUE,
            tipo VARCHAR(50) NOT NULL,
            recompensa_ouro INTEGER NOT NULL,
            recompensa_xp INTEGER NOT NULL,
            elite INTEGER NOT NULL CHECK (elite IN (0, 1)),
            chance_critico INTEGER NOT NULL,
            inteligencia INTEGER NOT NULL,
            proxima_acao VARCHAR(30) CHECK (proxima_acao IN ('ATAQUE', 'ATAQUE_CRITICO', 'DEFESA', 'BUFF', 'CURA')),
            FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem) ON DELETE CASCADE
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS chefe (
            id_chefe INTEGER PRIMARY KEY AUTOINCREMENT,
            id_inimigo INTEGER NOT NULL UNIQUE,
            fase INTEGER NOT NULL,
            modo_furia INTEGER NOT NULL CHECK (modo_furia IN (0, 1)),
            habilidade_especial VARCHAR(150) NOT NULL,
            FOREIGN KEY (id_inimigo) REFERENCES inimigo(id_inimigo) ON DELETE CASCADE
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS carta (
            id_carta INTEGER PRIMARY KEY AUTOINCREMENT,
            nome VARCHAR(100) NOT NULL UNIQUE,
            descricao VARCHAR(255) NOT NULL,
            custo INTEGER NOT NULL,
            tipo VARCHAR(30) NOT NULL CHECK (tipo IN ('ATAQUE', 'DEFESA', 'CURA', 'ESPECIAL', 'PODER')),
            raridade VARCHAR(30) NOT NULL CHECK (raridade IN ('COMUM', 'RARA', 'EPICA', 'LENDARIA')),
            aprimorada INTEGER NOT NULL CHECK (aprimorada IN (0, 1)),
            valor_venda INTEGER NOT NULL
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS efeito (
            id_efeito INTEGER PRIMARY KEY AUTOINCREMENT,
            id_carta INTEGER NOT NULL,
            tipo_efeito VARCHAR(30) NOT NULL CHECK (tipo_efeito IN ('DANO', 'ESCUDO', 'CURA', 'COMPRA', 'MOEDA', 'PODER', 'DANO_MULTIPLICADO')),
            valor INTEGER,
            duracao INTEGER,
            permanente INTEGER NOT NULL CHECK (permanente IN (0, 1)),
            multiplicador DECIMAL(5, 2),
            ignora_defesa INTEGER CHECK (ignora_defesa IN (0, 1)),
            critico INTEGER CHECK (critico IN (0, 1)),
            ordem INTEGER NOT NULL,
            FOREIGN KEY (id_carta) REFERENCES carta(id_carta) ON DELETE CASCADE
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS jogador_carta (
            id_jogador_carta INTEGER PRIMARY KEY AUTOINCREMENT,
            id_jogador INTEGER NOT NULL,
            id_carta INTEGER NOT NULL,
            localizacao VARCHAR(30) NOT NULL CHECK (localizacao IN ('BARALHO', 'MAO', 'DESCARTE', 'REMOVIDA')),
            ordem INTEGER NOT NULL,
            FOREIGN KEY (id_jogador) REFERENCES jogador(id_jogador) ON DELETE CASCADE,
            FOREIGN KEY (id_carta) REFERENCES carta(id_carta)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS item (
            id_item INTEGER PRIMARY KEY AUTOINCREMENT,
            nome VARCHAR(100) NOT NULL UNIQUE,
            descricao VARCHAR(255) NOT NULL,
            preco INTEGER NOT NULL,
            consumivel INTEGER NOT NULL CHECK (consumivel IN (0, 1)),
            raro INTEGER NOT NULL CHECK (raro IN (0, 1)),
            quantidade INTEGER NOT NULL,
            bonus_vida INTEGER NOT NULL,
            bonus_ataque INTEGER NOT NULL,
            bonus_defesa INTEGER NOT NULL,
            tipo VARCHAR(30) NOT NULL CHECK (tipo IN ('POCAO', 'RELIQUIA', 'CHAVE', 'ARTEFATO'))
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS jogador_item (
            id_jogador_item INTEGER PRIMARY KEY AUTOINCREMENT,
            id_jogador INTEGER NOT NULL,
            id_item INTEGER NOT NULL,
            quantidade INTEGER NOT NULL,
            FOREIGN KEY (id_jogador) REFERENCES jogador(id_jogador) ON DELETE CASCADE,
            FOREIGN KEY (id_item) REFERENCES item(id_item),
            UNIQUE (id_jogador, id_item)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS masmorra (
            id_masmorra INTEGER PRIMARY KEY AUTOINCREMENT,
            nivel INTEGER NOT NULL
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS regiao (
            id_regiao INTEGER PRIMARY KEY AUTOINCREMENT,
            id_masmorra INTEGER NOT NULL,
            linha INTEGER NOT NULL,
            coluna INTEGER NOT NULL,
            tipo_regiao VARCHAR(30) NOT NULL CHECK (tipo_regiao IN ('PAREDE', 'CORREDOR', 'SALA_INIMIGO', 'SALA_TESOURO', 'SALA_LOJA')),
            FOREIGN KEY (id_masmorra) REFERENCES masmorra(id_masmorra) ON DELETE CASCADE,
            UNIQUE (id_masmorra, linha, coluna)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS loja (
            id_loja INTEGER PRIMARY KEY AUTOINCREMENT,
            nome VARCHAR(100) NOT NULL,
            nivel_loja INTEGER NOT NULL,
            aberta INTEGER NOT NULL CHECK (aberta IN (0, 1))
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS sala (
            id_sala INTEGER PRIMARY KEY AUTOINCREMENT,
            id_masmorra INTEGER NOT NULL,
            nome VARCHAR(100) NOT NULL,
            descricao VARCHAR(255),
            explorada INTEGER NOT NULL CHECK (explorada IN (0, 1)),
            dificuldade INTEGER NOT NULL,
            tipo VARCHAR(30) NOT NULL CHECK (tipo IN ('INICIO', 'COMBATE', 'BAU', 'LOJA', 'CHEFE', 'EVENTO')),
            id_inimigo INTEGER,
            id_loja INTEGER,
            id_recompensa INTEGER,
            FOREIGN KEY (id_masmorra) REFERENCES masmorra(id_masmorra) ON DELETE CASCADE,
            FOREIGN KEY (id_inimigo) REFERENCES inimigo(id_inimigo),
            FOREIGN KEY (id_loja) REFERENCES loja(id_loja),
            FOREIGN KEY (id_recompensa) REFERENCES item(id_item)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS loja_item (
            id_loja_item INTEGER PRIMARY KEY AUTOINCREMENT,
            id_loja INTEGER NOT NULL,
            id_item INTEGER NOT NULL,
            preco INTEGER NOT NULL,
            FOREIGN KEY (id_loja) REFERENCES loja(id_loja) ON DELETE CASCADE,
            FOREIGN KEY (id_item) REFERENCES item(id_item),
            UNIQUE (id_loja, id_item)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS loja_carta (
            id_loja_carta INTEGER PRIMARY KEY AUTOINCREMENT,
            id_loja INTEGER NOT NULL,
            id_carta INTEGER NOT NULL,
            preco INTEGER NOT NULL,
            FOREIGN KEY (id_loja) REFERENCES loja(id_loja) ON DELETE CASCADE,
            FOREIGN KEY (id_carta) REFERENCES carta(id_carta),
            UNIQUE (id_loja, id_carta)
        )
        """,
        """
        CREATE TABLE IF NOT EXISTS save_game (
            id_save INTEGER PRIMARY KEY AUTOINCREMENT,
            nome_save VARCHAR(100) NOT NULL UNIQUE,
            data_criacao VARCHAR(30) NOT NULL,
            tempo_jogado INTEGER NOT NULL,
            save_automatico INTEGER NOT NULL CHECK (save_automatico IN (0, 1)),
            id_jogador INTEGER NOT NULL UNIQUE,
            id_masmorra INTEGER NOT NULL UNIQUE,
            FOREIGN KEY (id_jogador) REFERENCES jogador(id_jogador) ON DELETE CASCADE,
            FOREIGN KEY (id_masmorra) REFERENCES masmorra(id_masmorra) ON DELETE CASCADE
        )
        """
    };
}
