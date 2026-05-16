package ucu.edu.aed.ejercicio12;

import java.util.HashMap;
import java.util.Set;

public class TNodoTrieHashMap {
    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esFin;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esFin = false;
    }

    public TNodoTrieHashMap getHijo(char c) {
        return hijos.get(c);
    }

    public TNodoTrieHashMap crearHijo(char c) {
        TNodoTrieHashMap nuevo = new TNodoTrieHashMap();
        hijos.put(c, nuevo);
        return nuevo;
    }

    public boolean tieneHijo(char c) {
        return hijos.containsKey(c);
    }

    public Set<Character> getCaracteresHijos() {
        return hijos.keySet();
    }

    public void eliminarHijo(char c) {
        hijos.remove(c);
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }

    public boolean isEsFin() {
        return esFin;
    }

    public void setEsFin(boolean esFin) {
        this.esFin = esFin;
    }
}