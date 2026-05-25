package br.edu.ifgoiano.jogo.ui;

/**
 * Interface grafica da masmorra, exibindo as salas disponiveis para navegacao.
 */
public class TelaExploracao extends JFrame {

    public TelaExploracao(){

        //Função que pega o tamanho da tela.
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        //Define o tamanho do frame para o tamanho capturado da tela.
        setSize(tamanhoTela.width, tamanhoTela.height);
        //Definindo que deve ser fechado ao clicar no "X"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centraliza
        setLocationRelativeTo(null);

        
    }
}
