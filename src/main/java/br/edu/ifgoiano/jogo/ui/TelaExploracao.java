package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

public class TelaExploracao extends JFrame {

    private JPanel painelPrincipal;

    public TelaExploracao() {

        setTitle("Dungeon Crawler");

        setSize(1600, 900);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        criarInterface();

        setVisible(true);
    }

    private void criarInterface() {

        painelPrincipal = new JPanel(
                new BorderLayout()
        );

        painelPrincipal.setBackground(
                Color.BLACK
        );

        JLabel titulo = new JLabel(
                "DUNGEON CRAWLER",
                SwingConstants.CENTER
        );

        titulo.setForeground(
                new Color(180, 0, 0)
        );

        titulo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        52
                )
        );

        titulo.setBorder(
                BorderFactory.createEmptyBorder(
                        80,
                        10,
                        50,
                        10
                )
        );

        painelPrincipal.add(
                titulo,
                BorderLayout.NORTH
        );


        JPanel painelCentro = new JPanel();

        painelCentro.setOpaque(false);

        painelCentro.setLayout(
                new BoxLayout(
                        painelCentro,
                        BoxLayout.Y_AXIS
                )
        );

        JButton btnCombate = criarBotao(
                "ENTRAR EM COMBATE"
        );

        btnCombate.addActionListener(e -> {

            abrirTelaCombate();
        });

        JButton btnLoja = criarBotao(
                "VISITAR LOJA"
        );

        btnLoja.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Loja ainda não implementada."
            );
        });


        JButton btnSair = criarBotao(
                "SAIR"
        );

        btnSair.addActionListener(e -> {

            System.exit(0);
        });

        painelCentro.add(btnCombate);

        painelCentro.add(
                Box.createVerticalStrut(30)
        );

        painelCentro.add(btnLoja);

        painelCentro.add(
                Box.createVerticalStrut(30)
        );

        painelCentro.add(btnSair);

        painelPrincipal.add(
                painelCentro,
                BorderLayout.CENTER
        );

        add(painelPrincipal);
    }

    private JButton criarBotao(
            String texto
    ) {

        JButton botao = new JButton(texto);

        botao.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botao.setPreferredSize(
                new Dimension(350, 80)
        );

        botao.setMaximumSize(
                new Dimension(350, 80)
        );

        botao.setBackground(
                new Color(40, 40, 40)
        );

        botao.setForeground(
                new Color(220, 20, 20)
        );

        botao.setFocusPainted(false);

        botao.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        return botao;
    }

    private void abrirTelaCombate() {

        // Remove conteúdo antigo
        getContentPane().removeAll();

        // Adiciona tela combate
        add(new TelaCombate());

        // Atualiza janela
        revalidate();

        repaint();
    }
}