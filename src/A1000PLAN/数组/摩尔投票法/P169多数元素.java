package A1000PLAN.数组.摩尔投票法;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P169多数元素 {
    /*  https://leetcode.cn/problems/majority-element/solutions/146811/3chong-fang-fa-by-gfu-2/
    摩尔投票法-推荐-消消乐 On + O1
    抵消阶段:两个互相比较   如 A B C A
            [A,1]和B比较 -> [A,0] -> [C,0] -> [A,1]
    计数阶段：验证这个数是否超过数组的一半,由于题目一定存在多数元素，所以不需判断
     */
    public int majorityElement(int[] nums) {
        int n = nums.length;
        // 初始化还未获取投票
        int candNum = nums[0];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == candNum) {
                cnt++;
            } else {
                cnt--;
                if (cnt == 0) {
                    candNum = nums[i];
                    cnt = 1;
                }
            }
        }
        return candNum;
    }

    // 随机数 是众数即可，>一半元素  可以达到 O 1的空间复杂度
    public int majorityElement2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                return nums[i];
            }
            count = 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        new P169多数元素().majorityElement2(new int[] {3, 2, 3});
    }
}
