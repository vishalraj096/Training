import java.util.Scanner;

public class SpaceWithHyphen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder s = new StringBuilder();
        for (int i = 0, n = str.length(); i < n; ++i) {
            if (str.charAt(i) == ' ') {
                s.append('-');
            } else {
                s.append(str.charAt(i));
            }
        }
        System.out.println(s);
        sc.close();
    }
}