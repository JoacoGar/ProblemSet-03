package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.TTrie;
import ucu.edu.aed.tda.trie.Entry;

import java.util.*;
import java.util.function.Consumer;

public class Trie implements TTrie<String> {

    private final NodoTrie raiz = new NodoTrie();


    @Override
    public boolean insertar(String palabra, String dato) {
        NodoTrie actual = raiz;

        for (char c : palabra.toCharArray()) {
            actual.hijos.putIfAbsent(c, new NodoTrie());
            actual = actual.hijos.get(c);
        }

        actual.esPalabra = true;
        actual.valor = dato;
        return true;
    }

    @Override
    public Entry<String> buscar(String palabra) {
        NodoTrie actual = raiz;

        for (char c : palabra.toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) return null;
        }

        if (!actual.esPalabra) return null;

        return new Entry<>(actual.valor, actual.esPalabra, palabra);
    }


    @Override
    public List<Entry<String>> predecir(String prefijo) {
        List<Entry<String>> resultado = new ArrayList<>();
        NodoTrie actual = raiz;

        for (char c : prefijo.toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) return resultado;
        }

        recolectar(actual, prefijo, resultado);
        return resultado;
    }

    private void recolectar(NodoTrie nodo, String palabra, List<Entry<String>> res) {
        if (nodo.esPalabra) {
            res.add(new Entry<>(nodo.valor, nodo.esPalabra, palabra));
        }

        for (Map.Entry<Character, NodoTrie> e : nodo.hijos.entrySet()) {
            recolectar(e.getValue(), palabra + e.getKey(), res);
        }
    }


    @Override
    public void recorrer(Consumer<Entry<String>> consumer) {
        List<Entry<String>> todas = predecir("");
        for (Entry<String> e : todas) {
            consumer.accept(e);
        }
    }

    private static class NodoTrie {
        Map<Character, NodoTrie> hijos = new HashMap<>();
        boolean esPalabra = false;
        String valor;
    }
}