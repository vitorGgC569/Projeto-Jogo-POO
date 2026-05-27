package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.core.Combate;
import br.edu.ifgoiano.jogo.entidades.Baralho;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Inimigo;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Mao;
import br.edu.ifgoiano.jogo.entidades.PilhaDescarte;
import br.edu.ifgoiano.jogo.enums.AcaoInimigo;
import br.edu.ifgoiano.jogo.factory.CartaFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela de combate por turnos.
 * Exibe a mao de cartas seleccionavel do jogador, os status de ambos os lados,
 * e os botoes para jogar carta, embaralhar e finalizar turno.
 * Ao terminar o combate invoca o callback {@code onCombateTerminado}.
 */
public class TelaCombate extends JPanel {

    // ---- constantes visuais ----
    private static final int   CARD_W    = 155;
    private static final int   CARD_H    = 235;
    private static final Color BG        = new Color(8,   8,   8);
    private static final Color BG_CARD   = new Color(18,  14,  14);
    private static final Color BG_SEL    = new Color(42,  30,   8);
    private static final Color RED_DARK  = new Color(120,   0,   0);
    private static final Color RED_LIGHT = new Color(200,  30,  30);
    private static final Color GOLD      = new Color(210, 160,  10);

    // ---- model ----
    private final Combate  combate;
    private final Runnable onCombateTerminado;
    private Carta cartaSelecionada = null;
    private final List<JPanel> paineisCarta = new ArrayList<>();

    // ---- referencias de UI que precisam de update ----
    private JLabel       lblNomeInimigo;
    private JLabel       lblProximaAcao;
    private JProgressBar barraVidaInimigo;
    private JLabel       lblVida;
    private JLabel       lblEscudo;
    private JLabel       lblEnergia;
    private JLabel       lblBaralho;
    private JLabel       lblDescarte;
    private JPanel       painelMao;
    private JButton      btnJogar;
    private JLabel       lblLog;

    // =========================================================
    // CONSTRUTORES
    // =========================================================

    /**
     * Cria a tela de combate a partir de um {@link Combate} ja configurado.
     *
     * @param combate            sessao de combate a ser jogada
     * @param onCombateTerminado callback invocado ao fim do combate (vitoria ou derrota)
     */
    public TelaCombate(Combate combate, Runnable onCombateTerminado) {
        this.combate            = combate;
        this.onCombateTerminado = onCombateTerminado;
        setLayout(new BorderLayout());
        setBackground(BG);
        inicializarCombate();
        criarInterface();
        atualizarUI();
    }

    /**
     * Cria a tela de combate com um {@link Combate} padrao e o callback de termino.
     *
     * @param onCombateTerminado callback invocado ao fim do combate
     */
    public TelaCombate(Runnable onCombateTerminado) {
        this(new Combate(criarJogadorPadrao(), criarInimigoPadrao()), onCombateTerminado);
    }

    /**
     * Cria a tela de combate completamente padrao (sem callback de termino).
     */
    public TelaCombate() {
        this(new Combate(criarJogadorPadrao(), criarInimigoPadrao()), null);
    }

    // =========================================================
    // PERSONAGENS PADRAO
    // =========================================================

    /**
     * Cria um {@link Jogador} inicial com baralho de 10 cartas e atributos basicos.
     *
     * @return jogador pronto para o combate
     */
    static Jogador criarJogadorPadrao() {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(CartaFactory.criarGolpe());
        cartas.add(CartaFactory.criarGolpe());
        cartas.add(CartaFactory.criarGolpe());
        cartas.add(CartaFactory.criarDefesa());
        cartas.add(CartaFactory.criarDefesa());
        cartas.add(CartaFactory.criarCura());
        cartas.add(CartaFactory.criarGolpeCritico());
        cartas.add(CartaFactory.criarInvestida());
        cartas.add(CartaFactory.criarRaiva());
        cartas.add(CartaFactory.criarGolpeDuplo());

        Jogador j = new Jogador();
        j.setNome("Heroi");
        j.setVida(80);           j.setVidaMaxima(80);
        j.setEnergia(3);         j.setEnergiaMaxima(3);
        j.setAtaque(5);          j.setDefesa(2);
        j.setEscudo(0);          j.setNivel(1);
        j.setBaralho(new Baralho(20, cartas.size(), false, cartas));
        j.setMao(new Mao(new ArrayList<>(), 5));
        j.setPilhaDescarte(new PilhaDescarte(new ArrayList<>()));
        return j;
    }

