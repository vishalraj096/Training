import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Claim {
    private String claimId;
    private String policyNumber;
    private double claimAmount;
    private LocalDate claimDate;
    private String status;

    public Claim(String claimId, String policyNumber, double claimAmount, LocalDate claimDate, String status) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.status = status;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public String getStatus() {
        return status;
    }
}

class PolicyClaimStats {
    private String policyNumber;
    private double totalAmount;
    private double averageAmount;

    public PolicyClaimStats(String policyNumber, double totalAmount, double averageAmount) {
        this.policyNumber = policyNumber;
        this.totalAmount = totalAmount;
        this.averageAmount = averageAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    @Override
    public String toString() {
        return "PolicyClaimStats{" +
                "policyNumber='" + policyNumber + '\'' +
                ", totalAmount=" + totalAmount +
                ", averageAmount=" + averageAmount +
                '}';
    }
}

public class ClaimsAnalysis {

    public static List<PolicyClaimStats> analyzeClaims(List<Claim> claims) {
        Map<String, DoubleSummaryStatistics> statsByPolicy = claims.stream()
                .filter(c -> "Approved".equalsIgnoreCase(c.getStatus()) && c.getClaimAmount() > 5000)
                .collect(Collectors.groupingBy(Claim::getPolicyNumber,
                        Collectors.summarizingDouble(Claim::getClaimAmount)));

        List<PolicyClaimStats> list = statsByPolicy.entrySet().stream()
                .map(e -> new PolicyClaimStats(
                        e.getKey(),
                        e.getValue().getSum(),
                        e.getValue().getAverage()))
                .collect(Collectors.toList());

        return list.stream()
                .sorted(Comparator.comparingDouble(PolicyClaimStats::getTotalAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Claim> claims = new ArrayList<>();
        claims.add(new Claim("C1", "POL1", 6000, LocalDate.now(), "Approved"));
        claims.add(new Claim("C2", "POL1", 10000, LocalDate.now(), "Approved"));
        claims.add(new Claim("C3", "POL2", 8000, LocalDate.now(), "Approved"));
        claims.add(new Claim("C4", "POL3", 20000, LocalDate.now(), "Rejected"));
        claims.add(new Claim("C5", "POL2", 9000, LocalDate.now(), "Approved"));
        claims.add(new Claim("C6", "POL3", 15000, LocalDate.now(), "Approved"));
        claims.add(new Claim("C7", "POL3", 25000, LocalDate.now(), "Approved"));

        List<PolicyClaimStats> topPolicies = analyzeClaims(claims);

        System.out.println("Top 3 policies by total claim amount:");
        topPolicies.forEach(System.out::println);
    }
}
