package TenSortAlgorithm;

/**
 * @author: Junlin
 * @Date: 2020/07/07 17:21
 * @Describe: 二分法查找一个数的下标，这个算法可以用来插入一个值到有序数组中并保持有序性
 * int mid=low+(high-low)/2; // 防止溢出
 * 搜索右边界注意减一
 *
 * https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-48c1d/wo-xie-le--9c7a4/
 */
public class BinarySearch {
    // 左闭右闭：寻找一个数
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            //            int mid=(left+right)/2;
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }

    // 左闭右开：一般用于找边界问题，也可以二分搜索一个数如下：不推荐
    //  右开说明右边是 )  right=mid-1  会错，这样 [left,mid-1)  mid-1就扫描不到了 ，所以right=mid
    // int binarySearch2(int[] nums, int target) {
    //     int left = 0;
    //     int right = nums.length;
    //     while (left < right) {
    //         int mid = left + (right - left) / 2;
    //         if (target == nums[mid])
    //             return mid;
    //         else if (nums[mid] < target)
    //             left = mid + 1;
    //         else if (nums[mid] > target)
    //             right = mid;
    //     }
    //     return -1;
    // }

    // 左闭右开：搜索最左边界，返回下标  右开是因为right=length
    static int binarySearchByLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) // 此时不要返回，继续向左搜索
                right = mid;  // 向左向右搜索的不同点，搜索边界方向
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return left; // 因为结束条件是 left==right 所以返回哪个都一样
        // target 比所有数都大
        //        if (left == nums.length) return -1;
        // 类似之前算法的处理方式
        //        return nums[left] == target ? left : -1;
    }

    // 左闭右开：搜索最右边界，返回下标    右开是因为right=length
    static int binarySearchByRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])// 此时不要返回，继续向右搜索
                left = mid + 1;  // 向左向右搜索的不同点，搜索边界方向
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        //又因为收紧左侧边界时必须 left = mid + 1
        //所以最后无论返回 left 还是 right，必须减一
        return right - 1;
    }

    // 测试方法
    public static int testLeft() {
        int[] nums = new int[]{1, 1, 2};
        int target = 3;
        int leftResult = binarySearchByLeft(nums, target);
        // target 比所有数都大
        if (leftResult == nums.length)
            return -1;
        // 类似之前算法的处理方式
        return nums[leftResult] == target ? leftResult : -1;
    }

    // 测试方法
    public static int testRight() {
        int[] nums = new int[]{1, 1, 2, 2, 4};
        int target = 2;
        int rightResult = binarySearchByRight(nums, target);
        if (rightResult == -1)
            return -1; // 比所有数都小
        return nums[rightResult] == target ? (rightResult) : -1;
    }

    public static void main(String[] args) {
        System.out.println(testLeft());
        System.out.println(testRight());
    }

}
