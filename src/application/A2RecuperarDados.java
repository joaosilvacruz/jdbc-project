package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A2RecuperarDados {
    public static void main(String[] args) {
        // Preparando o banco, consulta e guardar a consulta

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            // Instanciando um objeto do tipo Statement
            st = conn.createStatement();
            // Execute query requer uma string que é o comando SQL
            rs = st.executeQuery("SELECT * FROM department");

            // Enquanto existir um próximo valor imprima os valores de Id e de Name
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();

        }

    }
}
