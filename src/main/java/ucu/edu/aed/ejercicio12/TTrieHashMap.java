package ucu.edu.aed.ejercicio12;

import java.util.ArrayList;
import java.util.List;

public class TTrieHashMap {
    private TNodoTrieHashMap raiz = new TNodoTrieHashMap();

    public void insertar(String palabra) {
        TNodoTrieHashMap actual = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (!actual.tieneHijo(c)) {
                actual.crearHijo(c);
            }
            actual = actual.getHijo(c);
        }
        actual.setEsFin(true);
    }

    public boolean buscar(String palabra) {
        TNodoTrieHashMap nodo = buscarNodo(palabra);
        return nodo != null && nodo.isEsFin();
    }

    private TNodoTrieHashMap buscarNodo(String texto) {
        TNodoTrieHashMap actual = raiz;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!actual.tieneHijo(c)) return null;
            actual = actual.getHijo(c);
        }
        return actual;
    }

    public List<String> predecir(String prefijo) {
        List<String> resultados = new ArrayList<>();
        TNodoTrieHashMap nodo = buscarNodo(prefijo);
        if (nodo == null) return resultados;                // prefijo no existe
        recolectarPalabras(nodo, new StringBuilder(prefijo), resultados);
        return resultados;
    }

    private void recolectarPalabras(TNodoTrieHashMap nodo,
                                    StringBuilder actual,
                                    List<String> lista) {
        if (nodo.isEsFin()) {
            lista.add(actual.toString());
        }
        for (char c : nodo.getCaracteresHijos()) {
            actual.append(c);
            recolectarPalabras(nodo.getHijo(c), actual, lista);
            actual.deleteCharAt(actual.length() - 1);   // backtrack
        }
    }

    public static List<Integer> buscarPatron(String texto, String patron) {
        TrieSufijos ts = new TrieSufijos();
        for (int i = 0; i < texto.length(); i++) {
            ts.insertar(texto.substring(i), i);
        }

        return ts.buscar(patron);
    }


    public TNodoTrieHashMap getRaiz() {
        return raiz;
    }
}