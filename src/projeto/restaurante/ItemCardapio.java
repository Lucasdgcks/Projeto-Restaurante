
package projeto.restaurante;


public class ItemCardapio {
    private int id;
    private String nome;
    private double preco;
    private String categoria;

    public ItemCardapio(int id, String nome, double preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + categoria + "): R$ " + preco;
    }
}
