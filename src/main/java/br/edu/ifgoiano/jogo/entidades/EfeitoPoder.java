package br.edu.ifgoiano.jogo.entidades;

import br.edu.ifgoiano.jogo.core.ContextoCombate;
import br.edu.ifgoiano.jogo.interfaces.Danificavel;
import br.edu.ifgoiano.jogo.interfaces.Efeito;

/**
 * Efeito responsavel por modificar temporaria ou permanentemente o poder de combate.
 */
public class EfeitoPoder extends EfeitoBase implements Efeito {
    private int intensidade;
    private String habilidade;

    public EfeitoPoder() {}

    public EfeitoPoder(int intensidade) {
        this.intensidade = intensidade;
    }

    @Override
    public void aplicar(ContextoCombate contexto, Danificavel alvo) {
        if (contexto != null && contexto.getJogador() != null) {
            contexto.getJogador().setAtaque(
                contexto.getJogador().getAtaque() + this.intensidade
            );
        }
    }

    public int getIntensidade() { return intensidade; }
    public void setIntensidade(int intensidade) { this.intensidade = intensidade; }
    public String getHabilidade() { return habilidade; }
    public void setHabilidade(String habilidade) { this.habilidade = habilidade; }
}
