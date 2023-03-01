package A2023.排序;

/**
 * 快排进阶 快速选择排序算法  -  这是求一段数
 *
 * 对子数组进行划分，如果划分得到的
 * q 正好就是我们需要的下标，就直接返回a[q]；否则，如果 q 比目标下标小，就递归右子区间，
 * 否则递归左子区间。这样就可以把原来递归两个区间变成只递归一个区间，提高了时间效率。这就是「快速选择」算法。
 */
public class P973最接近原点的K个点QuickSort {

    public int[][] kClosest(int[][] points, int k) {
        quickSort(points, 0, points.length - 1, k);
        int[][] resutl = new int[k][2];
        for (int i = 0; i < k; i++) {
            resutl[i] = points[i];
        }
        return resutl;
    }

    /*
    如果 k = 中轴元素-left+1  说明 中轴元素 就是第 k 个距离最小的点，我们可以结束整个过程
    如果 k < 中轴元素-left+1  说明 第 k 个距离最小的点 在中轴左边，向左边递归
    如果 k > 中轴元素-left+1  说明 第 k 个距离最小的点 在中轴右边，向右边递归，且递归的k数减少为 k - (中轴元素-left+1)
        相当于减去左边所有的个数，右边剩下的是属于第k个距离最小的点
        中轴元素-left+1 = 中轴左边的个数,包括中轴点
     */
    private void quickSort(int[][] points, int left, int right, int k) {
        int i = left;
        int j = right;
        int pivotValue = points[left][0] * points[left][0] + points[left][1] * points[left][1];
        int[] pivot = points[left];
        while (i < j) {
            // 右往左找比pivot小
            while (i < j && points[j][0] * points[j][0] + points[j][1] * points[j][1] >= pivotValue) {
                j--;
            }
            // 左往右找比pivot大
            while (i < j && points[i][0] * points[i][0] + points[i][1] * points[i][1] <= pivotValue) {
                i++;
            }
            if (i < j) {
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
            }
        }
        // 交换中间元素与pivot
        points[left] = points[i];
        points[i] = pivot;

        // 左排
        if (k < i - left + 1) {
            quickSort(points, left, i - 1, k);
        }
        // 右排
        if (k > i - left + 1) {
            quickSort(points, i + 1, right, k - (i - left + 1));
        }
        // 此时 i==j == 中轴元素 结束
    }

    public static void main(String[] args) {
        int[][] result = new P973最接近原点的K个点QuickSort().kClosest(new int[][]{{1, 3}}, 1);
        for (int[] re : result) {
            System.out.println(re[0] + " " + re[1]);
        }
    }
}
