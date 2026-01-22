import java.util.*;

class Account {
    private final String id;
    private double balance;

    Account(String id) {
        this.id = id;
        this.balance = 0.0;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("amount must be positive");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("amount must be positive");
        if (amount > balance)
            throw new IllegalArgumentException("insufficient funds");
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" + "id='" + id + "', balance=" + balance + '}';
    }
}

class Customer {
    private final String name;
    private final List<Account> accounts = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    void attachAccount(Account acc) {
        accounts.add(acc);
    }

    public void viewBalance(String accountId) {
        Account acc = accounts.stream().filter(a -> a.getId().equals(accountId)).findFirst().orElse(null);
        if (acc == null) {
            System.out.println(name + " has no account with id " + accountId);
        } else {
            System.out.println(name + " balance in account " + accountId + ": $" + acc.getBalance());
        }
    }
}

class Bank {
    private final String name;
    private final Map<String, Account> accounts = new HashMap<>();
    private int nextId = 1;

    Bank(String name) {
        this.name = name;
    }

    public Account openAccount(Customer customer) {
        String id = name.substring(0, Math.min(3, name.length())).toUpperCase() + (nextId++);
        Account acc = new Account(id);
        accounts.put(id, acc);
        customer.attachAccount(acc);
        System.out.println("Opened account " + id + " for customer " + customer.getName() + " at bank " + name);
        return acc;
    }
}

public class BankCustomersAssociation {
    public static void main(String[] args) {
        Bank bank = new Bank("AcmeBank");
        Customer alice = new Customer("Alice");

        Account a1 = bank.openAccount(alice);
        a1.deposit(250.0);
        alice.viewBalance(a1.getId());

        Account a2 = bank.openAccount(alice);
        a2.deposit(100.0);
        alice.viewBalance(a2.getId());

        System.out.println("Alice accounts: " + alice.getAccounts());
    }
}
