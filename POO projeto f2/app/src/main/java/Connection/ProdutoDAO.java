package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Produto;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public Produto buscarProdutoPorCodigo(String codigo) {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                        // Adicione outros campos conforme necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção apropriadamente
        }
        return null;
    }
}
























// package Connection;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// import Model.Produto;

// public class ProdutoDAO {
//     private Connection connection;

//     public ProdutoDAO() {
//         this.connection = ConnectionFactory.getConnection();
//     }

//     public Produto buscarProdutoPorCodigo(String codigo) {
//         String sql = "SELECT * FROM produtos WHERE codigo = ?";
//         try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//             stmt.setString(1, codigo);
//             ResultSet rs = stmt.executeQuery();
//             if (rs.next()) {
//                 return new Produto(
//                         rs.getInt("id"),
//                         rs.getString("descricao"),
//                         rs.getDouble("preco")
//                         // Adicione outros campos conforme necessário
//                 );
//             }
//         } catch (SQLException e) {
//             e.printStackTrace(); // Trate a exceção apropriadamente
//         }
//         return null;
//     }
// }
