package logica;

public class Rutinas {
    int id;
    int alumno_dni;
    String ejercicios;
    String series_repeticiones;
    String peso_ejercicio;
    String descanso;
    String musculo_objetivo;
    String dia;
    
    public Rutinas() {
    }
    
    public Rutinas(int alumno_dni, String ejercicios, String series_repeticiones, 
                   String peso_ejercicio, String descanso, String musculo_objetivo, String dia) {
        this.alumno_dni = alumno_dni;
        this.ejercicios = ejercicios;
        this.series_repeticiones = series_repeticiones;
        this.peso_ejercicio = peso_ejercicio;
        this.descanso = descanso;
        this.musculo_objetivo = musculo_objetivo;
        this.dia = dia;
    }
    
    public Rutinas(int id, int alumno_dni, String ejercicios, String series_repeticiones, 
                   String peso_ejercicio, String descanso, String musculo_objetivo, String dia) {
        this.id = id;
        this.alumno_dni = alumno_dni;
        this.ejercicios = ejercicios;
        this.series_repeticiones = series_repeticiones;
        this.peso_ejercicio = peso_ejercicio;
        this.descanso = descanso;
        this.musculo_objetivo = musculo_objetivo;
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlumnoDni() {
        return alumno_dni;
    }

    public void setAlumnoDni(int alumno_dni) {
        this.alumno_dni = alumno_dni;
    }

    public String getEjercicio() {
        return ejercicios;
    }

    public void setEjercicio(String ejercicios) {
        this.ejercicios = ejercicios;
    }

    public String getSeriesRepeticiones() {
        return series_repeticiones;
    }

    public void setSeriesRepeticiones(String series_repeticiones) {
        this.series_repeticiones = series_repeticiones;
    }

    public String getPesoEjercicio() {
        return peso_ejercicio;
    }

    public void setPesoEjercicio(String peso_ejercicio) {
        this.peso_ejercicio = peso_ejercicio;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getMusculoObjetivo() {
        return musculo_objetivo;
    }

    public void setMusculoObjetivo(String musculo_objetivo) {
        this.musculo_objetivo = musculo_objetivo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Rutinas{" + "id=" + id + ", alumno_dni=" + alumno_dni + 
               ", ejercicios=" + ejercicios + ", series_repeticiones=" + series_repeticiones + 
               ", peso_ejercicio=" + peso_ejercicio + ", descanso=" + descanso + 
               ", musculo_objetivo=" + musculo_objetivo + ", dia=" + dia + '}';
    }
}
    
