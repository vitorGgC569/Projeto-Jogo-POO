-- =========================================================
-- Seed das cartas, itens e inimigos iniciais
-- =========================================================

-- ---- Cartas ----
INSERT OR IGNORE INTO carta (nome, descricao, custo, tipo, raridade, aprimorada, valor_venda, arquivo_img) VALUES
    ('Golpe',          'Causa 6 de dano.',                          1, 'ATAQUE',   'COMUM',    0, 10, 'carta_adaga'),
    ('Defender',       'Ganha 5 de escudo.',                        1, 'DEFESA',   'COMUM',    0, 10, 'carta_escudo'),
    ('Cura',           'Recupera 8 de vida.',                       1, 'CURA',     'COMUM',    0, 15, 'carta_pocao_cura'),
    ('Golpe Critico',  'Causa 12 de dano.',                         2, 'ATAQUE',   'RARA',     0, 25, 'carta_foice'),
    ('Investida',      'Causa 4 de dano e ganha 3 de escudo.',      1, 'ESPECIAL', 'COMUM',    0, 15, 'carta_lanca'),
    ('Raiva',          'Aumenta o ataque em 3.',                    1, 'PODER',    'RARA',     0, 20, 'carta_poder'),
    ('Golpe Duplo',    'Causa 4 de dano duas vezes.',               2, 'ATAQUE',   'RARA',     0, 30, 'carta_maca'),
    ('Saque',          'Causa 3 de dano e ganha 2 moedas.',         1, 'ESPECIAL', 'RARA',     0, 20, 'carta_flecha'),
    ('Flecha Envenenada','Causa 5 de dano direto.',                 1, 'ATAQUE',   'COMUM',    0, 18, 'carta_flecha_envenenada'),
    ('Agua Benta',     'Recupera 12 de vida.',                      2, 'CURA',     'RARA',     0, 30, 'carta_agua_benta'),
    ('Antidoto',       'Recupera 6 de vida e remove venenos.',      1, 'CURA',     'COMUM',    0, 20, 'carta_antidoto'),
    ('Biblia Sagrada', 'Aumenta ataque em 5 permanentemente.',      2, 'PODER',    'EPICA',    0, 40, 'carta_biblia'),
    ('Maldicao',       'Causa 15 de dano em si e no inimigo.',      0, 'ESPECIAL', 'EPICA',    0, 25, 'carta_maldicao');

-- ---- Efeitos das cartas ----
-- Golpe -> Dano 6
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 6, 0 FROM carta WHERE nome = 'Golpe';
-- Defender -> Escudo 5
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'ESCUDO', 5, 0 FROM carta WHERE nome = 'Defender';
-- Cura -> Cura 8
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'CURA', 8, 0 FROM carta WHERE nome = 'Cura';
-- Golpe Critico -> Dano 12 (critico)
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 12, 1 FROM carta WHERE nome = 'Golpe Critico';
-- Investida -> Dano 4 + Escudo 3
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO',    4, 0 FROM carta WHERE nome = 'Investida';
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'ESCUDO',  3, 0 FROM carta WHERE nome = 'Investida';
-- Raiva -> Poder 3
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'PODER', 3, 0 FROM carta WHERE nome = 'Raiva';
-- Golpe Duplo -> 2 x Dano 4
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 4, 0 FROM carta WHERE nome = 'Golpe Duplo';
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 4, 0 FROM carta WHERE nome = 'Golpe Duplo';
-- Saque -> Dano 3 + Moedas 2
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO',   3, 0 FROM carta WHERE nome = 'Saque';
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'MOEDA',  2, 0 FROM carta WHERE nome = 'Saque';
-- Flecha Envenenada -> Dano 5
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 5, 0 FROM carta WHERE nome = 'Flecha Envenenada';
-- Agua Benta -> Cura 12
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'CURA', 12, 0 FROM carta WHERE nome = 'Agua Benta';
-- Antidoto -> Cura 6
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'CURA', 6, 0 FROM carta WHERE nome = 'Antidoto';
-- Biblia Sagrada -> Poder 5
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'PODER', 5, 0 FROM carta WHERE nome = 'Biblia Sagrada';
-- Maldicao -> Dano 15
INSERT OR IGNORE INTO carta_efeito (carta_id, tipo_efeito, valor, extra_bool)
    SELECT id, 'DANO', 15, 0 FROM carta WHERE nome = 'Maldicao';

-- ---- Itens ----
INSERT OR IGNORE INTO item (nome, descricao, preco, consumivel, raro, quantidade, bonus_vida, bonus_ataque, bonus_defesa, tipo) VALUES
    ('Pocao Pequena',  'Recupera 10 de vida.',           20, 1, 0, 1, 10, 0, 0, 'POCAO'),
    ('Pocao Grande',   'Recupera 25 de vida.',           50, 1, 1, 1, 25, 0, 0, 'POCAO'),
    ('Espada Afiada',  '+3 de ataque permanente.',       80, 0, 1, 1,  0, 3, 0, 'RELIQUIA'),
    ('Escudo de Aco',  '+3 de defesa permanente.',       80, 0, 1, 1,  0, 0, 3, 'RELIQUIA');

-- ---- Inimigos ----
INSERT OR IGNORE INTO inimigo (nome, tipo, vida, vida_maxima, ataque, defesa, chance_critico, inteligencia, recompensa_ouro, recompensa_xp, elite, arquivo_img) VALUES
    ('Cobra Venenosa', 'Reptil', 60, 60, 10, 2, 20, 5, 15, 20, 0, 'inimigo_cobra'),
    ('Rato Gigante',   'Roedor', 40, 40,  8, 1, 15, 3, 10, 15, 0, 'inimigo_rato');
