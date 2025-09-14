package service;
import model.Cliente;
import repository.ClienteRepository;
import java.util.List;

public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente.getDocumento() == null || cliente.getDocumento().isEmpty()) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        repository.salvar(cliente);
    }

    public Cliente buscarCliente(int id) {
        return repository.buscarPorId(id);
    }

    public List<Cliente> listarClientes() {
        return repository.listar();
    }
}
