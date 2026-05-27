package br.edu.ifgoiano.jogo.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Contrato genérico que define as operações básicas de persistência (CRUD) para qualquer entidade.
 * Classes que precisam salvar, buscar, atualizar ou deletar dados devem implementar
 * esta interface, garantindo um padrão uniforme de acesso a dados no jogo.
 *
 * @param <T> tipo da entidade gerenciada por este DAO
 */
public interface GenericDAO<T> {

    /**
     * Persiste uma nova entidade.
     *
     * @param entidade objeto a ser salvo
     */
    void salvar(T entidade);

    /**
     * Atualiza os dados de uma entidade já existente.
     *
     * @param entidade objeto com os dados atualizados
     */
    void atualizar(T entidade);

    /**
     * Remove uma entidade pelo seu identificador.
     *
     * @param id identificador único da entidade a ser removida
     */
    void deletar(int id);

    /**
     * Busca uma entidade pelo seu identificador.
     *
     * @param id identificador único da entidade
     * @return {@link Optional} contendo a entidade se encontrada, ou vazio caso contrário
     */
    Optional<T> buscarPorId(int id);

    /**
     * Retorna todas as entidades armazenadas.
     *
     * @return lista com todas as entidades; lista vazia se não houver nenhuma
     */
    List<T> listarTodos();
}
