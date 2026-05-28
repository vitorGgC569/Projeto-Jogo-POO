package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.entidades.Baralho;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Carteira;
import br.edu.ifgoiano.jogo.entidades.Jogador;
import br.edu.ifgoiano.jogo.entidades.Mao;
import br.edu.ifgoiano.jogo.entidades.Masmorra;
import br.edu.ifgoiano.jogo.entidades.PilhaDescarte;
import br.edu.ifgoiano.jogo.entidades.Regiao;
import br.edu.ifgoiano.jogo.enums.Direcao;
import br.edu.ifgoiano.jogo.enums.TipoRegiao;
import br.edu.ifgoiano.jogo.factory.CartaFactory;
import br.edu.ifgoiano.jogo.util.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tela principal de exploração da masmorra em primeira pessoa.
 * Exibe a visão do jogador via {@link PainelExploracao}, a bússola,
 * o minimapa e os botões de movimento e ação.
 * Mantém um {@link Jogador} único compartilhado entre exploração,
 * combate e loja para preservar carteira, baralho e progresso.
 */
public class TelaExploracao extends JFrame {

    private PainelExploracao painelExploracao;
    /** Painel principal de exploração — reutilizado ao retornar do combate/loja. */
    private JPanel painelPrincipal;

    /** Jogador único compartilhado entre combate, exploração e loja. */
    private final Jogador jogador;

    /** Botão de abrir baú — habilitado somente quando o jogador está na posição correta. */
    private JButton btnAbrirBau;

    private static final Random RANDOM = new Random();

    /**
     * Cria e exibe a tela de exploração em tela cheia.
     */
    public TelaExploracao() {
        this.jogador = criarJogadorInicial();

        setTitle("Dungeon Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        criarInterface();
        setVisible(true);

        // Música de exploração
        AudioPlayer.tocarMusica("xDeviruchi - Mysterious Dungeon.wav");
    }

    /**
     * Cria o jogador inicial com baralho, mao, descarte e carteira.
     * É mantido entre as telas (exploração, combate, loja).
     */
    private Jogador criarJogadorInicial() {
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
        j.setBaralho(new Baralho(40, cartas.size(), false, new ArrayList<>(cartas)));
        j.setMao(new Mao(new ArrayList<>(), 5));
        j.setPilhaDescarte(new PilhaDescarte(new ArrayList<>()));
        j.setCarteira(new Carteira(150, 150, 0));
        return j;
    }

    private void criarInterface() {
        painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.BLACK);

        // Visão POV 3D — ocupa toda a área central
        painelExploracao = new PainelExploracao(new Masmorra());
        painelPrincipal.add(painelExploracao, BorderLayout.CENTER);

        // Controles na parte inferior
        painelPrincipal.add(criarPainelControles(), BorderLayout.SOUTH);

        add(painelPrincipal);

        painelExploracao.setOnCombate(this::abrirTelaCombate);
        painelExploracao.setOnLoja(this::abrirTelaLoja);
        painelExploracao.setOnMovimento(this::atualizarBotoes);
        painelExploracao.setFocusable(true);
        SwingUtilities.invokeLater(() -> painelExploracao.requestFocusInWindow());
    }

    // =====================================
    // PAINEL DE CONTROLES
    // =====================================
    private JPanel criarPainelControles() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(15, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        painel.add(criarPainelMovimento(), BorderLayout.WEST);
        painel.add(criarPainelAcoes(),    BorderLayout.EAST);

        return painel;
    }

