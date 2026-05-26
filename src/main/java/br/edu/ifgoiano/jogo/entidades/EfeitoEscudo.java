package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por conceder pontos de escudo temporarios.
 */

public class EfeitoEscudo extends EfeitoBase implements Efeito {
    private int valorEscudo;
    private int turnosDuracao;

    public EfeitoEscudo(){}

    public EfeitoEscudo(int valorEscudo,int turnosDuracao,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano, nome, descricao, valor, duracao, permanente);
        this.valorEscudo = valorEscudo;
        this.turnosDuracao = turnosDuracao;
    }

    public int getValorEscudo() {
        return valorEscudo;
    }
    public void setValorEscudo(int valorEscudo) {
        this.valorEscudo = valorEscudo;
    }
    public int getTurnosDuracao() {
        return turnosDuracao;
    }
    public void setTurnosDuracao(int turnosDuracao) {
        this.turnosDuracao = turnosDuracao;
    }
}
