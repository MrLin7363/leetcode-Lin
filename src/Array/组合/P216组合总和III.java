package Array.组合;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/21
  *@Describe:
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P216组合总和III {
    /*
    搜索起点优化最大值
    k : 几个数
    n : 和
    1-9 里面选数字
    17 + 68
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        // 一开始做一些特殊判断
        if (k <= 0 || n <= 0 || k > n) {
            return ans;
        }
        dfs(n, k, 1, ans, path);
        System.out.println(ans);
        return ans;
    }

    /**
     * @param k 剩下要找 k 个数
     * @param idx 下一次取的数的 下标起点
     */
    public void dfs(int target, int k, int idx, List<List<Integer>> ans, Deque<Integer> path) {
        if (target < 0 || 10 - idx < k) {
            return;
        }
        if (0 == k) {
            if (target == 0) {
                ans.add(new ArrayList<>(path));
                return;
            }
            return;
        }
        // for循环是第一层子树
        // 枚举起点值 [..., 7, 8, 9]
        // 找 3 个数，结尾最多到 7
        // 找 2 个数，结尾最多到 8
        // 规律是，结尾 + k = 10，故结尾 = 10 - k
        for (int i = idx; i <= 10 - k; i++) {
            // 大剪枝
            if (target - i < 0) {
                break;
            }
            path.addLast(i);
            // 相同的不能再选,i+1,递归是下面更深一层，重复元素   不选
            // 这里如果 k-1 上面第二个 if 就需要 0==k, 否则可以用 path.size== k  判断循环完了没有
            dfs(target - i, k - 1, i + 1, ans, path);
            path.removeLast();
        }
    }

    /**
     * 简单版本 100 + 50
     */
    public List<List<Integer>> combinationSum4(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(res, path, 1, k, n);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int begin, int k, int target) {
        if (target < 0) { // 剪枝 || 10 - begin < k
            return;
        }
        if (k == 0) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = begin; i <= 9; i++) { // 剪枝 i<=9  -->  i<= 10-k
            if (target - i < 0) {
                break;
            }
            path.offerLast(i);
            dfs(res, path, i + 1, k - 1, target - i);
            path.pollLast();
        }
    }
}
