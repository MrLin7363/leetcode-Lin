package A1000PLAN.数组;

import java.util.Arrays;

/**
 * desc: 还有三路快排-太难不看
 *
 * @author lin
 * @since 2023/12/5
 **/
public class P324摆动排序II {
    /*
    排序+分两半往前填充
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int x = (n + 1) / 2;
        int[] arr = nums.clone();
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
        System.out.println(nums);
    }

    public static void main(String[] args) {
        new P324摆动排序II().wiggleSort(new int[] {1, 1, 2, 2, 5, 6});
        // new P324摆动排序II().wiggleSort(new int[] {1, 1, 1, 4, 5, 6});
    }
}
