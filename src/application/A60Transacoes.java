package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class A60Transacoes {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;

        try {
            //Para as transações utiliza o Statement

            conn = DB.getConnection();
            // Para não confirmar as alterações automaticamente usa false em .setAutoCommit
            // Assim é uma forma de assegurar ser feito tudo ou nada.
            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2500 " +
                    "WHERE DepartmentId = 1");

            //Interrompendo propositalmente a execução do programa
            int x = 1;
            if (x < 2) {
                throw new SQLException("Fake error");
            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3900 " +
                    "WHERE DepartmentId = 2");

            // Caso o programa rode, então aí sim haverá o commit
            conn.commit();

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);

        } catch (SQLException e){
            // caso haja uma pausa na transação, utiliza rollback para que nada seja feito
            try{
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
