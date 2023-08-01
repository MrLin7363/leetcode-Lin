package Array.几数之和;

/**
 * @Author JunLin
 * @Date 2020/11/7
 * @Describe: 1.二分 17+8
 * 2.双指针 100+8
 */
public class P167两数之和II_输入有序数组 {
    /*
    双指针  因为数组是有序的，所以可以用双指针
    */
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0]; // 返回空数组代表无结果
    }

    /*
    二分
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            // 二分搜索
            int left = i + 1, right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (numbers[mid] + numbers[i] == target) {
                    return new int[]{i + 1, mid + 1}; // 因为题目要求从1开始，所以+1
                } else if (numbers[mid] + numbers[i] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[0]; // 返回空数组代表无结果
    }

}