package service;
import model.Cliente;

public class NotificacaoService {
    public void enviarEmail(Cliente cliente, String mensagem) {
        System.out.println("Email enviado para " + cliente.getEmail() + ": " + mensagem);
    }
}
