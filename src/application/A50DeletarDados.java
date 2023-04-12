package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class A50DeletarDados {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            // A forma de DELETAR os Dados é a mesma de inserir os dados
            // Tendo como diferença aquela a SQL Query
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department"
                    + "WHERE "
                    + "Id = ?");

            st.setInt(1, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        } catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
