

package A1000PLAN.深广遍历.记忆化搜索;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/26
 **/
public class P486预测赢家_记忆化搜索 {
    private int[][] cache;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        cache = new int[n][n];
        // left 到 right 的分数差的 最大值
        int res = dfs(0, n - 1, nums);
        return res >= 0;
    }

    private int dfs(int left, int right, int[] nums) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return nums[left];
        }
        if (cache[left][right] != 0) {
            System.out.println("1");
            return cache[left][right];
        }
        int ans;
        int chooseLeft = nums[left] - dfs(left + 1, right, nums);
        int chooseRight = nums[right] - dfs(left, right - 1, nums);
        ans = Math.max(chooseLeft, chooseRight);
        cache[left][right] = ans;
        return ans;
    }

    public static void main(String[] args) {
        new P486预测赢家_记忆化搜索().predictTheWinner(new int[] {1,5,233,7});
    }
}
