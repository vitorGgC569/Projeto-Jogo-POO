package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

public class TelaLoja extends JPanel {

    //moedas, declaradas como int, arrumar depois
    private int moedas = 500;

    private JLabel lblMoedas;

    public TelaLoja() {

        setLayout(new BorderLayout());

        setBackground(new Color(20, 20, 20));


        JPanel painelTopo = new JPanel(
                new BorderLayout()
        );

        painelTopo.setOpaque(false);

        //titulo
        JLabel titulo = new JLabel(
                "um forastero re4",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font("Serif", Font.BOLD, 42)
        );

        titulo.setForeground(
                new Color(220, 20, 20)
        );

        titulo.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        10,
                        10,
                        10
                )
        );

        //visual moedas
        lblMoedas = new JLabel(
                "Moedas: " + moedas
        );

        lblMoedas.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        lblMoedas.setForeground(
                new Color(255, 215, 0)
        );

        lblMoedas.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        10,
                        20
                )
        );

        painelTopo.add(
                titulo,
                BorderLayout.CENTER
        );

        painelTopo.add(
                lblMoedas,
                BorderLayout.EAST
        );

        add(painelTopo, BorderLayout.NORTH);

        // vai mostrar as cartas
        JPanel painelCartas = new JPanel();

        painelCartas.setOpaque(false);

        painelCartas.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER,
                        40,
                        50
                )
        );

        painelCartas.add(
                criarCarta(
                        "Carta Flamejante",
                        100
                )
        );

        painelCartas.add(
                criarCarta(
                        "Carta Sombria",
                        150
                )
        );

        painelCartas.add(
                criarCarta(
                        "Carta Lendária",
                        250
                )
        );

        add(painelCartas, BorderLayout.CENTER);

        //sair
        JButton btnSair = new JButton("Sair");

        btnSair.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        btnSair.setBackground(
                new Color(30, 30, 30)
        );

        btnSair.setForeground(
                new Color(220, 20, 20)
        );

        btnSair.setFocusPainted(false);

        btnSair.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        2
                )
        );

        btnSair.setPreferredSize(
                new Dimension(220, 50)
        );

        btnSair.addActionListener(e ->
                System.exit(0)
        );

        JPanel painelSul = new JPanel();

        painelSul.setOpaque(false);

        painelSul.add(btnSair);

        add(painelSul, BorderLayout.SOUTH);
    }

    // cartas
    private JButton criarCarta(
            String nome,
            int preco
    ) {

        JButton carta = new JButton();

        carta.setPreferredSize(
                new Dimension(200, 320)
        );

        carta.setLayout(
                new BorderLayout()
        );

        carta.setBackground(
                new Color(45, 45, 45)
        );

        carta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        carta.setFocusPainted(false);

        // place holder das cartas que vão aparecer na loja
        JLabel imagem = new JLabel(
                "IMAGEM",
                SwingConstants.CENTER
        );

        imagem.setPreferredSize(
                new Dimension(180, 220)
        );

        imagem.setForeground(
                new Color(220, 20, 20)
        );

        imagem.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        imagem.setBorder(
                BorderFactory.createLineBorder(
                        new Color(80, 80, 80),
                        2
                )
        );

        // nome das cartas
        JLabel lblNome = new JLabel(
                nome,
                SwingConstants.CENTER
        );

        lblNome.setForeground(Color.WHITE);

        lblNome.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        //preço das cartas
        JLabel lblPreco = new JLabel(
                preco + " moedas",
                SwingConstants.CENTER
        );

        lblPreco.setForeground(
                new Color(255, 215, 0)
        );

        lblPreco.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        //info
        JPanel painelInfo = new JPanel();

        painelInfo.setLayout(
                new GridLayout(2, 1)
        );

        painelInfo.setOpaque(false);

        painelInfo.add(lblNome);

        painelInfo.add(lblPreco);

        carta.add(imagem, BorderLayout.CENTER);

        carta.add(painelInfo, BorderLayout.SOUTH);

        //botão compra
        carta.addActionListener(e -> {

            // Verifica moedas
            if (moedas >= preco) {

                moedas -= preco;

                atualizarMoedas();

                JOptionPane.showMessageDialog(
                        this,
                        nome + " comprada!"
                );

                // após comprar, a carta desaparece
                carta.setVisible(false);

                repaint();
                revalidate();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Moedas insuficientes!"
                );
            }
        });

        return carta;
    }

    // atualizar preço das moedas
    private void atualizarMoedas() {

        lblMoedas.setText(
                "Moedas: " + moedas
        );
    }
}