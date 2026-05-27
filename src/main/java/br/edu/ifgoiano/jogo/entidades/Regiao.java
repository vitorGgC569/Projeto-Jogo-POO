package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;
import br.edu.ifgoiano.jogo.util.PathUtils;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class Regiao {

    private Map<Direcao, Image> imagens;
    private TipoRegiao tipoRegiao;

    public Regiao() {}

    public Regiao(TipoRegiao tipoRegiao) {
        this.tipoRegiao = tipoRegiao;
        this.imagens = new EnumMap<>(Direcao.class);
        for (Direcao dir : Direcao.values()) {
            String path = PathUtils.obterPathPorTipo(tipoRegiao, dir);
            this.imagens.put(dir, new ImageIcon(path).getImage());
        }
    }

    public Image getImagem(Direcao direcao) {
        if (imagens == null) return null;
        return imagens.get(direcao);
    }

    // Compatibilidade: retorna imagem voltada para NORTE
    public Image getImage() {
        return getImagem(Direcao.NORTE);
    }

    public TipoRegiao getTipoRegiao() {
        return tipoRegiao;
    }

    public void setTipoRegiao(TipoRegiao tipoRegiao) {
        this.tipoRegiao = tipoRegiao;
    }
}
