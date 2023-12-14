package Model;

public class VendaRegister {

//atributos

private String cpf;
private String id;
private String datahora;
private String total;

//construtores

public VendaRegister(String cpf, String id, String datahora, String total) {
    this.cpf = cpf;
    this.id = id;
    this.datahora = datahora;
    this.total = total;
}

//getters e setters

public String getCpf() {
    return cpf;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getDatahora() {
    return datahora;
}

public void setDatahora(String datahora) {
    this.datahora = datahora;
}

public String getTotal() {
    return total;
}

public void setTotal(String total) {
    this.total = total;
}

}