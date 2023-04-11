package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class A31InserirDadosERetornarID {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    // Para retornar o ID é necessário acrescentar essa linha
                    Statement.RETURN_GENERATED_KEYS);

            // o set possui dois parâmetros, i = número da posição da interrogação e s = valor
            st.setString(1, "Joao");
            st.setString(2, "joao@gmail.com");
            // Para datas no SQL é necessário criar um objeto java.sql.Date
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4);

            // O comando abaixo realiza a atualização e retorna a quantidade de linhas alteradas
            int rowsAffected = st.executeUpdate();

            if (rowsAffected>0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
