package View;

import Controller.VendaController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Venda;

public class VendasPainel extends JPanel {
    // Componentes da GUI
    private JPanel painelCliente, painelVenda, painelBotoes, painelCodigoQuantidade;
    private JLabel labelCliente, labelCodigo, labelQuantidade, labelTotal, labelInsiraCodigo;
    private JTextField campoCliente, campoCodigo, campoQuantidade, campoModeloVenda;
    private JButton botaoBuscar, botaoAdicionar, botaoFinalizar, botaoCadastrar;
    private JTable tabelaVenda;
    private DefaultTableModel modeloVenda;
    private JCheckBox checkVIP;
    private JScrollPane scrollVenda;
    private double totalVenda = 0.0;

    private VendaController vendaController;

    public VendasPainel() {
        // Cria o painel principal com GridLayout (3 rows, 1 column)
        setLayout(new GridLayout(3, 1));

        // Cria o painel do cliente
        painelCliente = new JPanel();
        painelCliente.setBorder(BorderFactory.createTitledBorder("Cliente"));
        painelCliente.setLayout(new FlowLayout());

        // Cria os componentes do painel do cliente
        labelCliente = new JLabel("Cliente:");
        campoCliente = new JTextField(100);
        botaoBuscar = new JButton("Buscar");
        botaoCadastrar = new JButton("Cadastrar");
        checkVIP = new JCheckBox("VIP (Clube Desconto)");
        checkVIP.setEnabled(false);

        // Adiciona os componentes ao painel do cliente
        painelCliente.add(labelCliente);
        painelCliente.add(campoCliente);
        painelCliente.add(botaoBuscar);
        painelCliente.add(botaoCadastrar);
        painelCliente.add(checkVIP);

        // Adiciona o painel do cliente ao painel principal
        add(painelCliente);

        // Painel de adiçao de produtos
        painelCodigoQuantidade = new JPanel();
        painelCliente.setBorder(BorderFactory.createTitledBorder("Inserir"));
        painelCodigoQuantidade.setLayout(new GridLayout(2, 1));

        labelCodigo = new JLabel("Código:");
        campoCodigo = new JTextField(1000);
        labelQuantidade = new JLabel("Quantidade:");
        campoQuantidade = new JTextField(1000);

        painelCodigoQuantidade.add(labelCodigo);
        painelCodigoQuantidade.add(campoCodigo);
        painelCodigoQuantidade.add(labelQuantidade);
        painelCodigoQuantidade.add(campoQuantidade);

        add(painelCodigoQuantidade);

        // Cria o painel da venda
        painelVenda = new JPanel();
        painelVenda.setBorder(BorderFactory.createTitledBorder("Venda"));
        painelVenda.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Margins

        // Cria os componentes do painel da venda

        labelTotal = new JLabel("Total: R$ 0.00");
        labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        // Adiciona os componentes ao painel da venda

        gbc.gridx++;
        painelVenda.add(painelCodigoQuantidade, gbc);
        gbc.gridx = 0;
        gbc.gridwidth = 5; // Ocupa cinco colunas
        painelVenda.add(labelTotal, gbc);

        // Cria o modelo da tabela da venda
        modeloVenda = new DefaultTableModel();
        // Define as colunas do modelo
        modeloVenda.addColumn("Código");
        modeloVenda.addColumn("Descrição");
        modeloVenda.addColumn("Quantidade");
        modeloVenda.addColumn("Preço");
        modeloVenda.addColumn("Subtotal");

        // Cria a tabela da venda
        tabelaVenda = new JTable(modeloVenda);
        // Define o tamanho das colunas da tabela
        tabelaVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaVenda.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabelaVenda.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelaVenda.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelaVenda.getColumnModel().getColumn(4).setPreferredWidth(50);
        // Define a tabela como não editável
        tabelaVenda.setEnabled(false);

        // Cria o scroll da tabela da venda
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        painelVenda.add(new JScrollPane(tabelaVenda), gbc);

        // Adiciona o painel da venda ao painel principal
        add(painelVenda);

        // Cria o painel dos botões
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        // Cria os botões
        botaoAdicionar = new JButton("Adicionar");
        botaoFinalizar = new JButton("Finalizar");

        // Adiciona os botões ao painel dos botões
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoFinalizar);

        // Adiciona o painel dos botões ao painel principal
        add(painelBotoes);

          vendaController = new VendaController(this);

        // Tratamento de eventos para o botão "Adicionar"
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendaController.adicionarProduto();
            }
        });

        // Tratamento de eventos para o botão "Finalizar"
        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendaController.finalizarVenda();
            }
        });
    }

    // Métodos necessários
    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public JTextField getCampoQuantidade() {
        return campoQuantidade;
    }

    public JTable getTabelaVenda() {
        return tabelaVenda;
    }

    public void atualizarTotal(double subtotal) {
        totalVenda += subtotal;
        labelTotal.setText("Total: R$ " + String.format("%.2f", totalVenda));
    }

    public void limparCampos() {
        campoCodigo.setText("");
        campoQuantidade.setText("");
    }

    public void limparTabela() {
        modeloVenda.setRowCount(0); // Remove todas as linhas da tabela
        totalVenda = 0.0;
        labelTotal.setText("Total: R$ 0.00");
    }

    public double getTotalVenda() {
        return totalVenda;
    }
}