package Model;

public class Venda {
    private String id;
    private String nome;
    private String preco;
    private String quantidade;

    public Venda(String id, String nome, String preco, String quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getter para o atributo 'id'
    public String getId() {
        return id;
    }

    // Setter para o atributo 'id'
    public void setId(String id) {
        this.id = id;
    }

    // Getter para o atributo 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para o atributo 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o atributo 'preco'
    public String getPreco() {
        return preco;
    }

    // Setter para o atributo 'preco'
    public void setPreco(String preco) {
        this.preco = preco;
    }

    // Getter para o atributo 'quantidade'
    public String getQuantidade() {
        return quantidade;
    }

    // Setter para o atributo 'quantidade'
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
