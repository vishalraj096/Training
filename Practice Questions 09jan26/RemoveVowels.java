import java.util.Scanner;

public class RemoveVowels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder s = new StringBuilder();
        for (int i = 0, n = str.length(); i < n; ++i) {
            char ch = str.charAt(i);
            if (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                s.append(str.charAt(i));
            }
        }
        System.out.println(s);
        sc.close();
    }
}