    /**
     * Cria um {@link Inimigo} padrao com atributos basicos.
     *
     * @return inimigo pronto para o combate
     */
    static Inimigo criarInimigoPadrao() {
        Inimigo i = new Inimigo();
        i.setNome("Cobra Venenosa");
        i.setVida(60);           i.setVidaMaxima(60);
        i.setAtaque(10);         i.setDefesa(2);
        i.setEscudo(0);          i.setNivel(1);
        i.setChanceCritico(20);  i.setInteligencia(5);
        i.setRecompensaOuro(15); i.setRecompensaXp(20);
        i.definirProximaAcao();
        return i;
    }

    // =========================================================
    // INICIALIZACAO
    // =========================================================

    /**
     * Embaralha o baralho do jogador e distribui a mao inicial de 5 cartas.
     */
    private void inicializarCombate() {
        Jogador j = combate.getJogador();
        if (j.getBaralho() != null) j.getBaralho().embaralhar();
        comprarMao(5);
    }

    // =========================================================
    // CRIACAO DA INTERFACE
    // =========================================================

    private void criarInterface() {
        add(criarPainelTopo(),   BorderLayout.NORTH);
        add(criarPainelCentro(), BorderLayout.CENTER);
        add(criarPainelSul(),    BorderLayout.SOUTH);
    }

    // ---- TOPO — nome e vida do inimigo ----

