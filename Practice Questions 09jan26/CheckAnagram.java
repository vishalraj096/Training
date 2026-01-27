import java.util.Scanner;

public class CheckAnagram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine().toLowerCase();
        String b = sc.nextLine().toLowerCase();

        int[] cnt = new int[256];
        for (int i = 0; i < a.length(); ++i) {
            char ch = a.charAt(i);
            if (ch != ' ')
                cnt[ch]++;
        }
        for (int i = 0; i < b.length(); ++i) {
            char ch = b.charAt(i);
            if (ch != ' ')
                cnt[ch]--;
        }
        boolean ok = true;
        for (int i = 0; i < 256; ++i) {
            if (cnt[i] != 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            System.out.println("Anagram");
        } else {
            System.out.println("Not Anagram");
        }
        sc.close();
    }
}
