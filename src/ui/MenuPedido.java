package ui;

import controller.PedidoController;
import controller.ClienteController;
import controller.ProdutoController;
import model.Pedido;
import model.ItemPedido;

import java.util.List;
import java.util.Scanner;

public class MenuPedido {
    private final PedidoController pedidoController;
    private final ClienteController clienteController;
    private final ProdutoController produtoController;
    private final Scanner scanner;

    public MenuPedido(PedidoController pedidoController, ClienteController clienteController, ProdutoController produtoController, Scanner scanner) {
        this.pedidoController = pedidoController;
        this.clienteController = clienteController;
        this.produtoController = produtoController;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;

        do {
            System.out.println("\n--- MENU PEDIDOS ---");
            System.out.println("1 - Criar Pedido");
            System.out.println("2 - Adicionar Item ao Pedido");
            System.out.println("3 - Remover Item do Pedido");
            System.out.println("4 - Finalizar Pedido");
            System.out.println("5 - Pagar Pedido");
            System.out.println("6 - Entregar Pedido");
            System.out.println("7 - Listar Pedidos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            try {
                switch (opcao) {
                    case 1 -> criarPedido();
                    case 2 -> adicionarItem();
                    case 3 -> removerItem();
                    case 4 -> finalizarPedido();
                    case 5 -> pagarPedido();
                    case 6 -> entregarPedido();
                    case 7 -> listarPedidos();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void criarPedido() {
        System.out.println("\n--- Criar Pedido ---");
        System.out.println("Clientes cadastrados:");
        clienteController.listar().forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
        System.out.print("Informe o ID do cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());

        pedidoController.criarPedido(clienteId);
        System.out.println("Pedido criado com sucesso!");
    }

    private void adicionarItem() {
        System.out.println("\n--- Adicionar Item ---");
        listarPedidos();
        System.out.print("Informe o ID do pedido: ");
        int pedidoId = Integer.parseInt(scanner.nextLine());

        System.out.println("Produtos disponíveis:");
        produtoController.listar().forEach(p -> System.out.println(p.getId() + " - " + p.getNome() + " | R$ " + p.getPreco()));
        System.out.print("Informe o ID do produto: ");
        int produtoId = Integer.parseInt(scanner.nextLine());

        System.out.print("Informe a quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Informe o preço de venda: ");
        double precoVenda = Double.parseDouble(scanner.nextLine());

        pedidoController.adicionarItem(pedidoId, produtoId, quantidade, precoVenda);
        System.out.println("Item adicionado com sucesso!");
    }

    private void removerItem() {
        System.out.println("\n--- Remover Item ---");
        listarPedidos();
        System.out.print("Informe o ID do pedido: ");
        int pedidoId = Integer.parseInt(scanner.nextLine());

        Pedido pedido = pedidoController.buscar(pedidoId);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        List<ItemPedido> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("Pedido não tem itens para remover.");
            return;
        }

        System.out.println("Itens do pedido:");
        for (int i = 0; i < itens.size(); i++) {
            ItemPedido item = itens.get(i);
            System.out.println(i + " - " + item.getProduto().getNome() + " | Qtde: " + item.getQuantidade() + " | Preço: R$ " + item.getPrecoVenda());
        }

        System.out.print("Informe o índice do item a remover: ");
        int itemIndex = Integer.parseInt(scanner.nextLine());

        pedidoController.removerItem(pedidoId, itemIndex);
        System.out.println("Item removido com sucesso!");
    }

    private void finalizarPedido() {
        System.out.println("\n--- Finalizar Pedido ---");
        listarPedidos();
        System.out.print("Informe o ID do pedido: ");
        int pedidoId = Integer.parseInt(scanner.nextLine());

        pedidoController.finalizarPedido(pedidoId);
        System.out.println("Pedido finalizado!");
    }

    private void pagarPedido() {
        System.out.println("\n--- Pagar Pedido ---");
        listarPedidos();
        System.out.print("Informe o ID do pedido: ");
        int pedidoId = Integer.parseInt(scanner.nextLine());

        pedidoController.pagarPedido(pedidoId);
        System.out.println("Pedido pago com sucesso!");
    }

    private void entregarPedido() {
        System.out.println("\n--- Entregar Pedido ---");
        listarPedidos();
        System.out.print("Informe o ID do pedido: ");
        int pedidoId = Integer.parseInt(scanner.nextLine());

        pedidoController.entregarPedido(pedidoId);
        System.out.println("Pedido entregue!");
    }

    private void listarPedidos() {
        System.out.println("\n--- Lista de Pedidos ---");
        List<Pedido> pedidos = pedidoController.listar();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId() + " | Cliente: " + pedido.getCliente().getNome() + " | Status: " + pedido.getStatus() + " | Total: R$ " + pedido.calcularTotal());
        }
    }
}

