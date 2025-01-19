
package projeto.restaurante;

import java.io.*;
import java.util.List;

public class Relatorio {
    public static void gerarRelatorio(String caminho, List<Pedido> pedidos) throws IOException {
        double faturamentoTotal = 0.0;
        int totalPedidos = pedidos.size();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write("Relatório do Dia\n");
            writer.write("================\n");

            for (Pedido pedido : pedidos) {
                writer.write(pedido.toString());
                writer.newLine();
                faturamentoTotal += pedido.getTotal();
            }

            writer.write("\nResumo do Dia\n");
            writer.write("Pedidos realizados: " + totalPedidos + "\n");
            writer.write("Faturamento total: R$ " + faturamentoTotal + "\n");
        }
    }
}
