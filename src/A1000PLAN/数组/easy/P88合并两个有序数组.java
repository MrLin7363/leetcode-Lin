package A1000PLAN.数组.easy;

/**
 *desc:
 *@author lin
 *@since 2023/11/16
 **/
public class P88合并两个有序数组 {
    // O 1空间+逆向双指针,取较大的放后面  O m+n O 1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (i1 >= 0 || i2 >= 0) {
            if (i1 == -1) {
                cur = nums2[i2--];
            } else if (i2 == -1) {
                cur = nums1[i1--];
            } else if (nums1[i1] >= nums2[i2]) {
                cur = nums1[i1--];
            } else {
                cur = nums2[i2--];
            }
            nums1[tail--] = cur;
        }
    }

    // 额外数组+正向双指针  O m+n O m+n
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i1 = 0;
        int i2 = 0;
        int index = 0;
        int[] temp = new int[m + n];
        System.arraycopy(nums1, 0, temp, 0, m);
        while (i1 < m && i2 < n) {
            if (temp[i1] <= nums2[i2]) {
                nums1[index++] = temp[i1++];
            } else {
                nums1[index++] = nums2[i2++];
            }
        }
        while (i1 < m) {
            nums1[index++] = temp[i1++];
        }
        while (i2 < n) {
            nums1[index++] = nums2[i2++];
        }
    }

    public static void main(String[] args) {
        new P88合并两个有序数组().merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
    }
}
