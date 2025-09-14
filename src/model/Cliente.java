package model;

public class Cliente {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String email;
    private String documento;

    public Cliente(String nome, String email, String documento) {
        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        this.id = proximoId++;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
