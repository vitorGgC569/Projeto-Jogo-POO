-- =========================================================
-- Schema do banco SQLite do jogo Dungeon Crawler POO
-- =========================================================

-- Tabela de cartas disponiveis no jogo
CREATE TABLE IF NOT EXISTS carta (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    nome         TEXT    NOT NULL UNIQUE,
    descricao    TEXT    NOT NULL,
    custo        INTEGER NOT NULL DEFAULT 1,
    tipo         TEXT    NOT NULL CHECK (tipo IN ('ATAQUE','DEFESA','CURA','ESPECIAL','PODER')),
    raridade     TEXT    NOT NULL CHECK (raridade IN ('COMUM','RARA','EPICA','LENDARIA')),
    aprimorada   INTEGER NOT NULL DEFAULT 0,
    valor_venda  INTEGER NOT NULL DEFAULT 0,
    arquivo_img  TEXT
);

-- Tabela de efeitos associados a uma carta
CREATE TABLE IF NOT EXISTS carta_efeito (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    carta_id    INTEGER NOT NULL,
    tipo_efeito TEXT    NOT NULL,
    valor       INTEGER NOT NULL DEFAULT 0,
    extra_bool  INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (carta_id) REFERENCES carta(id) ON DELETE CASCADE
);

-- Tabela de itens disponiveis no jogo
CREATE TABLE IF NOT EXISTS item (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    nome         TEXT    NOT NULL UNIQUE,
    descricao    TEXT    NOT NULL,
    preco        INTEGER NOT NULL DEFAULT 0,
    consumivel   INTEGER NOT NULL DEFAULT 1,
    raro         INTEGER NOT NULL DEFAULT 0,
    quantidade   INTEGER NOT NULL DEFAULT 1,
    bonus_vida   INTEGER NOT NULL DEFAULT 0,
    bonus_ataque INTEGER NOT NULL DEFAULT 0,
    bonus_defesa INTEGER NOT NULL DEFAULT 0,
    tipo         TEXT
);

-- Tabela de inimigos
CREATE TABLE IF NOT EXISTS inimigo (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    nome            TEXT    NOT NULL UNIQUE,
    tipo            TEXT,
    vida            INTEGER NOT NULL DEFAULT 30,
    vida_maxima     INTEGER NOT NULL DEFAULT 30,
    ataque          INTEGER NOT NULL DEFAULT 5,
    defesa          INTEGER NOT NULL DEFAULT 0,
    chance_critico  INTEGER NOT NULL DEFAULT 10,
    inteligencia    INTEGER NOT NULL DEFAULT 5,
    recompensa_ouro INTEGER NOT NULL DEFAULT 10,
    recompensa_xp   INTEGER NOT NULL DEFAULT 10,
    elite           INTEGER NOT NULL DEFAULT 0,
    arquivo_img     TEXT
);

-- Tabela do jogador (save unico, atualizado no progresso)
CREATE TABLE IF NOT EXISTS jogador (
    id                   INTEGER PRIMARY KEY AUTOINCREMENT,
    nome                 TEXT    NOT NULL,
    vida                 INTEGER NOT NULL DEFAULT 80,
    vida_maxima          INTEGER NOT NULL DEFAULT 80,
    energia              INTEGER NOT NULL DEFAULT 3,
    energia_maxima       INTEGER NOT NULL DEFAULT 3,
    ataque               INTEGER NOT NULL DEFAULT 5,
    defesa               INTEGER NOT NULL DEFAULT 2,
    escudo               INTEGER NOT NULL DEFAULT 0,
    nivel                INTEGER NOT NULL DEFAULT 1,
    xp                   INTEGER NOT NULL DEFAULT 0,
    ouro                 INTEGER NOT NULL DEFAULT 0,
    chaves               INTEGER NOT NULL DEFAULT 0,
    pontuacao            INTEGER NOT NULL DEFAULT 0,
    salas_exploradas     INTEGER NOT NULL DEFAULT 0,
    inimigos_derrotados  INTEGER NOT NULL DEFAULT 0,
    cartas_jogadas       INTEGER NOT NULL DEFAULT 0,
    moedas               INTEGER NOT NULL DEFAULT 100,
    moedas_totais        INTEGER NOT NULL DEFAULT 100,
    moedas_gastas        INTEGER NOT NULL DEFAULT 0
);

-- Tabela do inventario do jogador (cartas que ele possui)
CREATE TABLE IF NOT EXISTS jogador_carta (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    jogador_id INTEGER NOT NULL,
    carta_id   INTEGER NOT NULL,
    FOREIGN KEY (jogador_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (carta_id)   REFERENCES carta(id)   ON DELETE CASCADE
);
