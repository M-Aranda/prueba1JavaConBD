package Registros;

import java.io.Serializable;

public class Matricula implements Serializable{

    private Alumno alumno;
    private String curso;
    private int precioMatricula;
    private int precioMensualidad;
    private int precioTotal;
    private boolean convenio;
    private String tipoConvenio;
    private int descuento;
    private int totalFinal;
    private int cantCuotas;
    private int totalCuota;

    public Matricula(Alumno alumno, String curso, int precioMatricula, int precioMensualidad, int precioTotal, boolean convenio, String tipoConvenio, int descuento, int totalFinal, int cantCuotas, int totalCuota) {
        this.alumno = alumno;
        this.curso = curso;
        this.precioMatricula = precioMatricula;
        this.precioMensualidad = precioMensualidad;
        this.precioTotal = precioTotal;
        this.convenio = convenio;
        this.tipoConvenio=tipoConvenio;
        this.descuento = descuento;
        this.totalFinal = totalFinal;
        this.cantCuotas = cantCuotas;
        this.totalCuota = totalCuota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPrecioMatricula() {
        return precioMatricula;
    }

    public void setPrecioMatricula(int precioMatricula) {
        this.precioMatricula = precioMatricula;
    }

    public int getPrecioMensualidad() {
        return precioMensualidad;
    }

    public void setPrecioMensualidad(int precioMensualidad) {
        this.precioMensualidad = precioMensualidad;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean isConvenio() {
        return convenio;
    }

    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }
    


    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(int totalFinal) {
        this.totalFinal = totalFinal;
    }

    public int getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(int cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public int getTotalCuota() {
        return totalCuota;
    }

    public void setTotalCuota(int totalCuota) {
        this.totalCuota = totalCuota;
    }

    public String getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(String tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }
    

}
