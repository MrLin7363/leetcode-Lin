 

package hw.并查集;

/**
 * desc:
 *
 * @author junlin
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
            // for (int j = i+1; j < n; j++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j]==1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.count;
    }

    public static void main(String[] args) {
        P547省份数量 p547省份数量=new P547省份数量();
        int[][] req= new int[5][5];
        req[0][1]=1;
        req[1][2]=1;
        req[3][0]=1;
        p547省份数量.findCircleNum(req);
    }

}
