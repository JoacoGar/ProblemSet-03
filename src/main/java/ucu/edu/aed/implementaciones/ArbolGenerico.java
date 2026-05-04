package ucu.edu.aed.implementaciones;

import ucu.edu.aed.tda.generic_trie.TArbolGenerico;
import ucu.edu.aed.tda.generic_trie.TNodoGenerico;

import java.util.function.Consumer;

public class ArbolGenerico<T extends Comparable<T>> implements TArbolGenerico<T> {
    TNodoGenerico<T> primerHijo;

    @Override
    public boolean agregarHijo(Comparable<T> padre, T hijo) {
        return false;
    }

    @Override
    public void eliminar(Comparable<T> criterio) {

    }

    @Override
    public T obtenerPadre(Comparable<T> criterio) {
        return null;
    }

    @Override
    public T buscar(Comparable<T> criterio) {
        if (primerHijo != null) {
            primerHijo.buscar(criterio);
            criterio.
        }
        return null;
    }

    @Override
    public void preOrden(Consumer<T> consumidor) {

    }

    @Override
    public void inOrden(Consumer<T> consumidor) {

    }

    @Override
    public void postOrden(Consumer<T> consumidor) {

    }

    @Override
    public void vaciar() {

    }

    @Override
    public int grado(Comparable<T> nodo) {
        return 0;
    }

    @Override
    public int altura(Comparable<T> nodo) {
        return 0;
    }
}
