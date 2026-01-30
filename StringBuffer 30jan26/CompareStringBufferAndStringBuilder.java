public class CompareStringBufferAndStringBuilder {
    public static long timeBuffer(int n) {
        long t1 = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("hello");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static long timeBuilder(int n) {
        long t1 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("hello");
        String s = sb.toString();
        long t2 = System.nanoTime();
        return (t2 - t1) / 1_000_000;
    }

    public static void main(String[] args) {
        int n = 1000000;
        long a = timeBuffer(n);
        long b = timeBuilder(n);
        System.out.println("StringBuffer=" + a + "ms StringBuilder=" + b + "ms");
    }
}
