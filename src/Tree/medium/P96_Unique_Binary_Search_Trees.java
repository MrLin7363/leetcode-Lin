package Tree.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/6
  *@Describe:
 */

public class P96_Unique_Binary_Search_Trees {
    /*
    DP  100 + 5
     */
    public int numTrees(int n) {
        int[] G=new int[n+1];
        G[0]=1;
        G[1]=1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i]=G[j-1]*G[i-j];
            }
        }
        return G[n];
    }
}
