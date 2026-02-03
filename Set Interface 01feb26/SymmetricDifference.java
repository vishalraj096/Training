import java.util.*;

public class SymmetricDifference {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            
            System.out.println("Enter number of elements in Set1:");
            int n1 = sc.nextInt();
            System.out.println("Enter elements for Set1:");
            for (int i = 0; i < n1; i++) {
                set1.add(sc.nextInt());
            }
            
            System.out.println("Enter number of elements in Set2:");
            int n2 = sc.nextInt();
            System.out.println("Enter elements for Set2:");
            for (int i = 0; i < n2; i++) {
                set2.add(sc.nextInt());
            }
            
            System.out.println("Set1: " + set1);
            System.out.println("Set2: " + set2);
            
            Set<Integer> symmetricDiff = new HashSet<>(set1);
            symmetricDiff.addAll(set2);
            
            Set<Integer> intersection = new HashSet<>(set1);
            intersection.retainAll(set2);
            
            symmetricDiff.removeAll(intersection);
            
            System.out.println("Symmetric Difference: " + symmetricDiff);
        }
    }
}
