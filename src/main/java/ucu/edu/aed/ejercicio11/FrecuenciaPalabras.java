package ucu.edu.aed.ejercicio11;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrecuenciaPalabras {

    public static void main(String[] args) {

        Map<String, Integer> frecuencias = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("libro.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.toLowerCase().replaceAll("[^a-záéíóúñ ]", "");
                String[] palabras = linea.split("\\s+");

                for (String palabra : palabras) {

                    if (palabra.isEmpty()) continue;

                    frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convertir a lista y ordenar por frecuencia
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(frecuencias.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Top 10 palabras más frecuentes:\n");

        for (int i = 0; i < 10 && i < lista.size(); i++) {

            Map.Entry<String, Integer> entry = lista.get(i);

            System.out.printf("%-15s %5d | ", entry.getKey(), entry.getValue());

            // gráfico simple en texto
            for (int j = 0; j < entry.getValue() / 10 + 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}