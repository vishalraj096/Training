import java.util.*;

public class BankingSystem {
    private final Map<String, Double> accounts;
    private final TreeMap<Double, List<String>> accountsByBalance;
    private final Queue<String> withdrawalQueue;
    
    public BankingSystem() {
        accounts = new HashMap<>();
        accountsByBalance = new TreeMap<>(Collections.reverseOrder());
        withdrawalQueue = new LinkedList<>();
    }
    
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Request Withdrawal");
            System.out.println("4. Process Withdrawal Queue");
            System.out.println("5. Display Account Balance");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Display Accounts Sorted by Balance");
            System.out.println("8. Display Withdrawal Queue");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> bank.createAccount(sc);
                case 2 -> bank.deposit(sc);
                case 3 -> bank.requestWithdrawal(sc);
                case 4 -> bank.processWithdrawalQueue(sc);
                case 5 -> bank.displayAccountBalance(sc);
                case 6 -> bank.displayAllAccounts();
                case 7 -> bank.displayAccountsByBalance();
                case 8 -> bank.displayWithdrawalQueue();
                case 9 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    public void createAccount(Scanner sc) {
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();
        
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return;
        }
        
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();
        
        accounts.put(accountNumber, balance);
        
        if (!accountsByBalance.containsKey(balance)) {
            accountsByBalance.put(balance, new ArrayList<>());
        }
        accountsByBalance.get(balance).add(accountNumber);
        
        System.out.println("Account created successfully!");
    }
    
    public void deposit(Scanner sc) {
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found!");
            return;
        }
        
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        double oldBalance = accounts.get(accountNumber);
        double newBalance = oldBalance + amount;
        
        if (accountsByBalance.containsKey(oldBalance)) {
            accountsByBalance.get(oldBalance).remove(accountNumber);
            if (accountsByBalance.get(oldBalance).isEmpty()) {
                accountsByBalance.remove(oldBalance);
            }
        }
        
        accounts.put(accountNumber, newBalance);
        
        if (!accountsByBalance.containsKey(newBalance)) {
            accountsByBalance.put(newBalance, new ArrayList<>());
        }
        accountsByBalance.get(newBalance).add(accountNumber);
        
        System.out.println("Deposit successful! New balance: $" + newBalance);
    }
    
    public void requestWithdrawal(Scanner sc) {
        System.out.print("Enter account number for withdrawal request: ");
        String accountNumber = sc.nextLine();
        
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found!");
            return;
        }
        
        withdrawalQueue.add(accountNumber);
        System.out.println("Withdrawal request added to queue!");
    }
    
    public void processWithdrawalQueue(Scanner sc) {
        if (withdrawalQueue.isEmpty()) {
            System.out.println("No withdrawal requests in queue!");
            return;
        }
        
        String accountNumber = withdrawalQueue.poll();
        
        System.out.println("Processing withdrawal for account: " + accountNumber);
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        double oldBalance = accounts.get(accountNumber);
        
        if (amount > oldBalance) {
            System.out.println("Insufficient balance!");
            return;
        }
        
        double newBalance = oldBalance - amount;
        
        if (accountsByBalance.containsKey(oldBalance)) {
            accountsByBalance.get(oldBalance).remove(accountNumber);
            if (accountsByBalance.get(oldBalance).isEmpty()) {
                accountsByBalance.remove(oldBalance);
            }
        }
        
        accounts.put(accountNumber, newBalance);
        
        if (!accountsByBalance.containsKey(newBalance)) {
            accountsByBalance.put(newBalance, new ArrayList<>());
        }
        accountsByBalance.get(newBalance).add(accountNumber);
        
        System.out.println("Withdrawal successful! New balance: $" + newBalance);
    }
    
    public void displayAccountBalance(Scanner sc) {
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();
        
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account " + accountNumber + " balance: $" + accounts.get(accountNumber));
        } else {
            System.out.println("Account not found!");
        }
    }
    
    public void displayAllAccounts() {
        System.out.println("\n=== All Accounts ===");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
        } else {
            for (Map.Entry<String, Double> entry : accounts.entrySet()) {
                System.out.println("Account " + entry.getKey() + ": $" + entry.getValue());
            }
        }
    }
    
    public void displayAccountsByBalance() {
        System.out.println("\n=== Accounts Sorted by Balance (Highest First) ===");
        if (accountsByBalance.isEmpty()) {
            System.out.println("No accounts found!");
        } else {
            for (Map.Entry<Double, List<String>> entry : accountsByBalance.entrySet()) {
                System.out.println("Balance $" + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    
    public void displayWithdrawalQueue() {
        System.out.println("\n=== Withdrawal Queue ===");
        if (withdrawalQueue.isEmpty()) {
            System.out.println("No withdrawal requests in queue!");
        } else {
            System.out.println("Pending withdrawals: " + withdrawalQueue);
        }
    }
}
