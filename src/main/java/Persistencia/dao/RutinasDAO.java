package Persistencia.dao;

import logica.Rutinas;
import java.sql.*;

public class RutinasDAO {
    private Connection connection;
    
    public RutinasDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void crearTabla() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS rutinas(
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                alumno_dni INTEGER NOT NULL,
                ejercicios TEXT,
                series_repeticiones TEXT,
                peso_ejercicio TEXT,
                descanso TEXT,
                musculo_objetivo TEXT,
                dia TEXT,
                FOREIGN KEY (alumno_dni) REFERENCES alumnos(dni) ON DELETE CASCADE
                )
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }
    
    public boolean añadir(Rutinas rutina) throws SQLException {
        String sql = "INSERT INTO rutinas (alumno_dni, ejercicios, series_repeticiones, peso_ejercicio, descanso, musculo_objetivo, dia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, rutina.getAlumnoDni());
        stmt.setString(2, rutina.getEjercicio());
        stmt.setString(3, rutina.getSeriesRepeticiones());
        stmt.setString(4, rutina.getPesoEjercicio());
        stmt.setString(5, rutina.getDescanso());
        stmt.setString(6, rutina.getMusculoObjetivo());
        stmt.setString(7, rutina.getDia());
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
    
    public boolean crearRutinaInicial(int alumno_dni) throws SQLException {
        String sql = "INSERT INTO rutinas (alumno_dni, ejercicios, series_repeticiones, peso_ejercicio, descanso, musculo_objetivo, dia) VALUES (?, '', '', '', '', '', '')";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alumno_dni);
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
    
    public Rutinas obtenerPorAlumno(int alumno_dni) throws SQLException {
        String sql = "SELECT * FROM rutinas WHERE alumno_dni = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alumno_dni);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Rutinas rutina = new Rutinas(
                rs.getInt("id"),
                rs.getInt("alumno_dni"),
                rs.getString("ejercicios"),
                rs.getString("series_repeticiones"),
                rs.getString("peso_ejercicio"),
                rs.getString("descanso"),
                rs.getString("musculo_objetivo"),
                rs.getString("dia")
            );
            rs.close();
            stmt.close();
            return rutina;
        }
        rs.close();
        stmt.close();
        return null;
    }
    
    public boolean actualizar(Rutinas rutina) throws SQLException {
        String sql = "UPDATE rutinas SET ejercicios = ?, series_repeticiones = ?, peso_ejercicio = ?, descanso = ?, musculo_objetivo = ?, dia = ? WHERE alumno_dni = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, rutina.getEjercicio());
        stmt.setString(2, rutina.getSeriesRepeticiones());
        stmt.setString(3, rutina.getPesoEjercicio());
        stmt.setString(4, rutina.getDescanso());
        stmt.setString(5, rutina.getMusculoObjetivo());
        stmt.setString(6, rutina.getDia());
        stmt.setInt(7, rutina.getAlumnoDni());
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
    
    public boolean borrar(int alumno_dni) throws SQLException {
        String sql = "DELETE FROM rutinas WHERE alumno_dni = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alumno_dni);
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
}