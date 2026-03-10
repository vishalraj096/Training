public class Ques3 {
    public static void main(String[] args) {
        String s = "11101111011111";
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0, n = s.length(); i < n; ++i) {
            char ch = s.charAt(i);
            if (ch == '0') {
                sb.append((char) (64 + cnt));
                cnt = 0;
            } else {
                cnt++;
            }
        }
        if (cnt != 0) {
            sb.append((char) (64 + cnt));
        }
        System.out.println(sb.toString());
    }
}
