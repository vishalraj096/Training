import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InsurancePolicyManagement {
    private final Set<Policy> hashSetPolicies;
    private final Set<Policy> linkedHashSetPolicies;
    private final Set<Policy> treeSetPolicies;
    private final DateTimeFormatter formatter;
    
    public InsurancePolicyManagement() {
        hashSetPolicies = new HashSet<>();
        linkedHashSetPolicies = new LinkedHashSet<>();
        treeSetPolicies = new TreeSet<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    public static void main(String[] args) {
        InsurancePolicyManagement system = new InsurancePolicyManagement();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Insurance Policy Management System ===");
            System.out.println("1. Add Policy");
            System.out.println("2. Display All Policies (HashSet)");
            System.out.println("3. Display All Policies (LinkedHashSet)");
            System.out.println("4. Display All Policies (TreeSet - Sorted by Expiry)");
            System.out.println("5. Display Policies Expiring Soon");
            System.out.println("6. Display Policies by Coverage Type");
            System.out.println("7. Check for Duplicate Policies");
            System.out.println("8. Performance Comparison");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> system.addPolicy(sc);
                case 2 -> system.displayPolicies("HashSet", system.hashSetPolicies);
                case 3 -> system.displayPolicies("LinkedHashSet", system.linkedHashSetPolicies);
                case 4 -> system.displayPolicies("TreeSet", system.treeSetPolicies);
                case 5 -> system.displayPoliciesExpiringSoon();
                case 6 -> system.displayPoliciesByCoverageType(sc);
                case 7 -> system.checkDuplicates();
                case 8 -> system.performanceComparison();
                case 9 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    public void addPolicy(Scanner sc) {
        System.out.print("Enter Policy Number: ");
        String policyNumber = sc.nextLine();
        
        System.out.print("Enter Policyholder Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        LocalDate expiryDate = LocalDate.parse(dateStr, formatter);
        
        System.out.print("Enter Coverage Type (Health/Auto/Home): ");
        String coverageType = sc.nextLine();
        
        System.out.print("Enter Premium Amount: ");
        double premium = sc.nextDouble();
        sc.nextLine();
        
        Policy policy = new Policy(policyNumber, name, expiryDate, coverageType, premium);
        
        hashSetPolicies.add(policy);
        linkedHashSetPolicies.add(policy);
        treeSetPolicies.add(policy);
        
        System.out.println("Policy added successfully!");
    }
    
    public void displayPolicies(String setType, Set<Policy> policies) {
        System.out.println("\n=== " + setType + " Policies ===");
        if (policies.isEmpty()) {
            System.out.println("No policies found!");
        } else {
            for (Policy policy : policies) {
                System.out.println(policy);
            }
        }
    }
    
    public void displayPoliciesExpiringSoon() {
        System.out.println("\n=== Policies Expiring Within 30 Days ===");
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        boolean found = false;
        
        for (Policy policy : hashSetPolicies) {
            if (policy.getExpiryDate().isAfter(today) && policy.getExpiryDate().isBefore(thirtyDaysLater)) {
                System.out.println(policy);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No policies expiring soon!");
        }
    }
    
    public void displayPoliciesByCoverageType(Scanner sc) {
        System.out.print("Enter Coverage Type to search: ");
        String coverageType = sc.nextLine();
        
        System.out.println("\n=== Policies with Coverage Type: " + coverageType + " ===");
        boolean found = false;
        
        for (Policy policy : hashSetPolicies) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                System.out.println(policy);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No policies found with this coverage type!");
        }
    }
    
    public void checkDuplicates() {
        System.out.println("\n=== Checking for Duplicates ===");
        Map<String, Integer> policyCount = new HashMap<>();
        
        for (Policy policy : hashSetPolicies) {
            String policyNumber = policy.getPolicyNumber();
            policyCount.put(policyNumber, policyCount.getOrDefault(policyNumber, 0) + 1);
        }
        
        boolean foundDuplicates = false;
        for (Map.Entry<String, Integer> entry : policyCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Duplicate Policy Number: " + entry.getKey() + " (Count: " + entry.getValue() + ")");
                foundDuplicates = true;
            }
        }
        
        if (!foundDuplicates) {
            System.out.println("No duplicate policies found!");
        }
    }
    
    public void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");
        
        Policy testPolicy = new Policy("TEST123", "Test User", LocalDate.now().plusYears(1), "Health", 1000.0);
        
        long startTime = System.nanoTime();
        hashSetPolicies.add(testPolicy);
        long endTime = System.nanoTime();
        System.out.println("HashSet - Add Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        linkedHashSetPolicies.add(testPolicy);
        endTime = System.nanoTime();
        System.out.println("LinkedHashSet - Add Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        treeSetPolicies.add(testPolicy);
        endTime = System.nanoTime();
        System.out.println("TreeSet - Add Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        hashSetPolicies.contains(testPolicy);
        endTime = System.nanoTime();
        System.out.println("HashSet - Search Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        linkedHashSetPolicies.contains(testPolicy);
        endTime = System.nanoTime();
        System.out.println("LinkedHashSet - Search Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        treeSetPolicies.contains(testPolicy);
        endTime = System.nanoTime();
        System.out.println("TreeSet - Search Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        hashSetPolicies.remove(testPolicy);
        endTime = System.nanoTime();
        System.out.println("HashSet - Remove Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        linkedHashSetPolicies.remove(testPolicy);
        endTime = System.nanoTime();
        System.out.println("LinkedHashSet - Remove Time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        treeSetPolicies.remove(testPolicy);
        endTime = System.nanoTime();
        System.out.println("TreeSet - Remove Time: " + (endTime - startTime) + " ns");
    }
}
