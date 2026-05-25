package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.entidades.Masmorra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Interface grafica da masmorra, exibindo as salas disponiveis para navegacao.
 */
public class TelaExploracao extends JFrame {

    private Masmorra masmorra = new Masmorra();

    public TelaExploracao() {
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanhoTela.width, tamanhoTela.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Exploração da Masmorra");

        PainelExploracao painelExploracao = new PainelExploracao(this.masmorra);
        add(painelExploracao);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        painelExploracao.mover(-1, 0); // Sobe
                        break;
                    case KeyEvent.VK_S:
                        painelExploracao.mover(1, 0);  // Desce
                        break;
                    case KeyEvent.VK_A:
                        painelExploracao.mover(0, -1); // Esquerda
                        break;
                    case KeyEvent.VK_D:
                        painelExploracao.mover(0, 1);  // Direita
                        break;
                }
            }
        });

        setVisible(true);
    }
}