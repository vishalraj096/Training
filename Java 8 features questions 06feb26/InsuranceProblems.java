import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class InsurancePolicy {
    private String policyNumber;
    private String holderName;
    private double premiumAmount;

    public InsurancePolicy(String policyNumber, String holderName, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                ", premiumAmount=" + premiumAmount +
                '}';
    }
}

public class InsuranceProblems {

    public static void main(String[] args) {
        List<InsurancePolicy> policies = new ArrayList<>();
        policies.add(new InsurancePolicy("P001", "John Smith", 900));
        policies.add(new InsurancePolicy("P002", "Alice Brown", 1500));
        policies.add(new InsurancePolicy("P003", "Bob Smith", 2100));
        policies.add(new InsurancePolicy("P004", "Charlie Green", 1800));
        policies.add(new InsurancePolicy("P005", "David White", 2500));
        policies.add(new InsurancePolicy("P006", "Eve Smith", 1200));
        policies.add(new InsurancePolicy("P007", "Frank Miller", 800));

        System.out.println("1) Filter policies with premium > 1200");
        List<InsurancePolicy> premiumGreaterThan1200 = policies.stream()
                .filter(p -> p.getPremiumAmount() > 1200)
                .collect(Collectors.toList());
        premiumGreaterThan1200.forEach(System.out::println);

        System.out.println("\n2) Sort policies by holder name");
        List<InsurancePolicy> sortedByName = new ArrayList<>(policies);
        sortedByName.sort((p1, p2) -> p1.getHolderName().compareToIgnoreCase(p2.getHolderName()));
        sortedByName.forEach(System.out::println);

        System.out.println("\n3) Total premium of all policies");
        double totalPremium = policies.stream()
                .mapToDouble(InsurancePolicy::getPremiumAmount)
                .sum();
        System.out.println("Total premium = " + totalPremium);

        System.out.println("\n4) Print policy details");
        policies.forEach(p -> System.out.println(
                "Policy: " + p.getPolicyNumber() +
                        ", Holder: " + p.getHolderName() +
                        ", Premium: " + p.getPremiumAmount()));

        System.out.println("\n5) Policies with premium between 1000 and 2000");
        List<InsurancePolicy> premiumRange = policies.stream()
                .filter(p -> p.getPremiumAmount() >= 1000 && p.getPremiumAmount() <= 2000)
                .collect(Collectors.toList());
        premiumRange.forEach(System.out::println);

        System.out.println("\n6) Policy with highest premium");
        Optional<InsurancePolicy> maxPremiumPolicy = policies.stream()
                .max(Comparator.comparingDouble(InsurancePolicy::getPremiumAmount));
        maxPremiumPolicy.ifPresent(System.out::println);

        System.out.println("\n7) Group policies by holder name initial");
        Map<Character, List<InsurancePolicy>> groupedByInitial = policies.stream()
                .collect(Collectors.groupingBy(p -> Character.toUpperCase(p.getHolderName().charAt(0))));
        groupedByInitial.forEach((ch, list) -> {
            System.out.println(ch + ":");
            list.forEach(System.out::println);
        });

        System.out.println("\n8) Average premium of all policies");
        DoubleSummaryStatistics stats = policies.stream()
                .mapToDouble(InsurancePolicy::getPremiumAmount)
                .summaryStatistics();
        System.out.println("Average premium = " + stats.getAverage());

        System.out.println("\n9) Sort policies by premium ascending and print");
        policies.stream()
                .sorted(Comparator.comparingDouble(InsurancePolicy::getPremiumAmount))
                .forEach(System.out::println);

        System.out.println("\n10) Any policy with premium > 2000?");
        boolean anyPremiumGreaterThan2000 = policies.stream()
                .anyMatch(p -> p.getPremiumAmount() > 2000);
        System.out.println(anyPremiumGreaterThan2000);

        System.out.println("\n11) Count policies in ranges");
        long range1 = policies.stream().filter(p -> p.getPremiumAmount() >= 0 && p.getPremiumAmount() <= 1000).count();
        long range2 = policies.stream().filter(p -> p.getPremiumAmount() > 1000 && p.getPremiumAmount() <= 2000)
                .count();
        long range3 = policies.stream().filter(p -> p.getPremiumAmount() > 2000).count();
        System.out.println("0-1000: " + range1);
        System.out.println("1001-2000: " + range2);
        System.out.println(">2000: " + range3);

        System.out.println("\n12) Unique holder names");
        List<String> uniqueNames = policies.stream()
                .map(InsurancePolicy::getHolderName)
                .distinct()
                .collect(Collectors.toList());
        uniqueNames.forEach(System.out::println);

        System.out.println("\n13) Policies where holder name contains 'Smith'");
        List<InsurancePolicy> smithPolicies = policies.stream()
                .filter(p -> p.getHolderName().toLowerCase(Locale.ROOT).contains("smith"))
                .collect(Collectors.toList());
        smithPolicies.forEach(System.out::println);

        System.out.println("\n14) Map of policy number to premium amount");
        Map<String, Double> policyToPremium = policies.stream()
                .collect(Collectors.toMap(InsurancePolicy::getPolicyNumber, InsurancePolicy::getPremiumAmount));
        policyToPremium.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n15) Most frequent words in text corpus");
        String text = "This is a sample text. This text is just a simple sample text for testing text frequency.";
        String[] words = text.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9 ]", " ")
                .split("\\s+");

        Map<String, Long> wordCounts = java.util.Arrays.stream(words)
                .filter(w -> !w.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int topN = 3;
        Map<String, Long> topWords = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));

        topWords.forEach((w, c) -> System.out.println(w + " -> " + c));

        System.out.println("\n16) Second most repeated word");
        List<Map.Entry<String, Long>> sortedWords = new ArrayList<>(wordCounts.entrySet());
        sortedWords.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        if (sortedWords.size() >= 2) {
            Map.Entry<String, Long> second = sortedWords.get(1);
            System.out.println("Second most repeated word: " + second.getKey() + " -> " + second.getValue());
        } else {
            System.out.println("Not enough distinct words.");
        }
    }
}
