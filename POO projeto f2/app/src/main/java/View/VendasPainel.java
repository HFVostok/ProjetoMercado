package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.ClientesDAO;
import Connection.VendaRegisterDAO;
import Connection.VendasDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Cliente;
import Model.Produto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VendasPainel extends JPanel {
    // Componentes da GUI
    private JPanel painelCliente, painelVenda, painelBotoes, painelCodigoQuantidade;
    private JLabel labelCliente, labelCodigo, labelQuantidade, labelTotal;
    private JTextField campoCliente, campoCodigo, campoQuantidade;
    private JButton botaoBuscar, botaoAdicionar, botaoFinalizar, botaoCadastrar;
    private JCheckBox checkVIP;
    private JTable table;
    private DefaultTableModel tableModel;
    private DefaultTableModel modeloVenda;
    private int linhaSelecionada = -1;
    private VendasDAO vendasDAO;
    private ClientesDAO clientesDAO;
    private boolean clienteVIP = false;
    private VendaRegisterDAO vendaRegisterDAO;

    // private VendaController vendaController;

    public DefaultTableModel getModeloVenda() {
        return modeloVenda;
    }

    public VendasPainel() {
        vendaRegisterDAO = new VendaRegisterDAO();
        // Cria o painel principal com GridLayout (3 rows, 1 column)
        setLayout(new GridLayout(3, 1));
        vendasDAO = new VendasDAO();
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
        gbc.gridwidth = 2; // Ocupa duas colunas
        painelVenda.add(labelTotal, gbc);

        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Codigo", "Descrição", "Preço", "Quantidade", "Subtotal" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

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

        // Chama os metodos da classe's DAO para a criação de tabelas no banco de dados
        new VendaRegisterDAO().criaTabela();
        new ClientesDAO().criaTabelaCL();

        // Tratamento de Eventos dos botões

        // Tratamento de eventos para o botão "Adicionar"
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });
        // Tratamento de eventos para o botão "Buscar"
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        // tratamento para click do mouse na tabela
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    campoCodigo.setText((String) table.getValueAt(linhaSelecionada, 0));

                }
            }
        });

        checkVIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clienteVIP = checkVIP.isSelected();
                if (clienteVIP) {
                    JOptionPane.showMessageDialog(VendasPainel.this, "Cliente VIP selecionado!");
                } else {
                    JOptionPane.showMessageDialog(VendasPainel.this, "Cliente VIP desmarcado!");
                }
            }
        });

        // Tratamento de eventos para o botão "Adicionar"
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        // Tratamento de eventos para o botão "Finalizar"
        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTotal();
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarNovoCliente();
            }
        });
        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarVenda();
            }
        });
    }

    // Metodos
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // adiciona itens
    private void adicionarItem() {
        try {
            String codigo = campoCodigo.getText();
            String quantidadeText = campoQuantidade.getText();

            // Verifica se os campos estão vazios
            if (codigo.isEmpty() || quantidadeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha os campos Código e Quantidade antes de adicionar.");
                return;
            }

            // Verifica se o código e a quantidade são Strings
            if (!(codigo instanceof String) || !(quantidadeText instanceof String)) {
                JOptionPane.showMessageDialog(this, "Código e Quantidade devem ser do tipo String.");
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao processar os campos. Certifique-se de que os valores são do tipo String.");
        }
    }

    private void cadastrarNovoCliente() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();

        Object[] message = {
                "CPF:", cpfField,
                "Nome:", nomeField,
                "Idade:", idadeField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String cpf = cpfField.getText();
                String nome = nomeField.getText();
                String idade = idadeField.getText();

                // Verifica se os campos estão vazios
                if (cpf.isEmpty() || nome.isEmpty() || idade.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de cadastrar o cliente.");
                    return;
                }

                // Verifica se os campos são Strings
                if (!(cpf instanceof String) || !(nome instanceof String) || !(idade instanceof String)) {
                    JOptionPane.showMessageDialog(this, "CPF, Nome e Idade devem ser do tipo String.");
                    return;
                }

                // Verifica se o CPF já existe
                if (clientesDAO.buscarCPF(cpf) == null) {
                    // Realiza o cadastro do cliente
                    clientesDAO.cadastrarCL(cpf, nome, idade);
                    JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "CPF já cadastrado!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao processar os campos. Certifique-se de que os valores são do tipo String.");
            }
        }
    }

    // calcula o total
    private void calcularTotal() {
        double total = 0;

        // Itera sobre as linhas da tabela
        for (int i = 0; i < table.getRowCount(); i++) {
            // Obtém o código do produto da coluna "Código"
            String codigo = table.getValueAt(i, 0).toString();

            // Chama o método buscarProdutoPorCodigo da instância de VendasDAO
            Produto produto = vendasDAO.buscarProdutoPorCodigo(codigo);

            // Se encontrar o produto, atualiza os valores na tabela
            if (produto != null) {
                table.setValueAt(produto.getDescricao(), i, 1);
                table.setValueAt(Double.parseDouble(produto.getPreco()), i, 2);
            }

            // Obtém os valores das colunas "Quantidade" e "Preço"
            int quantidade = Integer.parseInt(table.getValueAt(i, 2).toString());
            double preco = Double.parseDouble(table.getValueAt(i, 3).toString());

            // Calcula o subtotal multiplicando quantidade por preço
            double subtotal = quantidade * preco;

            // Se o cliente for VIP, aplica um desconto de 20%
            if (clienteVIP) {
                subtotal *= 0.8; // 20% de desconto
            }

            // Atribui o subtotal à coluna "Subtotal"
            table.setValueAt(subtotal, i, 4);

            // Acumula a soma total
            total += subtotal;
        }

        // Exibe o total em um JOptionPane
        JOptionPane.showMessageDialog(this, "Total da venda: R$ " + String.format("%.2f", total));
    }

    private void buscarCliente() {
        String cpf = campoCliente.getText();

        // Chama o método buscarCPF da instância de VendasDAO
        Cliente cliente = clientesDAO.buscarCPF(cpf);

        if (cliente != null) {
            JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado:\nNome: " + cliente.getNome() + "\nIdade: " + cliente.getIdade());
        } else {
            JOptionPane.showMessageDialog(this, "O cliente não possui cadastro.");
        }
    }

    // Adicione este método à sua classe VendasPainel
    private void finalizarVenda() {
        // Verifica se há itens na tabela
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione itens à tabela antes de finalizar a venda.");
            return;
        }

        String cpfCliente = campoCliente.getText();
        if (cpfCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o CPF do cliente antes de finalizar a venda.");
            return;
        }

        // Obtém a data e hora do evento (assumindo que você tem um método para isso)
        String dataHoraEvento = obterDataHoraEvento();

        // Calcula o valor total da venda
        String valorTotal = calcularValorTotal();

        // Chama o método atualizar da instância de VendaRegisterDAO
        VendaRegisterDAO vendaRegisterDAO = new VendaRegisterDAO();
        vendaRegisterDAO.atualizar(cpfCliente, dataHoraEvento, valorTotal);

        // Limpa a tabela e os campos
        limparTabelaEFields();
        vendaRegisterDAO.atualizar(cpfCliente, dataHoraEvento, valorTotal);
    }

    // Método para obter a data e hora do evento
    private String obterDataHoraEvento() {
        // Obtém a data e hora atual
        Date dataAtual = new Date();

        // Define o formato desejado (ano-mês-dia hora:minuto:segundo)
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formata a data e hora atual de acordo com o formato
        return formato.format(dataAtual);
    }

    // Método para calcular o valor total da venda e retornar como String formatada
    private String calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += Double.parseDouble(table.getValueAt(i, 4).toString());
        }

        // Formata o total como uma string
        return String.format("%.2f", total);
    }

    // Método para limpar a tabela e os campos
    private void limparTabelaEFields() {
        // Limpa a tabela
        tableModel.setRowCount(0);

        // Limpa os campos
        campoCliente.setText("");
        campoCodigo.setText("");
        campoQuantidade.setText("");
        labelTotal.setText("Total: R$ 0.00");
        checkVIP.setSelected(false);

        JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");
    }

}