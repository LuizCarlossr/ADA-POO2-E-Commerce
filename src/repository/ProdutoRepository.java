package repository;
import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements IRepository<Produto> {
    private final List<Produto> produtos = new ArrayList<>();

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public Produto buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Produto> listar() {
        return produtos;
    }
}
