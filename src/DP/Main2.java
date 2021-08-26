package DP;

/**
 * @author faded828x
 * @date 2021/8/13
 */

import java.util.*;

class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 物品数量
        int v = scanner.nextInt(); // 背包容量

        int[] cost = new int[n]; // 物品体积
        int[] weight = new int[n]; // 物品价值

        for(int i = 0; i < n; i++) {
            cost[i] = scanner.nextInt();
            weight[i] = scanner.nextInt();
            // System.out.println(cost[i] + " " + weight[i]);
        }

        // System.out.println(Arrays.toString(cost));
        // System.out.println(Arrays.toString(weight));

        int[] dp = new int[v + 1];
        for(int i = 0; i < n; i++) {
            int c = cost[i];
            int w = weight[i];
            for(int j = v; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j - c] + w);
            }
            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[v]);

    }

}