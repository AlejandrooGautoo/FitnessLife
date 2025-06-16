package Persistencia.connection;

import Persistencia.config.DataBaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase simple para manejar conexiones a la base de datos
 */
public class ConexionBD {
    
    /**
     * Obtiene una conexión a la base de datos
     * @return Connection objeto de conexión
     * @throws SQLException si hay error en la conexión
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            DataBaseConfig.URL,
            DataBaseConfig.USER,
            DataBaseConfig.PASSWORD
        );
    }
    
    /**
     * Cierra una conexión de forma segura
     * @param connection conexión a cerrar
     */
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