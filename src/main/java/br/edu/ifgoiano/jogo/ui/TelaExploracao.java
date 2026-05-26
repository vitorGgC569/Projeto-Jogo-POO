package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

public class TelaExploracao extends JFrame {

    private CardLayout cardLayout;

    private JPanel painelPrincipal;

    public TelaExploracao() {

        setTitle("Dungeon Crawler");

        setSize(1600, 900);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        cardLayout = new CardLayout();

        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(
                criarMenu(),
                "MENU"
        );

        painelPrincipal.add(
                new TelaLoja(
                        cardLayout,
                        painelPrincipal
                ),
                "LOJA"
        );

        painelPrincipal.add(
                new TelaCombate(),
                "COMBATE"
        );

        add(painelPrincipal);

        setVisible(true);
    }

    private JPanel criarMenu() {

        JPanel painel = new JPanel(
                new BorderLayout()
        );

        painel.setBackground(
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

        painel.add(
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

            cardLayout.show(
                    painelPrincipal,
                    "COMBATE"
            );
        });

        JButton btnLoja = criarBotao(
                "VISITAR LOJA"
        );

        btnLoja.addActionListener(e -> {

            cardLayout.show(
                    painelPrincipal,
                    "LOJA"
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

        painel.add(
                painelCentro,
                BorderLayout.CENTER
        );

        return painel;
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
}