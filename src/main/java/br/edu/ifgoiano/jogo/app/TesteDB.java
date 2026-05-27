package br.edu.ifgoiano.jogo.app;

import br.edu.ifgoiano.jogo.config.DatabaseInitializer;
import br.edu.ifgoiano.jogo.dao.CartaDAO;
import br.edu.ifgoiano.jogo.dao.ItemDAO;
import br.edu.ifgoiano.jogo.entidades.Carta;
import br.edu.ifgoiano.jogo.entidades.Item;

import java.util.List;

/**
 * Classe de teste rapido do banco SQLite — executa o initializer e
 * imprime todas as cartas e itens. Util durante o desenvolvimento.
 */
public class TesteDB {
    public static void main(String[] args) {
        DatabaseInitializer.inicializar();

        System.out.println("==== CARTAS ====");
        List<Carta> cartas = new CartaDAO().listarTodos();
        for (Carta c : cartas) {
            System.out.printf("  #%d %-22s %s | custo=%d preco=%d efeitos=%d%n",
                    c.getId(), c.getNome(), c.getTipo(), c.getCusto(),
                    c.getValorVenda(), c.getEfeitos().size());
        }

        System.out.println("\n==== ITENS ====");
        List<Item> itens = new ItemDAO().listarTodos();
        for (Item i : itens) {
            System.out.printf("  #%d %-18s preco=%d vida+%d ataque+%d defesa+%d%n",
                    i.getId(), i.getNome(), i.getPreco(),
                    i.getBonusVida(), i.getBonusAtaque(), i.getBonusDefesa());
        }

        System.out.println("\nOK: " + cartas.size() + " cartas e " + itens.size() + " itens persistidos.");
    }
}
