package A1000PLAN.数组.摩尔投票法;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/30
 **/
public class P229多数元素II {
    /*
    统计超过n/3投票的人 - 消消乐
    理解摩尔投票  https://leetcode.cn/problems/majority-element-ii/
    抵消阶段  最多只有两个候选人，三个候选人的投票会==n,剩下的在加>n
    计数阶段
    这种题目规律：如果至多选一个代表，那他的票数至少要超过一半（⌊ 1/2 ⌋）的票数；
    如果至多选两个代表，那他们的票数至少要超过 ⌊ 1/3 ⌋ 的票数；
    如果至多选m个代表，那他们的票数至少要超过 ⌊ 1/(m+1) ⌋ 的票数。
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int candNum1 = nums[0];
        int candNum2 = nums[0];
        int cnt1 = 0;
        int cnt2 = 0;
        // 抵消阶段
        for (int i = 0; i < nums.length; i++) {
            if (candNum1 == nums[i]) {
                cnt1++;
                continue;
            }
            if (candNum2 == nums[i]) {
                cnt2++;
                continue;
            }
            // 第1个候选人配对
            if (cnt1 == 0) {
                candNum1 = nums[i];
                cnt1++;
                continue;
            }
            // 第2个候选人配对
            if (cnt2 == 0) {
                candNum2 = nums[i];
                cnt2++;
                continue;
            }

            cnt1--;
            cnt2--;
        }

        // 计数阶段:找到了两个候选人之后，需要确定票数是否满足大于 N/3
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == candNum1) {
                cnt1++;
            } else if (num == candNum2) {
                cnt2++;
            }
        }
        if (cnt1 > nums.length / 3) {
            res.add(candNum1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(candNum2);
        }
        return res;
    }

    public static void main(String[] args) {
        new P229多数元素II().majorityElement(new int[] {2, 1, 1, 3, 1, 4, 5, 6});
    }
}
