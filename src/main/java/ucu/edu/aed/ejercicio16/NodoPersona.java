package ucu.edu.aed.ejercicio16;
import java.util.*;

public class NodoPersona {

    Persona persona;
    List<NodoPersona> hijos = new ArrayList<>();

    public NodoPersona(Persona persona) {
        this.persona = persona;
    }

    public void agregarHijo(NodoPersona hijo) {
        hijos.add(hijo);
    }

    //Listar descendientes
    public void listarDescendientes() {
        for (NodoPersona h : hijos) {
            System.out.println(h.persona);
            h.listarDescendientes();
        }
    }

    //Altura del árbol
    public int altura() {

        int max = 0;

        for (NodoPersona h : hijos) {
            max = Math.max(max, h.altura());
        }

        return max + 1;
    }

    //Cantidad total de personas
    public int contar() {

        int total = 1;

        for (NodoPersona h : hijos) {
            total += h.contar();
        }

        return total;
    }

    //Personas de una generación
    public void generacion(int g) {

        if (g == 0) {
            System.out.println(persona);
            return;
        }

        for (NodoPersona h : hijos) {
            h.generacion(g - 1);
        }
    }

    //Determinar si es descendiente
    public boolean esDescendiente(String nombre) {

        for (NodoPersona h : hijos) {

            if (h.persona.nombre.equals(nombre))
                return true;

            if (h.esDescendiente(nombre))
                return true;
        }

        return false;
    }

}