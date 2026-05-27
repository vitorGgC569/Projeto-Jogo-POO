package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;
import br.edu.ifgoiano.jogo.util.PathUtils;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * Representa uma célula da grade da masmorra.
 * Armazena o tipo da região e duas camadas de imagem para cada direção:
 * a imagem de fundo (o que está à frente) e o overlay opcional das paredes laterais.
 */
public class Regiao {

    /** Imagens de fundo indexadas por direção. */
    private Map<Direcao, Image> imagens;

    /**
     * Imagens de sobreposição (paredes laterais do corredor) indexadas por direção.
     * Pode estar vazia para regiões que não usam overlay.
     */
    private Map<Direcao, Image> imagensOverlay;

    /** Tipo desta região (corredor, sala de inimigo, etc.). */
    private TipoRegiao tipoRegiao;

    /** Construtor padrão sem inicialização de imagens. */
    public Regiao() {}

    /**
     * Cria uma região do tipo informado e carrega as imagens de fundo e overlay
     * para cada uma das quatro direções cardinais.
     *
     * @param tipoRegiao tipo desta região
     */
    public Regiao(TipoRegiao tipoRegiao) {
        this.tipoRegiao    = tipoRegiao;
        this.imagens       = new EnumMap<>(Direcao.class);
        this.imagensOverlay = new EnumMap<>(Direcao.class);

        for (Direcao dir : Direcao.values()) {
            // Camada de fundo
            String path = PathUtils.obterPathPorTipo(tipoRegiao, dir);
            this.imagens.put(dir, new ImageIcon(path).getImage());

            // Camada de overlay (pode ser null se não existir para este tipo/direção)
            String overlayPath = PathUtils.obterOverlayPath(tipoRegiao, dir);
            if (overlayPath != null) {
                this.imagensOverlay.put(dir, new ImageIcon(overlayPath).getImage());
            }
        }
    }

    /**
     * Retorna a imagem de fundo para a direção informada.
     *
     * @param direcao direção desejada
     * @return imagem de fundo, ou {@code null} se não houver
     */
    public Image getImagem(Direcao direcao) {
        if (imagens == null) return null;
        return imagens.get(direcao);
    }

    /**
     * Retorna a imagem overlay (paredes laterais) para a direção informada,
     * ou {@code null} se esta região não possuir overlay nesta direção.
     *
     * @param direcao direção desejada
     * @return imagem overlay, ou {@code null}
     */
    public Image getImagemOverlay(Direcao direcao) {
        if (imagensOverlay == null) return null;
        return imagensOverlay.get(direcao);
    }

    /**
     * Compatibilidade retroativa: retorna a imagem de fundo voltada para NORTE.
     *
     * @return imagem de fundo na direção NORTE
     */
    public Image getImage() {
        return getImagem(Direcao.NORTE);
    }

    /**
     * Retorna o tipo desta região.
     *
     * @return tipo da região
     */
    public TipoRegiao getTipoRegiao() {
        return tipoRegiao;
    }

    /**
     * Define o tipo desta região.
     *
     * @param tipoRegiao novo tipo
     */
    public void setTipoRegiao(TipoRegiao tipoRegiao) {
        this.tipoRegiao = tipoRegiao;
    }
}
