import java.io.*;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter text (or type 'file' to read from file): ");
            String input = sc.nextLine();
            
            String text;
            
            if (input.equalsIgnoreCase("file")) {
                System.out.print("Enter file path: ");
                String filePath = sc.nextLine();
                
                try {
                    StringBuilder sb;
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line).append(" ");
                        }
                    }
                    text = sb.toString();
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                    sc.close();
                    return;
                }
            } else {
                text = input;
            }
            
            Map<String, Integer> frequencyMap = new HashMap<>();
            
            String cleanText = text.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
            
            String[] words = cleanText.split("\\s+");
            
            for (String word : words) {
                if (!word.isEmpty()) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
            
            System.out.println("\nWord Frequency:");
            System.out.println(frequencyMap);
            
            System.out.println("\nDetailed Frequency:");
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
    }
}
