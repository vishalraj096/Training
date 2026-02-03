import java.util.*;

public class VotingSystem {
    private final Map<String, Integer> votesHashMap;
    private final LinkedHashMap<String, Integer> votesLinkedHashMap;
    private final TreeMap<String, Integer> votesTreeMap;
    
    public VotingSystem() {
        votesHashMap = new HashMap<>();
        votesLinkedHashMap = new LinkedHashMap<>();
        votesTreeMap = new TreeMap<>();
    }
    
    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Voting System ===");
            System.out.println("1. Cast Vote");
            System.out.println("2. Display Results (HashMap)");
            System.out.println("3. Display Results (LinkedHashMap - Vote Order)");
            System.out.println("4. Display Results (TreeMap - Sorted by Candidate)");
            System.out.println("5. Display Winner");
            System.out.println("6. Display Total Votes");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> system.castVote(sc);
                case 2 -> system.displayHashMapResults();
                case 3 -> system.displayLinkedHashMapResults();
                case 4 -> system.displayTreeMapResults();
                case 5 -> system.displayWinner();
                case 6 -> system.displayTotalVotes();
                case 7 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    public void castVote(Scanner sc) {
        System.out.print("Enter candidate name: ");
        String candidate = sc.nextLine();
        
        votesHashMap.put(candidate, votesHashMap.getOrDefault(candidate, 0) + 1);
        votesLinkedHashMap.put(candidate, votesLinkedHashMap.getOrDefault(candidate, 0) + 1);
        votesTreeMap.put(candidate, votesTreeMap.getOrDefault(candidate, 0) + 1);
        
        System.out.println("Vote cast successfully for " + candidate + "!");
    }
    
    public void displayHashMapResults() {
        System.out.println("\n=== Voting Results (HashMap) ===");
        if (votesHashMap.isEmpty()) {
            System.out.println("No votes cast yet!");
        } else {
            for (Map.Entry<String, Integer> entry : votesHashMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        }
    }
    
    public void displayLinkedHashMapResults() {
        System.out.println("\n=== Voting Results (LinkedHashMap - Vote Order) ===");
        if (votesLinkedHashMap.isEmpty()) {
            System.out.println("No votes cast yet!");
        } else {
            for (Map.Entry<String, Integer> entry : votesLinkedHashMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        }
    }
    
    public void displayTreeMapResults() {
        System.out.println("\n=== Voting Results (TreeMap - Sorted by Candidate) ===");
        if (votesTreeMap.isEmpty()) {
            System.out.println("No votes cast yet!");
        } else {
            for (Map.Entry<String, Integer> entry : votesTreeMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
            }
        }
    }
    
    public void displayWinner() {
        if (votesHashMap.isEmpty()) {
            System.out.println("No votes cast yet!");
            return;
        }
        
        String winner = null;
        int maxVotes = 0;
        
        for (Map.Entry<String, Integer> entry : votesHashMap.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        
        System.out.println("\n=== Winner ===");
        System.out.println(winner + " with " + maxVotes + " votes!");
    }
    
    public void displayTotalVotes() {
        int totalVotes = 0;
        
        for (int votes : votesHashMap.values()) {
            totalVotes += votes;
        }
        
        System.out.println("\nTotal votes cast: " + totalVotes);
    }
}
