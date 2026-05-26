package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import java.awt.*;

public class TelaLoja extends JPanel {

    private int moedas = 100;

    private JLabel lblMoedas;

    private JPanel painelCartas;

    private CardLayout cardLayout;

    private JPanel painelPrincipal;

    public TelaLoja(
            CardLayout cardLayout,
            JPanel painelPrincipal
    ) {

        this.cardLayout = cardLayout;

        this.painelPrincipal = painelPrincipal;

        setLayout(new BorderLayout());

        setBackground(Color.BLACK);

        criarInterface();
    }

    private void criarInterface() {

        JPanel topo = new JPanel(
                new BorderLayout()
        );

        topo.setBackground(
                Color.BLACK
        );

        JLabel titulo = new JLabel(
                "LOJA",
                SwingConstants.CENTER
        );

        titulo.setForeground(
                new Color(220, 20, 20)
        );

        titulo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        48
                )
        );

        titulo.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        10,
                        30,
                        10
                )
        );

        lblMoedas = new JLabel(
                "MOEDAS: " + moedas
        );

        lblMoedas.setForeground(
                Color.WHITE
        );

        lblMoedas.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        lblMoedas.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        0,
                        20
                )
        );

        topo.add(
                titulo,
                BorderLayout.CENTER
        );

        topo.add(
                lblMoedas,
                BorderLayout.EAST
        );

        add(
                topo,
                BorderLayout.NORTH
        );

        painelCartas = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        40,
                        50
                )
        );

        painelCartas.setBackground(
                Color.BLACK
        );

        painelCartas.add(
                criarCarta(
                        "ESPADA SOMBRIA",
                        30
                )
        );

        painelCartas.add(
                criarCarta(
                        "ESCUDO REAL",
                        40
                )
        );

        painelCartas.add(
                criarCarta(
                        "MAGIA ARCANA",
                        50
                )
        );

        add(
                painelCartas,
                BorderLayout.CENTER
        );

        JPanel inferior = new JPanel();

        inferior.setBackground(
                Color.BLACK
        );

        JButton btnVoltar = new JButton(
                "VOLTAR"
        );

        btnVoltar.setPreferredSize(
                new Dimension(300, 60)
        );

        btnVoltar.setBackground(
                new Color(40, 40, 40)
        );

        btnVoltar.setForeground(
                new Color(220, 20, 20)
        );

        btnVoltar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        btnVoltar.setFocusPainted(false);

        btnVoltar.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        btnVoltar.addActionListener(e -> {

            cardLayout.show(
                    painelPrincipal,
                    "MENU"
            );
        });

        inferior.add(btnVoltar);

        add(
                inferior,
                BorderLayout.SOUTH
        );
    }

    private JPanel criarCarta(
            String nome,
            int preco
    ) {

        JPanel carta = new JPanel(
                new BorderLayout()
        );

        carta.setPreferredSize(
                new Dimension(240, 380)
        );

        carta.setBackground(
                new Color(25, 25, 25)
        );

        carta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        JLabel nomeCarta = new JLabel(
                nome,
                SwingConstants.CENTER
        );

        nomeCarta.setForeground(
                new Color(220, 20, 20)
        );

        nomeCarta.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        24
                )
        );

        nomeCarta.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        15,
                        10
                )
        );

        JPanel imagem = new JPanel();

        imagem.setPreferredSize(
                new Dimension(180, 220)
        );

        imagem.setBackground(
                new Color(50, 50, 50)
        );

        imagem.setBorder(
                BorderFactory.createLineBorder(
                        Color.DARK_GRAY,
                        2
                )
        );

        JLabel txtImagem = new JLabel(
                "IMAGEM"
        );

        txtImagem.setForeground(
                Color.WHITE
        );

        txtImagem.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        imagem.add(txtImagem);

        JPanel inferior = new JPanel();

        inferior.setBackground(
                new Color(25, 25, 25)
        );

        inferior.setLayout(
                new BoxLayout(
                        inferior,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblPreco = new JLabel(
                "PREÇO: " + preco
        );

        lblPreco.setForeground(
                Color.WHITE
        );

        lblPreco.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        lblPreco.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton btnComprar = new JButton(
                "COMPRAR"
        );

        btnComprar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        btnComprar.setBackground(
                new Color(40, 40, 40)
        );

        btnComprar.setForeground(
                new Color(220, 20, 20)
        );

        btnComprar.setFocusPainted(false);

        btnComprar.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        btnComprar.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        2
                )
        );

        btnComprar.addActionListener(e -> {

            if (moedas >= preco) {

                moedas -= preco;

                lblMoedas.setText(
                        "MOEDAS: " + moedas
                );

                painelCartas.remove(carta);

                painelCartas.revalidate();

                painelCartas.repaint();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Moedas insuficientes."
                );
            }
        });

        inferior.add(
                Box.createVerticalStrut(10)
        );

        inferior.add(lblPreco);

        inferior.add(
                Box.createVerticalStrut(20)
        );

        inferior.add(btnComprar);

        inferior.add(
                Box.createVerticalStrut(15)
        );

        carta.add(
                nomeCarta,
                BorderLayout.NORTH
        );

        carta.add(
                imagem,
                BorderLayout.CENTER
        );

        carta.add(
                inferior,
                BorderLayout.SOUTH
        );

        return carta;
    }
}