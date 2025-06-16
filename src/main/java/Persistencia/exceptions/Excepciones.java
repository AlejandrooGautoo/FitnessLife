package Persistencia.exceptions;

/**
 * Excepción simple para errores de persistencia
 */
public class Excepciones extends Exception {
    
    // Mensajes predefinidos
    public static final String ERROR_CONEXION = "Error al conectar con la base de datos";
    public static final String ERROR_INSERTAR = "Error al insertar datos";
    public static final String ERROR_ACTUALIZAR = "Error al actualizar datos";
    public static final String ERROR_ELIMINAR = "Error al eliminar datos";
    public static final String ERROR_CONSULTAR = "Error al consultar datos";
    public static final String REGISTRO_NO_ENCONTRADO = "Registro no encontrado";
    
    public Excepciones(String mensaje) {
        super(mensaje);
    }
}