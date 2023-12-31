package View;

import Model.VendaRegister;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ListarProdutosView extends JPanel {

    private JTable tabelaVendas;
    private DefaultTableModel modeloTabela;
    private List<VendaRegister> listaVendas;
    private JLabel labelTotal;

    public ListarProdutosView() {
        // Configurações básicas do JPanel
        setLayout(new BorderLayout());

        // Adiciona um painel superior para os controles
        JPanel painelControles = new JPanel(new FlowLayout());
        add(painelControles, BorderLayout.NORTH);

        // Botão para calcular o total de vendas
        JButton botaoCalcularTotal = new JButton("Calcular Total");

        // Adiciona os botões ao painelControles
        painelControles.add(botaoCalcularTotal);

        // Inicializa a lista de vendas
        listaVendas = new ArrayList<>();

        // Configurações da tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Data/Hora");
        modeloTabela.addColumn("Total");

        tabelaVendas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaVendas);

        // Adiciona a tabela ao JPanel
        add(scrollPane, BorderLayout.CENTER);

        // Rótulo para mostrar o total de vendas
        labelTotal = new JLabel("Total de Vendas: R$ 0.00");
        add(labelTotal, BorderLayout.SOUTH);

        // Tratamento de eventos do botão "Calcular Total"
        botaoCalcularTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotalVendas();
            }
        });

    }


    // Método para calcular o total de vendas
    private void calcularTotalVendas() {
        double totalVendas = 0;
    
        // Itera sobre as linhas da tabela
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            // Obtém o valor da coluna "Total" e converte para double
            double totalVendaLinha = Double.parseDouble(modeloTabela.getValueAt(i, 3).toString());
            // Soma o valor ao total geral
            totalVendas += totalVendaLinha;
        }
    
        // Formata o total como uma string e exibe no rótulo
        labelTotal.setText("Total de Vendas: R$ " + String.format("%.2f", totalVendas));
    }
    


}
