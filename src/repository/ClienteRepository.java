package repository;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IRepository<Cliente>{
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> listar() {
        return clientes;
    }
}
