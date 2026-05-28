package br.edu.ifgoiano.jogo.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Utilitário para reprodução de música de fundo em loop.
 * Usa javax.sound.sampled para carregar e tocar arquivos .wav
 * da pasta src/musica/. Apenas uma faixa toca por vez.
 */
public final class AudioPlayer {

    private static Clip clipAtual = null;

    /** Construtor privado — classe utilitária. */
    private AudioPlayer() {}

    /**
     * Toca a faixa de música informada em loop contínuo.
     * Para qualquer faixa que estiver tocando antes de iniciar a nova.
     *
     * @param nomeArquivo nome do arquivo .wav dentro de src/musica/ (com extensão)
     */
    public static synchronized void tocarMusica(String nomeArquivo) {
        pararMusica();

        try {
            File arquivo = new File("src/musica/" + nomeArquivo);
            if (!arquivo.exists()) return;

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);
            clipAtual = AudioSystem.getClip();
            clipAtual.open(audioStream);
            clipAtual.loop(Clip.LOOP_CONTINUOUSLY);
            clipAtual.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Falha silenciosa — música é opcional
            clipAtual = null;
        }
    }

    /**
     * Para e libera a faixa de música em reprodução, se houver.
     */
    public static synchronized void pararMusica() {
        if (clipAtual != null) {
            if (clipAtual.isRunning()) clipAtual.stop();
            clipAtual.close();
            clipAtual = null;
        }
    }
}
