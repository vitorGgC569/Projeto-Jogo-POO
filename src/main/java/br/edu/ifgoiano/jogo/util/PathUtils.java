package br.edu.ifgoiano.jogo.util;

import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;

import java.net.URL;

/**
 * Utilitário para resolução dos caminhos dos assets de imagem da masmorra.
 *
 * <p>O mapeamento é primariamente <strong>posicional</strong>: a direção do
 * interior de uma sala é determinada pela sua coluna/linha no grid, não pelo
 * seu tipo. Isso permite que qualquer tipo de sala (inimigo, tesouro, loja)
 * seja colocado em qualquer posição lateral sem alterar este arquivo.</p>
 *
 * <h3>Direções de interior por posição:</h3>
 * <ul>
 *   <li>Coluna 0 (salas laterais esquerdas) → interior visível olhando <b>Oeste</b></li>
 *   <li>Coluna 4 (salas laterais direitas)  → interior visível olhando <b>Leste</b></li>
 *   <li>Linha 1 (sala do chefe, cols 1-3)   → interior visível olhando <b>Norte</b></li>
 * </ul>
 *
 * <h3>Imagens disponíveis em src/Assets/:</h3>
 * <ul>
 *   <li>{@code fundo_corredor_parede_SUL}                    — parede de frente (dead-end)</li>
 *   <li>{@code fundo_corredor_boss_NORTE}                    — horizonte profundo (chefe)</li>
 *   <li>{@code corredor_sem_fundo_lateral_parede_NORTE_SUL}  — corredor sem portas laterais</li>
 *   <li>{@code corredor_sem_fundo_lateral_portas_1_2_NORTE}  — corredor com portas I e II</li>
 *   <li>{@code corredor_sem_fundo_lateral_portas_3_4_NORTE}  — corredor com portas III e IV</li>
 *   <li>{@code corredor_fundo_boss_lateral_portas_5_6_NORTE} — chefe visível + portas V e VI</li>
 *   <li>{@code corredor_fundo_parede_lateral_portas_1_2_SUL} — parede Sul + portas I e II</li>
 *   <li>{@code lateral_porta_1..6}                           — vista de perto de cada porta</li>
 *   <li>{@code lateral_parede}                               — parede sólida de perto</li>
 *   <li>{@code sala_vazia}                                   — interior genérico (inimigo/baú)</li>
 *   <li>{@code sala_loja}                                    — interior da loja</li>
 * </ul>
 */
public final class PathUtils {

    private PathUtils() {}

    // =========================================================
    // API pública
    // =========================================================

    public static String obterPathPorTipo(TipoRegiao tipo, Direcao direcao) {
        return obterPathPorTipo(tipo, direcao, -1, -1);
    }

    public static String obterPathPorTipo(TipoRegiao tipo, Direcao direcao, int linha, int coluna) {
        return resolver(nomeFundo(tipo, direcao, linha, coluna));
    }

    public static String obterPathPorTipo(TipoRegiao tipo) {
        return obterPathPorTipo(tipo, Direcao.NORTE, -1, -1);
    }

    /** Overlay removido — retorna sempre null para compatibilidade. */
    public static String obterOverlayPath(TipoRegiao tipo, Direcao direcao) { return null; }
    public static String obterOverlayPath(TipoRegiao tipo, Direcao direcao, int linha, int coluna) { return null; }

    // =========================================================
    // Resolução de caminho
    // =========================================================

    private static String resolver(String nome) {
        URL url = PathUtils.class.getClassLoader().getResource("Assets/" + nome + ".png");
        if (url != null) return url.getPath();
        return "src/Assets/" + nome + ".png";
    }

    // =========================================================
    // Mapeamento principal — posicional primeiro
    // =========================================================

