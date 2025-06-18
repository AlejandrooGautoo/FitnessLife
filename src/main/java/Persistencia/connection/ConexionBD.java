package Persistencia.connection;

import Persistencia.config.DataBaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            DataBaseConfig.URL,
            DataBaseConfig.USER,
            DataBaseConfig.PASSWORD
        );
    }
    
   public static Object[][] obtenerTodosLosAlumnos() {
        List<Object[]> alumnos = new ArrayList<>();
        
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM alumnos");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"), 
                    resultSet.getString("dni"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("altura"),
                    resultSet.getString("genero"),
                    resultSet.getString("rutina"),
                    resultSet.getInt("dias")
                };
                alumnos.add(fila);
            }
            
            closeConnection(connection);
            
        } catch (SQLException e) {
            // No hacer nada
        }
        
        return alumnos.toArray(new Object[0][]);
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