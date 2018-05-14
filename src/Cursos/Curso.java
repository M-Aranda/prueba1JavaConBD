package Cursos;

import java.io.Serializable;

public class Curso implements Serializable {

    private String nombre;
    private int matricula;
    private int mensualidad;
    private int cantidadDeMeses;

    public Curso() {
    }

    public Curso(String nombre, int matricula, int mensualidad, int cantidadDeMeses) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.mensualidad = mensualidad;
        this.cantidadDeMeses = cantidadDeMeses;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(int mensualidad) {
        this.mensualidad = mensualidad;
    }

    public int getCantidadDeMeses() {
        return cantidadDeMeses;
    }

    public void setCantidadDeMeses(int cantidadDeMeses) {
        this.cantidadDeMeses = cantidadDeMeses;
    }

    @Override
    public String toString() {
        return "Curso{" + "Nombre=" + nombre + ", Matricula=" + matricula + ", Mensualidad=" + mensualidad + ", Duracion=" + cantidadDeMeses +" meses"+ '}';
    }
    
    
    

}
