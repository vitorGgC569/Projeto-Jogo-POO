package br.edu.ifgoiano.jogo.util;

import br.edu.ifgoiano.jogo.enums.TipoRegiao;
import java.net.URL;

/**
 * Utilitario para resolucao de caminhos de assets do jogo.
 */
public final class PathUtils {

    private PathUtils() {}

    public static String obterPathPorTipo(TipoRegiao tipo) {
        String nomeArquivo;
        switch (tipo) {
            case CORREDOR:      nomeArquivo = "Corredor.png"; break;
            case SALA_INIMIGO:  nomeArquivo = "Inimigo.png";  break;
            case SALA_TESOURO:  nomeArquivo = "Tesouro.png";  break;
            case SALA_LOJA:     nomeArquivo = "Loja.png";     break;
            case PAREDE:
            default:            nomeArquivo = "Parede.jpg";   break;
        }
        URL url = PathUtils.class.getClassLoader().getResource("Assets/" + nomeArquivo);
        if (url != null) {
            return url.getPath();
        }
        return "src/Assets/" + nomeArquivo;
    }
}