package A1000PLAN.双指针;

import java.util.Arrays;

/**
 * desc:
 *
 * @author c30021507
 * @since 2023/9/21
 **/
public class P581最短无序连续子数组 {
    /*
    双指针+线性遍历
    1.从左往右，找到比左边最大值还小的最右下标，从右往左，找到比右边最小值还大的最左下标
    左段+中段无序+右段
    从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，我们要求的end就是遍历中最后一个小于max元素的位置；
    同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都是大于min的，要求的begin也就是最后一个大于min元素的位置。

    注意:右指针从右往左寻找无序,左指针从左往右寻找无序 : 这个想法错误的1,2,4,5,3  找到[3,4]互换但是[1,2,4,3,5]有问题
     */
    public int findUnsortedSubarray(int[] nums) {
        int left = -1;
        int right = -1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        // 同时遍历
        for (int i = 0; i < n; i++) {
            // 遍历左 最后一个小于max元素的位置
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }

            // 遍历右 最后一个大于min元素的位置
            if (nums[n - i - 1] > min) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    /*
    双指针+排序
    旧数组与排序后的数组，比较不同点
     */
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n];
        System.arraycopy(nums, 0, copy, 0, n);
        Arrays.sort(copy);
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] != copy[i]) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }
        return right == Integer.MIN_VALUE ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray2(new int[] {2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray2(new int[] {1, 1}));
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray2(new int[] {1, 2, 4, 5, 3}));
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray(new int[] {1, 2, 3, 4}));
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray(new int[] {1, 3, 2, 2, 2}));
        System.out.println(new P581最短无序连续子数组().findUnsortedSubarray(new int[] {1}));
    }
}
