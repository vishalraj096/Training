public class FindSentenceWithWord {
    public static String find(String[] s, String w) {
        for (String x : s)
            if (x.contains(w))
                return x;
        return "Not Found";
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            String w = args[0];
            String[] s = new String[args.length - 1];
            for (int i = 1; i < args.length; i++)
                s[i - 1] = args[i];
            System.out.println(find(s, w));
        } else {
            String[] s = { "hello world", "java programming", "search the word" };
            String w = "java";
            System.out.println(find(s, w));
        }
    }
}
