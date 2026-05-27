package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Tela do menu principal do jogo.
 * Exibe o título "DUNGEON CRAWLER", o fundo temático e os botões
 * de Novo Jogo, Continuar e Sair.
 * Inicia em tela cheia sem decoração de janela.
 */
public class TelaPrincipal extends JFrame {

    private Image imagemFundo;

    /**
     * Cria e exibe a tela do menu principal em tela cheia.
     */
    public TelaPrincipal() {

        configurarJanela();
        carregarImagem();
        criarInterface();
    }


    private void configurarJanela() {

        setTitle("Dungeon Crawler");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         setExtendedState(JFrame.MAXIMIZED_BOTH);

        setUndecorated(true);
    }


    private void carregarImagem() {


        imagemFundo = null;
    }


    private void criarInterface() {

        JPanel painelFundo = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                // Se existir imagem
                if (imagemFundo != null) {

                    g.drawImage(
                            imagemFundo,
                            0,
                            0,
                            getWidth(),
                            getHeight(),
                            this
                    );

                } else {

                    // Fundo placeholder
                    Graphics2D g2 = (Graphics2D) g;

                    GradientPaint gradient = new GradientPaint(
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

                    // Texto placeholder
                    g2.setColor(new Color(220, 20, 20));

                    g2.setFont(
                            new Font(
                                    "Serif",
                                    Font.BOLD,
                                    40
                            )
                    );

                    String texto = "BACKGROUND PLACEHOLDER";

                    FontMetrics fm = g2.getFontMetrics();

                    int x =
                            (getWidth() - fm.stringWidth(texto)) / 2;

                    int y = getHeight() / 2;

                    g2.drawString(texto, x, y);
                }
            }
        };

        painelFundo.setLayout(
                new BoxLayout(
                        painelFundo,
                        BoxLayout.Y_AXIS
                )
        );


        painelFundo.add(Box.createVerticalStrut(60));


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

        painelFundo.add(titulo);

        painelFundo.add(Box.createVerticalStrut(80));

        //botões de menu
        JButton btnNovoJogo =
                criarBotao("Novo Jogo");

        btnNovoJogo.addActionListener(e -> {
            dispose();
            TelaExploracao telaExploracao = new TelaExploracao();
            telaExploracao.setVisible(true);
        });

        JButton btnContinuar =
                criarBotao("Continuar");

        btnContinuar.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Sistema de save ainda nao implementado."
                )
        );

        JButton btnSair =
                criarBotao("Sair");

        btnSair.addActionListener(e ->
                System.exit(0)
        );

        painelFundo.add(btnNovoJogo);

        painelFundo.add(Box.createVerticalStrut(25));

        painelFundo.add(btnContinuar);

        painelFundo.add(Box.createVerticalStrut(25));

        painelFundo.add(btnSair);

        add(painelFundo);
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

        // fundo com o efeito de fade
        botao.setBackground(
                new Color(20, 20, 20)
        );

        // Vermelho sangue
        botao.setForeground(
                new Color(220, 20, 20)
        );

        // borda
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
