package Construct;

import java.util.LinkedList;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/07 19:34
 * @Describe: 邻接表-有向无环图
 */

public class Graph {
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s, int t) { // s先于t，边s->t
        adj[s].add(t);
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }


}