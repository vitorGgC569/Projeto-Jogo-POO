package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private CardLayout cardLayout;

    private JPanel painelPrincipal;

    public TelaPrincipal() {

        setTitle("Dungeon Crawler");

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        cardLayout = new CardLayout();

        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(criarMenu(), "MENU");

        painelPrincipal.add(
                new TelaLoja(
                        cardLayout,
                        painelPrincipal
                ),
                "LOJA"
        );

        add(painelPrincipal);

        cardLayout.show(painelPrincipal, "MENU");

        setVisible(true);
    }

    private JPanel criarMenu() {

        JPanel painel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                GradientPaint gradient =
                        new GradientPaint(
                                0,
                                0,
                                new Color(15, 15, 15),
                                0,
                                getHeight(),
                                new Color(40, 0, 0)
                        );

                g2.setPaint(gradient);

                g2.fillRect(
                        0,
                        0,
                        getWidth(),
                        getHeight()
                );
            }
        };

        painel.setLayout(
                new BoxLayout(
                        painel,
                        BoxLayout.Y_AXIS
                )
        );

        painel.add(Box.createVerticalStrut(80));

        JLabel titulo = new JLabel(
                "DUNGEON CRAWLER"
        );

        titulo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        52
                )
        );

        titulo.setForeground(
                new Color(220, 20, 20)
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        painel.add(titulo);

        painel.add(Box.createVerticalStrut(100));

        JButton btnNovoJogo =
                criarBotao("Novo Jogo");

        btnNovoJogo.addActionListener(e -> {

            cardLayout.show(
                    painelPrincipal,
                    "LOJA"
            );
        });

        JButton btnContinuar =
                criarBotao("Continuar");

        JButton btnSair =
                criarBotao("Sair");

        btnSair.addActionListener(e -> {

            System.exit(0);
        });

        painel.add(btnNovoJogo);

        painel.add(Box.createVerticalStrut(25));

        painel.add(btnContinuar);

        painel.add(Box.createVerticalStrut(25));

        painel.add(btnSair);

        return painel;
    }

    private JButton criarBotao(String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        botao.setMaximumSize(
                new Dimension(320, 55)
        );

        botao.setFocusPainted(false);

        botao.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        botao.setBackground(
                new Color(20, 20, 20)
        );

        botao.setForeground(
                new Color(220, 20, 20)
        );

        botao.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        2
                )
        );

        botao.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        return botao;
    }
}