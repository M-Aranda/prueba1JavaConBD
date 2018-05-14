
package Registros;

import java.io.Serializable;


public class Alumno implements Serializable {
    
    String run;
    String sexo;
    String nombre;
    String direccion;

    public Alumno(String run, String sexo, String nombre, String direccion) {
        this.run = run;
        this.sexo = sexo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Alumno{" + "run=" + run + ", sexo=" + sexo + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
}
