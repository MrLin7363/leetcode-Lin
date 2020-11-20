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
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (k==0) {
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(n,k,1,ans,path);
        return ans;
    }
    public static void backtrack(int n,int k, int idx, List<List<Integer>> ans,Deque<Integer> path ){
        if (0==k){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i <= n; i++) {
            // 前面的不要再用
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
            path.addLast(i);
            System.out.println("  递归之前 => " + path);
            // 注意用idx+1 会错，因为idx在第二次for循环中是不变的
            backtrack(n, k-1,i + 1, ans, path);
            System.out.println("递归之后 => " + path);
//            used[i] = false;
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = combine(4,2);
        System.out.println(lists);
    }

    private static List<List<Integer>> ans2= new ArrayList<>();

    public static List<List<Integer>> combine2(int n, int k) {
        getCombine(n, k, 1, new ArrayList<>());
        return ans2;
    }

    public static void getCombine(int n, int k, int start, List<Integer> list) {
        if(k == 0) {
            ans2.add(new ArrayList<>(list));
            return;
        }
        // i <= n - k + 1
        for(int i = start;i <= n;i++) {
            list.add(i);
            System.out.println("  递归之前 => " + list);
            getCombine(n, k - 1, i+1, list);
            System.out.println("递归之后 => " + list);

            list.remove(list.size() - 1);
        }
    }
}
