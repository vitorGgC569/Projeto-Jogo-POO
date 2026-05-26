package br.edu.ifgoiano.jogo.core;

/**
 * Controla os estados ativos do jogo, como menu, combate ou exploracao.
 */
public class EstadoJogo {

    public enum Estado {
        MENU_PRINCIPAL,
        EXPLORANDO,
        EM_COMBATE,
        NA_LOJA,
        GAME_OVER,
        VITORIA
    }

    private Estado estadoAtual;

    public EstadoJogo() {
        this.estadoAtual = Estado.MENU_PRINCIPAL;
    }

    public void mudarPara(Estado novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public boolean is(Estado estado) {
        return this.estadoAtual == estado;
    }

    public Estado getEstadoAtual() { return estadoAtual; }
    public void setEstadoAtual(Estado estadoAtual) { this.estadoAtual = estadoAtual; }
}
