import java.util.*;

abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public void setBalance(double balance) { this.balance = balance; }
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { if (balance >= amount) balance -= amount; }
    public abstract double calculateInterest();
}

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

class SavingsAccount extends BankAccount implements Loanable {
    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }
    @Override
    public double calculateInterest() { return getBalance() * 0.04; }
    @Override
    public void applyForLoan(double amount) { System.out.println("Savings Account Loan Applied: " + amount); }
    @Override
    public boolean calculateLoanEligibility() { return getBalance() > 1000; }
}

class CurrentAccount extends BankAccount implements Loanable {
    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }
    @Override
    public double calculateInterest() { return getBalance() * 0.01; }
    @Override
    public void applyForLoan(double amount) { System.out.println("Current Account Loan Applied: " + amount); }
    @Override
    public boolean calculateLoanEligibility() { return getBalance() > 5000; }
}

public class BankingSystem {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SA123", "Alice", 5000));
        accounts.add(new CurrentAccount("CA456", "Bob", 10000));
        for (BankAccount acc : accounts) {
            System.out.println(acc.getHolderName() + " | Interest: " + acc.calculateInterest());
            if (acc instanceof Loanable loanable) {
                loanable.applyForLoan(2000);
                System.out.println("Loan Eligible: " + loanable.calculateLoanEligibility());
            }
            System.out.println();
        }
    }
}
