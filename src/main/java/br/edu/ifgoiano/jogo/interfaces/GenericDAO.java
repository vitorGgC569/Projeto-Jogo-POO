package br.edu.ifgoiano.jogo.interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Interface generica que define operacoes padrao de persistencia (CRUD).
 */
public interface GenericDAO<T> {
    void salvar(T entidade);
    void atualizar(T entidade);
    void deletar(int id);
    Optional<T> buscarPorId(int id);
    List<T> listarTodos();
}
