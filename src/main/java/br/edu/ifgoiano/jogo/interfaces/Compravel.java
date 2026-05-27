package br.edu.ifgoiano.jogo.interfaces;

/**
 * Contrato para objetos que possuem um custo em moedas e podem ser adquiridos em lojas.
 * Qualquer item ou carta que apareça à venda deve implementar esta interface
 * para que o sistema de loja saiba qual preço exibir e cobrar.
 */
public interface Compravel {

    /**
     * Retorna o preço do objeto em moedas.
     *
     * @return quantidade de moedas necessária para comprar o objeto
     */
    int getPreco();
}
