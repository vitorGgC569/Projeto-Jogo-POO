package br.edu.ifgoiano.jogo.exception;

/**
 * Excecao lancada para erros ocorridos na camada de banco de dados.
 */
public class DatabaseException extends RuntimeException {

    public DatabaseException(String mensagem) {
        super(mensagem);
    }

    public DatabaseException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
