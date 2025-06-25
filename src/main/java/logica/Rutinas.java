package logica;

public class Rutinas {
    int idRutina;
    
    String ejercicios;
    String series_repeticiones;
    String peso_ejercicio;
    String descanso;
    String musculo_objetivo;
    String dia;
    String tipoRutina;
    
    public Rutinas() {
    }
    
    public Rutinas(int idRutina, String ejercicios, String series_repeticiones, 
                   String peso_ejercicio, String descanso, String musculo_objetivo, String dia, String tipoRutina) {
        this.idRutina = idRutina;//el atributo idRutina es igual al parametro idRutina que obtengo
        this.ejercicios = ejercicios;
        this.series_repeticiones = series_repeticiones;
        this.peso_ejercicio = peso_ejercicio;
        this.descanso = descanso;
        this.musculo_objetivo = musculo_objetivo;
        this.dia = dia;
        this.tipoRutina = tipoRutina;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int id) {
        this.idRutina = id;
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
    
    public String getTipoRutina(){
        return tipoRutina;
    }
    
    public void setTipoRutina(String tipoRutina){
        this.tipoRutina = tipoRutina;
    }
    @Override
    public String toString() {
        return "Rutinas{" + "id=" + idRutina + 
               ", ejercicios=" + ejercicios + ", series_repeticiones=" + series_repeticiones + 
               ", peso_ejercicio=" + peso_ejercicio + ", descanso=" + descanso + 
               ", musculo_objetivo=" + musculo_objetivo + ", dia=" + dia + "tipoRutina=" + tipoRutina +'}';
    }
}
    
