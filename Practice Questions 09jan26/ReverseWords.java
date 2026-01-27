import java.util.Scanner;

public class ReverseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] words = str.split(" ");
        StringBuilder rev = new StringBuilder();
        for (int i = 0, n = words.length; i < n; ++i) {
            for (int j = words[i].length() - 1; j >= 0; --j) {
                rev.append(words[i].charAt(j));
            }
            if (i != n - 1) {
                rev.append(" ");
            }
        }
        System.out.println(rev);
        sc.close();
    }
}
