package ucu.edu.aed.tda.hash;

import junit.framework.TestCase;
import ucu.edu.aed.ejercicio12.TTrieHashMap;

import java.util.List;

public class TTrieHashMapTest extends TestCase {

    private TTrieHashMap trie;

    protected void setUp() {
        trie = new TTrieHashMap();
    }

    public void testBuscarPalabraInsertada() {
        trie.insertar("casa");
        assertTrue(trie.buscar("casa"));
    }

    public void testBuscarPalabraNoInsertada() {
        trie.insertar("casa");
        assertFalse(trie.buscar("casas"));
    }

    public void testPrefijoNoEsPalabra() {
        trie.insertar("casamiento");
        assertFalse(trie.buscar("casa"));
    }

    public void testPalabrasAnidadas() {
        trie.insertar("car");
        trie.insertar("carro");
        assertTrue(trie.buscar("car"));
        assertTrue(trie.buscar("carro"));
    }

    public void testPredecirPrefijoExistente() {
        trie.insertar("sol");
        trie.insertar("solar");
        trie.insertar("soltar");
        List res = trie.predecir("sol");
        assertTrue(res.contains("sol"));
        assertTrue(res.contains("solar"));
        assertTrue(res.contains("soltar"));
    }

    public void testPredecirPrefijoInexistente() {
        trie.insertar("sol");
        assertTrue(trie.predecir("xyz").isEmpty());
    }

    public void testBuscarPatronPosiciones() {
        List pos = TTrieHashMap.buscarPatron("abracadabra", "abra");
        assertEquals(2, pos.size());
        assertEquals(0, pos.get(0));
        assertEquals(7, pos.get(1));
    }

    public void testBuscarPatronNoEncontrado() {
        assertTrue(TTrieHashMap.buscarPatron("abracadabra", "xyz").isEmpty());
    }
}