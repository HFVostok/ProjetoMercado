package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Connection.ProdutoDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProdutoPainel extends JPanel {

    private JTextField campoId, campoNome, campoPreco, campoQuantidade;
    private JButton botaoCadastrar, botaoLimparCampos, botaoExcluirProduto;


    private JTable table;
    private DefaultTableModel tableModel;


    public ProdutoPainel() {
        // Configurações básicas do JPanel
        setLayout(new BorderLayout());

        // Adiciona um painel superior para os campos e botão
        JPanel painelCampos = new JPanel(new GridLayout(5, 2));
        add(painelCampos, BorderLayout.NORTH);

        // Componentes para inserir informações do produto
        JLabel labelId = new JLabel("ID:");
        campoId = new JTextField();
        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();
        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField();
        JLabel labelQuantidade = new JLabel("Quantidade:");
        campoQuantidade = new JTextField();

        // Adiciona os componentes ao painelCampos
        painelCampos.add(labelId);
        painelCampos.add(campoId);
        painelCampos.add(labelNome);
        painelCampos.add(campoNome);
        painelCampos.add(labelPreco);
        painelCampos.add(campoPreco);
        painelCampos.add(labelQuantidade);
        painelCampos.add(campoQuantidade);

        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Codigo", "Descrição", "Preço", "Subtotal" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Cria e configura o painelControles
        JPanel painelControles = new JPanel(new FlowLayout());
        add(painelControles, BorderLayout.SOUTH);

        // Botão para cadastrar os dados
        botaoCadastrar = new JButton("Cadastrar");
        // Botão para excluir o produto selecionado na tabela
        botaoExcluirProduto = new JButton("Excluir Produto");

        // cria banco de dados
        new ProdutoDAO().criaTabela();
        // executar o método de atualizar tabela

        //======================tratamento de eventos =====================================

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarProduto();
            }
        });

        botaoLimparCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        botaoExcluirProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirProduto();
            }
        });

        // Adiciona os botões ao painelControles
        painelControles.add(botaoCadastrar);
        painelControles.add(botaoLimparCampos);
        painelControles.add(botaoExcluirProduto);
    }

    // Método para cadastrar um novo produto
    // Método para cadastrar um novo produto
    private void cadastrarProduto() {
        String id = campoId.getText();
        String nome = campoNome.getText();
        String precoStr = campoPreco.getText();
        String quantidadeStr = campoQuantidade.getText();

        // Verifica se os campos estão preenchidos
        if (id.isEmpty() || nome.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Converte os valores dos campos para os tipos corretos
        double preco = Double.parseDouble(precoStr);
        int quantidade = Integer.parseInt(quantidadeStr);

        // Adiciona uma nova linha à tabela
        Object[] rowData = { id, nome, preco, quantidade, preco * quantidade }; // Adiciona a coluna de subtotal
        tableModel.addRow(rowData);

        // Limpa os campos após cadastrar
        limparCampos();
    }

    // Método para limpar os campos
    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoPreco.setText("");
        campoQuantidade.setText("");
    }

    // Método para excluir o produto selecionado
    private void excluirProduto() {
        int selectedRow = table.getSelectedRow(); // Corrigido para usar o nome correto da tabela
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
