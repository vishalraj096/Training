public class Ques13 {
    public static void main(String[] args) {
        String s = "HeLLo";
        int up = 0, lo = 0;
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                up++;
            } else if (c >= 'a' && c <= 'z') {
                lo++;
            }
        }
        System.out.println(up > lo ? s.toUpperCase() : s.toLowerCase());
    }
}
