public class Ques4 {
    public static void main(String[] args) {
        String s = "abcdd";
        int[] frq = new int[26];
        int maxi = 0;
        for (int i = 0, n = s.length(); i < n; ++i) {
            char ch = s.charAt(i);
            frq[ch - 'a']++;
            maxi = Math.max(maxi, frq[ch - 'a']);
        }
        int cnt = 0;
        int id = -1;
        for (int i = 0; i < 26; ++i) {
            if (frq[i] == maxi) {
                cnt++;
                id = i;
            }
        }
        if (cnt == 1) {
            System.out.println((char) (97 + id));
        } else {
            System.out.println(0);
        }
    }
}
