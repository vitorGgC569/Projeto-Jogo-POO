package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Sala;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Sala.
 */
public class SalaDAO implements GenericDAO<Sala> {
    @Override
    public void salvar(Sala entidade) {

    }

    @Override
    public void atualizar(Sala entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Sala> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Sala> listarTodos() {
        return List.of();
    }
}
