package Controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import View.VendasPainel;
import Model.Produto;
import Connection.ProdutoDAO;

public class VendaController {
    private VendasPainel vendasPainel;
    private DefaultTableModel tableModel;

    public VendaController(VendasPainel vendasPainel) {
        this.vendasPainel = vendasPainel;
        this.tableModel = (DefaultTableModel) vendasPainel.getTabelaVenda().getModel();
    }

    public void adicionarProduto() {
        String codigo = vendasPainel.getCampoCodigo().getText();
        
        // Use a classe ProdutoDAO para buscar o produto no banco de dados
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = produtoDAO.buscarProdutoPorCodigo(codigo);
        
        if (produto != null) {
            int quantidade = Integer.parseInt(vendasPainel.getCampoQuantidade().getText());
    
            // Calcular subtotal
            double subtotal = produto.getPreco() * quantidade;
    
            // Adicionar à tabela
            tableModel.addRow(new Object[] { produto.getId(), produto.getDescricao(), quantidade,
                    produto.getPreco(), subtotal });
    
            // Atualizar total da venda
            vendasPainel.atualizarTotal(subtotal);
    
            // Limpar campos após a adição
            vendasPainel.limparCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    }

    public void finalizarVenda() {
        // Adicione aqui a lógica para finalizar a venda
        JOptionPane.showMessageDialog(vendasPainel, "Venda finalizada. Total: R$ " + String.format("%.2f", vendasPainel.getTotalVenda()));

        // Limpe a tabela e reinicie o total
        vendasPainel.limparTabela();
    }
}



    // // atributos
    // private List<Venda> vendas;
    // private DefaultTableModel tableModel;
    // private JTable table;

    // public VendaController(List<Venda> vendas, DefaultTableModel tableModel, JTable table) {
    //     this.vendas = vendas;
    //     this.tableModel = tableModel;
    //     this.table = table;
    // }

    // // // atributos
    // // private List<Clientes> clientes;
    // // private DefaultTableModel tableModel;
    // // private JTable table;

    // // public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel,
    // // JTable table) {
    // // this.clientes = clientes;
    // // this.tableModel = tableModel;
    // // this.table = table;
    // // }

    // private void atualizarTabela() {
    //     tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
    //     // Obtém os clientes atualizados do banco de dados
    //     for (Venda venda : vendas) {
    //         // Adiciona os dados de cada cliente como uma nova linha na tabela Swing
    //         tableModel.addRow(new Object[] { venda.getId(), venda.getNome(), venda.getPreco(), venda.getQuantidade });
    //     }
    // }

    // public void cadastrar(String id, String nome, String preco, String quantidade) {

    //     // Lógica de cadastro no banco de dados sem try-catch
    //     new ClienteDAO().cadastrarCL(id, nome, preco, quantidade);
    //     // Atualiza a tabela de exibição após o cadastro, mesmo que haja uma exceção
    //     atualizarTabela();
    // }

    // // Método para atualizar os dados de um cliente no banco de dados
    // public void atualizar(String id, String nome, String preco, String quantidade) {
    //     new ClienteDAO().atualizarCL(id, nome, preco, quantidade);
    //     // Chama o método de atualização no banco de dados
    //     atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    // }

    // // Método para apagar um cliente do banco de dados
    // public void apagar(String cpf) {
    //     // Exibe uma caixa de diálogo de confirmação
    //     int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar este Produto?", "Confirmação",
    //             JOptionPane.YES_NO_OPTION);

    //     if (resposta == JOptionPane.YES_OPTION) {
    //         // Lógica de exclusão no banco de dados sem try-catch
    //         new ClienteDAO().apagarCL(cpf);
    //         // Atualiza a tabela de exibição após a exclusão
    //         atualizarTabela();
    //     }
    // }
