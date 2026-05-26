package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Inimigo;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Inimigo.
 */
public class InimigoDAO implements GenericDAO<Inimigo> {
    @Override
    public void salvar(Inimigo entidade) {

    }

    @Override
    public void atualizar(Inimigo entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Inimigo> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Inimigo> listarTodos() {
        return List.of();
    }
}
