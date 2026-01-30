public class FirstNegativeIndex {
    public static int find(int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] < 0)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] a;
        if (args.length > 0) {
            a = new int[args.length];
            for (int i = 0; i < args.length; i++)
                a[i] = Integer.parseInt(args[i]);
        } else {
            a = new int[] { 3, 2, -5, 7 };
        }
        System.out.println(find(a));
    }
}
