package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.entidades.Loja;
import br.edu.ifgoiano.jogo.interfaces.GenericDAO;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia dos dados da Loja.
 */
public class LojaDAO implements GenericDAO<Loja> {

    @Override
    public void salvar(Loja entidade) {
        throw new UnsupportedOperationException("Nao implementado.");
    }

    @Override
    public void atualizar(Loja entidade) {
        throw new UnsupportedOperationException("Nao implementado.");
    }

    @Override
    public void deletar(int id) {
        throw new UnsupportedOperationException("Nao implementado.");
    }

    @Override
    public Optional<Loja> buscarPorId(int id) {
        throw new UnsupportedOperationException("Nao implementado.");
    }

    @Override
    public List<Loja> listarTodos() {
        throw new UnsupportedOperationException("Nao implementado.");
    }
}
