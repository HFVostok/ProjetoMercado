package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Cliente;

public class ClientesDAO {
    // atributo
    private Connection connection;
    private List<Cliente> clientes;

    // construtor
    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // private String cpf;
    // private String nome;
    // private String idade;

    // criar Tabela
    public void criaTabelaCL() {
        String sql = "CREATE TABLE IF NOT EXISTS cadastro_cliente (CPF VARCHAR(255) PRIMARY KEY, NOME VARCHAR(255),IDADE VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de Clientes criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de Clientes: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // private String cpf;
    // private String nome;
    // private String idade;

    // Listar todos os valores cadastrados
    public List<Cliente> listarTodosCL() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        clientes = new ArrayList<>();
        // Cria uma lista para armazenar os Clientes recuperados do banco de dados
        try {
            String sql = "SELECT * FROM  cadastro_cliente";
            stmt = connection.prepareStatement(sql);
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Clientes com os valores do
                // registro

                Cliente cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("idade"));
                clientes.add(cliente); // Adiciona o objeto Clientes à lista de clintes
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);

            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return clientes; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Clientes no banco
    public void cadastrarCL(String cpf, String nome, String idade) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO  cadastro_cliente (cpf, nome, idade,) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, idade);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (

        SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizarCL(String cpf, String nome, String idade) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE  cadastro_cliente SET cpf = ?, nome = ?, idade = ?";
        try {
            stmt = connection.prepareStatement(sql);
            // cpf é chave primaria não pode ser alterada.
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, idade);

            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para buscar um cpf
    public Cliente buscarCPF(String cpf) {
        String sql = "SELECT CPF, NOME, IDADE FROM cadastro_cliente WHERE CPF = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("NOME");
                    String idade = resultSet.getString("IDADE");

                    // Crie e retorne um objeto Cliente com os dados do banco de dados
                    return new Cliente(cpf, nome, idade);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF: " + e.getMessage(), e);
        }

        // Se não encontrar o cliente, retorna null
        return null;
    }

    // Apagar dados do banco
    public void apagarCL(String cpf) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM  cadastro_cliente WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
