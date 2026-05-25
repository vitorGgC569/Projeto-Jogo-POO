package br.edu.ifgoiano.jogo.ui;

import br.edu.ifgoiano.jogo.entidades.Masmorra;
import br.edu.ifgoiano.jogo.entidades.Regiao;

import javax.swing.*;
import java.awt.*;

/**
 * Painel responsável por renderizar e atualizar a tela em que o jogador está
 */
public class PainelExploracao extends JPanel {

    private Masmorra masmorra;

    // Armazenando a posição inicial do Jogador, pode ser alterado.
    private int playerLinha = 7;
    private int playerColuna = 2;

    public PainelExploracao(Masmorra masmorra) {
        this.masmorra = masmorra;
        setBackground(Color.BLACK); // Fundo escuro
    }

    @Override
    protected void paintComponent(Graphics g) {

        //Posição do Jogador
        Regiao regiaoAtual = masmorra.getRegiao(playerLinha, playerColuna);
        super.paintComponent(g);

        if (regiaoAtual != null && regiaoAtual.getImage() != null) {

            int larguraDoPainel = this.getWidth();
            int alturaDoPainel = this.getHeight();
            g.drawImage(regiaoAtual.getImage(), 0, 0, larguraDoPainel, alturaDoPainel, this);

            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.YELLOW); // Amarelo costuma dar bom contraste em imagens escuras

            // Monta as strings com as informações
            String textoPosicao = "Coordenadas: [" + playerLinha + ", " + playerColuna + "]";
            String textoTipoSala = "Local: " + regiaoAtual.getTipoRegiao();

            // Desenha o texto na tela. Os números são as posições X e Y.
            g.drawString(textoPosicao, 20, 40);
            g.drawString(textoTipoSala, 20, 70);

        }
    }

    /**
     * Método para atualizar a posição do jogador e forçar a troca da imagem na tela.
     * Use isso quando adicionar os Listeners do teclado futuramente.
     */
    public void atualizarPosicao(int novaLinha, int novaColuna) {
        if (novaLinha >= 0 && novaLinha < 9 && novaColuna >= 0 && novaColuna < 5) {
            this.playerLinha = novaLinha;
            this.playerColuna = novaColuna;
            repaint();
        }
    }

    public void mover(int deltaLinha, int deltaColuna) {
        int novaLinha = this.playerLinha + deltaLinha;
        int novaColuna = this.playerColuna + deltaColuna;

        // Verifica se a nova posição está dentro do limite do mapa
        if (novaLinha >= 0 && novaLinha < 9 && novaColuna >= 0 && novaColuna < 5) {
            this.playerLinha = novaLinha;
            this.playerColuna = novaColuna;
            // desenha a tela da nova sala
            repaint();
        }
    }
}