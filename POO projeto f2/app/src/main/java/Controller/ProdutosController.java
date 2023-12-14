package Controller;

import Model.Produto;
import View.ProdutoPainel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutosController {

    private DefaultTableModel tableModel;
    private ProdutoPainel produtoPainel;
    private List<Produto> listaProdutos;

    public ProdutosController(ProdutoPainel produtoPainel) {
        this.produtoPainel = produtoPainel;
        this.tableModel = (DefaultTableModel) produtoPainel.getTabelaProdutos().getModel();
        this.listaProdutos = new ArrayList<>();
    }

    // Método para cadastrar o produto
    public void cadastrarProduto() {
        // Obtenha os valores dos campos da view
        String id = produtoPainel.getCampoId();
        String nome = produtoPainel.getCampoNome();
        String preco = produtoPainel.getCampoPreco();
        String quantidade = produtoPainel.getCampoQuantidade();

        // Validação simples dos campos (pode ser ajustada conforme necessário)
        if (id.isEmpty() || nome.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
            JOptionPane.showMessageDialog(produtoPainel, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crie um objeto Produto com os valores dos campos
        Produto produto = new Produto(id, nome, preco, quantidade);

        // Adicione o produto à lista
        listaProdutos.add(produto);

        // Atualize a tabela na view
        produtoPainel.adicionarProdutoNaTabela(produto);

        // Limpe os campos na view
        produtoPainel.limparCampos();
    }

    // Método para limpar os campos na view
    public void limparCampos() {
        produtoPainel.limparCampos();
    }

    // Método para excluir o produto selecionado na tabela na view
    public void excluirProduto() {
        Produto produtoSelecionado = produtoPainel.getProdutoSelecionado();

        if (produtoSelecionado != null) {
            // Remova o produto da lista
            listaProdutos.remove(produtoSelecionado);

            // Atualize a tabela na view
            produtoPainel.atualizarTabela(listaProdutos);
        }
    }

    // Método para obter a lista de produtos
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
}
