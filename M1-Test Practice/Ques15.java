public class Ques15 {
    public static void main(String[] args) {
        String s = "internationalization";
        int n = s.length();
        StringBuilder str = new StringBuilder();
        str.append(s.charAt(0)).append(s.length() - 2).append(s.charAt(n - 1));
        System.out.println(str.toString());
    }
}
