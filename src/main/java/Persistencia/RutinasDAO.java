package Persistencia;

import logica.Rutinas; // Importa tu clase POJO Rutinas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutinaDAO {

    /**
     * Inserta una nueva rutina en la base de datos.
     * @param rutina El objeto Rutinas a insertar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Obtiene una rutina por su ejercicio y día.
     * Nota: Podrías necesitar un ID único para la tabla de rutinas
     * si ejercicio y día no son suficientes para identificar una rutina única.
     * @param ejercicio El nombre del ejercicio.
     * @param dia El día de la rutina.
     * @return El objeto Rutinas encontrado, o null si no existe.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Obtiene todas las rutinas de la base de datos.
     * @return Una lista de objetos Rutinas.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Actualiza una rutina existente en la base de datos.
     * @param rutina El objeto Rutinas con los datos actualizados.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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

    /**
     * Elimina una rutina de la base de datos.
     * @param ejercicio El nombre del ejercicio de la rutina a eliminar.
     * @param dia El día de la rutina a eliminar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
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
}