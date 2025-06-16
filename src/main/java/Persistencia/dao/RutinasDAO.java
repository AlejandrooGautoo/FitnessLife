package Persistencia.dao;

/* 
package Persistencia;

import logica.Rutinas; // Importa tu clase POJO Rutinas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutinaDAO {

   
    public void insertarRutina(Rutinas rutina) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = bddConexion.getConnection();
            // Asegúrate que los nombres de las columnas coincidan con tu tabla 'rutinas' en la BD
            String sql = "INSERT INTO rutinas (ejercicio, series_repeticiones, peso_ejercicio, descanso, musculo_objetivo, dia) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rutina.getEjercicio());
            pstmt.setInt(2, rutina.getSeriesRepeticiones());
            pstmt.setInt(3, rutina.getPesoEjercicio());
            pstmt.setInt(4, rutina.getDescanso());
            pstmt.setString(5, rutina.getMusculoObjetivo());
            pstmt.setString(6, rutina.getDia());
            pstmt.executeUpdate();
            System.out.println("Rutina para ejercicio '" + rutina.getEjercicio() + "' insertada correctamente.");
        } finally {
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
    }

   
    public Rutinas obtenerRutinaPorEjercicioYDia(String ejercicio, String dia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Rutinas rutina = null;
        try {
            conn = bddConexion.getConnection();
            String sql = "SELECT * FROM rutinas WHERE ejercicio = ? AND dia = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ejercicio);
            pstmt.setString(2, dia);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rutina = new Rutinas();
                // Asigna los valores del ResultSet a las propiedades de tu objeto Rutinas
                // ¡Ajusta los nombres de las columnas a como estén en tu tabla 'rutinas' en la BD!
                rutina.setEjercicio(rs.getString("ejercicio"));
                rutina.setSeriesRepeticiones(rs.getInt("series_repeticiones"));
                rutina.setPesoEjercicio(rs.getInt("peso_ejercicio"));
                rutina.setDescanso(rs.getInt("descanso"));
                rutina.setMusculoObjetivo(rs.getString("musculo_objetivo"));
                rutina.setDia(rs.getString("dia"));
            }
        } finally {
            bddConexion.close(rs);
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
        return rutina;
    }

    public List<Rutinas> obtenerTodasLasRutinas() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Rutinas> listaRutinas = new ArrayList<>();
        try {
            conn = bddConexion.getConnection();
            String sql = "SELECT * FROM rutinas";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Rutinas rutina = new Rutinas();
                rutina.setEjercicio(rs.getString("ejercicio"));
                rutina.setSeriesRepeticiones(rs.getInt("series_repeticiones"));
                rutina.setPesoEjercicio(rs.getInt("peso_ejercicio"));
                rutina.setDescanso(rs.getInt("descanso"));
                rutina.setMusculoObjetivo(rs.getString("musculo_objetivo"));
                rutina.setDia(rs.getString("dia"));
                listaRutinas.add(rutina);
            }
        } finally {
            bddConexion.close(rs);
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
        return listaRutinas;
    }

    
    public void actualizarRutina(Rutinas rutina) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = bddConexion.getConnection();
            String sql = "UPDATE rutinas SET series_repeticiones = ?, peso_ejercicio = ?, descanso = ?, musculo_objetivo = ? WHERE ejercicio = ? AND dia = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rutina.getSeriesRepeticiones());
            pstmt.setInt(2, rutina.getPesoEjercicio());
            pstmt.setInt(3, rutina.getDescanso());
            pstmt.setString(4, rutina.getMusculoObjetivo());
            pstmt.setString(5, rutina.getEjercicio()); // Usado para la condición WHERE
            pstmt.setString(6, rutina.getDia());       // Usado para la condición WHERE
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Rutina para ejercicio '" + rutina.getEjercicio() + "' y día '" + rutina.getDia() + "' actualizada correctamente.");
            } else {
                System.out.println("No se encontró la rutina para actualizar (ejercicio: " + rutina.getEjercicio() + ", día: " + rutina.getDia() + ").");
            }
        } finally {
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
    }

    
    public void eliminarRutina(String ejercicio, String dia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = bddConexion.getConnection();
            String sql = "DELETE FROM rutinas WHERE ejercicio = ? AND dia = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ejercicio);
            pstmt.setString(2, dia);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Rutina para ejercicio '" + ejercicio + "' y día '" + dia + "' eliminada correctamente.");
            } else {
                System.out.println("No se encontró la rutina para eliminar (ejercicio: " + ejercicio + ", día: " + dia + ").");
            }
        } finally {
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
    }
}*/