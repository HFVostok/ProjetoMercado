package Model;

public class Venda {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Venda(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getter para o atributo 'id'
    public int getId() {
        return id;
    }

    // Setter para o atributo 'id'
    public void setId(int id) {
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
    public double getPreco() {
        return preco;
    }

    // Setter para o atributo 'preco'
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Getter para o atributo 'quantidade'
    public int getQuantidade() {
        return quantidade;
    }

    // Setter para o atributo 'quantidade'
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
