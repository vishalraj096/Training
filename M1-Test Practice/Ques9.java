public class Ques9 {
    public static void main(String[] args) {
        String s = "quick";
        StringBuilder sb = new StringBuilder();
        int pos = 3;
        for (int i = 0, n = s.length(); i < n; ++i) {
            char ch = s.charAt(i);
            if (ch >= 'a' + pos) {
                sb.append((char) (ch - pos));
            } else {
                sb.append((char) (ch + 26 - pos));
            }
        }
        System.out.println(sb.toString());
    }
}
