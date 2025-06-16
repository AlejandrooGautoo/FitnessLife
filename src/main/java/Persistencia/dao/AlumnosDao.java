package Persistencia.dao;

import logica.Alumnos;
import java.sql.*;
public class AlumnosDao {
    private Connection connection;
    
    public AlumnosDao(Connection connection){
        this.connection = connection;
    }
    
    public void crearTabla()throws SQLException{
    String sql= """
                CREATE TABLE IF NOT EXISTS alumnos(
                dni INTEGER PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                apellido VARCHAR(100) NOT NULL,
                peso INTEGER NOT NULL,
                altura REAL NOT NULL,
                genero VARCHAR(50) NOT NULL,
                rutina VARCHAR(100),
                dias INTEGER NOT NULL
                )
                """;
            try(PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.executeUpdate();
            }
    }
    
    public boolean añadir(Alumnos alumno){
    
        String sql = "INSERT INTO alumnos (dni, nombre, apellido, peso, altura, genero, rutina, dias) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, alumno.getDni());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getApellido());
            stmt.setInt(4, alumno.getPeso());
            stmt.setDouble(5, alumno.getAltura());
            stmt.setString(6, alumno.getGenero());
            stmt.setString(7, alumno.getRutina());
            stmt.setInt(8, alumno.getDias());
        
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        }catch (SQLException e){
            System.err.println("ERROR al añadir alumno: " + e.getMessage());
            return false;
        }
    }
    
    public boolean borrar(int dni){
        String sql = "DELETE FROM alumnos WHERE dni = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, dni);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        }catch (SQLException e){
            System.err.println("Error al borrar alumno:" + e.getMessage());
            return false;
        }
    }     
}
