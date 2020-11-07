package Array.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/7
  *@Describe:
  1.二分 17+8
  2.双指针 100+8
 */

public class Two_Sum_II_sorted_array_167 {
    /*
    二分
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            // 二分搜索
            int low = i + 1, high = n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1}; // 因为题目要求从1开始，所以+1
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[0]; // 返回空数组代表无结果
    }

    /*
    双指针
     */
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum=numbers[left]+numbers[right];
            if ( sum == target) {
                return new int[]{left + 1, right + 1};
            } else if ( sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0]; // 返回空数组代表无结果
    }
}