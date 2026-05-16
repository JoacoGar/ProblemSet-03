package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.Map;
import java.util.List;

public class MedicionPredecirHashMap extends Medible<List<String>> {

    private final Map<String, String> map;

    public MedicionPredecirHashMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String key : map.keySet()) {
                if (key.startsWith("cas")) {
                    map.get(key);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return map;
    }
}