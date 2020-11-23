package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/21
  *@Describe: 只用输出个数，所以用回溯超时
  回溯参考39题，只用 for循环里面 i = 0 修改即可，此题目顺序不同也算组合，不过超时
  DP
 */

import java.util.*;

public class P377Combination_Sum_IV {
    /**
     * 这里状态定义就是题目要求的，并不难，状态转移方程要动点脑子，也不难：
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     * 特别注意：dp[0] = 1，表示，如果那个硬币的面值刚刚好等于需要凑出的价值，这个就成为 1 种组合方案
     * 再举一个具体的例子：nums=[1, 3, 4], target=7;
     *    * dp[7] = dp[6] + dp[4] + dp[3]
     *    * 即：7 的组合数可以由三部分组成，1 和 dp[6]，3 和 dp[4], 4 和dp[3];
     *    dp[6]=dp[5]+1  +dp[3]+3  +dp[2]+4   都是 i-num，如到达dp[5]后再加上1的组合就可以到达dp[6]
     *    dp[3]=dp[2]+1   +dp[0]+3
     *    dp[2]=dp[1]+1
     *    dp[1]=dp[0]+1
     *    dp[0]=1
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // 这个值被其它状态参考，设置为 1 是合理的
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                System.out.println("num="+num);
                if (num <= i) {
                    dp[i] += dp[i - num];
                    int s=i-num;
                    System.out.println("dp["+i+"]="+dp[i]+"  dp["+s+"]="+dp[i-num]);
                }
            }
            // 这两个for 循环一样的
           /* for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] += dp[i - coins[j]];
                }
            }*/
        }
        System.out.println(dp[target]);
        return dp[target];
    }

    public static void main(String[] args) {
        combinationSum4(new int[]{1,3,4},7);
    }
    /*
    超时不看----------------------------------------------------------------------
     */
    public static int combinationSum42(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs2(nums, 0, len, target, path, res);
        System.out.println(res);
        System.out.println(res.size());
        return res.size();
    }

    private static void dfs2(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            path.addLast(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            dfs2(candidates, i, len, target - candidates[i], path, res);

            path.removeLast();
            System.out.println("递归之后 => " + path);

        }
    }

}
