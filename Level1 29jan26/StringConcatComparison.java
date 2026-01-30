public class StringConcatComparison {
    static long timeString(int n) {
        long t1 = System.nanoTime();
        String s = "";
        for (int i = 0; i < n; i++)
            s = s + "a";
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    static long timeStringBuilder(int n) {
        long t1 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append("a");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    static long timeStringBuffer(int n) {
        long t1 = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++)
            sb.append("a");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static void main(String[] args) {
        int[] sizes = { 1000, 10000, 100000 };
        for (int n : sizes) {
            long a = timeString(n);
            long b = timeStringBuilder(n);
            long c = timeStringBuffer(n);
            System.out.println("N=" + n + " String=" + a + "ms StringBuilder=" + b + "ms StringBuffer=" + c + "ms");
        }
    }
}
