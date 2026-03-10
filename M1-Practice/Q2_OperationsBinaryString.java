import java.util.*;

public class Q2_OperationsBinaryString {
    public static int operationsBinaryString(String str) {
        if (str == null) return -1;
        int result = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i += 2) {
            char op = str.charAt(i);
            int next = str.charAt(i + 1) - '0';
            if (op == 'A') result = result & next;
            else if (op == 'B') result = result | next;
            else if (op == 'C') result = result ^ next;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(operationsBinaryString(str));
    }
}
