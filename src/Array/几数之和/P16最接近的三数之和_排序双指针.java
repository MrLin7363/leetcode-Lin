package Array.几数之和;
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe: 返回靠近target的三数之和值
  排序+双指针  89+12
 */
import java.util.Arrays;

public class P16最接近的三数之和_排序双指针 {

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int bestSum = 10000000;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
                    bestSum = sum;
                }
                if (sum > target) {
                    right--;
                    // 移动到下一个不相等的元素
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return bestSum;
    }
}
