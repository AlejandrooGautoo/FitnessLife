package Persistencia.connection;

import Persistencia.config.DataBaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            DataBaseConfig.URL,
            DataBaseConfig.USER,
            DataBaseConfig.PASSWORD
        );
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}