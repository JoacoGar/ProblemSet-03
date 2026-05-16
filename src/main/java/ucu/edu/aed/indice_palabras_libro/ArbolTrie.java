package ucu.edu.aed.indice_palabras_libro;

import ucu.edu.aed.tda.generic_tree.TArbolGenerico;
import ucu.edu.aed.tda.generic_tree.TNodoGenerico;
import ucu.edu.aed.tda.trie.TNodoTrie;

import java.util.function.Consumer;

public class ArbolTrie implements TArbolGenerico<java.lang.String> {
    TNodoTrie<java.lang.String>[] hijos = new NodoLetra[26];
    @Override
    public boolean agregarHijo(Comparable<java.lang.String> padre, java.lang.String hijo) {
        return false;
    }

    @Override
    public void eliminar(Comparable<java.lang.String> criterio) {

    }

    @Override
    public java.lang.String obtenerPadre(Comparable<java.lang.String> criterio) {
        return "";
    }

    @Override
    public java.lang.String buscar(Comparable<java.lang.String> criterio) {
        return "";
    }

    @Override
    public void preOrden(Consumer<java.lang.String> consumidor) {

    }

    @Override
    public void inOrden(Consumer<java.lang.String> consumidor) {

    }

    @Override
    public void postOrden(Consumer<java.lang.String> consumidor) {

    }

    @Override
    public void vaciar() {

    }

    @Override
    public int grado(Comparable<java.lang.String> nodo) {
        return 0;
    }

    @Override
    public int altura(Comparable<java.lang.String> nodo) {
        return 0;
    }
}
