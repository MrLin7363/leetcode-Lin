package A1000PLAN.排序.二分;

/**
 * desc: 1.合并数组+中位数  简单
 * 2.二分查找  - 困难
 * 这题了解即可
 *
 * @author Lin
 * @since 2023/10/23
 **/
public class P4寻找两个正序数组的中位数_hard {
    public static void main(String[] args) {
        new P4寻找两个正序数组的中位数_hard().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4});
    }

    /* 看官方题解视频的
    二分查找-找分割线-官方解的视频里来
    1.找较短的nums
    2.二分查找分割点，分割线左边所有元素=分割线右边所有元素，如果奇数则左边的元素多一个 (m+n+1)/2
    3.nums1[i-1]<=nums2[j] && nums2[j-1]<=nums[i]
    4.以短数组查找分割线，常数组的分割线== totalLeft-i
    5.分割线周围四个元素决定结果
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        // 分割线左边所有元素 + 1是考虑左边更多
        int totalLeft = (m + n + 1) / 2;

        // 在nums1的区间[0,m]里找分割线
        // 使 nums1[i-1]<=nums2[j] && nums2[j-1]<=nums[i]
        int left = 0;
        int right = m;

        while (left < right) {
            int i = left + (right - left) / 2; // nums1分隔线右侧元素
            int j = totalLeft - i; // nums2分隔线右侧元素
            if (nums2[j - 1] > nums1[i]) {
                // 下一轮搜索区间 [i+1,right]
                left = i + 1;
            } else {
                // 下一轮搜索区间 [left,i]
                right = i;
            }
        }
        int i = left;
        int j = totalLeft - i;

        // 分割线左右两次的四个元素
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if ((m + n) % 2 == 1) {
            // 奇数时，多余的元素在左侧，所以取左侧最大的数
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            // 偶数时，取左侧最大+右侧最小/2  注意double
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }
    }
}
