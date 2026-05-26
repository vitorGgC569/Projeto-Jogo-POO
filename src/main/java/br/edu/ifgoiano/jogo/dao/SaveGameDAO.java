package br.edu.ifgoiano.jogo.dao;

import br.edu.ifgoiano.jogo.interfaces.GenericDAO;
import br.edu.ifgoiano.jogo.entidades.SaveGame;

import java.util.List;
import java.util.Optional;

/**
 * Responsavel pelas operacoes de persistencia do SaveGame.
 */
public class SaveGameDAO implements GenericDAO<SaveGame> {
    @Override
    public void salvar(SaveGame entidade) {

    }

    @Override
    public void atualizar(SaveGame entidade) {

    }

    @Override
    public void deletar(int id) {

    }

    @Override
    public Optional<SaveGame> buscarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<SaveGame> listarTodos() {
        return List.of();
    }
}
