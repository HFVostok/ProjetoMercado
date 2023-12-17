package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Produto;

public class ProdutoDAO {
    // atributo
    private Connection connection;
    private List<Produto> produtos;

    // construtor
    public ProdutoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_produto (ID VARCHAR(255) PRIMARY KEY, CODIGO VARCHAR(255),DESCRICAO VARCHAR(255), PRECO VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Produto> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        produtos = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            String sql = "SELECT * FROM cadastro_produto";
            stmt = connection.prepareStatement(sql);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro

                Produto produto = new Produto(
                        rs.getString("id"),
                        rs.getString("codigo"),
                        rs.getString("descricao"),
                        rs.getString("preco"));
                produtos.add(produto); // Adiciona o objeto Carros à lista de carros
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);

            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return produtos; // Retorna a lista de carros recuperados do banco de dados
    }

  
    public void cadastrar(String id, String codigo, String descricao, String preco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO cadastro_produto (id, codigo, descricao, preco) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, id);
            stmt.setString(2, codigo);
            stmt.setString(3, descricao);
            stmt.setString(4, preco);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (

        SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void atualizar(String id, String codigo, String descricao, String preco) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pelo ID
        String sql = "UPDATE cadastro_produto SET codigo = ?, descricao = ?, preco = ? WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setString(1, codigo);
            stmt.setString(2, descricao);
            stmt.setString(3, preco);
            stmt.setString(4, id);
    
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String codigo) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM cadastro_produto WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}

