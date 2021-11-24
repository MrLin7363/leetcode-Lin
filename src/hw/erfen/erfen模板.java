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
public class erfen模板 {
    /*
     标准二分查找
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*
    二分查找左边界
    1.数组有序，但包含重复元素
    2.数组部分有序，且不包含重复元素
     */
    public static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
    /*
    二分查找左边界
    3.数组部分有序，且包含重复元素
     */
    public static int searchLeft2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /*
    二分查找右边界有序
    1.有序
    2.部分有序 且不含重复元素
     */
    public static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 注意后面要加1  对于奇数还是偶数，这个中间的位置都是偏右的
            int mid = left + ((right - left) >> 1) + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    public static int testLeft() {
        int[] nums=new int[]{1,1,2};
        int target=3;
        return searchLeft(nums,target);
    }
    // 测试方法
    public static int testRight() {
        int[] nums = new int[]{1, 1, 2,2,4};
        int target = 1;
        return searchRight(nums, target);
    }
    public static void main(String[] args) {
        System.out.println(testLeft());
        System.out.println(testRight());
    }

}