    private static String nomeFundo(TipoRegiao tipo, Direcao direcao, int linha, int coluna) {
        if (tipo == TipoRegiao.CORREDOR) {
            return nomeFundoCorredor(direcao, linha, coluna);
        }
        if (tipo == TipoRegiao.PAREDE) {
            return "lateral_parede";
        }

        // Salas: lógica posicional — o tipo só influencia QUAL imagem de interior usar.
        // A direção de acesso é determinada pela coluna/linha.
        return nomeFundoSala(tipo, direcao, linha, coluna);
    }

    // =========================================================
    // CORREDOR
    // =========================================================

    private static String nomeFundoCorredor(Direcao direcao, int linha, int coluna) {
        return switch (direcao) {
            case NORTE -> nomeFundoCorredorNorte(linha, coluna);
            case SUL   -> nomeFundoCorredorSul(linha, coluna);
            case OESTE -> nomeFundoCorredorOeste(linha, coluna);
            case LESTE -> nomeFundoCorredorLeste(linha, coluna);
        };
    }

    private static String nomeFundoCorredorNorte(int linha, int coluna) {
        if (coluna != 2) {
            // Corredor horizontal: sem portas laterais, corredor se estende
            return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
        }
        // Coluna central (vertical):
        if (linha == 6) return "corredor_sem_fundo_lateral_portas_1_2_NORTE";    // cruza portas I/II
        if (linha == 4) return "corredor_sem_fundo_lateral_portas_3_4_NORTE";    // cruza portas III/IV
        if (linha == 2) return "corredor_fundo_boss_lateral_portas_5_6_NORTE";   // chefe + portas V/VI
        // Linhas 7, 5, 3: trecho sem cruzamento — corredor limpo
        return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
    }

    private static String nomeFundoCorredorSul(int linha, int coluna) {
        // Corredores horizontais (linhas 2, 4, 6): olhando sul a partir do corredor lateral
        if (coluna != 2) return "fundo_corredor_parede_SUL";

        // Coluna central (vertical) — o que há ao sul depende da linha:
        return switch (linha) {
            // [8,2] é PAREDE: parede imediata ao sul
            case 7 -> "fundo_corredor_parede_SUL";
            // [7,2] é CORREDOR sem cruzamento: corredor simples se estende
            case 6 -> "corredor_sem_fundo_lateral_parede_NORTE_SUL";
            // Olhando sul de [5,2] enxerga o cruzamento de [6,2] com portas I/II
            case 5 -> "corredor_fundo_parede_lateral_portas_1_2_SUL";
            // [5,2] é CORREDOR sem cruzamento
            case 4 -> "corredor_sem_fundo_lateral_parede_NORTE_SUL";
            // Olhando sul de [3,2] enxerga o cruzamento de [4,2] com portas III/IV
            case 3 -> "corredor_sem_fundo_lateral_parede_NORTE_SUL";
            // [3,2] é CORREDOR
            case 2 -> "corredor_sem_fundo_lateral_parede_NORTE_SUL";
            default -> "fundo_corredor_parede_SUL";
        };
    }

    private static String nomeFundoCorredorOeste(int linha, int coluna) {
        // Coluna 1: antecâmara — jogador vê a porta da sala à esquerda de perto
        if (coluna == 1) {
            if (linha == 6) return "lateral_porta_1";
            if (linha == 4) return "lateral_porta_3";
            if (linha == 2) return "lateral_porta_5";
            return "fundo_corredor_parede_SUL"; // col 1 em linhas sem cruzamento (PAREDE ao oeste)
        }
        // Coluna 2 nas linhas de cruzamento (2, 4, 6): corredor horizontal se estende para oeste
        if (coluna == 2 && (linha == 2 || linha == 4 || linha == 6)) {
            return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
        }
        // Coluna 2 em linhas sem cruzamento (3, 5, 7): parede ao oeste ([*,1] é PAREDE)
        if (coluna == 2) return "fundo_corredor_parede_SUL";
        // Coluna 3 olhando oeste: extensão do corredor horizontal
        if (coluna == 3) return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
        return "fundo_corredor_parede_SUL";
    }

