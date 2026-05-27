package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.entidades.Masmorra;
import br.edu.ifgoiano.jogo.entidades.Regiao;
import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Painel principal de exploração da masmorra.
 * Renderiza a visão em primeira pessoa com overlay de inimigo,
 * bússola e minimapa. Dispara o combate automaticamente
 * ao entrar em uma sala de inimigo.
 */
public class PainelExploracao extends JPanel {

    private final Masmorra masmorra;

    private int playerLinha  = 7;
    private int playerColuna = 2;
    private Direcao playerDirecao = Direcao.NORTE;

    /** Callback disparado quando o jogador entra numa sala de inimigo. */
    private Runnable onCombate;
    private boolean combatePendente = false;

    /** Callback disparado quando o jogador entra numa sala de loja. */
    private Runnable onLoja;
    private boolean lojaPendente = false;

    private final Image imagemInimigo;
    private final Image imagemBau;

    // ---- Fade ----
    private float fadeAlpha   = 0f;
    private boolean fadingOut = false;
    private Runnable acaoPendente = null;
    private Timer fadeTimer = null;

    // ---- Cores minimapa ----
    private static final Color COR_PAREDE       = new Color(25,  25,  25);
    private static final Color COR_CORREDOR     = new Color(110, 110, 110);
    private static final Color COR_SALA_INIMIGO = new Color(180,  45,  45);
    private static final Color COR_SALA_TESOURO = new Color(200, 165,  30);
    private static final Color COR_SALA_LOJA    = new Color( 40,  90, 180);

    /**
     * Cria o painel de exploração vinculado à masmorra informada.
     *
     * @param masmorra masmorra cujo layout será renderizado
     */
    public PainelExploracao(Masmorra masmorra) {
        this.masmorra = masmorra;
        setBackground(Color.BLACK);
        imagemInimigo = new ImageIcon("src/Assets/inimigo_cobra.png").getImage();
        imagemBau     = new ImageIcon("src/Assets/bau_fechado.png").getImage();
    }

    /**
     * Define o callback chamado quando o jogador entra numa sala de inimigo.
     *
     * @param cb ação a executar ao iniciar o combate
     */
    public void setOnCombate(Runnable cb) {
        this.onCombate = cb;
    }

    /**
     * Define o callback chamado quando o jogador entra numa sala de loja.
     *
     * @param cb acao a executar ao abrir a loja
     */
    public void setOnLoja(Runnable cb) {
        this.onLoja = cb;
    }

    // =========================================================
    // FADE
    // =========================================================
    private void iniciarFade(Runnable acao) {
        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
        acaoPendente = acao;
        fadingOut    = true;
        fadeAlpha    = 0f;

        fadeTimer = new Timer(16, null);
        fadeTimer.addActionListener(e -> {
            if (fadingOut) {
                fadeAlpha += 0.15f;
                if (fadeAlpha >= 1f) {
                    fadeAlpha = 1f;
                    fadingOut = false;
                    if (acaoPendente != null) { acaoPendente.run(); acaoPendente = null; }
                }
            } else {
                fadeAlpha -= 0.15f;
                if (fadeAlpha <= 0f) {
                    fadeAlpha = 0f;
                    ((Timer) e.getSource()).stop();
                    // Dispara combate depois que o fade termina e o inimigo aparece
                    if (combatePendente && onCombate != null) {
                        combatePendente = false;
                        SwingUtilities.invokeLater(onCombate);
                    } else if (lojaPendente && onLoja != null) {
                        lojaPendente = false;
                        SwingUtilities.invokeLater(onLoja);
                    }
                }
            }
            repaint();
        });
        fadeTimer.start();
    }

    // =========================================================
    // RENDERIZAÇÃO
    // =========================================================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Regiao regiaoAtual = masmorra.getRegiao(playerLinha, playerColuna);

        // Camada 1 — imagem de fundo (o que está à frente ou parede)
        Image img = resolverImagemFrente();
        if (img != null) g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        // Camada 2 — overlay das paredes laterais do corredor (PNG transparente no centro)
        Image overlay = resolverImagemOverlay();
        if (overlay != null) g2.drawImage(overlay, 0, 0, getWidth(), getHeight(), this);

        // Camada 3 — overlay do inimigo quando o jogador está numa sala de inimigo
        if (regiaoAtual != null && regiaoAtual.getTipoRegiao() == TipoRegiao.SALA_INIMIGO) {
            desenharInimigo(g2);
        }

        // Camada 4 — overlay do baú quando o jogador está numa sala de tesouro
        if (regiaoAtual != null && regiaoAtual.getTipoRegiao() == TipoRegiao.SALA_TESOURO) {
            desenharBau(g2);
        }

