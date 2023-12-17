package Model;

public class Produto {
    private String id;
    private String codigo;
    private String descricao;
    private String preco;

    // Construtor
    public Produto(String id, String codigo, String descricao, String preco) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    // Outros métodos, se necessário
}
