package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 15:25
 * @Describe: 分治思想
 * 1、选取第一个元素作为中轴元素，每次把比中间元素小的元素排在中间元素的左边，
 * 大的在右边。这样下次排序就分开排序，有点分治的思想
 * O nlogn
 * O logn
 */
public class QuickSort {
    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // 每次排一个节点
            int mid = partition(arr, left, right);
            // 分别左右再排
            arr = quickSort(arr, left, mid - 1);
            arr = quickSort(arr, mid + 1, right);
        }
        return arr;
    }

    /*
    先选取一个中间元素，比如最左边的4，然后交换比4大和比4小的元素
    最后i==j就是中间元素该放的中间位置
     */
    private static int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        //中轴元素,这里随机选择最左边元素
        int pivot = arr[left];
        //当i==j时停止循环，找到中间元素
        while (i < j) {
            // 从右边往左找比中轴元素小的
            while (i < j && arr[j] >= pivot)
                j--;
            // 再从左往右边找，直到找到比pivote值大的数
            while (i < j && arr[i] <= pivot)
                i++;
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 剩下的中间位置的和一开始选的中轴位置的互换,因为经过交换中间元素一定小于中轴元素
        arr[left] = arr[i];
        arr[i] = pivot;
        //此时的i==j
        return i;
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 4, 7, 5, 9};
        quickSortSimple(test, 0, test.length - 1);
        quickSort(test, 0, test.length - 1);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }

    // 简易版本，一个函数内搞定
    private static void quickSortSimple(int[] points, int left, int right) {
        int i = left;
        int j = right;
        int pivot = points[left];
        while (i < j) {
            // 右往左找比pivot小
            while (i < j && points[j] >= pivot) {
                j--;
            }
            // 左往右找比picot大
            while (i < j && points[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = points[i];
                points[i] = points[j];
                points[j] = temp;
            }
        }
        // 交换中间元素与pivot
        points[left] = points[i];
        points[i] = pivot;

        // 左排
        if (i + 1 < right) {
            quickSortSimple(points, i + 1, right);
        }
        // 右排
        if (left < i - 1) {
            quickSortSimple(points, left, i - 1);
        }
        // 结束
    }
}
