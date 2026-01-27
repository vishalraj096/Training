import java.util.Scanner;

public class CapitalizeWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder out = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                out.append(ch);
                newWord = true;
            } else {
                if (newWord) {
                    out.append(Character.toUpperCase(ch));
                    newWord = false;
                } else {
                    out.append(ch);
                }
            }
        }
        System.out.println(out);
        sc.close();
    }
}
