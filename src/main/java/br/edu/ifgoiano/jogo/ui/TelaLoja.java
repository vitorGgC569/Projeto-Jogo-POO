package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.dao.CartaDAO;
import br.edu.ifgoiano.jogo.entidades.Baralho;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Carteira;
import br.edu.ifgoiano.jogo.entidades.Jogador;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Painel da loja onde o jogador compra cartas com moedas do banco de dados.
 * Cada carta exibida vem da tabela {@code carta} e e adicionada ao baralho
 * do jogador ao ser comprada. Saldo de moedas vem da {@link Carteira} do jogador.
 */
public class TelaLoja extends JPanel {

    private static final int   CARD_W   = 175;
    private static final int   CARD_H   = 280;
    private static final Color BG       = new Color(15, 15, 20);
    private static final Color BG_CARD  = new Color(28, 22, 22);
    private static final Color RED_DARK = new Color(120, 0, 0);
    private static final Color GOLD     = new Color(255, 215, 0);

    private final Jogador  jogador;
    private final Runnable onSair;
    private final List<Carta> ofertas;
    private final CartaDAO cartaDAO = new CartaDAO();

    private JLabel lblMoedas;
    private JPanel painelCartas;

    /**
     * Cria a loja vinculada ao jogador e ao callback de saida.
     *
     * @param jogador jogador que esta comprando (sua carteira e baralho serao usados)
     * @param onSair  acao a executar ao clicar em SAIR (volta a exploracao)
     */
    public TelaLoja(Jogador jogador, Runnable onSair) {
        this.jogador = jogador;
        this.onSair  = onSair;
        this.ofertas = carregarOfertas();

        setLayout(new BorderLayout());
        setBackground(BG);
        criarInterface();
    }

    /** Construtor antigo para compatibilidade — cria jogador padrao de teste. */
    public TelaLoja() {
        this(criarJogadorTeste(), null);
    }

    private static Jogador criarJogadorTeste() {
        Jogador j = new Jogador();
        j.setNome("Teste");
        j.setBaralho(new Baralho(40, 0, false, new ArrayList<>()));
        j.setCarteira(new Carteira(500, 500, 0));
        return j;
    }

    // =========================================================
    // CARREGAMENTO DAS OFERTAS
    // =========================================================

