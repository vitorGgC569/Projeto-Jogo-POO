package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.Carta;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia da entidade Carta.
 */
public class CartaDAO implements GenericDAO<Carta> {
    @Override
    public void salvar(Carta entidade) {

    }

    @Override
    public void atualizar(Carta entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<Carta> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Carta> listarTodos() {
        return List.of();
    }
}
