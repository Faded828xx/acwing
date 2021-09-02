package CSP;

import java.util.Scanner;

/**
 * @author faded828x
 * @date 2021/9/2
 */


public class Main3412 {

    static int[][] pre;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // matrix edge
        int L = scanner.nextInt();  // max -> useless
        int r = scanner.nextInt();  // abs(x - i) <= r
        int t = scanner.nextInt();  // threshold

        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        pre = new int[n][n];
        for(int i = 0; i < n; i++) {
            pre[i][0] = matrix[i][0];
            for(int j = 1; j < n; j++) {
                pre[i][j] = pre[i][j - 1] + matrix[i][j];
            }
        }

        int cnt = 0;
        int th = 0;
        int size = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int[] arr = sum(pre, i, j, r);
                int s = arr[0];
                size = arr[1];
                th = size * t;
                // System.out.println(s);
                if(s <= th)
                    cnt++;
            }
        }

        // for(int[] p : pre)
        //     System.out.println(Arrays.toString(p));

        System.out.println(cnt);



    }

    static int[] sum(int[][] pre, int x, int y, int r) {
        int n = pre.length;
        int up = x - r >= 0 ? x - r : 0;
        int down = x + r >= n ? n - 1 : x + r;
        int left = y - r >= 0 ? y - r : 0;
        int right = y + r >= n ? n - 1 : y + r;
        int res = 0;
        for(int i = up; i <= down; i++) {
            res += pre[i][right] - (left == 0 ? 0 : pre[i][left - 1]);
        }
        return new int[]{res, (down - up + 1) * (right - left + 1)};
    }

}