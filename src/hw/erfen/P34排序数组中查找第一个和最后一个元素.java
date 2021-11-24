/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package hw.erfen;

/**
 * desc:
 *
 * @author c30021507
 * @since 2021/11/23
 **/
public class P34排序数组中查找第一个和最后一个元素 {

    /*
    二分-分两次找大于等于target的元素
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = binearySearch(nums, target);
        int right = binearySearch(nums, target + 1) ;
        if (left==nums.length || nums[left]!=target) {
            return new int[]{-1, -1};
        }
        return new int[]{left, right-1};
    }

    public static void main(String[] args) {
        int[] newww = new int[0];
        searchRange(newww,0);
    }
    // 查找第一个大于等于target的元素
    public static int binearySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
