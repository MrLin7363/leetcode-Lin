package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/23
  *@Describe:
 */

public class P5_Longest_Palindromic_Substring {
    /*
    动态规划 填右上角的二维表格 dp[i][j]表示 字符串区间内的是否为回文子串
    因为左边界小于右边界
    21+29
     */
    public String longestPalindrome(String s) {
        if (s==null||s.length()<2) {
            return s;
        }
        int len=s.length();
        boolean[][] dp=new boolean[len][len];// 默认为false
        // 为了好理解，对角线赋值为true，因为单个字符都是回文
        for (int i = 0; i < len; i++) {
            dp[i][i]=true;
        }
        int maxLen=1;//一个字符
        int begin=0;
        // 按列填，从第一列开始，只填右上半部分
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
               /* if (s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else{
                    if (j-i<3){ // 如果区间的长度小于2是回文 j-1 - (i+1) +1 <2
                        dp[i][j]=true;
                    }else{
                        // 参考中间部分是否是回文， i+1,j-1是缩小区间，因为 j-i的限制，对角线值不会被参考到
                        dp[i][j]=dp[i+1][j-1];
                    }
                }*/
                // 下面一行可以替代上面
                dp[i][j]=s.charAt(i)==s.charAt(j) && ( j-i<3 || dp[i+1][j-1] );
                if (dp[i][j]&& j-i+1 > maxLen){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
    /*
    中心扩散法 70+90
     */
    public String longestPalindrome2(String s) {
        if (s==null||s.length()<1){
            return "";
        }
        int start=0,end=0;
        for (int i = 0; i < s.length(); i++) {
            int len1=expandAroundCenter(s,i,i); // 奇数扩散点
            int len2=expandAroundCenter(s,i,i+1);// 偶数扩散点，中间位置为两个元素之间
            int maxLen=Math.max(len1,len2);
            if (maxLen>end-start){
                start=i-(maxLen-1)/2; // maxLen是奇数
                end=i+maxLen/2;
            }
        }
        return s.substring(start,end+1);
    }
    public int expandAroundCenter(String s,int left,int right){
        while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        // right=1,left=0,如果 不相等，那么等于0
        return right-left-1; // 奇数 1 3 5
    }

    public static void main(String[] args) {
        P5_Longest_Palindromic_Substring s=new P5_Longest_Palindromic_Substring();
        s.longestPalindrome2("babad");
    }
}
