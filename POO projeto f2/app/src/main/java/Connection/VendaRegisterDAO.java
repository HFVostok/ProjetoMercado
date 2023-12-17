package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

import Model.VendaRegister;

public class VendaRegisterDAO {
    // atributo
    private Connection connection;
    private List<VendaRegister> vendaRegisters;

    // construtor
    public VendaRegisterDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas_feitas (CPF VARCHAR(255) PRIMARY KEY, ID VARCHAR(255), DATAHORA VARCHAR(255), TOTAL VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public void atualizar(String cpf, String dataHora, String total) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pelo CPF
        String sql = "UPDATE vendas_feitas SET dataHora = ?, total = ? WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, dataHora);
            stmt.setString(2, total);
            stmt.setString(3, cpf);

            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public void cadastrar(String cpf, String id, String dataHora, String total) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO vendas_feitas (cpf, dataHora, total) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, cpf);
            stmt.setString(3, dataHora);
            stmt.setString(4, total);

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (

        SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

   

}