    private JPanel criarPainelTopo() {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(new Color(10, 5, 5));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, RED_DARK),
                BorderFactory.createEmptyBorder(10, 24, 10, 24)));

        lblNomeInimigo = new JLabel("", SwingConstants.CENTER);
        lblNomeInimigo.setForeground(RED_LIGHT);
        lblNomeInimigo.setFont(new Font("Serif", Font.BOLD, 36));

        lblProximaAcao = new JLabel("", SwingConstants.CENTER);
        lblProximaAcao.setForeground(new Color(180, 150, 0));
        lblProximaAcao.setFont(new Font("SansSerif", Font.ITALIC, 15));

        barraVidaInimigo = new JProgressBar();
        barraVidaInimigo.setStringPainted(true);
        barraVidaInimigo.setForeground(RED_DARK);
        barraVidaInimigo.setBackground(new Color(25, 10, 10));
        barraVidaInimigo.setPreferredSize(new Dimension(640, 28));
        barraVidaInimigo.setFont(new Font("SansSerif", Font.BOLD, 13));
        barraVidaInimigo.setBorder(BorderFactory.createLineBorder(RED_DARK, 1));

        JPanel barraWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        barraWrap.setOpaque(false);
        barraWrap.add(barraVidaInimigo);

        JPanel nomeAcao = new JPanel(new GridLayout(2, 1));
        nomeAcao.setOpaque(false);
        nomeAcao.add(lblNomeInimigo);
        nomeAcao.add(lblProximaAcao);

        p.add(nomeAcao,  BorderLayout.NORTH);
        p.add(barraWrap, BorderLayout.CENTER);
        return p;
    }

    // ---- CENTRO — status jogador | imagem inimigo ----

    private JPanel criarPainelCentro() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);

        p.add(criarStatusJogador(), BorderLayout.WEST);
        p.add(criarAreaInimigo(),   BorderLayout.CENTER);

        lblLog = new JLabel("Combate iniciado!", SwingConstants.CENTER);
        lblLog.setForeground(new Color(210, 200, 80));
        lblLog.setFont(new Font("SansSerif", Font.ITALIC, 15));
        lblLog.setOpaque(true);
        lblLog.setBackground(new Color(10, 8, 8));
        lblLog.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(50, 40, 0)));
        lblLog.setPreferredSize(new Dimension(0, 30));
        p.add(lblLog, BorderLayout.SOUTH);
        return p;
    }

    private JPanel criarStatusJogador() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(10, 10, 10));
        p.setPreferredSize(new Dimension(168, 0));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, RED_DARK),
                BorderFactory.createEmptyBorder(14, 10, 14, 10)));

        lblVida     = criarChip();
        lblEscudo   = criarChip();
        lblEnergia  = criarChip();
        lblBaralho  = criarChip();
        lblDescarte = criarChip();

        p.add(lblVida);    p.add(Box.createVerticalStrut(7));
        p.add(lblEscudo);  p.add(Box.createVerticalStrut(7));
        p.add(lblEnergia); p.add(Box.createVerticalStrut(7));
        p.add(lblBaralho); p.add(Box.createVerticalStrut(7));
        p.add(lblDescarte);
        p.add(Box.createVerticalGlue());
        return p;
    }

    private JLabel criarChip() {
        JLabel lbl = new JLabel(" ", SwingConstants.CENTER);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(16, 14, 14));
        lbl.setBorder(BorderFactory.createLineBorder(new Color(50, 30, 30), 1));
        lbl.setPreferredSize(new Dimension(148, 62));
        lbl.setMaximumSize(new Dimension(148, 62));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    private JPanel criarAreaInimigo() {
        final Image imgFundo  = new ImageIcon("src/Assets/sala_vazia.png").getImage();
        final Image imgCobra  = new ImageIcon("src/Assets/inimigo_cobra.png").getImage();

        JPanel p = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imgFundo != null && imgFundo.getWidth(null) > 0) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(imgFundo, 0, 0, getWidth(), getHeight(), this);
                    g2.dispose();
                }
            }
        };
        p.setOpaque(true);

        JLabel imgInimigo = new JLabel("", SwingConstants.CENTER);
        imgInimigo.setOpaque(false);
        if (imgCobra.getWidth(null) > 0) {
            imgInimigo.setIcon(
                    new ImageIcon(imgCobra.getScaledInstance(-1, 320, Image.SCALE_SMOOTH)));
        }
        p.add(imgInimigo, BorderLayout.CENTER);
        return p;
    }

    // ---- SUL — mao de cartas + botoes ----

    private JPanel criarPainelSul() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(9, 7, 7));
        p.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, RED_DARK));

        painelMao = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        painelMao.setBackground(new Color(9, 7, 7));

        JScrollPane scroll = new JScrollPane(painelMao,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(0, CARD_H + 36));
        scroll.getViewport().setBackground(new Color(9, 7, 7));

        p.add(scroll,               BorderLayout.CENTER);
        p.add(criarPainelBotoes(),  BorderLayout.EAST);
        return p;
    }

    private JPanel criarPainelBotoes() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(10, 7, 7));
        p.setPreferredSize(new Dimension(200, 0));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, RED_DARK),
                BorderFactory.createEmptyBorder(14, 12, 14, 12)));

        btnJogar = criarBotao("JOGAR CARTA", new Color(90, 35, 0), RED_LIGHT);
        btnJogar.setEnabled(false);
        btnJogar.addActionListener(e -> jogarCartaSelecionada());

        JButton btnEmbaralhar = criarBotao("EMBARALHAR", new Color(15, 35, 75),
                new Color(80, 140, 220));
        btnEmbaralhar.addActionListener(e -> embaralharDescarte());

        JButton btnTurno = criarBotao("FINALIZAR TURNO", new Color(55, 0, 0), RED_LIGHT);
        btnTurno.addActionListener(e -> finalizarTurno());

        p.add(btnJogar);
        p.add(Box.createVerticalStrut(8));
        p.add(btnEmbaralhar);
        p.add(Box.createVerticalStrut(8));
        p.add(btnTurno);
        return p;
    }

    private JButton criarBotao(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(176, 50));
        btn.setPreferredSize(new Dimension(176, 50));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Serif", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(fg.darker(), 2));
        return btn;
    }

    // =========================================================
    // RENDERIZACAO DAS CARTAS
    // =========================================================

    /**
     * Reconstroi o painel da mao com as cartas atuais do jogador.
     */
    private void renderizarMao() {
        painelMao.removeAll();
        paineisCarta.clear();

        List<Carta> cartas = combate.getJogador().getMao().getCartas();
        for (Carta carta : cartas) {
            JPanel painel = criarPainelCarta(carta);
            paineisCarta.add(painel);
            painelMao.add(painel);
        }

        if (cartas.isEmpty()) {
            JLabel empty = new JLabel("Mao vazia - finalize o turno para comprar cartas");
            empty.setForeground(new Color(80, 80, 80));
            empty.setFont(new Font("SansSerif", Font.ITALIC, 16));
            painelMao.add(empty);
        }

        painelMao.revalidate();
        painelMao.repaint();
    }

    /**
     * Cria o painel visual de uma carta com imagem, nome, custo e descricao.
     * Clicar na carta (ou em qualquer filho) a seleciona ou desseleciona.
     *
     * @param carta carta a representar
     * @return painel pronto para insercao na mao
     */
    private JPanel criarPainelCarta(Carta carta) {
        JPanel card = new JPanel(new BorderLayout(0, 0));
        card.setPreferredSize(new Dimension(CARD_W, CARD_H));
        card.setBackground(BG_CARD);
        card.setBorder(bordaNormal(carta));
        card.putClientProperty("carta", carta);

        // Topo: nome + custo
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(corTipo(carta).darker().darker());
        topo.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
        JLabel lblNome = new JLabel(carta.getNome());
        lblNome.setFont(new Font("Serif", Font.BOLD, 13));
        lblNome.setForeground(Color.WHITE);
        JLabel lblCusto = new JLabel("* " + carta.getCusto());
        lblCusto.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblCusto.setForeground(GOLD);
        topo.add(lblNome,  BorderLayout.WEST);
        topo.add(lblCusto, BorderLayout.EAST);

        // Centro: imagem
        JLabel imgLbl = new JLabel("", SwingConstants.CENTER);
        imgLbl.setOpaque(true);
        imgLbl.setBackground(new Color(28, 28, 28));
        Image img = carregarImagemCarta(carta);
        if (img != null) {
            int ih = CARD_H - 88;
            imgLbl.setIcon(new ImageIcon(img.getScaledInstance(-1, ih, Image.SCALE_SMOOTH)));
        } else {
            imgLbl.setText(carta.getTipo() != null ? carta.getTipo().name() : "?");
            imgLbl.setForeground(corTipo(carta));
            imgLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        }

        // Rodape: descricao
        JLabel desc = new JLabel(
                "<html><center>" + carta.getDescricao() + "</center></html>",
                SwingConstants.CENTER);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        desc.setForeground(new Color(195, 195, 195));
        desc.setOpaque(true);
        desc.setBackground(new Color(14, 14, 14));
        desc.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 4));
        desc.setPreferredSize(new Dimension(CARD_W, 38));

        card.add(topo,   BorderLayout.NORTH);
        card.add(imgLbl, BorderLayout.CENTER);
        card.add(desc,   BorderLayout.SOUTH);

        // Mouse listener propagado a todos os filhos para capturar cliques
        MouseAdapter ma = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                selecionarCarta(carta, card);
            }
            @Override public void mouseEntered(MouseEvent e) {
                if (cartaSelecionada != carta)
                    card.setBorder(BorderFactory.createLineBorder(
                            corTipo(carta).brighter(), 2));
            }
            @Override public void mouseExited(MouseEvent e) {
                if (cartaSelecionada != carta) card.setBorder(bordaNormal(carta));
            }
        };
        card.addMouseListener(ma);
        propagarListener(card, ma);

        return card;
    }

    /**
     * Propaga um {@link MouseAdapter} para todos os componentes filhos de um container,
     * garantindo que cliques em sub-elementos (imagens, labels) sejam capturados.
     */
    private void propagarListener(Container container, MouseAdapter listener) {
        for (Component c : container.getComponents()) {
            c.addMouseListener(listener);
            if (c instanceof Container ct) propagarListener(ct, listener);
        }
    }

    private Border bordaNormal(Carta carta) {
        return BorderFactory.createLineBorder(new Color(55, 40, 40), 2);
    }

    private Border bordaSelecionada() {
        return BorderFactory.createLineBorder(GOLD, 3);
    }

    /**
     * Retorna a cor associada ao tipo da carta.
     *
     * @param carta carta a consultar
     * @return cor representativa do tipo
     */
    private Color corTipo(Carta carta) {
        if (carta.getTipo() == null) return Color.GRAY;
        return switch (carta.getTipo()) {
            case ATAQUE   -> new Color(180, 40,  40);
            case DEFESA   -> new Color(40,  80,  200);
            case CURA     -> new Color(40,  160, 80);
            case ESPECIAL -> new Color(160, 60,  180);
            case PODER    -> new Color(200, 140, 20);
        };
    }

    /**
     * Carrega a imagem de uma carta pelo nome, mapeando para os assets disponíveis.
     *
     * @param carta carta cujo asset sera carregado
     * @return imagem correspondente ou {@code null} se nao encontrada
     */
    private Image carregarImagemCarta(Carta carta) {
        String arquivo = switch (carta.getNome()) {
            case "Golpe"         -> "carta_adaga";
            case "Defender"      -> "carta_escudo";
            case "Cura"          -> "carta_pocao_cura";
            case "Golpe Critico" -> "carta_foice";
            case "Investida"     -> "carta_lanca";
            case "Raiva"         -> "carta_poder";
            case "Golpe Duplo"   -> "carta_maca";
            case "Saque"         -> "carta_flecha";
            default              -> null;
        };
        if (arquivo == null) return null;
        Image img = new ImageIcon("src/Assets/" + arquivo + ".png").getImage();
        return img.getWidth(null) > 0 ? img : null;
    }

    // =========================================================
    // SELECAO DE CARTA
    // =========================================================

    /**
     * Seleciona ou desseleciona uma carta da mao.
     * Destaca com borda dourada e habilita "JOGAR CARTA" se houver energia suficiente.
     *
     * @param carta  carta clicada
     * @param painel painel visual correspondente
     */
    private void selecionarCarta(Carta carta, JPanel painel) {
        // Remove destaque de todos
        for (JPanel p : paineisCarta) {
            Carta c = (Carta) p.getClientProperty("carta");
            p.setBorder(bordaNormal(c != null ? c : carta));
            p.setBackground(BG_CARD);
        }

        if (cartaSelecionada == carta) {
            // Toggle: deseleciona
            cartaSelecionada = null;
            btnJogar.setEnabled(false);
        } else {
            cartaSelecionada = carta;
            painel.setBorder(bordaSelecionada());
            painel.setBackground(BG_SEL);
            btnJogar.setEnabled(temEnergiaPara(carta));
        }
        painelMao.repaint();
    }

    /**
     * Verifica se o jogador possui energia suficiente para usar a carta.
     *
     * @param carta carta a verificar
     * @return {@code true} se a energia atual cobre o custo
     */
    private boolean temEnergiaPara(Carta carta) {
        return combate.getJogador().getEnergia() >= carta.getCusto();
    }

    // =========================================================
    // ACOES DO JOGADOR
    // =========================================================

    /**
     * Joga a carta selecionada: aplica os efeitos, desconta energia,
     * remove da mao e adiciona ao descarte.
     */
    private void jogarCartaSelecionada() {
        if (cartaSelecionada == null) return;
        Jogador j = combate.getJogador();

        if (!temEnergiaPara(cartaSelecionada)) {
            definirLog("Energia insuficiente para jogar " + cartaSelecionada.getNome() + "!");
            return;
        }

        cartaSelecionada.usar(combate.getContexto(), combate.getInimigo());
        j.setEnergia(j.getEnergia() - cartaSelecionada.getCusto());
        j.getMao().removerCarta(cartaSelecionada);
        adicionarAoDescarte(cartaSelecionada);

        definirLog("Jogou \"" + cartaSelecionada.getNome() + "\" — " + cartaSelecionada.getDescricao());
        cartaSelecionada = null;
        btnJogar.setEnabled(false);
        atualizarUI();
        verificarFimCombate();
    }

    /**
     * Move todas as cartas do descarte de volta ao baralho e embaralha.
     */
    private void embaralharDescarte() {
        reabastecerBaralho();
        definirLog("Descarte embaralhado de volta ao baralho!");
        atualizarUI();
    }

    /**
     * Finaliza o turno do jogador: descarta a mao, executa a acao do inimigo,
     * restaura energia e distribui nova mao de 5 cartas.
     */
    private void finalizarTurno() {
        descartarMao();
        String msgInimigo = executarTurnoInimigo();

        Jogador j = combate.getJogador();
        j.setEnergia(j.getEnergiaMaxima());
        j.setEscudo(0);   // escudo temporario expira entre turnos

        combate.getTurno().avancar();
        comprarMao(5);
        combate.getInimigo().definirProximaAcao();

        cartaSelecionada = null;
        btnJogar.setEnabled(false);
        definirLog(msgInimigo);
        atualizarUI();
        verificarFimCombate();
    }

    // =========================================================
    // TURNO DO INIMIGO
    // =========================================================

    /**
     * Executa a acao previamente definida do inimigo e retorna mensagem descritiva.
     *
     * @return texto descrevendo o que o inimigo fez
     */
    private String executarTurnoInimigo() {
        Inimigo inimigo = combate.getInimigo();
        Jogador jogador = combate.getJogador();
        AcaoInimigo acao = inimigo.getProximaAcao();
        if (acao == null) {
            inimigo.definirProximaAcao();
            acao = inimigo.getProximaAcao();
        }

        return switch (acao) {
            case ATAQUE -> {
                int d = Math.max(1, inimigo.getAtaque() - jogador.getDefesa());
                jogador.receberDano(d);
                yield "Inimigo atacou! Voce recebeu " + d + " de dano.";
            }
            case ATAQUE_CRITICO -> {
                int d = (int)(inimigo.getAtaque() * 1.5);
                jogador.receberDano(d);
                yield "GOLPE CRITICO! Voce recebeu " + d + " de dano!";
            }
            case DEFESA -> {
                inimigo.ganharEscudo(8);
                yield "Inimigo se defendeu! Ganhou 8 de escudo.";
            }
            case BUFF -> {
                inimigo.setAtaque(inimigo.getAtaque() + 2);
                yield "Inimigo se fortaleceu! Ataque +2.";
            }
            case CURA -> {
                int cura = inimigo.getVidaMaxima() / 5;
                inimigo.curar(cura);
                yield "Inimigo se curou! +" + cura + " de vida.";
            }
        };
    }

    // =========================================================
    // MECANICAS DE DECK
    // =========================================================

    /**
     * Compra {@code n} cartas do topo do baralho para a mao do jogador.
     * Se o baralho esvaziar, reabastece automaticamente com o descarte.
     *
     * @param n numero de cartas a comprar
     */
    private void comprarMao(int n) {
        Jogador j = combate.getJogador();
        for (int k = 0; k < n; k++) {
            if (j.getBaralho().estaVazio()) reabastecerBaralho();
            if (j.getBaralho().estaVazio()) break;
            if (!j.getMao().estaCheia()) {
                Carta c = j.getBaralho().comprar();
                if (c != null) j.getMao().adicionarCarta(c);
            }
        }
    }

    /**
     * Descarta todas as cartas da mao do jogador para a pilha de descarte.
     */
    private void descartarMao() {
        Jogador j = combate.getJogador();
        for (Carta c : new ArrayList<>(j.getMao().getCartas())) adicionarAoDescarte(c);
        j.getMao().getCartas().clear();
    }

    /**
     * Adiciona uma carta a pilha de descarte do jogador.
     *
     * @param carta carta a descartar
     */
    private void adicionarAoDescarte(Carta carta) {
        PilhaDescarte pd = combate.getJogador().getPilhaDescarte();
        if (pd != null && pd.getCartasDescartadas() != null) {
            pd.getCartasDescartadas().add(carta);
            pd.setQuantidadeCartas(pd.getCartasDescartadas().size());
        }
    }

    /**
     * Move todas as cartas do descarte de volta ao baralho e embaralha.
     */
    private void reabastecerBaralho() {
        Jogador j = combate.getJogador();
        PilhaDescarte pd = j.getPilhaDescarte();
        if (pd == null || pd.getCartasDescartadas() == null
                       || pd.getCartasDescartadas().isEmpty()) return;
        for (Carta c : pd.getCartasDescartadas()) j.getBaralho().adicionarCarta(c);
        pd.getCartasDescartadas().clear();
        pd.setQuantidadeCartas(0);
        j.getBaralho().embaralhar();
    }

    // =========================================================
    // FIM DE COMBATE
    // =========================================================

    /**
     * Verifica se o combate terminou e exibe dialogo de resultado.
     * Invoca {@code onCombateTerminado} ao confirmar.
     */
    private void verificarFimCombate() {
        if (!combate.estaFinalizado()) return;

        String titulo, msg;
        if (combate.jogadorVenceu()) {
            titulo = "Vitoria!";
            msg = "Voce derrotou " + combate.getInimigo().getNome() + "!\n"
                + "+XP: " + combate.getInimigo().getRecompensaXp()
                + "  |  +Ouro: " + combate.getInimigo().getRecompensaOuro();
            combate.getJogador().setInimigosDerrotados(
                    combate.getJogador().getInimigosDerrotados() + 1);
        } else {
            titulo = "Derrota...";
            msg = "Voce foi derrotado pelo " + combate.getInimigo().getNome() + "!";
        }

        JOptionPane.showMessageDialog(this, msg, titulo, JOptionPane.INFORMATION_MESSAGE);

        if (onCombateTerminado != null) {
            SwingUtilities.invokeLater(onCombateTerminado);
        }
    }

    // =========================================================
    // ATUALIZACAO DA UI
    // =========================================================

    /**
     * Atualiza todos os elementos visuais com os valores atuais do modelo.
     */
    private void atualizarUI() {
        Jogador j = combate.getJogador();
        Inimigo i = combate.getInimigo();

        // Inimigo
        lblNomeInimigo.setText(i.getNome() != null ? i.getNome().toUpperCase() : "INIMIGO");
        barraVidaInimigo.setMaximum(Math.max(1, i.getVidaMaxima()));
        barraVidaInimigo.setValue(Math.max(0, i.getVida()));
        barraVidaInimigo.setString(i.getVida() + " / " + i.getVidaMaxima()
                + (i.getEscudo() > 0 ? "   Escudo: " + i.getEscudo() : ""));
        lblProximaAcao.setText(descreverAcao(i.getProximaAcao()));

        // Jogador
        int pct    = j.getVidaMaxima() > 0 ? (j.getVida() * 100 / j.getVidaMaxima()) : 0;
        Color cVida = pct > 50 ? new Color(40, 160, 40)
                     : pct > 25 ? new Color(200, 140, 0)
                     :            new Color(200, 30,  30);

        preencherChip(lblVida,    "VIDA",    j.getVida() + " / " + j.getVidaMaxima(),       cVida);
        preencherChip(lblEscudo,  "ESCUDO",  String.valueOf(j.getEscudo()),                  new Color(60, 80, 200));
        preencherChip(lblEnergia, "ENERGIA", j.getEnergia() + " / " + j.getEnergiaMaxima(), GOLD);

        int qtdDeck = j.getBaralho() != null && j.getBaralho().getCartas() != null
                ? j.getBaralho().getCartas().size() : 0;
        int qtdDesc = j.getPilhaDescarte() != null
                && j.getPilhaDescarte().getCartasDescartadas() != null
                ? j.getPilhaDescarte().getCartasDescartadas().size() : 0;
        preencherChip(lblBaralho,  "BARALHO",  String.valueOf(qtdDeck),  new Color(100, 100, 100));
        preencherChip(lblDescarte, "DESCARTE", String.valueOf(qtdDesc),  new Color(100, 80,  80));

        renderizarMao();
    }

    /**
     * Preenche um chip de status com titulo e valor colorido via HTML.
     *
     * @param lbl      label a atualizar
     * @param titulo   texto pequeno do titulo
     * @param valor    texto grande do valor
     * @param corValor cor do texto do valor
     */
    private void preencherChip(JLabel lbl, String titulo, String valor, Color corValor) {
        String hex = String.format("#%02X%02X%02X",
                corValor.getRed(), corValor.getGreen(), corValor.getBlue());
        lbl.setText("<html><center>"
                + "<span style='color:#AA0000;font-size:10px;'>" + titulo + "</span><br>"
                + "<span style='color:" + hex + ";font-size:18px;font-weight:bold;'>"
                + valor + "</span>"
                + "</center></html>");
    }

    /**
     * Retorna descricao legivel da acao que o inimigo planeja executar.
     *
     * @param acao acao do inimigo
     * @return texto descritivo
     */
    private String descreverAcao(AcaoInimigo acao) {
        if (acao == null) return "";
        return switch (acao) {
            case ATAQUE         -> "Planejando atacar...";
            case ATAQUE_CRITICO -> "Preparando golpe critico!";
            case DEFESA         -> "Vai se defender";
            case BUFF           -> "Esta se fortalecendo";
            case CURA           -> "Vai se curar";
        };
    }

    /**
     * Atualiza o texto do log de combate exibido na parte inferior.
     *
     * @param msg mensagem a exibir
     */
    private void definirLog(String msg) {
        lblLog.setText(msg);
    }

    // =========================================================
    // GETTERS (compatibilidade com codigo legado)
    // =========================================================

    /** @return label de vida do jogador */
    public JLabel getLblVida()                { return lblVida;    }

    /** @return label de escudo do jogador */
    public JLabel getLblEscudo()              { return lblEscudo;  }

    /** @return label do baralho */
    public JLabel getLblBaralho()             { return lblBaralho; }

    /** @return label do descarte */
    public JLabel getLblDescarte()            { return lblDescarte; }

    /** @return label com nome do inimigo */
    public JLabel getLblNomeInimigo()         { return lblNomeInimigo; }

    /** @return barra de vida do inimigo */
    public JProgressBar getBarraVidaInimigo() { return barraVidaInimigo; }

    /** @return painel da mao do jogador */
    public JPanel getPainelMao()              { return painelMao; }

    /** @return {@code null} — botao gerenciado internamente */
    public JButton getBtnFinalizarTurno()     { return null; }
}
