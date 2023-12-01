package A1000PLAN.位运算;

/**
 * desc: O 1 空间复杂度
 *
 * @author Lin
 * @since 2023/10/24
 **/
public class P136只出现一次的数字 {
    /*
    数组中的全部元素的异或运算结果即为数组中只出现一次的数字
    相同的数异或=0 ， 其他数都是相同的
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
