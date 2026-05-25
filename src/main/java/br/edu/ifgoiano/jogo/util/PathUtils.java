package br.edu.ifgoiano.jogo.util;

import br.edu.ifgoiano.jogo.enums.TipoRegiao;

public final class PathUtils {

    private PathUtils() {}

    public static String obterPathPorTipo(TipoRegiao tipo) {
        switch (tipo) {
            case CORREDOR: return "src/Assets/Corredor.png";
            case SALA_INIMIGO: return "src/Assets/Inimigo.png";
            case SALA_TESOURO: return "src/Assets/Tesouro.png";
            case SALA_LOJA: return "src/Assets/Loja.png";
            case PAREDE:
            default:
                return "src/Assets/Parede.jpg";
        }
    }
}