    private JPanel criarPainelMovimento() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 1; gbc.gridy = 0;
        JButton btnAvancar = criarBotaoMovimento("↑  Avançar");
        btnAvancar.addActionListener(e -> painelExploracao.avancar());
        painel.add(btnAvancar, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JButton btnVirarEsq = criarBotaoMovimento("↺  Virar");
        btnVirarEsq.addActionListener(e -> painelExploracao.virarEsquerda());
        painel.add(btnVirarEsq, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JButton btnRecuar = criarBotaoMovimento("↓  Recuar");
        btnRecuar.addActionListener(e -> painelExploracao.recuar());
        painel.add(btnRecuar, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        JButton btnVirarDir = criarBotaoMovimento("↻  Virar");
        btnVirarDir.addActionListener(e -> painelExploracao.virarDireita());
        painel.add(btnVirarDir, gbc);

        return painel;
    }

    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        btnAbrirBau = criarBotaoAcao("ABRIR BAU");
        btnAbrirBau.setEnabled(false);
        btnAbrirBau.addActionListener(e -> abrirBau());

        JButton btnSair = criarBotaoAcao("SAIR");
        btnSair.addActionListener(e -> System.exit(0));

        painel.add(btnAbrirBau);
        painel.add(Box.createVerticalStrut(12));
        painel.add(btnSair);

        return painel;
    }

    // =====================================
    // LÓGICA DO BAÚ
    // =====================================

    /**
     * Atualiza o estado dos botões de ação com base na posição e direção do jogador.
     * Chamado automaticamente após cada movimento/rotação (callback onMovimento).
     */
    private void atualizarBotoes() {
        Regiao reg = painelExploracao.getRegiaoAtual();
        Direcao dir = painelExploracao.getPlayerDirecao();

        boolean podAbrirBau = reg != null
                && reg.getTipoRegiao() == TipoRegiao.SALA_TESOURO
                && dir == reg.getDirecaoInterior()
                && !reg.isBauAberto();

        btnAbrirBau.setEnabled(podAbrirBau);
    }

    /**
     * Abre o baú da sala de tesouro atual, concede recompensa ao jogador e
     * exibe um diálogo informando o loot obtido.
     */
    private void abrirBau() {
        Regiao reg = painelExploracao.getRegiaoAtual();
        if (reg == null || reg.isBauAberto()) return;

        reg.setBauAberto(true);
        btnAbrirBau.setEnabled(false);

        // Rola a recompensa: 1 = ouro, 2 = carta, 3 = ambos
        int rolar = RANDOM.nextInt(3) + 1;
        int ouro  = 0;
        Carta cartaGanha = null;
        StringBuilder msg = new StringBuilder("Você abriu o baú!\n\n");

        if (rolar == 1 || rolar == 3) {
            ouro = 20 + RANDOM.nextInt(61); // 20–80 moedas
            if (jogador.getCarteira() != null) {
                jogador.getCarteira().adicionar(ouro);
            }
            msg.append("Ouro encontrado: ").append(ouro).append(" moedas\n");
        }

        if (rolar == 2 || rolar == 3) {
            cartaGanha = CartaFactory.criarCartaAleatoria();
            if (jogador.getBaralho() != null) {
                jogador.getBaralho().adicionarCarta(cartaGanha);
            }
            msg.append("Carta encontrada: ").append(cartaGanha.getNome());
        }

        JOptionPane.showMessageDialog(
                this,
                msg.toString(),
                "Baú de Tesouro",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Repintar para mostrar baú aberto
        painelExploracao.repaint();
        SwingUtilities.invokeLater(() -> painelExploracao.requestFocusInWindow());
    }

    // =====================================
    // CRIAÇÃO DE BOTÕES
    // =====================================

    private JButton criarBotaoMovimento(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(115, 55));
        botao.setBackground(new Color(35, 35, 35));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 15));
        botao.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
        return botao;
    }

    private JButton criarBotaoAcao(String texto) {
        JButton botao = new JButton(texto);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(280, 55));
        botao.setBackground(new Color(40, 40, 40));
        botao.setForeground(new Color(220, 20, 20));
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 18));
        botao.setBorder(BorderFactory.createLineBorder(new Color(120, 0, 0), 2));
        return botao;
    }

    // =====================================
    // TRANSIÇÕES DE TELA E MÚSICA
    // =====================================

    /**
     * Substitui o conteúdo da janela pelo painel de combate.
     * Chamado automaticamente quando o jogador entra numa sala de inimigo.
     * Ao término do combate, {@code finalizarCombate()} restaura a exploração.
     */
    private void abrirTelaCombate() {
        AudioPlayer.tocarMusica("xDeviruchi - Decisive Battle.wav");
        getContentPane().removeAll();
        add(new TelaCombate(this::finalizarCombate));
        revalidate();
        repaint();
    }

    /**
     * Restaura a tela de exploração após o combate terminar.
     * Retorna o jogador ao corredor mais próximo da sala de inimigo.
     */
    private void finalizarCombate() {
        AudioPlayer.tocarMusica("xDeviruchi - Mysterious Dungeon.wav");
        getContentPane().removeAll();
        add(painelPrincipal);
        revalidate();
        repaint();
        painelExploracao.voltarAoCorredorMaisProximo();
        atualizarBotoes();
        SwingUtilities.invokeLater(() -> painelExploracao.requestFocusInWindow());
    }

    /**
     * Substitui o conteudo da janela pela loja. O jogador compartilhado
     * tem sua carteira/baralho atualizados conforme compra cartas.
     */
    private void abrirTelaLoja() {
        AudioPlayer.tocarMusica("xDeviruchi - Take some rest and eat some food!.wav");
        getContentPane().removeAll();
        add(new TelaLoja(jogador, this::finalizarLoja));
        revalidate();
        repaint();
    }

    /**
     * Restaura a exploração após sair da loja.
     */
    private void finalizarLoja() {
        AudioPlayer.tocarMusica("xDeviruchi - Mysterious Dungeon.wav");
        getContentPane().removeAll();
        add(painelPrincipal);
        revalidate();
        repaint();
        painelExploracao.voltarAoCorredorMaisProximo();
        atualizarBotoes();
        SwingUtilities.invokeLater(() -> painelExploracao.requestFocusInWindow());
    }
}
