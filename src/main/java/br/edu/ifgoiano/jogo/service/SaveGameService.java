package br.edu.ifgoiano.jogo.service;

import br.edu.ifgoiano.jogo.entidades.SaveGame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Controla os processos de persistencia, exclusao e carregamento de arquivos de jogo salvo.
 */
public class SaveGameService {

    private final List<SaveGame> saves = new ArrayList<>();

    /** Cria um novo save com a data atual e o adiciona a lista de saves. */
    public SaveGame salvar(SaveGame save) {
        String dataAtual = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        save.setDataCriacao(dataAtual);
        saves.add(save);
        return save;
    }

    public List<SaveGame> listarSaves() {
        return new ArrayList<>(saves);
    }

    public void deletar(String nomeSave) {
        saves.removeIf(s -> s.getNomeSave().equals(nomeSave));
    }

    public SaveGame buscarPorNome(String nomeSave) {
        return saves.stream()
                .filter(s -> s.getNomeSave().equals(nomeSave))
                .findFirst()
                .orElse(null);
    }
}
