package Dijkstra;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author faded828x
 * @date 2021/8/27
 */
public class Main1375 {

    static final int INF = Integer.MAX_VALUE / 2;

    // 将a~z A~Z  压缩到 52*52 的邻接矩阵
    static int[][] g = new int[52][52];

    static int[] res = new int[52];

    static boolean[] isOK = new boolean[52];



    // 25 -> (0~24) 最短路径
    static void dijkstra() {
        // int[0]:当前节点到源节点的可能最短路径 int[1]:节点号
        PriorityQueue<int[]> heap = new PriorityQueue<>((l1, l2) -> l1[0] - l2[0]);
        res[25] = 0;
        heap.add(new int[]{0,25});  // 源节点
        while(!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int pos = cur[1];
            if(isOK[pos]) continue;
            isOK[pos] = true;
            for(int i = 0; i < 52; i++) {
                int weight = g[pos][i];
                if(weight == INF || isOK[i]) continue;  // 与该节点无边或该节点已是最短路径
                if(dis + weight < res[i]) {
                    res[i] = dis + weight;
                    heap.add(new int[]{res[i], i});
                }
            }
        }


    }



    public static void main(String[] args) throws IOException {

        for(int[] gg : g)
            Arrays.fill(gg, INF);

        Arrays.fill(res, INF * 2);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int n = Integer.parseInt(s);


        for(int i = 0; i < n; i++) {
            String[] s1 = bufferedReader.readLine().split(" ");
            int from = s1[0].charAt(0) - 'A';   // 0～25 || 32～57
            int to = s1[1].charAt(0) - 'A';
            if(from == to) continue;    // 去掉自环
            int w = Integer.parseInt(s1[2]);
            if(from >= 32) from -= 6;
            if(to >= 32) to -= 6;
            g[from][to] = Math.min(g[from][to], w); // 多条重边取最短 这里踩坑好几次了 :(
            g[to][from] = Math.min(g[from][to], w); // 无向边
        }

        dijkstra();

        // 0～24 中最小值
        int ans = Integer.MAX_VALUE;
        int idx = -1;
        for(int i = 0; i < 25; i++) {
            if (res[i] < ans) {
                ans = res[i];
                idx = i;
            }
        }

//        System.out.println(Arrays.toString(res));


        System.out.println((char) (idx + 'A') + " " + ans);


    }


}
