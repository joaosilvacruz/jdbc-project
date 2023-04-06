package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    // Criando métodos estáticos auxiliares

    // Variável de conexão com banco de dados
    private static Connection conn = null;

    // Método para realizar a conexão com banco de dados
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Carregando propriedades do banco de dados e a url
                Properties props = loadProperties();
                String url = props.getProperty("dburl");

                // Utilizando DriverManager para obter a conexão com o banco de dados
                conn = DriverManager.getConnection(url, props);
            }
            // O catch captura uma possível exceção existente no getConnection
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection() {
        if (conn != null) {
            // Método para fechar a conexão
            try {
                conn.close();

            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    // Método para carregar as propriedades do arquivo db.properties
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    // Método para fechar o Statemente (para não poluir o programa principal)
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }

    // Método para fechar o ResultSet
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }
}
