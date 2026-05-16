package ucu.edu.aed.ejercicio12;

import java.util.ArrayList;
import java.util.List;

class TrieSufijos {
    private static class Nodo {
        java.util.HashMap<Character, Nodo> hijos = new java.util.HashMap<>();
        List<Integer> posiciones = new ArrayList<>();
    }

    private Nodo raiz = new Nodo();

    void insertar(String sufijo, int posInicio) {
        Nodo actual = raiz;
        for (int i = 0; i < sufijo.length(); i++) {
            char c = sufijo.charAt(i);
            actual.hijos.putIfAbsent(c, new Nodo());
            actual = actual.hijos.get(c);
        }
        actual.posiciones.add(posInicio);
    }

    List<Integer> buscar(String patron) {
        Nodo actual = raiz;
        for (int i = 0; i < patron.length(); i++) {
            char c = patron.charAt(i);
            if (!actual.hijos.containsKey(c)) return new ArrayList<>();
            actual = actual.hijos.get(c);
        }
        List<Integer> resultado = new ArrayList<>();
        recolectarPosiciones(actual, resultado);
        java.util.Collections.sort(resultado);
        return resultado;
    }

    private void recolectarPosiciones(Nodo nodo, List<Integer> lista) {
        lista.addAll(nodo.posiciones);
        for (Nodo hijo : nodo.hijos.values()) {
            recolectarPosiciones(hijo, lista);
        }
    }
}
