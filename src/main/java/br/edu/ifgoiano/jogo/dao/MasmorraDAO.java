package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Masmorra;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Masmorra.
 */
public class MasmorraDAO implements GenericDAO<Masmorra> {
    @Override
    public void salvar(Masmorra entidade) {

    }

    @Override
    public void atualizar(Masmorra entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Masmorra> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Masmorra> listarTodos() {
        return List.of();
    }
}
