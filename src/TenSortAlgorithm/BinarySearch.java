package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 17:21
 * @Describe: 二分法查找一个数的下标，这个算法可以用来插入一个值到有序数组中并保持有序性
 */
public class BinarySearch {
    int binarySearch(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if (target==nums[mid])
                return mid;
            else if (target>nums[mid])
                left=mid+1;
            else if (target<nums[mid])
                right=mid-1;
        }
        return -1;
    }
}
