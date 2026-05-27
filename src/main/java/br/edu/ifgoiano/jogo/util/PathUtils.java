package br.edu.ifgoiano.jogo.util;

import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;

import java.net.URL;

/**
 * Utilitário para resolução dos caminhos dos assets de imagem da masmorra.
 * Cada combinação de tipo e direção é mapeada para uma imagem de fundo (fundo)
 * e, opcionalmente, uma imagem de sobreposição (overlay) com as paredes laterais.
 */
public final class PathUtils {

    /** Construtor privado — classe utilitária, não deve ser instanciada. */
    private PathUtils() {}

    /**
     * Retorna o caminho da imagem de fundo para um tipo de região e direção.
     * Resolve primeiro via classpath (resources/Assets/); se não encontrar,
     * usa o caminho relativo src/Assets/.
     *
     * @param tipo    tipo da região
     * @param direcao direção para a qual o jogador está olhando
     * @return caminho do arquivo de imagem de fundo
     */
    public static String obterPathPorTipo(TipoRegiao tipo, Direcao direcao) {
        String nome = nomeFundo(tipo, direcao);
        URL url = PathUtils.class.getClassLoader().getResource("Assets/" + nome + ".png");
        if (url != null) return url.getPath();
        return "src/Assets/" + nome + ".png";
    }

    /**
     * Sobrecarga sem direção — usa NORTE como padrão.
     *
     * @param tipo tipo da região
     * @return caminho do arquivo de imagem de fundo voltado para NORTE
     */
    public static String obterPathPorTipo(TipoRegiao tipo) {
        return obterPathPorTipo(tipo, Direcao.NORTE);
    }

    /**
     * Retorna o caminho da imagem overlay (paredes laterais do corredor) para
     * uma região e direção, ou {@code null} se não houver overlay disponível.
     *
     * @param tipo    tipo da região
     * @param direcao direção para a qual o jogador está olhando
     * @return caminho do arquivo de overlay, ou {@code null}
     */
    public static String obterOverlayPath(TipoRegiao tipo, Direcao direcao) {
        String nome = nomeOverlay(tipo, direcao);
        if (nome == null) return null;
        URL url = PathUtils.class.getClassLoader().getResource("Assets/" + nome + ".png");
        if (url != null) return url.getPath();
        return "src/Assets/" + nome + ".png";
    }

    // -------------------------------------------------------------------------
    // Mapeamento de fundo (background)
    // -------------------------------------------------------------------------

    /**
     * Retorna o nome do arquivo de fundo para o tipo e direção informados.
     * <ul>
     *   <li>CORREDOR NORTE — corredor pré-composito com sala de boss ao fundo e portas V/VI</li>
     *   <li>CORREDOR SUL — corredor pré-composito com parede ao fundo e portas I/II</li>
     *   <li>CORREDOR LESTE/OESTE — fundo genérico de parede (o overlay adiciona as laterais)</li>
     *   <li>SALA_INIMIGO / SALA_TESOURO — sala vazia (inimigo/baú são desenhados como overlay)</li>
     *   <li>SALA_LOJA — interior da loja</li>
     *   <li>PAREDE — imagem de parede fechada</li>
     * </ul>
     */
    private static String nomeFundo(TipoRegiao tipo, Direcao direcao) {
        return switch (tipo) {
            case CORREDOR -> switch (direcao) {
                case NORTE        -> "corredor_fundo_boss_lateral_portas_5_6_NORTE";
                case SUL          -> "corredor_fundo_parede_lateral_portas_1_2_SUL";
                case LESTE, OESTE -> "fundo_corredor_parede_SUL";
            };
            case SALA_INIMIGO -> "sala_vazia";
            case SALA_TESOURO -> "sala_vazia";
            case SALA_LOJA    -> "sala_loja";
            case PAREDE       -> "fundo_corredor_parede_SUL";
        };
    }

    // -------------------------------------------------------------------------
    // Mapeamento de overlay (paredes laterais)
    // -------------------------------------------------------------------------

    /**
     * Retorna o nome do arquivo overlay para o tipo e direção informados,
     * ou {@code null} se não houver overlay para esta combinação.
     * <p>
     * Apenas CORREDOR LESTE e OESTE possuem overlay separado — NORTE e SUL
     * já utilizam imagens pré-compositas que incluem as laterais.
     * </p>
     */
    private static String nomeOverlay(TipoRegiao tipo, Direcao direcao) {
        if (tipo != TipoRegiao.CORREDOR) return null;
        return switch (direcao) {
            case NORTE, SUL -> null;  // imagens já pré-compositas, sem overlay extra
            case LESTE      -> "corredor_sem_fundo_lateral_portas_3_4_NORTE";
            case OESTE      -> "corredor_sem_fundo_lateral_portas_3_4_SUL";
        };
    }
}
