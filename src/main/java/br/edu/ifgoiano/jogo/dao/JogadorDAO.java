package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Jogador;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Jogador.
 */
public class JogadorDAO implements GenericDAO<Jogador> {
    @Override
    public void salvar(Jogador entidade) {

    }

    @Override
    public void atualizar(Jogador entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Jogador> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Jogador> listarTodos() {
        return List.of();
    }
}
