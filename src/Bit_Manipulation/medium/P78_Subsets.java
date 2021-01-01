package Bit_Manipulation.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/12/30 19:45
 * @Describe:
 */
public class P78_Subsets {
    static List<Integer> t = new ArrayList<Integer>();
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public static List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public static void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }

    public static void main(String[] args) {
        subsets(new int[]{1,2});
    }
}
