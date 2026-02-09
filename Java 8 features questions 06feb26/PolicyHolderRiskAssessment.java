import java.util.*;
import java.util.stream.Collectors;

class PolicyHolder {
    private String holderId;
    private String name;
    private int age;
    private String policyType;
    private double premiumAmount;

    public PolicyHolder(String holderId, String name, int age, String policyType, double premiumAmount) {
        this.holderId = holderId;
        this.name = name;
        this.age = age;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPolicyType() {
        return policyType;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }
}

class RiskAssessment {
    private String holderId;
    private String name;
    private double riskScore;

    public RiskAssessment(String holderId, String name, double riskScore) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getName() {
        return name;
    }

    public double getRiskScore() {
        return riskScore;
    }

    @Override
    public String toString() {
        return "RiskAssessment{" +
                "holderId='" + holderId + '\'' +
                ", name='" + name + '\'' +
                ", riskScore=" + riskScore +
                '}';
    }
}

public class PolicyHolderRiskAssessment {

    public static Map<String, List<RiskAssessment>> assessRisk(List<PolicyHolder> holders) {
        List<RiskAssessment> assessments = holders.stream()
                .filter(h -> "Life".equalsIgnoreCase(h.getPolicyType()) && h.getAge() > 60)
                .map(h -> new RiskAssessment(
                        h.getHolderId(),
                        h.getName(),
                        h.getPremiumAmount() / h.getAge()))
                .sorted(Comparator.comparingDouble(RiskAssessment::getRiskScore).reversed())
                .collect(Collectors.toList());

        return assessments.stream()
                .collect(Collectors.groupingBy(a -> a.getRiskScore() > 0.5 ? "High Risk" : "Low Risk"));
    }

    public static void main(String[] args) {
        List<PolicyHolder> holders = new ArrayList<>();
        holders.add(new PolicyHolder("H1", "John", 65, "Life", 500));
        holders.add(new PolicyHolder("H2", "Mary", 70, "Life", 400));
        holders.add(new PolicyHolder("H3", "Alex", 55, "Life", 600));
        holders.add(new PolicyHolder("H4", "Sara", 62, "Health", 700));
        holders.add(new PolicyHolder("H5", "Tom", 68, "Life", 800));

        Map<String, List<RiskAssessment>> result = assessRisk(holders);

        result.forEach((category, list) -> {
            System.out.println(category + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
