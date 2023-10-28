package A1000PLAN.位运算;

/**
 * desc: 复杂的不看, 随机数 计算是否是众数的情况可以达到 O 1的空间复杂度
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P169多数元素 {
    // 随机数 是众数即可，>一半元素
    public int majorityElement(int[] nums) {
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
        new P169多数元素().majorityElement(new int[]{3,2,3});
    }
}
