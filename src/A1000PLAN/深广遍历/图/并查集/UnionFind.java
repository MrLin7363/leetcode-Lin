package A1000PLAN.深广遍历.图.并查集;

/**
 *desc:原理的理解可以看这个
 * https://leetcode.cn/problems/number-of-provinces/solutions/550107/python-duo-tu-xiang-jie-bing-cha-ji-by-m-vjdr/
 *@author lin
 *@since 2023/11/9
 **/
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

    /* 返回某个节点 x 的根节点, 路径压缩：同一个集合中的父节点是一致的，都指向同一个父节点，深度比较浅*/
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

    /* 返回某个节点 x 的根节点 类似上面的find()只不过是递归 */
    private int findRecusion(int x) {
        // 递归找根节点是 parent[x] == x
        if (parent[x]==x){
            return x;
        }
        return parent[x]=find(parent[x]);
    }
}