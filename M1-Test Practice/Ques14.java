public class Ques14 {
    public static void main(String[] args) {
        int num = 1320;
        int rev = 0;
        while (num != 0) {
            int tmp = num % 10;
            rev = rev * 10 + tmp;
            num /= 10;
        }
        System.out.println(rev);
    }
}
