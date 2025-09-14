//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import controller.*;
import model.*;
import repository.*;
import service.*;


public class Main {
    public static void main(String[] args) {
        // Instanciando os repositórios
        ClienteRepository clienteRepo = new ClienteRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        // Instanciando serviços
        NotificacaoService notificacaoService = new NotificacaoService();
        ClienteService clienteService = new ClienteService(clienteRepo);
        ProdutoService produtoService = new ProdutoService(produtoRepo);
        PedidoService pedidoService = new PedidoService(pedidoRepo, notificacaoService);

        // Instanciando controllers
        ClienteController clienteController = new ClienteController(clienteService);
        ProdutoController produtoController = new ProdutoController(produtoService);
        PedidoController pedidoController = new PedidoController(pedidoService, clienteService, produtoService);

        // Cadastrando clientes
        clienteController.cadastrar("Luiz Carlos", "lcsr@email.com", "123456789");
        clienteController.cadastrar("Sandra", "sandra@email.com", "987654321");

        // Cadastrando produtos
        produtoController.cadastrar("Notebook", 3000);
        produtoController.cadastrar("Mouse", 100);

        // Buscar dados para uso
        Cliente cliente = clienteController.listar().get(0);
        Produto notebook = produtoController.listar().get(0);
        Produto mouse = produtoController.listar().get(1);

        // Criar pedido
        pedidoController.criarPedido(cliente.getId());
        pedidoController.adicionarItem(1, notebook.getId(), 1, 2800); // desconto
        pedidoController.adicionarItem(1, mouse.getId(), 2, 90); // desconto

        // Finalizar pedido
        pedidoController.finalizarPedido(1);

        // Pagar pedido
        pedidoController.pagarPedido(1);

        // Entregar pedido
        pedidoController.entregarPedido(1);

        // Exibir resumo final
        Pedido pedidoFinal = pedidoController.buscar(1);
        System.out.println("\nResumo do Pedido:");
        System.out.println("Cliente: " + pedidoFinal.getCliente().getNome());
        System.out.println("Status: " + pedidoFinal.getStatus());
        System.out.println("Total: R$ " + pedidoFinal.calcularTotal());
    }
}