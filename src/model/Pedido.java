package model;
import enums.StatusPedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int proximoId = 1;
    private int id;
    private Cliente cliente;
    private LocalDateTime dataCriacao;
    private StatusPedido status;
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(Produto produto, int quantidade, double precoVenda) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Pedido não está aberto para alterações.");
        }
        this.itens.add(new ItemPedido(produto, quantidade, precoVenda));
    }

    public void removerItem(int index) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Pedido não está aberto para alterações.");
        }
        if (index >= 0 && index < itens.size()) {
            itens.remove(index);
        }
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public void finalizar() {
        if (status != StatusPedido.ABERTO || itens.isEmpty() || calcularTotal() <= 0) {
            throw new IllegalStateException("Pedido inválido para finalização.");
        }
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public void pagar() {
        if (status != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Pedido não está aguardando pagamento.");
        }
        this.status = StatusPedido.PAGO;
    }

    public void entregar() {
        if (status != StatusPedido.PAGO) {
            throw new IllegalStateException("Pedido não pode ser entregue.");
        }
        this.status = StatusPedido.FINALIZADO;
    }
}
