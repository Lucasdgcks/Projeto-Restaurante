
package projeto.restaurante;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private List<ItemCardapio> itens;

    public Cardapio() {
        this.itens = new ArrayList<>();
    }

   public void carregarDeCSV(String caminho) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double preco = Double.parseDouble(dados[2]);
                String categoria = dados[3];
                itens.add(new ItemCardapio(id, nome, preco, categoria));
            }
        }
    }

    
    public List<ItemCardapio> getItens() {
        return itens;
    }

   
    public ItemCardapio buscarItemPorId(int id) {
        for (ItemCardapio item : itens) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Retorna null se o item não for encontrado
    }
}

