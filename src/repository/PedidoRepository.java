package repository;
import model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements IRepository<Pedido> {
    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void salvar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public Pedido buscarPorId(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Pedido> listar() {
        return pedidos;
    }
}
