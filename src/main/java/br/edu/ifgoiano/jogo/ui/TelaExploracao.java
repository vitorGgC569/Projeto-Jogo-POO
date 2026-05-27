package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.entidades.Masmorra;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal de exploração da masmorra em primeira pessoa.
 * Exibe a visão do jogador via {@link PainelExploracao}, a bússola,
 * o minimapa e os botões de movimento e ação.
 * Inicia em tela cheia sem decoração de janela.
 */
public class TelaExploracao extends JFrame {

    private PainelExploracao painelExploracao;

    /**
     * Cria e exibe a tela de exploração em tela cheia.
     */
    public TelaExploracao() {
        setTitle("Dungeon Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        criarInterface();
        setVisible(true);
    }

    private void criarInterface() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(Color.BLACK);

        // Visão POV 3D — ocupa toda a área central
        painelExploracao = new PainelExploracao(new Masmorra());
        painelPrincipal.add(painelExploracao, BorderLayout.CENTER);

        // Controles na parte inferior
        painelPrincipal.add(criarPainelControles(), BorderLayout.SOUTH);

        add(painelPrincipal);

        painelExploracao.setOnCombate(this::abrirTelaCombate);
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

    // =====================================
    // BOTÕES DE MOVIMENTO (dungeon crawler 1ª pessoa)
    //
    //        [ ↑ Avançar ]
    //  [← Virar]   [→ Virar]
    //        [ ↓ Recuar  ]
    // =====================================
    private JPanel criarPainelMovimento() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // ↑ Avançar (centro, linha 0)
        gbc.gridx = 1; gbc.gridy = 0;
        JButton btnAvancar = criarBotaoMovimento("↑  Avançar");
        btnAvancar.addActionListener(e -> painelExploracao.avancar());
        painel.add(btnAvancar, gbc);

        // ← Virar Esquerda (esquerda, linha 1)
        gbc.gridx = 0; gbc.gridy = 1;
        JButton btnVirarEsq = criarBotaoMovimento("↺  Virar");
        btnVirarEsq.addActionListener(e -> painelExploracao.virarEsquerda());
        painel.add(btnVirarEsq, gbc);

        // ↓ Recuar (centro, linha 1)
        gbc.gridx = 1; gbc.gridy = 1;
        JButton btnRecuar = criarBotaoMovimento("↓  Recuar");
        btnRecuar.addActionListener(e -> painelExploracao.recuar());
        painel.add(btnRecuar, gbc);

        // → Virar Direita (direita, linha 1)
        gbc.gridx = 2; gbc.gridy = 1;
        JButton btnVirarDir = criarBotaoMovimento("↻  Virar");
        btnVirarDir.addActionListener(e -> painelExploracao.virarDireita());
        painel.add(btnVirarDir, gbc);

        return painel;
    }

    // =====================================
    // BOTÕES DE AÇÃO
    // =====================================
    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JButton btnSair = criarBotaoAcao("SAIR");
        btnSair.addActionListener(e -> System.exit(0));

        painel.add(btnSair);

        return painel;
    }

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

    /**
     * Substitui o conteúdo da janela pelo painel de combate.
     * Chamado automaticamente quando o jogador entra numa sala de inimigo.
     */
    private void abrirTelaCombate() {
        getContentPane().removeAll();
        add(new TelaCombate());
        revalidate();
        repaint();
    }
}
