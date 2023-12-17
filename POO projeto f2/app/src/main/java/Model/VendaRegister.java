package Model;

public class VendaRegister {
    private String cpf;
    private String dataHora;
    private String total;

    // Construtor
    public VendaRegister(String cpf, String dataHora, String total) {
        this.cpf = cpf;
        this.dataHora = dataHora;
        this.total = total;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    
}
