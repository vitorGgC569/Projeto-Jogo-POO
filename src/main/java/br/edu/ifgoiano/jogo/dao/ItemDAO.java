package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Item;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Item.
 */
public class ItemDAO implements GenericDAO<Item> {
    @Override
    public void salvar(Item entidade) {

    }

    @Override
    public void atualizar(Item entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Item> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Item> listarTodos() {
        return List.of();
    }
}
