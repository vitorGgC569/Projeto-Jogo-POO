package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.TipoRegiao;
import br.edu.ifgoiano.jogo.util.PathUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Responsável por armazenar o tipo da do cenário e a imagem que será exibida.
 */
public class Regiao {
    private Image image;
    private TipoRegiao tipoRegiao;

    public Regiao (){}

    public Regiao(TipoRegiao tipoRegiao){
        this.tipoRegiao = tipoRegiao;
        //Consulta a imagem por tipo de sala
        this.image = new ImageIcon(PathUtils.obterPathPorTipo(this.tipoRegiao)).getImage();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public TipoRegiao getTipoRegiao() {
        return tipoRegiao;
    }

    public void setTipoRegiao(TipoRegiao tipoRegiao) {
        this.tipoRegiao = tipoRegiao;
    }

}
