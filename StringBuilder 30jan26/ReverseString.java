public class ReverseString {
    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        String s = args.length > 0 ? args[0] : "hello";
        System.out.println(reverse(s));
    }
}