    private static String nomeFundoCorredorLeste(int linha, int coluna) {
        // Coluna 3: antecâmara — jogador vê a porta da sala à direita de perto
        if (coluna == 3) {
            if (linha == 6) return "lateral_porta_2";
            if (linha == 4) return "lateral_porta_4";
            if (linha == 2) return "lateral_porta_6";
            return "fundo_corredor_parede_SUL";
        }
        // Coluna 2 nas linhas de cruzamento (2, 4, 6): corredor horizontal se estende para leste
        if (coluna == 2 && (linha == 2 || linha == 4 || linha == 6)) {
            return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
        }
        // Coluna 2 em linhas sem cruzamento: parede ao leste ([*,3] é PAREDE)
        if (coluna == 2) return "fundo_corredor_parede_SUL";
        // Coluna 1 olhando leste: extensão do corredor horizontal
        if (coluna == 1) return "corredor_sem_fundo_lateral_parede_NORTE_SUL";
        return "fundo_corredor_parede_SUL";
    }

    // =========================================================
    // SALAS (INIMIGO, TESOURO, LOJA) — lógica posicional
    //
    // Interior da sala:
    //   col 0 → acessada pelo Leste, interior olhando Oeste
    //   col 4 → acessada pelo Oeste, interior olhando Leste
    //   linha 1 → acessada pelo Sul, interior olhando Norte
    // =========================================================

    private static String nomeFundoSala(TipoRegiao tipo, Direcao direcao, int linha, int coluna) {
        // Determina a direção de interior pela posição
        if (coluna == 0) return nomeFundoSalaEsquerda(tipo, direcao, linha);
        if (coluna == 4) return nomeFundoSalaDireita(tipo, direcao, linha);
        // linha 1, colunas 1-3 (sala do chefe/boss)
        return nomeFundoSalaTopo(tipo, direcao);
    }

    /** Salas na coluna 0: entram pela direita, interior visível olhando Oeste. */
    private static String nomeFundoSalaEsquerda(TipoRegiao tipo, Direcao direcao, int linha) {
        return switch (direcao) {
            case OESTE -> interiorDaSala(tipo);   // conteúdo da sala (inimigo, baú, etc.)
            case LESTE -> {                        // saída — porta numerada ímpar
                if (linha == 6) yield "lateral_porta_1";
                if (linha == 4) yield "lateral_porta_3";
                yield "lateral_porta_5";
            }
            case NORTE, SUL -> "lateral_parede";  // paredes internas
        };
    }

    /** Salas na coluna 4: entram pela esquerda, interior visível olhando Leste. */
    private static String nomeFundoSalaDireita(TipoRegiao tipo, Direcao direcao, int linha) {
        return switch (direcao) {
            case LESTE -> interiorDaSala(tipo);   // conteúdo da sala
            case OESTE -> {                        // saída — porta numerada par
                if (linha == 6) yield "lateral_porta_2";
                if (linha == 4) yield "lateral_porta_4";
                yield "lateral_porta_6";
            }
            case NORTE, SUL -> "lateral_parede";
        };
    }

    /** Sala do chefe (linha 1, cols 1-3): entra pelo Sul, interior olhando Norte. */
    private static String nomeFundoSalaTopo(TipoRegiao tipo, Direcao direcao) {
        return switch (direcao) {
            case NORTE -> interiorDaSala(tipo);          // conteúdo
            case SUL   -> "fundo_corredor_boss_NORTE";   // olhando de volta para o corredor
            case LESTE, OESTE -> "lateral_parede";
        };
    }

    /**
     * Retorna o nome do asset de interior dependendo do tipo de sala.
     * Salas de loja têm imagem própria; as demais usam sala_vazia
     * (o overlay do inimigo/baú é desenhado por cima em PainelExploracao).
     */
    private static String interiorDaSala(TipoRegiao tipo) {
        return (tipo == TipoRegiao.SALA_LOJA) ? "sala_loja" : "sala_vazia";
    }
}
