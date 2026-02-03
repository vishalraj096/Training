import java.time.LocalDate;

public class Policy implements Comparable<Policy> {
    private final String policyNumber;
    private final String policyholderName;
    private final LocalDate expiryDate;
    private final String coverageType;
    private final double premium;
    
    public Policy(String policyNumber, String policyholderName, LocalDate expiryDate, String coverageType, double premium) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premium = premium;
    }
    
    public String getPolicyNumber() {
        return policyNumber;
    }
    
    public String getPolicyholderName() {
        return policyholderName;
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    public String getCoverageType() {
        return coverageType;
    }
    
    public double getPremium() {
        return premium;
    }
    
    @Override
    public int compareTo(Policy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }
    
    @Override
    public String toString() {
        return "Policy{" +
                "Number='" + policyNumber + '\'' +
                ", Holder='" + policyholderName + '\'' +
                ", Expiry=" + expiryDate +
                ", Coverage='" + coverageType + '\'' +
                ", Premium=" + premium +
                '}';
    }
}
