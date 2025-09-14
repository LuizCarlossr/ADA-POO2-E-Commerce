package controller;
import model.Cliente;
import service.ClienteService;
import java.util.List;

public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    public void cadastrar(String nome, String email, String documento) {
        Cliente cliente = new Cliente(nome, email, documento);
        service.cadastrarCliente(cliente);
    }

    public Cliente buscar(int id) {
        return service.buscarCliente(id);
    }

    public List<Cliente> listar() {
        return service.listarClientes();
    }
}
