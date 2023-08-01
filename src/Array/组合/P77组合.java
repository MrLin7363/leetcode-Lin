package Array.组合;

/**
 * @Author JunLin
 * @Date 2020/11/20
 * @Describe: 回溯
 * 1-n 的k个数的合集   211 112 算两个排列，只算一个组合
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P77组合 {
    /*
    path.size = 已经选择的个数
    k - path.size = 还要选择的个数
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k == 0 || n < k) {
            return ans;
        }
        // 为了防止底层动态数组扩容，初始化的时候传入最大长度
        Deque<Integer> path = new ArrayDeque<>(k);
        backtrack(n, k, 1, ans, path);
        return ans;
    }

    public static void backtrack(int n, int k, int startId, List<List<Integer>> ans, Deque<Integer> path) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // i<=n 未优化版本
        // 搜索起点的上界，如果当前 i > n - ( k -path.size()) +1 那么其实没意义
        // 如果 n = 7, k = 4，从 5开始搜索就已经没有意义了，这是因为：即使把 5 选上，后面的数只有 6 7 一共就3个候选数，凑不出4个数的组合
        // 比如五选二，但是 i到了倒数第二个，最多两个数值，到了倒数第一个值是不满足了。 如果k很大，剪枝就很有用了
        for (int i = startId; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            // 注意用startId+1 会错，因为idx在第二次for循环中是不变的，因为这里组合和排列不一样，所以相同的不能再选，如2,3  ---  3,2
            backtrack(n, k, i + 1, ans, path);
            System.out.println("递归之后 => " + path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = combine(4, 3);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

}
