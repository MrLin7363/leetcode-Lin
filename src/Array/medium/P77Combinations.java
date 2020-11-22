package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/20
  *@Describe: 回溯
  1-n 的k个数的合集
  leetcode 输出会有时间和空间占用
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P77Combinations {
    /*
    path.size = 已经选择的个数
    k - path.size = 还要选择的个数
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k==0 || n<k) {
            return ans;
        }
        // 为了防止底层动态数组扩容，初始化的时候传入最大长度
        Deque<Integer> path = new ArrayDeque<>(k);
        backtrack(n,k,1,ans,path);
        return ans;
    }
    public static void backtrack(int n,int k, int startId, List<List<Integer>> ans,Deque<Integer> path ){
        if (path.size()==k){
            ans.add(new ArrayList<>(path));
            return;
        }
        // i<=n 未优化版本
        // 搜索起点的上界，如果当前 i > n - ( k -path.size()) +1 那么其实没意义，比如选三个，但是 i到了倒数第二个，最多两个数值
        for (int i = startId; i <= n - ( k -path.size()) +1  ; i++) {
            path.addLast(i);
            System.out.println("  递归之前 => " + path);
            // 注意用startId+1 会错，因为idx在第二次for循环中是不变的，因为这里组合和排列不一样，所以相同的不能再选，如2,3  ---  3,2
            backtrack(n, k,i + 1, ans, path);
            System.out.println("递归之后 => " + path);
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = combine(4,4);
        System.out.println(lists);
    }

}
