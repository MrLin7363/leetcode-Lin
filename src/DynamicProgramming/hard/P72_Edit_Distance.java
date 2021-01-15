package DynamicProgramming.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/15
  *@Describe: 编辑距离：最少的步数。
 */

public class P72_Edit_Distance {
    /*
    DP 二维数组 62+40
    dp[i][j]=min(dp[i-1][j]+1,dp[i][j+1]+1,dp[i-1][j-1]+int(word1[i]!=word2[j]))
    官方，这里的二维矩阵反着来的
     */
    public int minDistance3(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        // 有一个为空串，编辑距离为一个个添加另一个字符串
        if (n*m==0){
            return m+n;
        }
        // 初始化DP数组，注意n+1 因为DP的定义从下标开始
        int[][] dp=new int[n+1][m+1];
        // 边界状态初始化
        for (int i = 0; i < n+1; i++) {
            dp[i][0]=i;
        }
        for (int j = 0; j < m+1; j++) {
            dp[0][j]=j;
        }
        // 计算DP值
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                int down=dp[i-1][j]+1;
                int left=dp[i][j-1]+1;
                int left_down=dp[i-1][j-1];
                // 最后一个字符不同，要加多一步
                if (word1.charAt(i-1)!=word2.charAt(j-1)){
                    left_down+=1;
                }
                dp[i][j]=Math.min(Math.min(left,down),left_down);
            }
        }
        return dp[n][m];
    }

    /*
    一维数组-滚动数组  word1 转 word2
    二维矩阵正着顺序来  92+52
     */
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        // 有一个为空串，编辑距离为一个个添加另一个字符串
        if (n*m==0){
            return m+n;
        }
        // 取较长的字符串作为每一列
        int colLen=m+1;
        int rowLen=n+1;
        // 初始化DP数组，注意n+1 因为DP的定义从下标开始
        int[] dp=new int[colLen];
        // 初始化第一行
        for (int i = 0; i < colLen; i++) {
            dp[i]=i;
        }
        // 计算DP值，只和二维矩阵 左边，左上，上，上个元素有关
        for (int i = 1; i < rowLen ; i++) {
            int left_up=dp[0];// 左上元素需要记住
            dp[0]=dp[0]+1;
            for (int j = 1; j < colLen ; j++) {
                int left=dp[j-1]+1;
                int up=dp[j];
                if (word1.charAt(i-1)!=word2.charAt(j-1)){
                    left_up+=1;
                }
                dp[j]=Math.min(Math.min(left,up+1),left_up);
                left_up=up;// 上面元素更新为左上元素，注意上面元素未修改
            }
        }
        return dp[colLen-1];
    }

    /*
       ""  w  o  r  d
   ""   0  1  2  3  4
   w    1  0  1  2  3
   o    2  1  0  1  2
     */
    public static void main(String[] args) {
        P72_Edit_Distance s=new P72_Edit_Distance();
        s.minDistance("wo","word");
    }

}
