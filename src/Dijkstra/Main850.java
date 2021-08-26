package Dijkstra;

import java.util.*;

/**
 * @author faded828x
 * @date 2021/8/22
 */
public class Main850 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 稀疏图用邻接表存储 m-->n
        // add edge x->y:c
        int[] he = new int[n + 1];  // 每个节点指向的链表头 为编号
        int[] e = new int[m];   // 每条边指向y
        int[] ne = new int[m];  // 每条边在同一节点内的下条边
        int[] w = new int[m];   // 每条边的权重

        int[] res = new int[n + 1]; // 每个节点到源节点的最短路径
        boolean[] isOK = new boolean[n + 1]; // 每个节点是否已经为最短

        // 每个节点初始无边 新加入边时 该边next为-1
        Arrays.fill(he, -1);

        for(int i = 0; i < m; i++) {

            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();

            // add edge (index = i)
            e[i] = to;
            ne[i] = he[from];
            he[from] = i;
            w[i] = weight;

        }

        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(res, INF);

        // dijkstra

        // list[1]:节点索引idx   list[0]:res[idx]
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>((l1, l2) -> l1.get(0) - l2.get(0));
        heap.add(new ArrayList<Integer>(){{add(0);add(1);}});

        while(!heap.isEmpty()) {
            List<Integer> top = heap.poll();
            int dis = top.get(0);
            int idx = top.get(1);   // 节点索引

            // 先前不同isOK的节点对该节点进行多次更新 将该节点多次加入heap中 因此该节点可能isOK了
            if(isOK[idx]) continue;

            isOK[idx] = true;

            // 遍历idx节点的所有边
            for(int i = he[idx]; i != -1; i = ne[i]) {
                int to = e[i];
                int weight = w[i];
                // 更新notOK的节点 并将其加入堆中 以便从中选出最小
                if(!isOK[to] && res[to] > dis + weight){
                    heap.add(new ArrayList<Integer>(){{add(dis + weight);add(to);}});
                    res[to] = dis + weight;
                }
            }

        }

        if(res[n] == INF)
            System.out.println(-1);
        else System.out.println(res[n]);

    }


}
