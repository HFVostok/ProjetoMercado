// package Controller;


// import javax.swing.table.DefaultTableModel;

// import View.ProdutoPainel;

// public class CadastroProdutoController {

//     private ProdutoPainel view;

//     public CadastroProdutoController(ProdutoPainel view) {
//         this.view = view;

//         // Configuração inicial, se necessário
//         setupController();
//     }

//     private void setupController() {
//         // Adicione configurações iniciais do controlador aqui, se necessário
//     }

//     public void cadastrarProduto(String id, String nome, String quantidade, String preco) {
//         // Lógica para cadastrar o produto na tabela
//         DefaultTableModel modeloTabela = view.getModeloTabela();

//         // Adiciona uma nova linha à tabela
//         Object[] rowData = {id, nome, quantidade, preco};
//         modeloTabela.addRow(rowData);

//         // Limpa os campos após cadastrar
//         view.limparCampos();
//     }

//     public void excluirProduto() {
//         // Lógica para excluir o produto selecionado na tabela
//         DefaultTableModel modeloTabela = view.getModeloTabela();

//         int selectedRow = view.getSelectedRow();

//         if (selectedRow != -1) {
//             modeloTabela.removeRow(selectedRow);
//         } else {
//             // Exibe um aviso se nenhum produto estiver selecionado
//             view.exibirAviso("Selecione um produto para excluir.");
//         }
//     }
// }
