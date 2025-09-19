package controller;

import model.Cliente;
import model.Pedido;
import model.Produto;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;
import java.util.List;

public class PedidoController {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService pedidoService, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public void criarPedido(int clienteId) {
        Cliente cliente = clienteService.buscarCliente(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        pedidoService.criarPedido(cliente);
    }

    public void adicionarItem(int pedidoId, int produtoId, int quantidade, double precoVenda) {
        Produto produto = produtoService.buscarProduto(produtoId);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        pedidoService.adicionarItem(pedidoId, produto, quantidade, precoVenda);
    }

    public void removerItem(int pedidoId, int itemIndex) {
        pedidoService.removerItem(pedidoId, itemIndex);
    }

    public void finalizarPedido(int pedidoId) {
        pedidoService.finalizarPedido(pedidoId);
    }

    public void pagarPedido(int pedidoId) {
        pedidoService.pagarPedido(pedidoId);
    }

    public void entregarPedido(int pedidoId) {
        pedidoService.entregarPedido(pedidoId);
    }

    public Pedido buscar(int id) {
        return pedidoService.buscarPedido(id);
    }

    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }
}