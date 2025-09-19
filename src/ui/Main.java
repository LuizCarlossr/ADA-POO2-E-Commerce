package ui;

import controller.*;
import model.Cliente;
import model.Produto;
import repository.*;
import service.*;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        ClienteRepository clienteRepo = new ClienteRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        ClienteService clienteService = new ClienteService(clienteRepo);
        ProdutoService produtoService = new ProdutoService(produtoRepo);
        PedidoService pedidoService = new PedidoService(pedidoRepo, new NotificacaoService());

        ClienteController clienteController = new ClienteController(clienteService);
        ProdutoController produtoController = new ProdutoController(produtoService);
        PedidoController pedidoController = new PedidoController(pedidoService, clienteService, produtoService);

        Scanner scanner = new Scanner(System.in);
        MenuPedido menuPedido = new MenuPedido(pedidoController, clienteController, produtoController, scanner);

        int opcao = -1;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("5 - Menu Pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Documento: ");
                        String documento = scanner.nextLine();
                        clienteController.cadastrar(nome, email, documento);
                        System.out.println("Cliente cadastrado com sucesso!");
                    }
                    case 2 -> {
                        List<Cliente> clientes = clienteController.listar();
                        if (clientes.isEmpty()) {
                            System.out.println("Nenhum cliente cadastrado.");
                        } else {
                            System.out.println("\n--- Lista de Clientes ---");
                            clientes.forEach(c -> System.out.println(c.getId() + " - " + c.getNome() + " | " + c.getEmail() + " | " + c.getDocumento()));
                        }
                    }
                    case 3 -> {
                        System.out.print("Nome do Produto: ");
                        String nomeProduto = scanner.nextLine();
                        System.out.print("Preço do Produto: ");
                        double precoProduto = Double.parseDouble(scanner.nextLine());
                        produtoController.cadastrar(nomeProduto, precoProduto);
                        System.out.println("Produto cadastrado com sucesso!");
                    }
                    case 4 -> {
                        List<Produto> produtos = produtoController.listar();
                        if (produtos.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado.");
                        } else {
                            System.out.println("\n--- Lista de Produtos ---");
                            produtos.forEach(p -> System.out.println(p.getId() + " - " + p.getNome() + " | R$ " + p.getPreco()));
                        }
                    }
                    case 5 -> menuPedido.exibir();
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        scanner.close();
    }
}