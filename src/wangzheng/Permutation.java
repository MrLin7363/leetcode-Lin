package wangzheng;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/02 15:11
 * @Describe:
 * 每次把第一个放在最后面，然后剩下三个的全排列，再把三个的第一个放最后面，剩下前两个前排列，
 * 再把剩两个全排列，最后剩一个时输出，回溯刚才的场景，交换
 */
public class Permutation {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        printPermutations(a, 4, 4);
    }
    // 调用方式：
    // k表示要处理的子数组的数据个数
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < k; ++i) {
            //交换头尾
            int tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;

            printPermutations(data, n, k - 1);
            //再换回来
            tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;
        }
    }
}
