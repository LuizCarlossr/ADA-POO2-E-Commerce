package service;
import model.Produto;
import repository.ProdutoRepository;
import java.util.List;

public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarProduto(Produto produto) {
        repository.salvar(produto);
    }

    public Produto buscarProduto(int id) {
        return repository.buscarPorId(id);
    }

    public List<Produto> listarProdutos() {
        return repository.listar();
    }
}
