package service;
import enums.StatusPedido;
import model.Cliente;
import model.Pedido;
import model.Produto;
import repository.PedidoRepository;
import java.util.List;

public class PedidoService {
    private final PedidoRepository repository;
    private final NotificacaoService notificacaoService;

    public PedidoService(PedidoRepository repository, NotificacaoService notificacaoService) {
        this.repository = repository;
        this.notificacaoService = notificacaoService;
    }

    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        repository.salvar(pedido);
        return pedido;
    }

    public Pedido buscarPedido(int id) {
        return repository.buscarPorId(id);
    }

    public List<Pedido> listarPedidos() {
        return repository.listar();
    }

    public void adicionarItem(int pedidoId, Produto produto, int quantidade, double precoVenda) {
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null) {
            pedido.adicionarItem(produto, quantidade, precoVenda);
        }
    }

    public void removerItem(int pedidoId, int index) {
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null) {
            pedido.removerItem(index);
        }
    }

    public void finalizarPedido(int pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null) {
            pedido.finalizar();
            notificacaoService.enviarEmail(pedido.getCliente(), "Seu pedido foi finalizado. Aguardando pagamento.");
        }
    }

    public void pagarPedido(int pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null && pedido.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            pedido.pagar();
            notificacaoService.enviarEmail(pedido.getCliente(), "Pagamento realizado com sucesso.");
        } else {
            throw new IllegalStateException("Pedido não está aguardando pagamento.");
        }
    }

    public void entregarPedido(int pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        if (pedido != null && pedido.getStatus() == StatusPedido.PAGO) {
            pedido.entregar();
            notificacaoService.enviarEmail(pedido.getCliente(), "Seu pedido foi entregue.");
        } else {
            throw new IllegalStateException("Pedido não está pago para ser entregue.");
        }
    }
}
