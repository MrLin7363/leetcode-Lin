package A1000PLAN.排序;

/**
 * 大顶堆，数组从小到大
 */
public class P215数组中的第K个最大元素HeapSort {
    public int findKthLargest(int[] nums, int k) {
        maxHeapSort(nums, k);
        return nums[nums.length - k];
    }

    private void maxHeapSort(int[] nums, int k) {
        // 构建大顶堆，从倒数第二层有孩子节点的节点向上层变量，每一次构建一个小的大顶堆，遍历完就是完整的大顶堆
        int n = nums.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            downAjust(nums, i, n);
        }
        // 堆顶最大元素交换堆尾，排除堆尾再次构建大顶堆， 此题目只需构建K次即可
        int count = 0;
        for (int i = n - 1; i >= 1; i--) {
            if (count++ == k) {
                break;
            }
            // 交换头尾
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            // 除了根节点，其他节点都是大顶堆了
            downAjust(nums, 0, i);
        }
    }

    // 下沉构建大顶堆
    private void downAjust(int[] nums, int parent, int heapsize) {
        int temp = nums[parent];
        // 左孩子节点
        int child = 2 * parent + 1;
        while (child < heapsize) {
            // 右孩子节点比左孩子大的情况
            if (child + 1 < heapsize && nums[child + 1] > nums[child]) {
                child++;
            }
            if (nums[child] <= temp) {
                break;
            }
            // 父节点下沉，孩子节点上移
            nums[parent] = nums[child];
            parent = child;
            // 继续往下判断
            child = parent * 2 + 1;
        }
        nums[parent] = temp;
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 2, 7, 1, 9, 2, 4, 5, 4, 2, 1, 23};
        new P215数组中的第K个最大元素HeapSort().findKthLargest(test, 5);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
