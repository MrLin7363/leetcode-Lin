/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package A1000PLAN.滑动窗口;

import java.util.HashSet;
import java.util.Set;

public class P219存在重复元素II {
    /*
    滑动窗口 84+53  还有一个map记录元素最大下标，一次遍历，这里不展示了
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i>k){
                set.remove(nums[i-k-1]);
            }
            // 窗口set内包含了这个元素
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /*
    O n^2   5+78
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i + j < nums.length && nums[i] == nums[i + j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
