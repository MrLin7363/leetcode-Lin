

package A1000PLAN.滑动窗口;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/10
 **/
public class P217存在重复元素 {
    public boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int slow = 0, fast = 1;
        while (fast < n) {
            if (nums[slow++] == nums[fast++]) {
                return true;
            }
        }
        return false;
    }
}
