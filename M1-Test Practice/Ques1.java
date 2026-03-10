public class Ques1 {
    public static void main(String[] args) {
        int n = 3;
        String s = "abc";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}