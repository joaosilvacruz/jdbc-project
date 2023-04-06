package application;

import db.DB;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        // Conexão com o banco de dados
        Connection conn = DB.getConnection();
        DB.closeConnection();

    }
}
