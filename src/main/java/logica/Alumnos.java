
package logica;

/**
 *
 * @author santi
 */
public class Alumnos {
    String nombre; 
    String apellido; 
    int dni;
    int peso; 
    double altura; 
    String genero; 
    String rutina;
    int dias;

    public Alumnos() {
    }

    public Alumnos(String nombre, String apellido, int dni, int peso, double altura, String genero, String rutina, int dias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
        this.rutina = rutina;
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRutina() {
        return rutina;
    }

    public void setRutina(String rutina) {
        this.rutina = rutina;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "Alumnos{" + "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", peso=" + peso + ", altura=" + altura + ", genero=" + genero + ", rutina=" + rutina + ", dias=" + dias + '}';
    }
    
    
    
}
