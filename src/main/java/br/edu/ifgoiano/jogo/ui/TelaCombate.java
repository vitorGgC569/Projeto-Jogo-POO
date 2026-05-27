package br.edu.ifgoiano.jogo.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaCombate extends JPanel {

    private JLabel lblVida;
    private JLabel lblEscudo;
    private JLabel lblBaralho;
    private JLabel lblDescarte;

    private JLabel lblNivel;
    private JProgressBar barraXp;

    private JLabel lblNomeInimigo;
    private JProgressBar barraVidaInimigo;

    private JPanel painelMao;

    private JButton btnFinalizarTurno;

    public TelaCombate() {

        setLayout(new BorderLayout());

        setBackground(Color.BLACK);

        criarInterface();
    }

    private void criarInterface() {

        JPanel painelTopo = new JPanel(
                new BorderLayout()
        );

        painelTopo.setOpaque(false);

        lblNomeInimigo = new JLabel(
                "CAVEIRÃO SATANICO",
                SwingConstants.CENTER
        );

        lblNomeInimigo.setForeground(
                new Color(180, 0, 0)
        );

        lblNomeInimigo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        42
                )
        );

        lblNomeInimigo.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        10,
                        10,
                        10
                )
        );

        barraVidaInimigo = new JProgressBar();

        barraVidaInimigo.setMaximum(150);

        barraVidaInimigo.setValue(120);

        barraVidaInimigo.setStringPainted(true);

        barraVidaInimigo.setString("120 / 150");

        barraVidaInimigo.setForeground(
                new Color(180, 0, 0)
        );

        barraVidaInimigo.setBackground(
                new Color(30, 30, 30)
        );

        barraVidaInimigo.setPreferredSize(
                new Dimension(700, 35)
        );

        JPanel painelBarra = new JPanel();

        painelBarra.setOpaque(false);

        painelBarra.add(barraVidaInimigo);

        painelTopo.add(
                lblNomeInimigo,
                BorderLayout.NORTH
        );

        painelTopo.add(
                painelBarra,
                BorderLayout.CENTER
        );

        add(painelTopo, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(
                new BorderLayout()
        );

        painelCentro.setOpaque(false);

        JPanel painelStatus = new JPanel();

        painelStatus.setOpaque(false);

        painelStatus.setLayout(
                new GridLayout(
                        4,
                        1,
                        10,
                        10
                )
        );

        painelStatus.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        20,
                        40,
                        20
                )
        );

        lblVida = criarPainelStatus(
                "VIDA",
                "100"
        );

        lblEscudo = criarPainelStatus(
                "ESCUDO",
                "15"
        );

        lblBaralho = criarPainelStatus(
                "PILHA",
                "12"
        );

        lblDescarte = criarPainelStatus(
                "DESCARTE",
                "3"
        );

        painelStatus.add(lblVida);

        painelStatus.add(lblEscudo);

        painelStatus.add(lblBaralho);

        painelStatus.add(lblDescarte);

        painelCentro.add(
                painelStatus,
                BorderLayout.WEST
        );

        JLabel inimigo = new JLabel(
                "CAVEIRÃO SATANICO",
                SwingConstants.CENTER
        );

        inimigo.setForeground(Color.WHITE);

        inimigo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );

        inimigo.setPreferredSize(
                new Dimension(500, 400)
        );

        inimigo.setBorder(
                BorderFactory.createLineBorder(
                        new Color(100, 0, 0),
                        4
                )
        );

        painelCentro.add(
                inimigo,
                BorderLayout.CENTER
        );

        JPanel painelDireita = new JPanel();

        painelDireita.setOpaque(false);

        painelDireita.setLayout(
                new BoxLayout(
                        painelDireita,
                        BoxLayout.Y_AXIS
                )
        );

        painelDireita.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        20,
                        40,
                        20
                )
        );

        JLabel tituloDescarte = new JLabel(
                "PILHA DE DESCARTE"
        );

        tituloDescarte.setForeground(
                new Color(180, 0, 0)
        );

        tituloDescarte.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        28
                )
        );

        tituloDescarte.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JPanel cartaDescarte = new JPanel();

        cartaDescarte.setPreferredSize(
                new Dimension(180, 250)
        );

        cartaDescarte.setMaximumSize(
                new Dimension(180, 250)
        );

        cartaDescarte.setBackground(
                new Color(25, 25, 25)
        );

        cartaDescarte.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        JLabel qtdDescarte = new JLabel(
                "3 CARTAS"
        );

        qtdDescarte.setForeground(Color.WHITE);

        qtdDescarte.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        qtdDescarte.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel tituloNivel = new JLabel(
                "NÍVEL DO JOGADOR"
        );

        tituloNivel.setForeground(
                new Color(180, 0, 0)
        );

        tituloNivel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        24
                )
        );

        tituloNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        lblNivel = new JLabel(
                "LEVEL 7"
        );

        lblNivel.setForeground(Color.WHITE);

        lblNivel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        lblNivel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        barraXp = new JProgressBar();

        barraXp.setMaximum(100);

        barraXp.setValue(65);

        barraXp.setStringPainted(true);

        barraXp.setString("XP 65 / 100");

        barraXp.setForeground(
                new Color(0, 120, 255)
        );

        barraXp.setBackground(
                new Color(25, 25, 25)
        );

        barraXp.setPreferredSize(
                new Dimension(220, 30)
        );

        barraXp.setMaximumSize(
                new Dimension(220, 30)
        );

        painelDireita.add(tituloDescarte);

        painelDireita.add(
                Box.createVerticalStrut(20)
        );

        painelDireita.add(cartaDescarte);

        painelDireita.add(
                Box.createVerticalStrut(20)
        );

        painelDireita.add(qtdDescarte);

        painelDireita.add(
                Box.createVerticalStrut(50)
        );

        painelDireita.add(tituloNivel);

        painelDireita.add(
                Box.createVerticalStrut(15)
        );

        painelDireita.add(lblNivel);

        painelDireita.add(
                Box.createVerticalStrut(20)
        );

        painelDireita.add(barraXp);

        painelCentro.add(
                painelDireita,
                BorderLayout.EAST
        );

        add(painelCentro, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(
                new BorderLayout()
        );

        painelInferior.setBackground(
                new Color(10, 10, 10)
        );

        painelInferior.setBorder(
                BorderFactory.createMatteBorder(
                        3,
                        0,
                        0,
                        0,
                        new Color(100, 0, 0)
                )
        );

        painelMao = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        20,
                        20
                )
        );

        painelMao.setOpaque(false);

        painelMao.add(
                criarCarta(
                        "GOLPE",
                        "ATAQUE"
                )
        );

        painelMao.add(
                criarCarta(
                        "DEFENDER",
                        "DEFESA"
                )
        );

        painelMao.add(
                criarCarta(
                        "RAIO",
                        "MAGIA"
                )
        );

        painelMao.add(
                criarCarta(
                        "CURA",
                        "SUPORTE"
                )
        );

        painelInferior.add(
                painelMao,
                BorderLayout.CENTER
        );

        btnFinalizarTurno = new JButton(
                "FINALIZAR TURNO"
        );

        btnFinalizarTurno.setPreferredSize(
                new Dimension(260, 70)
        );

        btnFinalizarTurno.setBackground(
                new Color(80, 0, 0)
        );

        btnFinalizarTurno.setForeground(Color.WHITE);

        btnFinalizarTurno.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        22
                )
        );

        btnFinalizarTurno.setFocusPainted(false);

        btnFinalizarTurno.setBorder(
                BorderFactory.createLineBorder(
                        new Color(180, 0, 0),
                        3
                )
        );

        JPanel painelBotao = new JPanel();

        painelBotao.setOpaque(false);

        painelBotao.add(btnFinalizarTurno);

        painelInferior.add(
                painelBotao,
                BorderLayout.EAST
        );

        add(painelInferior, BorderLayout.SOUTH);
    }

    private JLabel criarPainelStatus(
            String titulo,
            String valor
    ) {

        JLabel label = new JLabel(
                "<html><center>" +
                        "<span style='color:#AA0000; font-size:18px;'>" +
                        titulo +
                        "</span><br>" +
                        "<span style='color:white; font-size:28px;'>" +
                        valor +
                        "</span>" +
                        "</center></html>",
                SwingConstants.CENTER
        );

        label.setOpaque(true);

        label.setBackground(
                new Color(15, 15, 15)
        );

        label.setBorder(
                new LineBorder(
                        new Color(60, 60, 60),
                        2
                )
        );

        return label;
    }

    private JPanel criarCarta(
            String nome,
            String tipo
    ) {

        JPanel carta = new JPanel(
                new BorderLayout()
        );

        carta.setPreferredSize(
                new Dimension(180, 260)
        );

        carta.setBackground(
                new Color(20, 20, 20)
        );

        carta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 0, 0),
                        3
                )
        );

        JLabel imagem = new JLabel(
                "IMAGEM",
                SwingConstants.CENTER
        );

        imagem.setForeground(Color.WHITE);

        imagem.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        JPanel painelImagem = new JPanel(
                new BorderLayout()
        );

        painelImagem.setBackground(
                new Color(40, 40, 40)
        );

        painelImagem.add(imagem);

        JLabel nomeCarta = new JLabel(
                nome,
                SwingConstants.CENTER
        );

        nomeCarta.setForeground(
                new Color(220, 220, 220)
        );

        nomeCarta.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        28
                )
        );

        JLabel tipoCarta = new JLabel(
                tipo,
                SwingConstants.CENTER
        );

        tipoCarta.setForeground(
                new Color(180, 0, 0)
        );

        tipoCarta.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        carta.add(
                nomeCarta,
                BorderLayout.NORTH
        );

        carta.add(
                painelImagem,
                BorderLayout.CENTER
        );

        carta.add(
                tipoCarta,
                BorderLayout.SOUTH
        );

        return carta;
    }

    public JLabel getLblVida() {
        return lblVida;
    }

    public JLabel getLblEscudo() {
        return lblEscudo;
    }

    public JLabel getLblBaralho() {
        return lblBaralho;
    }

    public JLabel getLblDescarte() {
        return lblDescarte;
    }

    public JLabel getLblNivel() {
        return lblNivel;
    }

    public JProgressBar getBarraXp() {
        return barraXp;
    }

    public JLabel getLblNomeInimigo() {
        return lblNomeInimigo;
    }

    public JProgressBar getBarraVidaInimigo() {
        return barraVidaInimigo;
    }

    public JPanel getPainelMao() {
        return painelMao;
    }

    public JButton getBtnFinalizarTurno() {
        return btnFinalizarTurno;
    }
}