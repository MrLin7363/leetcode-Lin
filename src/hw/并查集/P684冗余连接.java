 

package hw.并查集;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/11/19
 **/
public class P684冗余连接 {

    /*
    并查集： 无向图
    方法一：查找环
    树是一个连通且无环的无向图，在树中多了一条附加的边之后就会出现环，因此附加的边即为导致环出现的边
    可以通过并查集寻找附加的边。初始时，每个节点都属于不同的连通分量。遍历每一条边，判断这条边连接的两个顶点是否属于相同的连通分量
    如果两个顶点属于不同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间不连通，因此当前的边不会导致环出现，合并这两个顶点的连通分量
    如果两个顶点属于相同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间已经连通，因此当前的边导致环出现，为附加的边，将当前的边作为答案返回
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (unionFind.find(edge[0]) != unionFind.find(edge[1])) {
                unionFind.union(edge[0], edge[1]);
            } else {
                // 此处也是最后出现的边，因为前面的是建立连通图，当找到这条造成环的边时就是最后的边
                return edge;
            }
        }
        return new int[0];
    }
    public class UnionFind {
        // 记录父节点,每个联通节点通过这个连接
        private int[] parent;
        // 连通分量，总共有多少个是连通的 = 边的数量
        private int count;


        /* 构造函数，n 为图的节点总数 */
        public UnionFind(int n) {
            // 一开始互不连通
            this.count = n;
            // 父节点指针初始指向自己
            // 因为此题特殊性，数组是1~n 所以数父节点初始化从1开始
            parent = new int[n+1];
            for (int i = 1; i <= n; i++) {
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

        /* 返回当前的连通分量个数 */
        public int count() {
            return count;
        }

        // 这样，如果节点p和q连通的话，它们一定拥有相同的根节点：
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        /* 返回某个节点 x 的根节点 */
        private int findRecusion(int x) {
            // 递归找根节点是 parent[x] == x
            if (parent[x]==x){
                return x;
            }
            return parent[x]=find(parent[x]);
        }
    }

}
