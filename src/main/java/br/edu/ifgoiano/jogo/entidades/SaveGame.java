package br.edu.ifgoiano.jogo.entidades;

/**
 * Representa os dados armazenados de uma sessao de jogo salva.
 */
public class SaveGame {
    private String nomeSave;
    private String dataCriacao;
    private int tempoJogado;
    private Jogador jogador;
    private Masmorra masmorra;
    private boolean saveAutomatico;

    public SaveGame() {}

    public SaveGame(String nomeSave,String dataCriacao,int tempoJogado,Jogador jogador, Masmorra masmorra,boolean saveAutomatico) {
        this.nomeSave = nomeSave;
        this.dataCriacao = dataCriacao;
        this.tempoJogado = tempoJogado;
        this.jogador = jogador;
        this.masmorra = masmorra;
        this.saveAutomatico = saveAutomatico;
    }
    public String getNomeSave() {
        return nomeSave;
    }
    public void setNomeSave(String nomeSave) {
        this.nomeSave = nomeSave;
    }
    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public int getTempoJogado() {
        return tempoJogado;
    }
    public void setTempoJogado(int tempoJogado) {
        this.tempoJogado = tempoJogado;
    }
    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    public Masmorra getMasmorra() {
        return masmorra;
    }
    public void setMasmorra(Masmorra masmorra) {
        this.masmorra = masmorra;
    }
    public boolean isSaveAutomatico() {
        return saveAutomatico;
    }
    public void setSaveAutomatico(boolean saveAutomatico) {
        this.saveAutomatico = saveAutomatico;
    }
}
