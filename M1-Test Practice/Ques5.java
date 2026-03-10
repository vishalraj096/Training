public class Ques5 {
    public static void main(String[] args) {
        String s1 = "build";
        String s2 = "dubli";
        int[] frq = new int[26];
        int n = s1.length(), m = s2.length();
        if (n != m) {
            System.out.println("no");
            return;
        }
        for (int i = 0; i < n; ++i) {
            frq[s1.charAt(i) - 'a']++;
            frq[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; ++i) {
            if (frq[i] != 0) {
                System.out.println("no");
                return;
            }
        }
        System.out.println("yes");
    }
}
