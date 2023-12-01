package A1000PLAN.数组.hard;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/10/26
 **/
public class P41缺失的第一个正数 {
    /*
    只要有一个元素<=0,那么结果必定是[1,n]之中
    1.去除<=0的元素，使其=n+1, n 是数组长度， 结果肯定属于[1,n+1]中
    2.使nums[num-1]的位置变成负数
    3.下标遍历， 如果都是负数，答案是n+1,否则第一个>0的下标就是结果,
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        int num;
        for (int i = 0; i < n; i++) {
            num = Math.abs(nums[i]);
            if (num <= n) {
                // 之所以这样是 赋值后下次还要再利用来赋值，-1不行
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        new P41缺失的第一个正数().firstMissingPositive(new int[] {-1, -2, 0});
        new P41缺失的第一个正数().firstMissingPositive(new int[] {3, 4, -1, 1});
        new P41缺失的第一个正数().firstMissingPositive(new int[] {1, 2, 0});
    }
}
