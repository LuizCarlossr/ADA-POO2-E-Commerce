package repository;
import java.util.List;

public interface IRepository<T> {
    void salvar(T entidade);
    T buscarPorId(int id);
    List<T> listar();
}
