package DynamicProgramming.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/28
  *@Describe: 0-1 背包 略难
  自顶向下：记忆化搜索-递归
  自底向上：动态规划-循环
 */


import java.util.Arrays;

public class P474Ones_and_Zeroes {
    // 动态规划-三维数组  17 + 13
    // dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
    public static int findMaxForm2(String[] strs, int m, int n) {
        int len=strs.length;
        int[][][] dp=new int[len+1][m+1][n+1];
        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int count[]=countZeroAndOne(strs[i-1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 抄上一行
                    dp[i][j][k]=dp[i-1][j][k];
                    int zeros=count[0];
                    int ones=count[1];
                    if (j>=zeros && k>=ones){
                        dp[i][j][k]=Math.max(dp[i-1][j][k],dp[i-1][j-zeros][k-ones]+1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }
    // 动态规划-二维数组 75+89
    public static int findMaxForm(String[] strs, int m, int n) {
        int len=strs.length;
        int[][] dp=new int[m+1][n+1];
        for (String s:strs) {
            // 注意：有一位偏移
            int count[]=countZeroAndOne(s);
            for (int j = m; j >=0 ; j--) {
                for (int k = n; k >= 0; k--) {
                    int zeros=count[0];
                    int ones=count[1];
                    if (j>=zeros && k>=ones){
                        dp[j][k]=Math.max(dp[j][k],dp[j-zeros][k-ones]+1);
                    }
                }
            }
        }
        return dp[m][n];
    }
    // 用两个元素存当前字符串0和1的个数
    private static int[] countZeroAndOne(String str){
        int[] count=new int[2];
        for(char c:str.toCharArray()){
            count[c-'0']++; // 0就是0，1就是1
        }
        return count;
    }

    /*
    自顶向下，记忆化搜索  27+12
     */
    private static int [][][] memo;
    public static int findMaxForm3(String[] strs, int m, int n) {
        memo=new int[strs.length][m+1][n+1];
        for(int i = 0;i<memo.length;i++){
            for(int j=0;j<memo[i].length;j++){
                // 会将 memo[i][j][0到n]都赋值为-1 ,如果是二维数组也是一样
                Arrays.fill(memo[i][j],-1);
            }
        }
        return tryFindMaxForm(strs,strs.length-1,m,n);
    }
    // 用m，n 拼出 strs[0,i] 的 最大个数
    public static int tryFindMaxForm(String[] strs,int i,int m, int n){
        if (i<0){
            return 0;
        }
        if (memo[i][m][n]!=-1){
            return memo[i][m][n];
        }
        // 计算当前字符串的 0和 1 个数
        int numsOf0 = 0;
        int numsOf1 = 0;
        String str = strs[i];
        for(int j = 0;j<str.length();j++){
            if(str.charAt(j) == '0'){
                numsOf0++;
            }else{
                numsOf1++;
            }
        }
        if (m>=numsOf0 && n>= numsOf1){
            memo[i][m][n]=Math.max( tryFindMaxForm(strs,i-1,m,n),
                    tryFindMaxForm(strs,i-1,m-numsOf0,n-numsOf1)+1 );
        }else {
            memo[i][m][n]=tryFindMaxForm(strs,i-1,m,n);
        }
        return  memo[i][m][n];
    }

}
