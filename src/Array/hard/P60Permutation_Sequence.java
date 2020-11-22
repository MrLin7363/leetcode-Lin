package Array.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/21
  *@Describe: 排列序列
  回溯  100 + 80
  有序数组/链表
 */

import java.util.*;

public class P60Permutation_Sequence {
    /**
     * 记录数字是否使用过
     */
    private static boolean[] used;

    /**
     * 阶乘数组
     */
    private static int[] factorial;

    public static String getPermutation(int n, int k) {
        StringBuilder ans=new StringBuilder();
        used=new boolean[n+1];
        calculateFactorial(n);
        backtrack(n,k,0,ans);
        System.out.println(ans.toString());
        return ans.toString();
    }
    private static void backtrack(int n,int k,int index,StringBuilder ans){
        if (index==n){
            return;
        }
        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int left=factorial[n-1-index];
        for (int i = 1; i <= n; i++) {
            // 如果数字被用过了,注意要在下面判断之前
            if (used[i]){
                continue;
            }
            // 剪枝，说明结果叶子节点不在该节点开头的排列下
            if (k>left){
                k-=left;
                continue;
            }
            ans.append(i);
            used[i]=true; // 没选过数字不能再选
            backtrack(n,k,index+1,ans);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }

    }
    // 计算阶乘
    private static void calculateFactorial(int n){
        factorial=new int[n+1];
        factorial[0]=1;
        for (int i = 1; i <= n; i++) {
            factorial[i]=factorial[i-1]*i;
        }
    }

    public static void main(String[] args) {
        getPermutation2(4,2);
    }


    /*
    有序数组  41+72
    计算阶乘
     */
    public static String getPermutation2(int n, int k) {
        k--;
        int[] factorial=new int[n+1];
        factorial[0]=1;
        for (int i = 1; i <= n; i++) {
            factorial[i]=factorial[i-1]*i;
        }
        LinkedList<Integer> nums=new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        StringBuilder ans = new StringBuilder();
        for (int i = n-1; i>=0; i--) {
            int index=k/factorial[i];
            ans.append(nums.remove(index));
            // * index 一般是 index=0的时候，实际上k不需要变化，因为都在这个数字开头的区间
            // 如果index==1 是不在这个数字开头
            k-=index*factorial[i];
        }
        System.out.println(ans.toString());
        return ans.toString();
    }
}
