import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private String transactionId;
    private String policyNumber;
    private double amount;
    private LocalDate transactionDate;
    private boolean isFraudulent;

    public Transaction(String transactionId, String policyNumber, double amount, LocalDate transactionDate,
            boolean isFraudulent) {
        this.transactionId = transactionId;
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.isFraudulent = isFraudulent;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public boolean isFraudulent() {
        return isFraudulent;
    }
}

class FraudStats {
    private String policyNumber;
    private long count;
    private double totalAmount;

    public FraudStats(String policyNumber, long count, double totalAmount) {
        this.policyNumber = policyNumber;
        this.count = count;
        this.totalAmount = totalAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public long getCount() {
        return count;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "FraudStats{" +
                "policyNumber='" + policyNumber + '\'' +
                ", count=" + count +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

public class FraudDetection {

    public static List<FraudStats> detectFraud(List<Transaction> transactions) {
        Map<String, List<Transaction>> byPolicy = transactions.stream()
                .filter(t -> t.isFraudulent() && t.getAmount() > 10000)
                .collect(Collectors.groupingBy(Transaction::getPolicyNumber));

        return byPolicy.entrySet().stream()
                .map(e -> {
                    long count = e.getValue().size();
                    double total = e.getValue().stream()
                            .mapToDouble(Transaction::getAmount)
                            .sum();
                    return new FraudStats(e.getKey(), count, total);
                })
                .filter(fs -> fs.getCount() > 5 || fs.getTotalAmount() > 50000)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T1", "POL1", 15000, LocalDate.now(), true));
        transactions.add(new Transaction("T2", "POL1", 20000, LocalDate.now(), true));
        transactions.add(new Transaction("T3", "POL1", 18000, LocalDate.now(), true));
        transactions.add(new Transaction("T4", "POL1", 17000, LocalDate.now(), true));
        transactions.add(new Transaction("T5", "POL1", 16000, LocalDate.now(), true));
        transactions.add(new Transaction("T6", "POL1", 19000, LocalDate.now(), true));
        transactions.add(new Transaction("T7", "POL2", 60000, LocalDate.now(), true));
        transactions.add(new Transaction("T8", "POL3", 9000, LocalDate.now(), true));

        List<FraudStats> alerts = detectFraud(transactions);

        System.out.println("Fraud alerts:");
        alerts.forEach(System.out::println);
    }
}
