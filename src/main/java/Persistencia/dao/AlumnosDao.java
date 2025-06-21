package Persistencia.dao;


import logica.Alumnos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnosDao {
    private Connection connection;
    
    public AlumnosDao(Connection connection) {
        this.connection = connection;
        new RutinasDAO(connection);
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
    public boolean actualizarAlumno(Alumnos alumno) throws SQLException {
    String sql = "UPDATE alumnos SET nombre = ?, apellido = ?, peso = ?, altura = ?, genero = ?, rutina = ?, dias = ? WHERE dni = ?";
    
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1, alumno.getNombre());
    stmt.setString(2, alumno.getApellido());
    stmt.setInt(3, alumno.getPeso());
    stmt.setDouble(4, alumno.getAltura());
    stmt.setString(5, alumno.getGenero());
    stmt.setString(6, alumno.getRutina());
    stmt.setInt(7, alumno.getDias());
    stmt.setInt(8, alumno.getDni()); // WHERE dni = ?
    
    int filasAfectadas = stmt.executeUpdate();
    stmt.close();
    return filasAfectadas > 0;
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
    

    public List<Alumnos> obtenerTodosAlumnos() {
        List<Alumnos> alumnos = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {             
            String sql = "SELECT * FROM alumnos"; // Columnas de tu tabla
            ps = this.connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Alumnos alumno = new Alumnos();
                // Usar los nombres de columna tal como están en tu tabla y los tipos Java que corresponden
                alumno.setDni(rs.getInt("dni"));             // DNI como int
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setPeso(rs.getInt("peso"));           // Peso como int
                alumno.setAltura(rs.getDouble("altura"));
                alumno.setGenero(rs.getString("genero"));
                alumno.setRutina(rs.getString("rutina"));
                alumno.setDias(rs.getInt("dias"));           // Días como int
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener alumnos de la base de datos: " + ex.getMessage(), "Error de Lectura DB", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Imprime la traza de la pila
        } finally {
            // Asegurarse de cerrar todos los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos de obtenerTodosAlumnos: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return alumnos;
    }
}