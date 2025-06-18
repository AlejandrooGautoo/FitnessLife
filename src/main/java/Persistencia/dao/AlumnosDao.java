package Persistencia.dao;

import logica.Alumnos;
import java.sql.*;

public class AlumnosDao {
    private Connection connection;
    private RutinasDAO rutinasDAO;
    
    public AlumnosDao(Connection connection) {
        this.connection = connection;
        this.rutinasDAO = new RutinasDAO(connection);
    }
    
    public void crearTabla() throws SQLException {
        String sql = """
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
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }
    
    public boolean añadir(Alumnos alumno) throws SQLException {
        String sql = "INSERT INTO alumnos (dni, nombre, apellido, peso, altura, genero, rutina, dias) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alumno.getDni());
        stmt.setString(2, alumno.getNombre());
        stmt.setString(3, alumno.getApellido());
        stmt.setInt(4, alumno.getPeso());
        stmt.setDouble(5, alumno.getAltura());
        stmt.setString(6, alumno.getGenero());
        stmt.setString(7, alumno.getRutina());
        stmt.setInt(8, alumno.getDias());
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        
        // Si se añadió el alumno correctamente, crear rutina inicial vacía
        if (filasAfectadas > 0) {
            rutinasDAO.crearRutinaInicial(alumno.getDni());
        }
        
        return filasAfectadas > 0;
    }
    
    public boolean borrar(int dni) throws SQLException {
        String sql = "DELETE FROM alumnos WHERE dni = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, dni);
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
}
