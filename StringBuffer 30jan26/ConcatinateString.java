public class ConcatinateString {
    public static String join(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (String s : arr)
            sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(join(args));
        else {
            String[] arr = { "Hello", " ", "World", "!" };
            System.out.println(join(arr));
        }
    }
}
