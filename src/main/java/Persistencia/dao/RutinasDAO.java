package Persistencia.dao;

import logica.Rutinas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutinasDAO {
    private Connection connection;
    
    public RutinasDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void crearTabla() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS rutinas(
                idRutina INTEGER PRIMARY KEY AUTO_INCREMENT,
                ejercicios VARCHAR(100),
                series_repeticiones INT,
                peso_ejercicio INT,
                descanso INT,
                musculo_objetivo VARCHAR(100),
                dia VARCHAR(100),
                tipoRutina VARCHAR(100)
                )
                """;
        PreparedStatement stmt = connection.prepareStatement(sql); 
        stmt.executeUpdate();   
        stmt.close();
    }
    
    //le pasamos al metodo un parametro de tipo Rutinas llamado rutina
   public int añadir(Rutinas rutina) throws SQLException {
    String sql = "INSERT INTO rutinas (ejercicios, series_repeticiones, peso_ejercicio, descanso, musculo_objetivo, dia, tipoRutina) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);       
    
    stmt.setString(1, rutina.getEjercicio());
    stmt.setString(2, rutina.getSeriesRepeticiones());
    stmt.setString(3, rutina.getPesoEjercicio());
    stmt.setString(4, rutina.getDescanso());
    stmt.setString(5, rutina.getMusculoObjetivo());
    stmt.setString(6, rutina.getDia());
    stmt.setString(7, rutina.getTipoRutina());
    
    int filasAfectadas = stmt.executeUpdate();
    
    int idGenerado = -1;
    if (filasAfectadas > 0) {
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1);
        }
        rs.close();
    }
    
    stmt.close();
    return idGenerado; // devuelve el ID generado
}
    
    
    //obtenemos con esto la lista completa de las rutians
    public List<Rutinas> obtenerRutina() {
    List<Rutinas> rutinas = new ArrayList<>();
    String sql = "SELECT * FROM rutinas";
    
    PreparedStatement stmt = null;//prepared statement lleva ordenes a la base de datos
    ResultSet rs = null; //result set obtiene los resultados
    
    try {
        stmt = connection.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            Rutinas rutina = new Rutinas(
                rs.getInt("idRutina"),
                rs.getString("ejercicios"),
                rs.getString("series_repeticiones"),
                rs.getString("peso_ejercicio"),
                rs.getString("descanso"),
                rs.getString("musculo_objetivo"),
                rs.getString("dia"),
                rs.getString("tipoRutina")
            );
            rutinas.add(rutina);
        }
    } catch (SQLException ex) {
        System.out.println("Error al obtener rutinas: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    
    return rutinas;
}

    
    public boolean actualizarRutina(Rutinas rutina) throws SQLException {
        String sql = "UPDATE rutinas SET ejercicios = ?, series_repeticiones = ?, peso_ejercicio = ?, descanso = ?, musculo_objetivo = ?, dia = ?, tipoRutina = ? WHERE idRutina = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, rutina.getEjercicio());
        stmt.setString(2, rutina.getSeriesRepeticiones());
        stmt.setString(3, rutina.getPesoEjercicio());
        stmt.setString(4, rutina.getDescanso());
        stmt.setString(5, rutina.getMusculoObjetivo());
        stmt.setString(6, rutina.getDia());
        stmt.setString(7, rutina.getTipoRutina());
        stmt.setInt(8, rutina.getIdRutina());
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
    
    public boolean borrar(int idRutina) throws SQLException {
        String sql = "DELETE FROM rutinas WHERE idRutina = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idRutina);
        
        int filasAfectadas = stmt.executeUpdate();
        stmt.close();
        return filasAfectadas > 0;
    }
}