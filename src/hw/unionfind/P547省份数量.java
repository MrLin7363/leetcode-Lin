/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package hw.unionfind;

/**
 * desc:
 *
 * @author c30021507
 * @since 2021/11/18
 **/
public class P547省份数量 {

    private class UnionFind{
        int count;
        int[] parent;

        public UnionFind(int n){
            this.count=n;
            this.parent=new int[n];
            for (int i = 0; i < n; i++) {
                parent[i]=i;
            }
        }

        public int find(int i){
            while(parent[i]!=i){
                i=parent[i];
            }
            return i;
        }

        public void union(int q,int p){
            int qRoot=find(q);
            int pRoot=find(p);
            if (qRoot==pRoot){
                return;
            }
            parent[qRoot]=pRoot;
            count--;
        }

    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            // 优化不用走相同的i j 的访问
            for (int j = i+1; j < n; j++) {
                if (isConnected[i][j]==1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.count;
    }

}
