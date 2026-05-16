package ucu.edu.aed.ejercicio15;

import java.util.Objects;


/**
 * Ejercicio 15
 *
 * 1. el ISBN identifica un libro a nivel mundial.
 *    Dos objetos Libro con el mismo ISBN representan el mismo libro aunque difieran
 *    en título o autor (erratas, ediciones corregidas). Por eso equals y hashCode
 *    se basan exclusivamente en isbn.
 *
 * 3. si equals usa isbn pero hashCode usa titulo, dos libros con
 *    el mismo ISBN pero distinto título serán equals=true pero tendrán hashCodes
 *    distintos. Esto viola el contrato: "si a.equals(b) entonces a.hashCode()==b.hashCode()".
 *    En un HashSet/HashMap ambos objetos terminarían en buckets distintos, por lo que
 *    el set aceptaría duplicados lógicos y el map tendría dos entradas para la misma clave.
 *
 * 4 y 5. Ver LibroTest: dos objetos distintos con el mismo ISBN → el HashSet
 *    los trata como uno solo y queda con size()==1.
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn  = isbn;
        this.titulo = titulo;
        this.autor  = autor;
        this.anio   = anio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro otro = (Libro) o;
        return Objects.equals(isbn, otro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public String getIsbn()   { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor()  { return autor; }
    public int    getAnio()   { return anio; }
}
