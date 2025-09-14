package controller;
import model.Produto;
import service.ProdutoService;
import java.util.List;

public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    public void cadastrar(String nome, double preco) {
        Produto produto = new Produto(nome, preco);
        service.cadastrarProduto(produto);
    }

    public Produto buscar(int id) {
        return service.buscarProduto(id);
    }

    public List<Produto> listar() {
        return service.listarProdutos();
    }
}
