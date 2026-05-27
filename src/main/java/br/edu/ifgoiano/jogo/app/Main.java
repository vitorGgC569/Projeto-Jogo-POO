package br.edu.ifgoiano.jogo.app;

import br.edu.ifgoiano.jogo.config.DatabaseInitializer;
import br.edu.ifgoiano.jogo.ui.TelaPrincipal;

import javax.swing.*;

/**
 * Ponto de entrada (main) que inicializa o aplicativo do jogo.
 * Antes de abrir a tela principal, garante que o banco de dados SQLite
 * esteja criado e populado com os dados iniciais (schema + seed).
 */
public class Main {

    public static void main(String[] args) {
        DatabaseInitializer.inicializar();

        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
