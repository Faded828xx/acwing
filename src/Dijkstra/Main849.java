package Dijkstra;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author faded828x
 * @date 2021/8/22
 */
public class Main849 {

    static int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] g = new int[n + 1][n + 1];
        for(int[] gg : g)
            Arrays.fill(gg, INF);

        for(int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int dis = scanner.nextInt();
            g[from][to] = Math.min(g[from][to], dis);
        }

//        for(int[] gg : g)
//            System.out.println(Arrays.toString(gg));

        dijkstra(g, n);

    }

    public static void dijkstra(int[][] g, int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, INF);
        boolean[] isOK = new boolean[n + 1];

        res[1] = 0;

        for(int i = 0; i < n; i++) {

            int min = INF;
            int idx = -1;
            for(int j = 1; j <= n; j++) {
                if(!isOK[j] && res[j] < min) {
                    min = res[j];
                    idx = j;
                }
            }

            if(idx == -1) {
                System.out.println(-1);
                return ;
            }

            isOK[idx] = true;

            for(int j = 1; j <= n; j++) {
                if(isOK[j]) continue;
                res[j] = Math.min(res[j], res[idx] + g[idx][j]);
            }

//            if(isOK[n]) {
//                System.out.println(res[n]);
//                return ;
//            }
        }

//        if(res[n] == INF)
//            System.out.println(-1);

        System.out.println(res[n]);

    }

}
