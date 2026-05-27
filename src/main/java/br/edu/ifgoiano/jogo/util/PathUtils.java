package br.edu.ifgoiano.jogo.util;

import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;

import java.net.URL;

/**
 * Utilitário para resolução dos caminhos dos assets de imagem da masmorra.
 * Localiza a imagem correta com base no tipo de região e na direção do jogador,
 * com fallback para a imagem base quando não houver variante direcional.
 */
public final class PathUtils {

    /** Construtor privado — classe utilitária, não deve ser instanciada. */
    private PathUtils() {}

    /**
     * Retorna o caminho do asset de imagem para um tipo de região e direção.
     * Tenta primeiro a variante direcional (ex.: "Corredor_Norte.png");
     * se não existir, usa a imagem base (ex.: "Corredor.png").
     *
     * @param tipo     tipo da região (corredor, sala de inimigo, etc.)
     * @param direcao  direção para a qual o jogador está olhando
     * @return caminho do arquivo de imagem correspondente
     */
    public static String obterPathPorTipo(TipoRegiao tipo, Direcao direcao) {
        String nomeBase = nomeBase(tipo);
        String extBase  = tipo == TipoRegiao.PAREDE ? ".jpg" : ".png";

        // Tenta imagem direcional: ex. "Corredor_Norte.png"
        String nomeDirecional = nomeBase + "_" + nomeDirecao(direcao) + ".png";
        URL url = PathUtils.class.getClassLoader().getResource("Assets/" + nomeDirecional);
        if (url != null) return url.getPath();

        // Fallback para imagem base: ex. "Corredor.png"
        url = PathUtils.class.getClassLoader().getResource("Assets/" + nomeBase + extBase);
        if (url != null) return url.getPath();

        return "src/Assets/" + nomeBase + extBase;
    }

    public static String obterPathPorTipo(TipoRegiao tipo) {
        return obterPathPorTipo(tipo, Direcao.NORTE);
    }

    private static String nomeBase(TipoRegiao tipo) {
        return switch (tipo) {
            case CORREDOR     -> "Corredor";
            case SALA_INIMIGO -> "Inimigo";
            case SALA_TESOURO -> "Tesouro";
            case SALA_LOJA    -> "Loja";
            case PAREDE       -> "Parede";
        };
    }

    private static String nomeDirecao(Direcao direcao) {
        return switch (direcao) {
            case NORTE -> "Norte";
            case SUL   -> "Sul";
            case LESTE -> "Leste";
            case OESTE -> "Oeste";
        };
    }
}
