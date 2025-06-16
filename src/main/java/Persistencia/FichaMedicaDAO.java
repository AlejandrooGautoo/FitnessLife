/*package Persistencia;

import logica.FichaMedico; // <-- Asegúrate de que el nombre de la clase sea exacto (FichaMedico)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FichaMedicaDAO {

    public FichaMedico obtenerFichaMedicaPorDNI(String dniAlumno) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FichaMedico ficha = null; // Usamos FichaMedico, el nombre de tu clase en logica
        try {
            conn = bddConexion.getConnection();
            String sql = "SELECT * FROM ficha_medica WHERE dni_alumno = ?"; // <-- Asegúrate que la columna se llame 'dni_alumno'
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniAlumno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ficha = new FichaMedico(); // Instanciamos tu clase POJO
                // Asigna los valores del ResultSet a las propiedades de tu objeto FichaMedico
                // ¡Ajusta los nombres de las columnas a como estén en tu tabla 'ficha_medica' en la BD!
                ficha.setId(rs.getInt("id_ficha_medica")); // Suponiendo que tienes un ID para la ficha médica
                ficha.setDniAlumno(rs.getInt("dni_alumno"));
                ficha.setGrupoSanguineo(rs.getString("grupo_sanguineo")); // Ejemplo de campo
                ficha.setAlergias(rs.getString("alergias"));             // Ejemplo de campo
                ficha.setEnfermedades(rs.getString("enfermedades"));     // Ejemplo de campo
                // Continúa con todos los demás campos de tu ficha médica
                // ficha.setOtroCampo(rs.getString("nombre_columna_bd"));
            }
        } finally {
            bddConexion.close(rs);
            bddConexion.close(pstmt);
            bddConexion.close(conn);
        }
        return ficha;
    }
    
    // Puedes añadir métodos para insertar, actualizar, eliminar fichas médicas aquí también
}*/