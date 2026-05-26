package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por modificar temporaria ou permanentemente o poder de combate.
 */
public class EfeitoPoder extends EfeitoBase implements Efeito {
    private String habilidade;
    private int intensidade;

    public EfeitoPoder(int intensidade, String habilidade,int dano, String nome, String descricao, int valor, int duracao, boolean permanente) {
        super(dano, nome, descricao, valor, duracao, permanente);
        this.intensidade = intensidade;
        this.habilidade = habilidade;
    }
    public String getHabilidade() {
        return habilidade;
    }
    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }
    public int getIntensidade() {
        return intensidade;
    }
    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }
}
