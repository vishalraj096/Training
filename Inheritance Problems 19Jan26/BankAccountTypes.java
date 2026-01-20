class BankAccount {
    BankAccount(String accountNumber, double balance) {
    }
}

class SavingsAccount extends BankAccount {
    SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
    }

    void displayAccountType() {
        System.out.println("Savings Account");
    }
}

class CheckingAccount extends BankAccount {
    CheckingAccount(String accountNumber, double balance, double withdrawalLimit) {
        super(accountNumber, balance);
    }

    void displayAccountType() {
        System.out.println("Checking Account");
    }
}

class FixedDepositAccount extends BankAccount {
    FixedDepositAccount(String accountNumber, double balance, int termMonths) {
        super(accountNumber, balance);
    }

    void displayAccountType() {
        System.out.println("Fixed Deposit Account");
    }
}

public class BankAccountTypes {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SA123", 5000, 3.5);
        CheckingAccount ca = new CheckingAccount("CA456", 2000, 1000);
        FixedDepositAccount fda = new FixedDepositAccount("FD789", 10000, 12);
        sa.displayAccountType();
        ca.displayAccountType();
        fda.displayAccountType();
    }
}
