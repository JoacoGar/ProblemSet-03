package ucu.edu.aed.tda;

import org.junit.jupiter.api.*;
import ucu.edu.aed.ejercicio12.TNodoTrieHashMap;
import ucu.edu.aed.ejercicio12.TTrieHashMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TTrieHashMapTest {

    private TTrieHashMap trie;

    @BeforeEach
    void setUp() {
        trie = new TTrieHashMap();
    }

    @Nested
    @DisplayName("insertar y buscar")
    class InsertarBuscar {

        @Test
        @DisplayName("buscar en trie vacío devuelve false")
        void trieVacioDevuelveFalse() {
            assertFalse(trie.buscar("hola"));
        }

        @Test
        @DisplayName("palabra insertada es encontrada")
        void palabraInsertadaSeEncuentra() {
            trie.insertar("casa");
            assertTrue(trie.buscar("casa"));
        }

        @Test
        @DisplayName("palabra no insertada no se encuentra")
        void palabraNoInsertadaNoSeEncuentra() {
            trie.insertar("casa");
            assertFalse(trie.buscar("casas"));
        }

        @Test
        @DisplayName("prefijo de una palabra no es encontrado como palabra")
        void prefijoNoEsPalabra() {
            trie.insertar("casamiento");
            assertFalse(trie.buscar("casa"));
            assertFalse(trie.buscar("cas"));
            assertFalse(trie.buscar("c"));
        }

        @Test
        @DisplayName("palabras anidadas: insertar ambas, ambas se encuentran")
        void palabrasAnidadasAmbas() {
            trie.insertar("car");
            trie.insertar("carro");
            trie.insertar("carroceria");
            assertTrue(trie.buscar("car"));
            assertTrue(trie.buscar("carro"));
            assertTrue(trie.buscar("carroceria"));
        }

        @Test
        @DisplayName("palabra insertada dos veces: no hay duplicados ni errores")
        void insercionDuplicadaSinError() {
            trie.insertar("sol");
            trie.insertar("sol");
            assertTrue(trie.buscar("sol"));
            // predecir no debe devolver duplicados
            assertEquals(1, trie.predecir("sol").size());
        }

        @Test
        @DisplayName("string vacío puede ser insertado y buscado")
        void stringVacio() {
            trie.insertar("");
            assertTrue(trie.buscar(""));
        }

        @Test
        @DisplayName("buscar string vacío en trie sin él devuelve false")
        void stringVacioNoInsertado() {
            trie.insertar("algo");
            assertFalse(trie.buscar(""));
        }

        @Test
        @DisplayName("mayúsculas y minúsculas se tratan como distintas")
        void caseSensitive() {
            trie.insertar("Java");
            assertTrue(trie.buscar("Java"));
            assertFalse(trie.buscar("java"));
            assertFalse(trie.buscar("JAVA"));
        }

        @Test
        @DisplayName("palabra de un solo carácter")
        void palabraUnCaracter() {
            trie.insertar("a");
            assertTrue(trie.buscar("a"));
            assertFalse(trie.buscar("b"));
        }
    }

    @Nested
    @DisplayName("predecir (autocompletar)")
    class Predecir {

        @BeforeEach
        void cargarDiccionario() {
            TTrieHashMap tTrieHashMap = trie;
            String[] palabras = {"casa", "casado", "casamiento", "caso", "cama", "camino",
                    "pato", "pata", "patron", "sol", "solar", "soltar",
                    "trie", "tries", "java", "javascript"};
            for (String s : palabras) {
                tTrieHashMap.insertar(s);
            }
        }

        @Test
        @DisplayName("prefijo inexistente devuelve lista vacía")
        void prefijoInexistente() {
            assertTrue(trie.predecir("xyz").isEmpty());
        }

        @Test
        @DisplayName("prefijo 'ca' devuelve todas las palabras que empiezan con 'ca'")
        void prefijoCa() {
            List<String> res = trie.predecir("ca");
            assertTrue(res.contains("casa") && res.contains("casado") && res.contains("casamiento") && res.contains("caso") && res.contains("cama") && res.contains("camino"));
            assertFalse(res.contains("pato"));
        }

        @Test
        @DisplayName("prefijo igual a palabra devuelve esa palabra y sus extensiones")
        void prefijoIgualAPalabra() {
            List<String> res = trie.predecir("sol");
            assertTrue(res.contains("sol"));
            assertTrue(res.contains("solar"));
            assertTrue(res.contains("soltar"));
        }

        @Test
        @DisplayName("prefijo que es exactamente una hoja devuelve solo esa palabra")
        void prefijoHoja() {
            List<String> res = trie.predecir("trie");
            assertTrue(res.contains("trie"));
            assertTrue(res.contains("tries"));
        }

        @Test
        @DisplayName("predicción en trie vacío devuelve lista vacía")
        void trieVacio() {
            assertTrue(new TTrieHashMap().predecir("ca").isEmpty());
        }

        @Test
        @DisplayName("no hay duplicados en la predicción tras inserción duplicada")
        void sinDuplicadosTrasInsercionDoble() {
            trie.insertar("solar");
            List<String> res = trie.predecir("solar");
            assertEquals(1, res.stream().filter(s -> s.equals("solar")).count());
        }
    }

    @Nested
    @DisplayName("buscarPatron")
    class BuscarPatron {

        @Test
        @DisplayName("patrón no presente devuelve lista vacía")
        void patronNoPresente() {
            assertTrue(TTrieHashMap.buscarPatron("abracadabra", "xyz").isEmpty());
        }

        @Test
        @DisplayName("'bra' aparece en posiciones 1 y 8")
        void abracadabraBra() {
            List<Integer> pos = TTrieHashMap.buscarPatron("abracadabra", "bra");
            assertEquals(2, pos.size());
            assertEquals(1, (int) pos.get(0));
            assertEquals(8, (int) pos.get(1));
        }

        @Test
        @DisplayName("patrón más largo que el texto devuelve lista vacía")
        void patronMasLargoQueTexto() {
            assertTrue(TTrieHashMap.buscarPatron("hi", "hola").isEmpty());
        }

        @Test
        @DisplayName("patrón solapado: 'aa' en 'aaaaaa'")
        void patronSolapado() {
            List<Integer> pos = TTrieHashMap.buscarPatron("aaaaaa", "aa");
            assertEquals(5, pos.size());
            assertEquals(0, (int) pos.get(0));
            assertEquals(1, (int) pos.get(1));
            assertEquals(2, (int) pos.get(2));
            assertEquals(3, (int) pos.get(3));
            assertEquals(4, (int) pos.get(4));        }

        @Test
        @DisplayName("posiciones están ordenadas de menor a mayor")
        void posicionesOrdenadas() {
            List<Integer> pos = TTrieHashMap.buscarPatron("abracadabra", "a");
            for (int i = 0; i < pos.size() - 1; i++) {
                assertTrue(pos.get(i) < pos.get(i + 1));
            }
        }

        @Test
        @DisplayName("patrón vacío: cada posición es un resultado (sufijo vacío)")
        void patronVacio() {
            List<Integer> pos = TTrieHashMap.buscarPatron("hola", "");
            assertEquals(4, pos.size());
        }

        @Test
        @DisplayName("última ocurrencia del patrón está en la posición correcta")
        void patronAlFinal() {
            List<Integer> pos = TTrieHashMap.buscarPatron("abracadabra", "bra");
            assertEquals(8, pos.get(pos.size() - 1));
        }

        @Test
        @DisplayName("patrón al inicio del texto: posición 0")
        void patronAlInicio() {
            List<Integer> pos = TTrieHashMap.buscarPatron("abracadabra", "abr");
            assertEquals(0, pos.get(0));
        }
    }

    @Nested
    @DisplayName("TNodoTrieHashMap")
    class NodoTests {

        @Test
        @DisplayName("nodo nuevo no tiene hijos y no es fin")
        void nodoNuevoVacio() {
            TNodoTrieHashMap nodo = new TNodoTrieHashMap();
            assertFalse(nodo.tieneHijo('a'));
            assertFalse(nodo.isEsFin());
            assertTrue(nodo.esHoja());
            assertTrue(nodo.getCaracteresHijos().isEmpty());
        }

        @Test
        @DisplayName("crearHijo agrega el hijo correctamente")
        void crearHijo() {
            TNodoTrieHashMap nodo = new TNodoTrieHashMap();
            TNodoTrieHashMap hijo = nodo.crearHijo('z');
            assertTrue(nodo.tieneHijo('z'));
            assertSame(hijo, nodo.getHijo('z'));
            assertFalse(nodo.esHoja());
        }

        @Test
        @DisplayName("getHijo con carácter inexistente devuelve null")
        void getHijoInexistente() {
            TNodoTrieHashMap nodo = new TNodoTrieHashMap();
            assertNull(nodo.getHijo('x'));
        }

        @Test
        @DisplayName("eliminarHijo elimina correctamente")
        void eliminarHijo() {
            TNodoTrieHashMap nodo = new TNodoTrieHashMap();
            nodo.crearHijo('a');
            nodo.eliminarHijo('a');
            assertFalse(nodo.tieneHijo('a'));
            assertTrue(nodo.esHoja());
        }
    }
}