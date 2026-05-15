package ucu.edu.aed.ejercicio16;

public class Persona {

    String nombre;
    int anioNacimiento;

    public Persona(String nombre, int anioNacimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
    }

    @Override
    public String toString() {
        return nombre + " (" + anioNacimiento + ")";
    }
}