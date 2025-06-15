package logica;

import java.time.LocalDate;

public class FichaMedico {
    
    private String nombrePaciente;
    private String apellidoPaciente;
    private LocalDate fechaNacimiento;
    private String dniPaciente;
    private String grupoSanguineo;
    private double alturaCm; 
    private double pesoKg;   
    private String alergias; 
    private String enfermedadesCronicas; 
    private String medicamentosActuales; 
    private LocalDate fechaUltimaConsulta;
    private String observacionesAdicionales;
    

   
    public FichaMedico(String nombrePaciente, String apellidoPaciente, LocalDate fechaNacimiento, String dniPaciente) {
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.fechaNacimiento = fechaNacimiento;
        this.dniPaciente = dniPaciente;
        this.grupoSanguineo = "";
        this.alturaCm = 0.0;
        this.pesoKg = 0.0;
        this.alergias = "Ninguna";
        this.enfermedadesCronicas = "Ninguna";
        this.medicamentosActuales = "Ninguno";
        this.fechaUltimaConsulta = null; 
        this.observacionesAdicionales = "";
    }

   
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public double getAlturaCm() {
        return alturaCm;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public LocalDate getFechaUltimaConsulta() {
        return fechaUltimaConsulta;
    }

    public String getObservacionesAdicionales() {
        return observacionesAdicionales;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public void setAlturaCm(double alturaCm) {
        this.alturaCm = alturaCm;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    public void setFechaUltimaConsulta(LocalDate fechaUltimaConsulta) {
        this.fechaUltimaConsulta = fechaUltimaConsulta;
    }

    public void setObservacionesAdicionales(String observacionesAdicionales) {
        this.observacionesAdicionales = observacionesAdicionales;
    }

    @Override
    public String toString() {
        return "FichaMedico{" + "nombrePaciente=" + nombrePaciente + ", apellidoPaciente=" + apellidoPaciente + ", fechaNacimiento=" + fechaNacimiento + ", dniPaciente=" + dniPaciente + ", grupoSanguineo=" + grupoSanguineo + ", alturaCm=" + alturaCm + ", pesoKg=" + pesoKg + ", alergias=" + alergias + ", enfermedadesCronicas=" + enfermedadesCronicas + ", medicamentosActuales=" + medicamentosActuales + ", fechaUltimaConsulta=" + fechaUltimaConsulta + ", observacionesAdicionales=" + observacionesAdicionales + '}';
    }
    
}
