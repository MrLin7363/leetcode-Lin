package A1000PLAN.数组.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/14
 **/
public class P228汇总区间 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer sb = new StringBuffer(Integer.toString(nums[low]));
            if (high - low > 0) {
                sb.append("->" + nums[high]);
            }
            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        new P228汇总区间().summaryRanges(new int[] {0, 2, 3, 4, 6, 8, 9});
        new P228汇总区间().summaryRanges(new int[] {0, 1, 2, 4, 5, 7});
    }
}
