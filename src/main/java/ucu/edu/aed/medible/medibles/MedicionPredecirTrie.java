package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.TTrie;
import ucu.edu.aed.tda.trie.Entry;

import java.util.List;

public class MedicionPredecirTrie extends Medible<List<String>> {

    private final TTrie<String> trie;

    public MedicionPredecirTrie(TTrie<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : data) {
                trie.predecir("cas");
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return trie;
    }
}