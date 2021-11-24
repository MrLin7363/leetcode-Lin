/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package hw.construct;

/**
 * desc: 并查集模板
 *
 * @author c30021507
 * @since 2021/11/18
 **/
public class UnionFind {
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