    /**
     * Carrega ate 6 cartas do banco, embaralha e seleciona variedade.
     * Em caso de falha do banco, retorna lista vazia (UI fica sem cartas).
     */
    private List<Carta> carregarOfertas() {
        try {
            List<Carta> todas = new ArrayList<>(cartaDAO.listarTodos());
            Collections.shuffle(todas);
            return todas.size() > 6 ? new ArrayList<>(todas.subList(0, 6)) : todas;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // =========================================================
    // INTERFACE
    // =========================================================

    private void criarInterface() {
        add(criarPainelTopo(),    BorderLayout.NORTH);
        add(criarPainelCentro(),  BorderLayout.CENTER);
        add(criarPainelInferior(), BorderLayout.SOUTH);
    }

    private JPanel criarPainelTopo() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, RED_DARK),
                BorderFactory.createEmptyBorder(20, 30, 14, 30)));

        JLabel titulo = new JLabel("LOJA DO FORASTEIRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 42));
        titulo.setForeground(new Color(220, 20, 20));

        lblMoedas = new JLabel();
        lblMoedas.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblMoedas.setForeground(GOLD);
        atualizarMoedas();

        p.add(titulo,    BorderLayout.CENTER);
        p.add(lblMoedas, BorderLayout.EAST);
        return p;
    }

    private JScrollPane criarPainelCentro() {
        painelCartas = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 30));
        painelCartas.setBackground(BG);
        renderizarOfertas();

        JScrollPane sp = new JScrollPane(painelCartas,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        sp.getViewport().setBackground(BG);
        sp.getVerticalScrollBar().setUnitIncrement(20);
        return sp;
    }

    private JPanel criarPainelInferior() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 18));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, RED_DARK));

        JButton btnSair = new JButton("SAIR DA LOJA");
        btnSair.setFont(new Font("Serif", Font.BOLD, 18));
        btnSair.setBackground(new Color(30, 30, 30));
        btnSair.setForeground(new Color(220, 20, 20));
        btnSair.setFocusPainted(false);
        btnSair.setBorder(BorderFactory.createLineBorder(RED_DARK, 2));
        btnSair.setPreferredSize(new Dimension(220, 50));
        btnSair.addActionListener(e -> {
            if (onSair != null) onSair.run();
            else {
                Window w = SwingUtilities.getWindowAncestor(this);
                if (w != null) w.dispose();
            }
        });
        p.add(btnSair);
        return p;
    }

    // =========================================================
    // CARTAS
    // =========================================================

    private void renderizarOfertas() {
        painelCartas.removeAll();
        if (ofertas.isEmpty()) {
            JLabel vazio = new JLabel("Loja vazia — banco de dados indisponivel.");
            vazio.setForeground(new Color(170, 170, 170));
            vazio.setFont(new Font("SansSerif", Font.ITALIC, 18));
            painelCartas.add(vazio);
            painelCartas.revalidate();
            painelCartas.repaint();
            return;
        }
        for (Carta c : ofertas) painelCartas.add(criarPainelCarta(c));
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    private JPanel criarPainelCarta(Carta carta) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(CARD_W, CARD_H));
        card.setBackground(BG_CARD);
        card.setBorder(bordaNormal());

        // Topo: nome + preco
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(corTipo(carta).darker().darker());
        topo.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        JLabel lblNome = new JLabel(carta.getNome());
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Serif", Font.BOLD, 14));
        JLabel lblPreco = new JLabel(carta.getValorVenda() + " ouro");
        lblPreco.setForeground(GOLD);
        lblPreco.setFont(new Font("SansSerif", Font.BOLD, 13));
        topo.add(lblNome,  BorderLayout.WEST);
        topo.add(lblPreco, BorderLayout.EAST);

        // Centro: imagem
        JLabel lblImg = new JLabel("", SwingConstants.CENTER);
        lblImg.setOpaque(true);
        lblImg.setBackground(new Color(20, 20, 20));
        Image img = carregarImagem(carta);
        if (img != null && img.getWidth(null) > 0) {
            lblImg.setIcon(new ImageIcon(img.getScaledInstance(-1, CARD_H - 130, Image.SCALE_SMOOTH)));
        } else {
            lblImg.setText(carta.getTipo() != null ? carta.getTipo().name() : "?");
            lblImg.setForeground(corTipo(carta));
            lblImg.setFont(new Font("SansSerif", Font.BOLD, 14));
        }

        // Rodape: descricao + botao
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setOpaque(false);
        JLabel lblDesc = new JLabel(
                "<html><center>" + carta.getDescricao() + "</center></html>",
                SwingConstants.CENTER);
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblDesc.setForeground(new Color(200, 200, 200));
        lblDesc.setOpaque(true);
        lblDesc.setBackground(new Color(14, 14, 14));
        lblDesc.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        lblDesc.setPreferredSize(new Dimension(CARD_W, 40));

        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.setBackground(new Color(40, 80, 30));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFocusPainted(false);
        btnComprar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnComprar.setBorder(BorderFactory.createLineBorder(new Color(20, 120, 40), 2));
        btnComprar.addActionListener(e -> comprar(carta, card, btnComprar));

        rodape.add(lblDesc,    BorderLayout.NORTH);
        rodape.add(btnComprar, BorderLayout.SOUTH);

        card.add(topo,    BorderLayout.NORTH);
        card.add(lblImg,  BorderLayout.CENTER);
        card.add(rodape,  BorderLayout.SOUTH);
        return card;
    }

    private void comprar(Carta carta, JPanel painel, JButton btn) {
        Carteira c = jogador.getCarteira();
        int preco  = carta.getValorVenda();

        if (c == null) {
            JOptionPane.showMessageDialog(this, "Carteira do jogador nao inicializada.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!c.gastar(preco)) {
            JOptionPane.showMessageDialog(this,
                    "Ouro insuficiente! Voce tem " + c.getMoedas() + " e precisa de " + preco + ".",
                    "Compra falhou", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Adiciona ao baralho do jogador
        if (jogador.getBaralho() != null && jogador.getBaralho().getCartas() != null) {
            jogador.getBaralho().adicionarCarta(carta);
        }

        atualizarMoedas();
        btn.setEnabled(false);
        btn.setText("COMPRADA");
        painel.setBorder(BorderFactory.createLineBorder(new Color(60, 160, 60), 3));

        JOptionPane.showMessageDialog(this,
                "Voce comprou " + carta.getNome() + "!",
                "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private void atualizarMoedas() {
        int saldo = jogador.getCarteira() != null ? jogador.getCarteira().getMoedas() : 0;
        lblMoedas.setText("Ouro: " + saldo);
    }

    private Border bordaNormal() {
        return BorderFactory.createLineBorder(new Color(70, 50, 50), 2);
    }

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

    private Image carregarImagem(Carta carta) {
        String arquivo = cartaDAO.buscarArquivoImg(carta.getId());
        if (arquivo == null) arquivo = mapearPorNome(carta.getNome());
        if (arquivo == null) return null;
        Image img = new ImageIcon("src/Assets/" + arquivo + ".png").getImage();
        return img.getWidth(null) > 0 ? img : null;
    }

    private String mapearPorNome(String nome) {
        if (nome == null) return null;
        return switch (nome) {
            case "Golpe"             -> "carta_adaga";
            case "Defender"          -> "carta_escudo";
            case "Cura"              -> "carta_pocao_cura";
            case "Golpe Critico"     -> "carta_foice";
            case "Investida"         -> "carta_lanca";
            case "Raiva"             -> "carta_poder";
            case "Golpe Duplo"       -> "carta_maca";
            case "Saque"             -> "carta_flecha";
            case "Flecha Envenenada" -> "carta_flecha_envenenada";
            case "Agua Benta"        -> "carta_agua_benta";
            case "Antidoto"          -> "carta_antidoto";
            case "Biblia Sagrada"    -> "carta_biblia";
            case "Maldicao"          -> "carta_maldicao";
            default                  -> null;
        };
    }
}
