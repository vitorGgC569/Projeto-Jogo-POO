package br.edu.ifgoiano.jogo.util;

import java.util.Random;

/**
 * Utilitario para geracao de numeros e eventos aleatorios.
 */
public class AleatorioUtil {

    private static final Random RANDOM = new Random();

    private AleatorioUtil() {}

    /**
     * Retorna inteiro aleatorio entre min e max (inclusivos).
     *
     * @param min valor minimo
     * @param max valor maximo
     * @return inteiro aleatorio entre min e max
     */
    public static int entreInclusive(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    /**
     * Retorna true com a probabilidade dada.
     *
     * @param probabilidade valor entre 0.0 e 1.0
     * @return true se ocorre o evento, false caso contrario
     */
    public static boolean chance(double probabilidade) {
        return RANDOM.nextDouble() < probabilidade;
    }
}
