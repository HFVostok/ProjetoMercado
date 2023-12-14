package Controller;

import Model.VendaRegister;

/**
 * ListaProdController
 */
public class ListaProdController {

    // Adicione esses métodos na classe ListarProdutosView

    public void addCalcularTotalListener(ActionListener listener) {
        botaoCalcularTotal.addActionListener(listener);
    }

    public void adicionarVendaNaTabela(VendaRegister venda) {
        modeloTabela.addRow(new Object[] { venda.getCpf(), venda.getId(), venda.getDataHora(), venda.getTotal() });
    }

    public void atualizarLabelTotal(String texto) {
        labelTotal.setText(texto);
    }

}