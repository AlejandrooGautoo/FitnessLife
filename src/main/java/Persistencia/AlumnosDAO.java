/*package Persistencia;

import logica.Alumnos;      // Tu clase POJO de Alumno
import logica.FichaMedico; // ¡Este es el import que faltaba si interactúa con FichaMedica!
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    // ... (métodos insertarAlumno, obtenerAlumnoPorDNI, obtenerTodosLosAlumnos ya mostrados)

    // Nuevo método para obtener la ficha médica de un alumno por su DNI
    public FichaMedico obtenerFichaMedicaDeAlumno(String dniAlumno) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FichaMedico ficha = null;
        try {
            conn = bddConexion.getConnection();
            String sql = "SELECT FROM ficha_medica WHERE dni_alumno = ?"; // Asumiendo que 'ficha_medica' tiene 'dni_alumno'
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniAlumno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ficha = new FichaMedico();
                // Aquí llenas el objeto FichaMedica con los datos del ResultSet
                ficha.setId(rs.getInt("id_ficha_medica")); // Suponiendo un ID
                ficha.setDniAlumno(rs.getInt("dni_alumno"));
                ficha.setGrupoSanguineo(rs.getString("grupo_sanguineo"));
                ficha.setAlergias(rs.getString("alergias"));
                ficha.setEnfermedades(rs.getString("enfermedades"));
                // ... y así sucesivamente con todos los campos de tu tabla ficha_medica
            }
        } finally {
            bddConexion.close(rs);
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
        return ficha;
    }
}*/