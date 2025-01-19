
package projeto.restaurante;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1; // Contador estático para IDs automáticos
    private int id;
    private List<ItemCardapio> itens;
    private double total;

    public Pedido() {
        this.id = contadorPedidos++; // Atribui e incrementa o ID automaticamente
        this.itens = new ArrayList<>();
        this.total = 0.0;
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
        total += item.getPreco();
    }

    public double getTotal() {
        return total;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append("\n");
        for (ItemCardapio item : itens) {
            sb.append("- ").append(item).append("\n");
        }
        sb.append("Total: R$ ").append(total);
        return sb.toString();
    }
}
