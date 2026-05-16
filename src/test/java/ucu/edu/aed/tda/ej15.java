package ucu.edu.aed.tda;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.ejercicio15.Libro;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

class LibroTest {

    // mismo ISBN, distinta referencia
    private final Libro a = new Libro("978-0-13-468599-1", "Clean Code", "Martin", 2008);
    private final Libro b = new Libro("978-0-13-468599-1", "Clean Code", "Martin", 2008);
    // ISBN diferente
    private final Libro c = new Libro("978-0-20-163361-0", "The Pragmatic Programmer", "Hunt", 1999);

    @Test
    void equalsReflexivo() {
        assertEquals(a, a);
    }

    @Test
    void equalsSimétrico() {
        assertEquals(a, b);
        assertEquals(b, a);
    }

    @Test
    void equalsTransitivo() {
        Libro c2 = new Libro("978-0-13-468599-1", "Clean Code", "Martin", 2008);
        assertEquals(a, b);
        assertEquals(b, c2);
        assertEquals(a, c2);
    }

    @Test
    void equalsDistintoIsbn() {
        assertNotEquals(a, c);
    }

    @Test
    void equalsConNull() {
        assertNotEquals(null, a);
    }

    @Test
    void hashCodeConsistente() {
        assertEquals(a.hashCode(), a.hashCode());
    }

    @Test
    void hashCodeIgualSiEquals() {
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void hashSetNoDuplicadosMismoIsbn() {
        HashSet set = new HashSet();
        set.add(a);
        set.add(b); // mismo ISBN → no se agrega
        assertEquals(1, set.size());
    }

    @Test
    void hashSetAdmiteIsbnDistintos() {
        HashSet set = new HashSet();
        set.add(a);
        set.add(c);
        assertEquals(2, set.size());
    }
}