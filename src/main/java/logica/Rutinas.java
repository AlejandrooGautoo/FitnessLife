package logica;


public class Rutinas {
    String ejercicio;
    int seriesRepeticiones;
    int pesoEjercicio;
    int descanso;
    String musculoObjetivo;
    String dia;

    public Rutinas(String ejercicio, int seriesRepeticiones, int pesoEjercicio, int descanso, String musculoObjetivo, String dia) {
        this.ejercicio = ejercicio;
        this.seriesRepeticiones = seriesRepeticiones;
        this.pesoEjercicio = pesoEjercicio;
        this.descanso = descanso;
        this.musculoObjetivo = musculoObjetivo;
        this.dia = dia;
    }

    public Rutinas() {
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public int getSeriesRepeticiones() {
        return seriesRepeticiones;
    }

    public int getPesoEjercicio() {
        return pesoEjercicio;
    }

    public int getDescanso() {
        return descanso;
    }

    public String getMusculoObjetivo() {
        return musculoObjetivo;
    }

    public String getDia() {
        return dia;
    }

    public void setEjercicio(String ejercici) {
        this.ejercicio = ejercici;
    }

    public void setSeriesRepeticiones(int seriesRepeticiones) {
        this.seriesRepeticiones = seriesRepeticiones;
    }

    public void setPesoEjercicio(int pesoEjercicio) {
        this.pesoEjercicio = pesoEjercicio;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public void setMusculoObjetivo(String musculoObjetivo) {
        this.musculoObjetivo = musculoObjetivo;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Rutinas{" + "ejercicio=" + ejercicio + ", seriesRepeticiones=" + seriesRepeticiones + ", pesoEjercicio=" + pesoEjercicio + ", descanso=" + descanso + ", musculoObjetivo=" + musculoObjetivo + ", dia=" + dia + '}';
    }
}
    
