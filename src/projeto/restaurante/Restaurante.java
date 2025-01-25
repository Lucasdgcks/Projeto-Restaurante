package projeto.restaurante; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Scanner;

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
        System.out.println("Restaurante aberto! Pronto para receber pedidos.");
    }

    @Override
    public void fechar() {
        this.aberto = false;
        System.out.println("Restaurante fechado! Gerando relatório do dia...");
        try {
            Relatorio.gerarRelatorio("src/resources/relatorio.txt", pedidos);
            System.out.println("Relatório gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    public void processarPedido(Pedido pedido) {
        if (aberto) {
            pedidos.add(pedido);
            System.out.println("Pedido #" + pedido.getId() + " processado com sucesso!");
        } else {
            System.out.println("O restaurante está fechado. Não é possível registrar pedidos.");
        }
    }

    public void mostrarCardapio() {
        System.out.println("=== Cardápio ===");
        for (ItemCardapio item : cardapio.getItens()) {
            System.out.println(item.getId() + " - " + item.getNome() + " (R$ " + item.getPreco() + ")");
        }
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido foi registrado ainda.");
        } else {
            System.out.println("=== Pedidos Registrados ===");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    public void iniciar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        cardapio.carregarDeCSV("src/resources/cardapio.csv");
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== Sistema do Restaurante ===");
            System.out.println("1. Abrir Restaurante");
            System.out.println("2. Registrar Pedido");
            System.out.println("3. Visualizar Pedidos");
            System.out.println("4. Fechar Restaurante");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    abrir();
                    break;
                case 2:
                    if (aberto) {
                        mostrarCardapio();
                        Pedido novoPedido = new Pedido();
                        boolean adicionando = true;

                        while (adicionando) {
                            System.out.print("Digite o ID do item para adicionar ao pedido (ou 0 para finalizar): ");
                            int idItem = scanner.nextInt();
                            if (idItem == 0) {
                                adicionando = false;
                            } else {
                                ItemCardapio item = cardapio.buscarItemPorId(idItem);
                                if (item != null) {
                                    novoPedido.adicionarItem(item);
                                    System.out.println("Item adicionado: " + item.getNome());
                                } else {
                                    System.out.println("Item não encontrado. Tente novamente.");
                                }
                            }
                        }

                        processarPedido(novoPedido);
                    } else {
                        System.out.println("O restaurante está fechado. Abra o restaurante para registrar pedidos.");
                    }
                    break;
                case 3:
                    mostrarPedidos();
                    break;
                case 4:
                    fechar();
                    break;
                case 5:
                    executando = false;
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            Restaurante restaurante = new Restaurante();
            restaurante.iniciar();
        } catch (IOException e) {
            System.err.println("Erro ao carregar o sistema: " + e.getMessage());
        }
    }
}