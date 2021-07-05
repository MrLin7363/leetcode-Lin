package Array.hard;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/05 22:43
 * @Describe:
 */
public class P41_First_Missing_Positive {

    /*
    置换 90+61
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 每个值都出现在数组的位置 ， 如 3 会置换到 数组下标为2的位置
        for (int i = 0; i < n; i++) {
            // 1.2 值属于[1,n]，才置换位置   3.防止相同值无线循环交换   如   1,3,3,-1 判断置换nums[1]时
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 这里注意交换顺序 必须用nums[nums[i]-1] 作为temp 因为这里面引用了 nums[i] 反之则不行
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        return n+1;
    }

    /*
    打标志  70 + 55
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            // 防止后面赋值为-1后下标越界
            int num=Math.abs(nums[i]);
            // 注意判断是否需要打标志是num
            if (num <= n) {
                nums[num-1]=-Math.abs(nums[num-1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i]>0){
                return i+1;
            }
        }
        return n+1;
    }

}
