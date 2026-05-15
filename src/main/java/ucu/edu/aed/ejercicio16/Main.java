package ucu.edu.aed.ejercicio16;

public class Main {

    public static void main(String[] args) {

        NodoPersona abuela = new NodoPersona(new Persona("Ana", 1940));

        NodoPersona hijo1 = new NodoPersona(new Persona("Carlos", 1965));
        NodoPersona hijo2 = new NodoPersona(new Persona("Maria", 1968));
        NodoPersona hijo3 = new NodoPersona(new Persona("Pedro", 1970));

        NodoPersona nieto1 = new NodoPersona(new Persona("Lucas", 1990));
        NodoPersona nieto2 = new NodoPersona(new Persona("Sofia", 1992));
        NodoPersona nieto3 = new NodoPersona(new Persona("Mateo", 1995));
        NodoPersona nieto4 = new NodoPersona(new Persona("Valentina", 1998));

        NodoPersona bisnieto1 = new NodoPersona(new Persona("Emma", 2020));
        NodoPersona bisnieto2 = new NodoPersona(new Persona("Leo", 2022));

        abuela.agregarHijo(hijo1);
        abuela.agregarHijo(hijo2);
        abuela.agregarHijo(hijo3);

        hijo1.agregarHijo(nieto1);
        hijo1.agregarHijo(nieto2);

        hijo2.agregarHijo(nieto3);

        hijo3.agregarHijo(nieto4);

        nieto1.agregarHijo(bisnieto1);
        nieto2.agregarHijo(bisnieto2);

        System.out.println("Altura: " + abuela.altura());

        System.out.println("Total personas: " + abuela.contar());

        System.out.println("\nGeneración 2:");
        abuela.generacion(2);

        System.out.println("\nDescendientes de Ana:");
        abuela.listarDescendientes();

        System.out.println("\n¿Emma es descendiente?");
        System.out.println(abuela.esDescendiente("Emma"));
    }
}