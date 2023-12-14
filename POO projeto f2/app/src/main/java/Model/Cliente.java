package Model;

public class Cliente {
// atributos
private String cpf;
private String nome;
private String idade;

// Construtores

public Cliente(String cpf, String nome, String idade) {
    this.cpf = cpf;
    this.nome = nome;
    this.idade = idade;
}

//getters e setters

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getIdade() {
    return idade;
}

public void setIdade(String idade) {
    this.idade = idade;
}
    
}
