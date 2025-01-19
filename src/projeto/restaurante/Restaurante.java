package projeto.restaurante; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurante extends RestauranteAbstrato {
    private Cardapio cardapio;
    private List<Pedido> pedidos;

    public Restaurante() {
        this.cardapio = new Cardapio();
        this.pedidos = new ArrayList<>();
    }

    @Override
    public void abrir() {
        this.aberto = true;
        System.out.println("Restaurante aberto!");
    }

    @Override
    public void fechar() {
        this.aberto = false;
        System.out.println("Restaurante fechado!");
        try {
            Relatorio.gerarRelatorio("src/resources/relatorio.txt", pedidos);
        } catch (IOException ex) {
            Logger.getLogger(Restaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Relatorio gerado com sucesso!");
    }

    public void processarPedido(Pedido pedido) {
        if (aberto) {
            pedidos.add(pedido);
            System.out.println("Pedido processado: \n" + pedido);
        } else {
            System.out.println("O restaurante está fechado. Não é possível realizar pedidos.");
        }
    }

    public void iniciar() throws IOException {
        cardapio.carregarDeCSV("src/resources/cardapio.csv");
        abrir();

        // Criando pedidos automaticamente
        Pedido pedido1 = new Pedido();
        pedido1.adicionarItem(cardapio.buscarItemPorId(1));
        pedido1.adicionarItem(cardapio.buscarItemPorId(2));
        processarPedido(pedido1);

        Pedido pedido2 = new Pedido();
        pedido2.adicionarItem(cardapio.buscarItemPorId(3));
        processarPedido(pedido2);
        
        Pedido pedido3 = new Pedido();
        pedido3.adicionarItem(cardapio.buscarItemPorId(6));
        pedido3.adicionarItem(cardapio.buscarItemPorId(10));
        pedido3.adicionarItem(cardapio.buscarItemPorId(8));
        pedido3.adicionarItem(cardapio.buscarItemPorId(3));
        processarPedido(pedido3);

        fechar();
    }

    public static void main(String[] args) {
        try {
            Restaurante restaurante = new Restaurante();
            restaurante.iniciar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar o restaurante: " + e.getMessage());
        }
    }
}