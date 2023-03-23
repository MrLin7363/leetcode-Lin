package A2023.排序;

/**
 * 快速选择排序-求单个数   第K大的数=第 length-K 个数， 因为快排默认从小到大
 * 这是求单个数 ， 与 P973最接近原点的K个点QuickSort 求一段数不一样，必须中轴元素就是所要求的那个数，而不是距离
 */
public class P215数组中的第K个最大元素QickSort {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        quickSort(nums, 0, n - 1, n - k);
        return nums[n - k];
    }

    public static void main(String[] args) {
        new P215数组中的第K个最大元素QickSort().findKthLargest(new int[]{3, 4, 1, 55, 6}, 2);
    }

    /*
    如果 k == 中轴 则中轴元素是所要求的数，可以结束
    如果 k < 中轴  则在左区域
    如果 k > 中轴  则在右区域
    */
    private void quickSort(int[] nums, int left, int right, int k) {
        // 中轴元素随便取，这里默认取最左边
        int pivot = nums[left];
        int i = left;
        int j = right;
        while (i < j) {
            // 右往左找比 pivot小 ， 注意要先从右往左，因为中轴在最左边
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            // 从左往右找比 pivot大
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = pivot;
        // 左排
        if (k < i) {
            quickSort(nums, left, i - 1, k);
        }
        // 右排
        if (k > i) {
            quickSort(nums, i + 1, right, k);
        }
        // 结束
    }
}
