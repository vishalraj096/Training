import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InsurancePolicyManagement {
    private final Map<String, Policy> hashMapPolicies;
    private final Map<String, Policy> linkedHashMapPolicies;
    private final TreeMap<LocalDate, List<Policy>> treeMapPolicies;
    private final DateTimeFormatter formatter;
    
    public InsurancePolicyManagement() {
        hashMapPolicies = new HashMap<>();
        linkedHashMapPolicies = new LinkedHashMap<>();
        treeMapPolicies = new TreeMap<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    public static void main(String[] args) {
        InsurancePolicyManagement system = new InsurancePolicyManagement();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Insurance Policy Management System ===");
            System.out.println("1. Add Policy");
            System.out.println("2. Retrieve Policy by Number");
            System.out.println("3. Display All Policies (HashMap)");
            System.out.println("4. Display All Policies (LinkedHashMap - Insertion Order)");
            System.out.println("5. Display All Policies (TreeMap - Sorted by Expiry)");
            System.out.println("6. List Policies Expiring Within 30 Days");
            System.out.println("7. List Policies by Policyholder Name");
            System.out.println("8. Remove Expired Policies");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> system.addPolicy(sc);
                case 2 -> system.retrievePolicy(sc);
                case 3 -> system.displayHashMap();
                case 4 -> system.displayLinkedHashMap();
                case 5 -> system.displayTreeMap();
                case 6 -> system.listPoliciesExpiringSoon();
                case 7 -> system.listPoliciesByHolder(sc);
                case 8 -> system.removeExpiredPolicies();
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
        
        System.out.print("Enter Coverage Type: ");
        String coverageType = sc.nextLine();
        
        System.out.print("Enter Premium Amount: ");
        double premium = sc.nextDouble();
        sc.nextLine();
        
        Policy policy = new Policy(policyNumber, name, expiryDate, coverageType, premium);
        
        hashMapPolicies.put(policyNumber, policy);
        linkedHashMapPolicies.put(policyNumber, policy);
        
        if (!treeMapPolicies.containsKey(expiryDate)) {
            treeMapPolicies.put(expiryDate, new ArrayList<>());
        }
        treeMapPolicies.get(expiryDate).add(policy);
        
        System.out.println("Policy added successfully!");
    }
    
    public void retrievePolicy(Scanner sc) {
        System.out.print("Enter Policy Number: ");
        String policyNumber = sc.nextLine();
        
        Policy policy = hashMapPolicies.get(policyNumber);
        
        if (policy != null) {
            System.out.println("Policy found: " + policy);
        } else {
            System.out.println("Policy not found!");
        }
    }
    
    public void displayHashMap() {
        System.out.println("\n=== HashMap Policies ===");
        if (hashMapPolicies.isEmpty()) {
            System.out.println("No policies found!");
        } else {
            for (Map.Entry<String, Policy> entry : hashMapPolicies.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
    
    public void displayLinkedHashMap() {
        System.out.println("\n=== LinkedHashMap Policies (Insertion Order) ===");
        if (linkedHashMapPolicies.isEmpty()) {
            System.out.println("No policies found!");
        } else {
            for (Map.Entry<String, Policy> entry : linkedHashMapPolicies.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
    
    public void displayTreeMap() {
        System.out.println("\n=== TreeMap Policies (Sorted by Expiry Date) ===");
        if (treeMapPolicies.isEmpty()) {
            System.out.println("No policies found!");
        } else {
            for (Map.Entry<LocalDate, List<Policy>> entry : treeMapPolicies.entrySet()) {
                for (Policy policy : entry.getValue()) {
                    System.out.println(policy);
                }
            }
        }
    }
    
    public void listPoliciesExpiringSoon() {
        System.out.println("\n=== Policies Expiring Within 30 Days ===");
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        boolean found = false;
        
        for (Policy policy : hashMapPolicies.values()) {
            LocalDate expiry = policy.getExpiryDate();
            if ((expiry.isEqual(today) || expiry.isAfter(today)) && expiry.isBefore(thirtyDaysLater)) {
                System.out.println(policy);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No policies expiring soon!");
        }
    }
    
    public void listPoliciesByHolder(Scanner sc) {
        System.out.print("Enter Policyholder Name: ");
        String holderName = sc.nextLine();
        
        System.out.println("\n=== Policies for " + holderName + " ===");
        boolean found = false;
        
        for (Policy policy : hashMapPolicies.values()) {
            if (policy.getPolicyholderName().equalsIgnoreCase(holderName)) {
                System.out.println(policy);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No policies found for this policyholder!");
        }
    }
    
    public void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();
        List<String> expiredPolicyNumbers = new ArrayList<>();
        
        for (Map.Entry<String, Policy> entry : hashMapPolicies.entrySet()) {
            if (entry.getValue().getExpiryDate().isBefore(today)) {
                expiredPolicyNumbers.add(entry.getKey());
            }
        }
        
        for (String policyNumber : expiredPolicyNumbers) {
            Policy policy = hashMapPolicies.get(policyNumber);
            hashMapPolicies.remove(policyNumber);
            linkedHashMapPolicies.remove(policyNumber);
            
            LocalDate expiry = policy.getExpiryDate();
            if (treeMapPolicies.containsKey(expiry)) {
                treeMapPolicies.get(expiry).remove(policy);
                if (treeMapPolicies.get(expiry).isEmpty()) {
                    treeMapPolicies.remove(expiry);
                }
            }
        }
        
        System.out.println("Removed " + expiredPolicyNumbers.size() + " expired policies.");
    }
}
