package A1000PLAN.排序.二分.边界类;

/**
 *desc:
 *@author lin
 *@since 2023/11/10
 **/
public class P162寻找峰值 {
    /*
https://leetcode.cn/problems/find-peak-element/solutions/6695/hua-jie-suan-fa-162-xun-zhao-feng-zhi-by-guanpengc/?envType=study-plan-v2&envId=top-interview-150
    贪心-二分   不断找上坡路,找右边界
    红：峰顶左侧
    蓝：峰顶及其右侧
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // 左闭右开，这里右) 防止mid+1溢出，而且n-1要么峰顶要么峰顶右侧，已经确定
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 当前mid为目标峰顶或其右侧
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // 目标峰顶左侧
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        new P162寻找峰值().findPeakElement(new int[] {1, 2, 3, 4});
        new P162寻找峰值().findPeakElement(new int[] {1});
    }
}
