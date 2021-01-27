package String.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/22 22:30
 * @Describe: 生成括号
 * 1.DFS
 * 2.DP
 */
public class Generate_Parentheses_22 {
    /*
    暴力DFS
     */
    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<>();
        // 2*n最多两个都有对应的括号
        generateAll(new char[2*n],0,result);
        return result;
    }

    private void generateAll(char[] cur,int pos,List<String>result){
        // 结束条件：长度等于对应长度，且检查当前括号是一一对应的
        if (pos==cur.length) {
            if (isValid(cur))
                result.add(new String(cur));
        }
        else{
            cur[pos]='(';
            generateAll(cur,pos+1,result); // 每次递归的cur都是不一样的
            cur[pos]=')';
            generateAll(cur,pos+1,result);
        }

    }
    private boolean isValid(char[] cur){
        int balance=0;
        for (int i=0;i<cur.length;i++){
            if (cur[i]=='('){
                balance++;
            }else balance--;
            if (balance<0) // 右括号比左括号多
                return false;
        }
        // 为0则左右括号对应
        return balance==0;
    }

    /*
    ------------------------------------------------------------------------------
    2.剪枝法DFS
     */
    public List<String> generateParenthesis2(int n) {
        ArrayList<String> result = new ArrayList<>();
        backtrack(result,"",0,0,n);
        return result;
    }
    private void backtrack(List<String> result, String cur,int open, int close, int max){
        // 结束条件
        if (cur.length()==max*2){
            result.add(cur);
//            return;   有无都行，一般来说是结束条件就return
        }
        // 左括号数量不大于 nn，我们可以放一个左括号
        if (open<max)
            backtrack(result,cur+"(",open+1,close,max);
        // 右括号数量小于左括号的数量，我们可以放一个右括号
        if (close<open)
            backtrack(result,cur+")",open,close+1,max);
    }

    /*
    -----------------------------------------------------
    3.动态规划，"(" + 【i=p时所有括号的排列组合】 + ")" + 【i=q时所有括号的排列组合】
    i=2 时  ( + dp[0]+)+dp[1]  (+dp[1]+)+dp[0]
    I=3   ( + dp[2]+)+dp[0]  (+dp[1]+)+dp[1]  (+dp[0]+)+dp[2]  其中dp[2]有两种可以拆分 ,共5种括号对组合
    2+0=3-1    p+q=n-1
    i=n 时     ( + dp[x] +) + dp[y]     x+y=n-1
    高效利用前面的解求后面的解
     */
    public static List<String> generateParenthesisByDP(int n) {
        if (n==0)
            return null;
        // 初始化第一个和第二个
        List<String> list0=new ArrayList<>();
        list0.add("");
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("()");
        // 返回结果
        List<List<String>> result=new ArrayList<>(); // 动态规划，记住之前每一个括号组合
        result.add(list0);
        result.add(list1);
        // 从第二个开始遍历 ，当括号对为2时
        for (int i = 2 ; i <= n; i++) {
            ArrayList<String> temp = new ArrayList<>(); // 每一个合适的括号对组合
            for (int j = 0; j < i ; j++) {   // 遍历开始
                List<String> StringP=result.get(j);// i=2 时 dp[0]
                List<String> StringQ=result.get(i-1-j); //  dp[2]
                for (String s1: StringP){ // 每个dp的组合
                    for (String s2:StringQ)
                        temp.add( "("+s1+")" +s2 );
                }
            }
            result.add(temp);
        }
        return result.get(n);
    }

    /*
    DP  递归版
     */
    public static List<String> generateParenthesisByDPRecursion(int n) {
        return generate(n);
    }
    private static  ArrayList[] cache =new ArrayList[9]; // list 数组定义方式,题目 0=<n<=8

    private static List<String> generate(int n){
        if ( cache[n] !=null )
            return cache[n]; // 剪枝，如果已经存了直接返回
        ArrayList<String> ans = new ArrayList<>();
        if (n==0) {
            ans.add("");
        }else{
            for (int i = 0; i < n; i++) {
                for (String left : generate(i))
                    for (String right : generate(n-1-i)){
                        ans.add( "("+left+")" +right );
                    }
            }
        }
        cache[n]=ans;
        return ans;
    }

    public static void main(String[] args) {
        generateParenthesisByDPRecursion(3);
    }

}
