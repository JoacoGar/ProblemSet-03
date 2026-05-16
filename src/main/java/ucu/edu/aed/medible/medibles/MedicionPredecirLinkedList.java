package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.LinkedList;
import java.util.List;

public class MedicionPredecirLinkedList extends Medible<List<String>> {

    private final LinkedList<String> lista;

    public MedicionPredecirLinkedList(LinkedList<String> lista) {
        this.lista = lista;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String s : lista) {
                if (s.startsWith("cas")) {
                    s.length();
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return lista;
    }
}