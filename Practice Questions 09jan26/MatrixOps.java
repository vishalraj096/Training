import java.util.Scanner;

public class MatrixOps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt(); // 1: transpose, 2: multiplication
        if (choice == 1) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int[][] a = new int[r][c];
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    a[i][j] = sc.nextInt();
                }
            }
            for (int j = 0; j < c; ++j) {
                StringBuilder row = new StringBuilder();
                for (int i = 0; i < r; ++i) {
                    row.append(a[i][j]);
                    if (i != r - 1)
                        row.append(" ");
                }
                System.out.println(row);
            }
        } else if (choice == 2) {
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int[][] A = new int[r1][c1];
            for (int i = 0; i < r1; ++i) {
                for (int j = 0; j < c1; ++j) {
                    A[i][j] = sc.nextInt();
                }
            }
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            int[][] B = new int[r2][c2];
            for (int i = 0; i < r2; ++i) {
                for (int j = 0; j < c2; ++j) {
                    B[i][j] = sc.nextInt();
                }
            }
            if (c1 != r2) {
                System.out.println("Invalid");
            } else {
                int[][] C = new int[r1][c2];
                for (int i = 0; i < r1; ++i) {
                    for (int j = 0; j < c2; ++j) {
                        int sum = 0;
                        for (int k = 0; k < c1; ++k) {
                            sum += A[i][k] * B[k][j];
                        }
                        C[i][j] = sum;
                    }
                }
                for (int i = 0; i < r1; ++i) {
                    StringBuilder row = new StringBuilder();
                    for (int j = 0; j < c2; ++j) {
                        row.append(C[i][j]);
                        if (j != c2 - 1)
                            row.append(" ");
                    }
                    System.out.println(row);
                }
            }
        }
        sc.close();
    }
}
