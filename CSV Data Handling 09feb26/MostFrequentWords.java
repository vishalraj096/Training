import java.io.*;
import java.util.*;

public class MostFrequentWords {
    public static void main(String[] args) {
        String fileName = "corpus.txt";
        int topN = 10;

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String text = sb.toString().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9 ]", " ");
        String[] words = text.split("\\s+");

        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            if (w.isEmpty())
                continue;
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < Math.min(topN, list.size()); i++) {
            Map.Entry<String, Integer> e = list.get(i);
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        if (list.size() >= 2) {
            Map.Entry<String, Integer> second = list.get(1);
            System.out.println("Second most repeated word: " + second.getKey() + " -> " + second.getValue());
        }
    }
}
