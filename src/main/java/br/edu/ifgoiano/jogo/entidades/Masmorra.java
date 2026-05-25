package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.TipoRegiao;

/**
 * Representa a estrutura de progressão por fases do jogo.
 */
public class Masmorra {

    //Matriz 9 X 5
    private Regiao[][] regioes = new Regiao[9][5];

    public Masmorra() {
        inicializarMasmorra();
    }
    /**
     * Inicializa a masmorra seguindo o template do mapa.
     */
    private void inicializarMasmorra() {
        TipoRegiao[][] mapaTemplate = {
                // Colunas:      0                         1                         2                         3                         4
                /* Linha 0 */ { TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE },
                /* Linha 1 */ { TipoRegiao.PAREDE,       TipoRegiao.SALA_INIMIGO, TipoRegiao.SALA_INIMIGO, TipoRegiao.SALA_INIMIGO, TipoRegiao.PAREDE },
                /* Linha 2 */ { TipoRegiao.SALA_TESOURO, TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.SALA_LOJA },
                /* Linha 3 */ { TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.PAREDE },
                /* Linha 4 */ { TipoRegiao.SALA_TESOURO, TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.SALA_LOJA },
                /* Linha 5 */ { TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.PAREDE },
                /* Linha 6 */ { TipoRegiao.SALA_TESOURO, TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.SALA_LOJA },
                /* Linha 7 */ { TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.CORREDOR,     TipoRegiao.PAREDE,       TipoRegiao.PAREDE },
                /* Linha 8 */ { TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE,       TipoRegiao.PAREDE }
        };

        // Instancia os objetos Regiao
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                this.regioes[i][j] = new Regiao(mapaTemplate[i][j]);
            }
        }
    }

    /**
     * Obtém a matriz completa de regiões da masmorra.
     */
    public Regiao[][] getRegioes() {
        return this.regioes;
    }

    /**
     * Obtém uma região específica com base nas coordenadas (linha, coluna).
     */
    public Regiao getRegiao(int linha, int coluna) {
        if (linha >= 0 && linha < 9 && coluna >= 0 && coluna < 5) {
            return this.regioes[linha][coluna];
        }
        return null; // Retorna null se tentar acessar fora dos limites
    }
}