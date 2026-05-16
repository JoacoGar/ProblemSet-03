package ucu.edu.aed.medible;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.*;
import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TTrie;
import ucu.edu.aed.tda.trie.impl.Trie;
import ucu.edu.aed.utils.FileUtils;

import java.util.*;

public class Main {

    private static final int REPETICIONES = 100;

    public static void main(String[] args) {

        TTrie<String> trie = new Trie();

        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        List<String> palabrasParaAgregar = new LinkedList<>();
        List<String> palabrasParaBuscar = new LinkedList<>();

        FileUtils.leerLineas("./ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("./ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);

        for (String p : palabrasParaAgregar) {

            trie.insertar(p, p);
            linkedList.add(p);
            arrayList.add(p);
            hashMap.put(p, p);
            treeMap.put(p, p);
        }

        List<Medible<List<String>>> medibles = new LinkedList<>();

        medibles.add(new MedicionBuscarLinkedList(linkedList));
        medibles.add(new MedicionBuscarArrayList(arrayList));
        medibles.add(new MedicionBuscarTrie(trie));
        medibles.add(new MedicionBuscarHashMap(hashMap));
        medibles.add(new MedicionBuscarTreeMap(treeMap));

        StringBuilder sb = new StringBuilder();
        sb.append("algoritmo,tiempo,memoria\n");

        for (Medible<List<String>> m : medibles) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sb.append(mi.toCSV()).append("\n");
        }

        FileUtils.escribirLineas("./salida.csv", sb.toString());


        // PARTE 5 - EJERCICIO 7

        String prefijo = "cas";

        System.out.println("\nAUTOCOMPLETADO PREFIJO: " + prefijo);

        System.out.println("\nTrie:");
        for (Entry<String> e : trie.predecir(prefijo)) {
            System.out.println(e.getPalabra());
        }

        System.out.println("\nLinkedList:");
        for (String s : linkedList) {
            if (s.startsWith(prefijo)) {
                System.out.println(s);
            }
        }

        System.out.println("\nHashMap:");
        for (String s : hashMap.keySet()) {
            if (s.startsWith(prefijo)) {
                System.out.println(s);
            }
        }

        List<Medible<List<String>>> mediblesPredecir = new LinkedList<>();

        mediblesPredecir.add(new MedicionPredecirLinkedList(linkedList));
        mediblesPredecir.add(new MedicionPredecirTrie(trie));
        mediblesPredecir.add(new MedicionPredecirHashMap(hashMap));

        StringBuilder sb2 = new StringBuilder();
        sb2.append("estructura,tiempo,memoria\n");

        for (Medible<List<String>> m : mediblesPredecir) {
            Medicion mi = m.medir(20, palabrasParaBuscar);
            mi.print();
            sb2.append(mi.toCSV()).append("\n");
        }

        FileUtils.escribirLineas("./salida_predecir.csv", sb2.toString());
    }
}