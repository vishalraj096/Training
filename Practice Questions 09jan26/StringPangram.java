import java.util.Scanner;

public class StringPangram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean[] alphabet = new boolean[26];
        for (int i = 0, n = str.length(); i < n; ++i) {
            char ch = Character.toLowerCase(str.charAt(i));
            if (ch >= 'a' && ch <= 'z') {
                alphabet[ch - 'a'] = true;
            }
        }
        boolean isPangram = true;
        for (boolean b : alphabet) {
            if (!b) {
                isPangram = false;
                break;
            }
        }
        if (isPangram) {
            System.out.println("Pangram");
        } else {
            System.out.println("Not Pangram");
        }
        sc.close();
    }
}