        // Escurecimento do fade
        if (fadeAlpha > 0f) {
            Composite orig = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setComposite(orig);
        }

        // HUD de texto
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.setColor(Color.YELLOW);
        g2.drawString("Pos: [" + playerLinha + ", " + playerColuna + "]", 16, 36);
        if (regiaoAtual != null) {
            g2.drawString("Local: " + regiaoAtual.getTipoRegiao(), 16, 58);
        }

        desenharBussola(g2);
        desenharMiniMapa(g2);

        g2.dispose();
    }

    /**
     * Desenha a imagem do inimigo centralizada na tela,
     * escalada para ocupar ~68% da altura.
     */
    private void desenharInimigo(Graphics2D g2) {
        if (imagemInimigo == null) return;
        int iw = imagemInimigo.getWidth(null);
        int ih = imagemInimigo.getHeight(null);
        if (iw <= 0 || ih <= 0) return;

        int targetH = (int)(getHeight() * 0.68);
        int targetW = (int)(iw * ((double)targetH / ih));
        int x = (getWidth() - targetW) / 2;
        int y = getHeight() - targetH - (int)(getHeight() * 0.04);

        g2.drawImage(imagemInimigo, x, y, targetW, targetH, this);
    }

    /**
     * Desenha o baú de tesouro centralizado na tela,
     * escalado para ocupar aproximadamente 55% da altura.
     */
    private void desenharBau(Graphics2D g2) {
        if (imagemBau == null) return;
        int iw = imagemBau.getWidth(null);
        int ih = imagemBau.getHeight(null);
        if (iw <= 0 || ih <= 0) return;

        int targetH = (int)(getHeight() * 0.55);
        int targetW = (int)(iw * ((double)targetH / ih));
        int x = (getWidth() - targetW) / 2;
        int y = getHeight() - targetH - (int)(getHeight() * 0.06);

        g2.drawImage(imagemBau, x, y, targetW, targetH, this);
    }

    /**
     * Retorna a imagem overlay (paredes laterais) da região atual
     * para a direção em que o jogador está olhando, ou {@code null} se não houver.
     */
    private Image resolverImagemOverlay() {
        Regiao regiaoAtual = masmorra.getRegiao(playerLinha, playerColuna);
        if (regiaoAtual == null) return null;
        return regiaoAtual.getImagemOverlay(playerDirecao);
    }

    /**
     * Decide qual imagem de fundo mostrar:
     * se a frente for parede/nula, mostra imagem de parede;
     * caso contrário mostra a imagem direcional da região atual.
     */
    private Image resolverImagemFrente() {
        Regiao regiaoAtual = masmorra.getRegiao(playerLinha, playerColuna);
        if (regiaoAtual == null) return null;

        int adjL = playerLinha  + playerDirecao.getDeltaLinha();
        int adjC = playerColuna + playerDirecao.getDeltaColuna();
        boolean frenteLivre = adjL >= 0 && adjL < 9 && adjC >= 0 && adjC < 5;
        Regiao regiaoFrente = frenteLivre ? masmorra.getRegiao(adjL, adjC) : null;

        boolean haParede = regiaoFrente == null || regiaoFrente.getTipoRegiao() == TipoRegiao.PAREDE;
        if (haParede) {
            return regiaoFrente != null
                    ? regiaoFrente.getImagem(playerDirecao)
                    : regiaoAtual.getImagem(playerDirecao);
        }
        return regiaoAtual.getImagem(playerDirecao);
    }

    // =========================================================
    // BÚSSOLA (canto superior direito)
    // =========================================================
    private void desenharBussola(Graphics2D g2) {
        int raio = 38;
        int cx   = getWidth() - raio - 16;
        int cy   = raio + 16;

        Composite orig = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.72f));
        g2.setColor(new Color(8, 8, 8));
        g2.fillOval(cx - raio, cy - raio, raio * 2, raio * 2);
        g2.setComposite(orig);

        g2.setColor(new Color(100, 100, 100));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawOval(cx - raio, cy - raio, raio * 2, raio * 2);

        String[]  labels  = { "N",           "S",          "L",           "O"          };
        Direcao[] dirs    = { Direcao.NORTE,  Direcao.SUL,  Direcao.LESTE, Direcao.OESTE };
        int[][]   offsets = { { 0, -1 },      { 0,  1 },    {  1,  0 },    { -1,  0 }   };

        g2.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2.getFontMetrics();

        for (int i = 0; i < 4; i++) {
            int lx = cx + (int)(offsets[i][0] * raio * 0.62);
            int ly = cy + (int)(offsets[i][1] * raio * 0.62) + fm.getAscent() / 2;
            g2.setColor(dirs[i] == playerDirecao ? new Color(220, 50, 50) : new Color(180, 180, 180));
            g2.drawString(labels[i], lx - fm.stringWidth(labels[i]) / 2, ly);
        }

        double ang = switch (playerDirecao) {
            case NORTE -> -Math.PI / 2;
            case SUL   ->  Math.PI / 2;
            case LESTE ->  0;
            case OESTE ->  Math.PI;
        };
        int px = cx + (int)(Math.cos(ang) * (raio - 9));
        int py = cy + (int)(Math.sin(ang) * (raio - 9));
        g2.setColor(new Color(220, 50, 50));
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(cx, cy, px, py);
        g2.setColor(new Color(210, 210, 210));
        g2.fillOval(cx - 3, cy - 3, 6, 6);
    }

    // =========================================================
    // MINIMAPA (canto inferior direito)
    // =========================================================
    private void desenharMiniMapa(Graphics2D g2) {
        final int CEL     = 13;
        final int COLUNAS = 5;
        final int LINHAS  = 9;
        int largura = COLUNAS * CEL;
        int altura  = LINHAS  * CEL;
        int margem  = 16;
        int xO = getWidth()  - largura - margem;
        int yO = getHeight() - altura  - margem;

        Composite orig = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.72f));
        g2.setColor(Color.BLACK);
        g2.fillRect(xO - 3, yO - 3, largura + 6, altura + 6);
        g2.setComposite(orig);

        g2.setColor(new Color(90, 90, 90));
        g2.setStroke(new BasicStroke(1f));
        g2.drawRect(xO - 3, yO - 3, largura + 6, altura + 6);

        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                g2.setColor(corRegiao(masmorra.getRegiao(l, c)));
                g2.fillRect(xO + c * CEL, yO + l * CEL, CEL - 1, CEL - 1);
            }
        }

        int mpx = xO + playerColuna * CEL + CEL / 2;
        int mpy = yO + playerLinha  * CEL + CEL / 2;
        desenharTrianguloJogador(g2, mpx, mpy, playerDirecao);
    }

    private void desenharTrianguloJogador(Graphics2D g2, int cx, int cy, Direcao dir) {
        int r = 5;
        double ang = switch (dir) {
            case NORTE -> -Math.PI / 2;
            case SUL   ->  Math.PI / 2;
            case LESTE ->  0;
            case OESTE ->  Math.PI;
        };
        int[] xs = {
            cx + (int)(Math.cos(ang)       * r),
            cx + (int)(Math.cos(ang + 2.4) * (int)(r * 0.65)),
            cx + (int)(Math.cos(ang - 2.4) * (int)(r * 0.65))
        };
        int[] ys = {
            cy + (int)(Math.sin(ang)       * r),
            cy + (int)(Math.sin(ang + 2.4) * (int)(r * 0.65)),
            cy + (int)(Math.sin(ang - 2.4) * (int)(r * 0.65))
        };
        g2.setColor(Color.WHITE);
        g2.fillPolygon(xs, ys, 3);
        g2.setColor(new Color(220, 50, 50));
        g2.setStroke(new BasicStroke(1f));
        g2.drawPolygon(xs, ys, 3);
    }

    private Color corRegiao(Regiao reg) {
        if (reg == null) return COR_PAREDE;
        return switch (reg.getTipoRegiao()) {
            case PAREDE       -> COR_PAREDE;
            case CORREDOR     -> COR_CORREDOR;
            case SALA_INIMIGO -> COR_SALA_INIMIGO;
            case SALA_TESOURO -> COR_SALA_TESOURO;
            case SALA_LOJA    -> COR_SALA_LOJA;
        };
    }

    // =========================================================
    // MOVIMENTO
    // =========================================================

    /** Move o jogador uma célula para frente na direção atual com efeito de fade. */
    public void avancar()       { iniciarFade(() -> tentarMoverSemRepaint(playerDirecao)); }

    /** Move o jogador uma célula para trás em relação à direção atual com efeito de fade. */
    public void recuar()        { iniciarFade(() -> tentarMoverSemRepaint(playerDirecao.viraDireita().viraDireita())); }

    /** Gira o jogador 90 graus para a esquerda com efeito de fade. */
    public void virarEsquerda() { iniciarFade(() -> playerDirecao = playerDirecao.viraEsquerda()); }

    /** Gira o jogador 90 graus para a direita com efeito de fade. */
    public void virarDireita()  { iniciarFade(() -> playerDirecao = playerDirecao.viraDireita()); }

    /**
     * Orienta o jogador para a direção especificada com efeito de fade.
     *
     * @param novaDirecao direção para a qual o jogador deve olhar
     */
    public void virar(Direcao novaDirecao) { iniciarFade(() -> playerDirecao = novaDirecao); }

    /**
     * Orienta o jogador para a direção informada e tenta avançar uma célula,
     * tudo com efeito de fade.
     *
     * @param direcao direção do movimento
     */
    public void mover(Direcao direcao) {
        iniciarFade(() -> { playerDirecao = direcao; tentarMoverSemRepaint(direcao); });
    }

    /**
     * Move o jogador com base em deltas de linha e coluna,
     * convertendo-os para a direção correspondente.
     *
     * @param deltaLinha   variação na linha (-1 Norte, +1 Sul)
     * @param deltaColuna  variação na coluna (-1 Oeste, +1 Leste)
     */
    public void mover(int deltaLinha, int deltaColuna) {
        if      (deltaLinha == -1 && deltaColuna ==  0) mover(Direcao.NORTE);
        else if (deltaLinha ==  1 && deltaColuna ==  0) mover(Direcao.SUL);
        else if (deltaLinha ==  0 && deltaColuna ==  1) mover(Direcao.LESTE);
        else if (deltaLinha ==  0 && deltaColuna == -1) mover(Direcao.OESTE);
    }

    private void tentarMoverSemRepaint(Direcao direcao) {
        int novaLinha  = playerLinha  + direcao.getDeltaLinha();
        int novaColuna = playerColuna + direcao.getDeltaColuna();
        if (novaLinha >= 0 && novaLinha < 9 && novaColuna >= 0 && novaColuna < 5) {
            Regiao reg = masmorra.getRegiao(novaLinha, novaColuna);
            if (reg != null && reg.getTipoRegiao() != TipoRegiao.PAREDE) {
                playerLinha  = novaLinha;
                playerColuna = novaColuna;
                if (reg.getTipoRegiao() == TipoRegiao.SALA_INIMIGO) {
                    combatePendente = true;
                } else if (reg.getTipoRegiao() == TipoRegiao.SALA_LOJA) {
                    lojaPendente = true;
                }
            }
        }
    }

    /**
     * Move o jogador por BFS até o corredor mais próximo da posição atual.
     * Usado após o combate terminar para sair da SALA_INIMIGO.
     * A transição é feita com efeito de fade.
     */
    public void voltarAoCorredorMaisProximo() {
        boolean[][] visited = new boolean[9][5];
        Queue<int[]> fila   = new LinkedList<>();
        fila.add(new int[]{ playerLinha, playerColuna });
        visited[playerLinha][playerColuna] = true;

        int[][] dirs = {{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

        while (!fila.isEmpty()) {
            int[] pos = fila.poll();
            Regiao reg = masmorra.getRegiao(pos[0], pos[1]);
            if (reg != null && reg.getTipoRegiao() == TipoRegiao.CORREDOR) {
                atualizarPosicao(pos[0], pos[1]);
                return;
            }
            for (int[] d : dirs) {
                int nl = pos[0] + d[0];
                int nc = pos[1] + d[1];
                if (nl >= 0 && nl < 9 && nc >= 0 && nc < 5 && !visited[nl][nc]) {
                    visited[nl][nc] = true;
                    fila.add(new int[]{ nl, nc });
                }
            }
        }
    }

    /**
     * Teleporta o jogador para uma nova posição no grid com efeito de fade,
     * ignorando a tentativa caso as coordenadas estejam fora dos limites.
     *
     * @param novaLinha   linha de destino (0–8)
     * @param novaColuna  coluna de destino (0–4)
     */
    public void atualizarPosicao(int novaLinha, int novaColuna) {
        if (novaLinha >= 0 && novaLinha < 9 && novaColuna >= 0 && novaColuna < 5) {
            iniciarFade(() -> { playerLinha = novaLinha; playerColuna = novaColuna; });
        }
    }

    /**
     * Retorna a linha atual do jogador no grid da masmorra.
     *
     * @return índice de linha (0–8)
     */
    public int getPlayerLinha()       { return playerLinha; }

    /**
     * Retorna a coluna atual do jogador no grid da masmorra.
     *
     * @return índice de coluna (0–4)
     */
    public int getPlayerColuna()      { return playerColuna; }

    /**
     * Retorna a direção para a qual o jogador está virado atualmente.
     *
     * @return direção atual do jogador
     */
    public Direcao getPlayerDirecao() { return playerDirecao; }
}
