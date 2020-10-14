package wangzheng.topoSort;

import Construct.Graph;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/07 19:23
 * @Describe: 贪心拓扑排序-可运行
 */
public class topoSortGreedy {
    public static void main(String[] args) {
        Graph graph = new Graph(4);//无环有向图
        graph.addEdge(1,0);//1节点指向0节点
        graph.addEdge(0,2);
        graph.addEdge(2,3);
        topoSortByKahn(graph.getV(),graph.getAdj());
        PriorityQueue queue = new PriorityQueue(2);
    }

    public static void topoSortByKahn(int v,LinkedList<Integer>[] adj) {
        int[] inDegree = new int[v]; // 统计每个顶点的入度
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        //找到入度为0的节点
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            //删除节点
            int i = queue.remove();
            System.out.print("->" + i);
            //将这个顶点可达的顶点的入度-1，入度为0加入队列，再重复
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }
}
