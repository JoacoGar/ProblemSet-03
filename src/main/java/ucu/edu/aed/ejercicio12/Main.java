package ucu.edu.aed.ejercicio12;

import java.util.List;

public class Main {
    static void seccion(String titulo) {
        System.out.println("\n" + "------------------------------------------");
        System.out.println("  " + titulo);
        System.out.println("------------------------------------------");
    }

    static void subseccion(String titulo) {
        System.out.println("\n----------------- " + titulo + " -----------------");
    }

    static void aplicacionAutocompletar() {
        seccion("AUTOCOMPLETAR");

        TTrieHashMap trie = new TTrieHashMap();

        String[] palabras = {
                "casa", "casamiento", "casado", "cascos", "caso", "casos",
                "cama", "camara", "camaron", "camino", "camisa", "campo",
                "trie", "tries", "trivia", "trigo",
                "java", "javascript", "javadoc"
        };

        System.out.println("\nPalabras insertadas en el Trie:");
        for (String p : palabras) {
            trie.insertar(p);
            System.out.print("  [" + p + "]");
        }
        System.out.println();

        subseccion("Búsqueda exacta");
        String[] aBuscar = {"casa", "casas", "java", "python"};
        for (String p : aBuscar) {
            System.out.printf("  buscar(%-12s) → %s%n", "\"" + p + "\"",
                    trie.buscar(p) ? "encontrada" : "no existe");
        }

        subseccion("Autocompletar (predecir)");
        String[] prefijos = {"ca", "cas", "cam", "tri", "jav", "xyz"};
        for (String pref : prefijos) {
            List<String> sugerencias = trie.predecir(pref);
            System.out.printf("  predecir(%-6s) → %s%n",
                    "\"" + pref + "\"",
                    sugerencias.isEmpty() ? "(sin resultados)" : sugerencias);
        }
    }

    static void aplicacionBusquedaPatrones() {
        seccion("BÚSQUEDA DE PATRONES EN TEXTO");

        subseccion("Texto 1 (simple)");
        String texto1 = "abracadabra";
        System.out.println("  Texto: \"" + texto1 + "\"");

        String[] patrones1 = {"abra", "bra", "a", "cad", "xyz"};
        for (String pat : patrones1) {
            List<Integer> pos = TTrieHashMap.buscarPatron(texto1, pat);
            imprimirResultadoBusqueda(texto1, pat, pos);
        }

        subseccion("Texto 2 (repeticiones)");
        String texto2 = "aaaaaa";
        System.out.println("  Texto: \"" + texto2 + "\"");

        String[] patrones2 = {"a", "aa", "aaa"};
        for (String pat : patrones2) {
            List<Integer> pos = TTrieHashMap.buscarPatron(texto2, pat);
            imprimirResultadoBusqueda(texto2, pat, pos);
        }
    }

    static void imprimirResultadoBusqueda(String texto, String patron, List<Integer> posiciones) {
        System.out.printf("  patron=%-10s → posiciones: %s%n",
                "\"" + patron + "\"",
                posiciones.isEmpty() ? "(no encontrado)" : posiciones);

        if (!posiciones.isEmpty()) {
            char[] marcador = new char[texto.length()];
            java.util.Arrays.fill(marcador, ' ');
            for (int p : posiciones) {
                if (p < marcador.length) marcador[p] = '^';
            }
            System.out.println("    \"" + texto + "\"");
            System.out.println("     " + new String(marcador));
        }
    }

    public static void main(String[] args) {
        aplicacionAutocompletar();
        aplicacionBusquedaPatrones();
        // Casos de prueba en /test
    }
}