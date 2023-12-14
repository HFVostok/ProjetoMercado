package View;

import Model.Produto;
import Controller.ProdutosController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProdutoPainel extends JPanel {

    private JTextField campoId, campoNome, campoPreco, campoQuantidade;
    private JButton botaoCadastrar, botaoLimparCampos, botaoExcluirProduto;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private ProdutosController produtosController;

    public ProdutoPainel() {
        // Configurações básicas do JPanel
        setLayout(new BorderLayout());

        // Inicializa o controller
        produtosController = new ProdutosController(this);

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

        // Botão para cadastrar os dados
        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtosController.cadastrarProduto();
            }
        });

        // Adiciona os componentes ao painelCampos
        painelCampos.add(labelId);
        painelCampos.add(campoId);
        painelCampos.add(labelNome);
        painelCampos.add(campoNome);
        painelCampos.add(labelPreco);
        painelCampos.add(campoPreco);
        painelCampos.add(labelQuantidade);
        painelCampos.add(campoQuantidade);
        painelCampos.add(new JLabel()); // Espaço vazio para melhorar a aparência
        painelCampos.add(botaoCadastrar);

        // Configurações da tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Quantidade");
        modeloTabela.addColumn("Preço");

        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        // Adiciona a tabela ao JPanel
        add(scrollPane, BorderLayout.CENTER);

        // Cria e configura o painelControles
        JPanel painelControles = new JPanel(new FlowLayout());
        add(painelControles, BorderLayout.SOUTH);

        // Botão para limpar os campos
        botaoLimparCampos = new JButton("Limpar Campos");
        botaoLimparCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtosController.limparCampos();
            }
        });

        // Botão para excluir o produto selecionado na tabela
        botaoExcluirProduto = new JButton("Excluir Produto");
        botaoExcluirProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtosController.excluirProduto();
            }
        });

        // Adiciona os botões ao painelControles
        painelControles.add(botaoCadastrar);
        painelControles.add(botaoLimparCampos);
        painelControles.add(botaoExcluirProduto);
    }

    // Métodos para acessar os dados dos campos
    public String getCampoId() {
        return campoId.getText();
    }

    public String getCampoNome() {
        return campoNome.getText();
    }

    public String getCampoPreco() {
        return campoPreco.getText();
    }

    public String getCampoQuantidade() {
        return campoQuantidade.getText();
    }

    // Método para obter o produto selecionado na tabela
    public Produto getProdutoSelecionado() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Obtém os dados do produto na linha selecionada
        String id = (String) tabelaProdutos.getValueAt(linhaSelecionada, 0);
        String nome = (String) tabelaProdutos.getValueAt(linhaSelecionada, 1);
        String quantidade = (String) tabelaProdutos.getValueAt(linhaSelecionada, 2);
        String preco = (String) tabelaProdutos.getValueAt(linhaSelecionada, 3);

    }}
