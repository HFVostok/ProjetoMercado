package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

import Model.Venda;
import Model.Produto;
import Model.Cliente; 

public class VendasDAO {

    // atributo
    private Connection connection;
    private List<Venda> vendas;
    private List<Cliente> clientes;
    private List<Produto> produtos;

    // construtor
    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }


    // Buscadores
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    // Método para buscar um produto por código
    public Produto buscarProdutoPorCodigo(String codigo) {
        String sql = "SELECT ID, CODIGO, DESCRICAO, PRECO FROM cadastro_produto WHERE CODIGO = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String descricao = resultSet.getString("DESCRICAO");
                    String preco = resultSet.getString("PRECO");

                    // Crie e retorne um objeto Produto com os dados do banco de dados
                    return new Produto(id, codigo, descricao, preco);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o produto por código: " + e.getMessage(), e);
        }

        // Se não encontrar o produto, retorna null
        return null;
    }
}