 

package hw.并查集;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/22
 **/
public class P685冗余连接2 {

    /*
    并查集-有向图
    1.  有冲突 且 有环，删除为环的边
    2.  有冲突 且 无环，选择删除后还能构成树的边
    3.  无冲突 一定有环，删除为环的边
    1+3 合并
     */
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        // 初始化连通图，注意长度无0标识符要加1
        UnionFind unionFind=new UnionFind(n+1);
        // 节点的入度初始化
        int inDegree[] = new int[n+1];
        for (int i = 0; i < n; i++) {
            inDegree[edges[i][1]]++;
        }
        // 入度的下标
        List<Integer> sides = new ArrayList<>(2);
        // 找入度为2的节点对应的两条边，注意要倒叙，因为返回最后的边
        for (int i = n-1; i >=0 ; i--) {
            if(inDegree[edges[i][1]]==2){
                sides.add(i);
            }
        }
        // 冲突
        if (sides.size()>1){
            if (isTree(edges,sides.get(0),unionFind)){
                return edges[sides.get(0)];
            }else{
                return edges[sides.get(1)];
            }
        }
        // 无冲突
        for (int[] edge:edges){
            if (unionFind.find(edge[0])!= unionFind.find(edge[1])){
                unionFind.union(edge[0],edge[1]);
            }else{
                return edge;
            }
        }
        return new int[0];
    }

    /*
    判断删了一条边后是不是树
     */
    private static boolean isTree(int[][] edges,int deleteEdge,UnionFind unionFind){
         for (int i = 0; i < edges.length; i++) {
            // 该边要删除不记录在图里
            if (i==deleteEdge) {
                continue;
            }
            // 构成有向环一定不是树
            if (unionFind.find(edges[i][0])== unionFind.find(edges[i][1])){
                return false;
            }else{
                unionFind.union(edges[i][0],edges[i][1]);
            }
        }
        // 没有构成环就是树
        return true;
    }

    public static void main(String[] args) {
        findRedundantDirectedConnection(new int[][]{{1,2},{1,3},{2,3}});

    }


    public static class UnionFind {
        // 记录父节点,每个联通节点通过这个连接
        private int[] parent;
        // 连通分量，总共有多少个是连通的  边的数量
        private int count;


        /* 构造函数，n 为图的节点总数 */
        public UnionFind(int n) {
            // 一开始互不连通
            this.count = n;
            // 父节点指针初始指向自己
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            // 如果是同一个连通节点
            if (rootP == rootQ) {
                return;
            }
            // 将两个区域合并为一区域
            parent[rootP] = rootQ;
            // parent[rootQ] = rootP 也一样
            count--; // 两个分量合二为一
        }
        /* 返回某个节点 x 的根节点 */
        public int find(int x) {
            // 循环找根节点是 parent[x] == x
            // 也可以递归
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }
    }
}
