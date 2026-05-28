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

    private int linha = -1;
    private int coluna = -1;

    /** Indica se o baú desta região já foi aberto pelo jogador. */
    private boolean bauAberto = false;

    /** Construtor padrão sem inicialização de imagens. */
    public Regiao() {}

    /**
     * Cria uma região do tipo informado e carrega as imagens de fundo e overlay
     * para cada uma das quatro direções cardinais.
     *
     * @param tipoRegiao tipo desta região
     */
    public Regiao(TipoRegiao tipoRegiao) {
        this(tipoRegiao, -1, -1);
    }

    public Regiao(TipoRegiao tipoRegiao, int linha, int coluna) {
        this.tipoRegiao    = tipoRegiao;
        this.linha         = linha;
        this.coluna        = coluna;
        this.imagens       = new EnumMap<>(Direcao.class);
        this.imagensOverlay = new EnumMap<>(Direcao.class);

        for (Direcao dir : Direcao.values()) {
            // Camada de fundo
            String path = PathUtils.obterPathPorTipo(tipoRegiao, dir, linha, coluna);
            this.imagens.put(dir, new ImageIcon(path).getImage());

            // Camada de overlay (pode ser null se não existir para este tipo/direção)
            String overlayPath = PathUtils.obterOverlayPath(tipoRegiao, dir, linha, coluna);
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

    /**
     * Retorna a linha desta região no grid da masmorra.
     *
     * @return índice de linha (0–8)
     */
    public int getLinha() { return linha; }

    /**
     * Retorna a coluna desta região no grid da masmorra.
     *
     * @return índice de coluna (0–4)
     */
    public int getColuna() { return coluna; }

    /**
     * Retorna se o baú desta região já foi aberto.
     *
     * @return true se o baú foi aberto, false caso contrário
     */
    public boolean isBauAberto() { return bauAberto; }

    /**
     * Define o estado do baú como aberto.
     */
    public void setBauAberto(boolean bauAberto) { this.bauAberto = bauAberto; }

    /**
     * Retorna a direção para a qual o jogador deve olhar para ver o interior da sala
     * (onde o conteúdo — inimigo, baú ou loja — fica visível).
     *
     * <p>A direção é determinada exclusivamente pela <em>posição</em> da célula no grid,
     * independentemente do seu tipo. Isso permite que qualquer tipo de sala seja
     * colocado em qualquer posição lateral sem quebrar a lógica de renderização:</p>
     * <ul>
     *   <li>Coluna 0 (salas à esquerda): jogador entra pela direita → olha para Oeste.</li>
     *   <li>Coluna 4 (salas à direita): jogador entra pela esquerda → olha para Leste.</li>
     *   <li>Linha 1 (sala do chefe): jogador entra pelo sul → olha para Norte.</li>
     * </ul>
     *
     * @return direção do interior da sala, ou {@code null} para células que não são salas
     */
    public Direcao getDirecaoInterior() {
        if (tipoRegiao == TipoRegiao.CORREDOR || tipoRegiao == TipoRegiao.PAREDE) return null;
        if (coluna == 0) return Direcao.OESTE;
        if (coluna == 4) return Direcao.LESTE;
        if (linha <= 1)  return Direcao.NORTE;
        return null;
    }
}
