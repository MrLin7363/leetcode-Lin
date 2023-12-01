package A1000PLAN.位运算;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P260只出现一次的数字III {
    /*
    P136的升级版， 先去掉出现2次的数，然后和原数组匹配剩下的两个出现1次的数

    由于除了两个数字出现了一次，其他数字都出现了两次。根据异或运算的性质：**两个相同的数异或结果为0，一个数与0异或还是它自己，
    异或运算满足交换律**。把nums中的元素全部异或起来的结果eor就是那两个只出现一次的数字的异或结果。
    而这两个数不相同，意味着eor至少有一位是1，我们可以用lowbit运算拿到最低位的1，然后遍历nums数组，将所有数nums[i]按照这一位是不是1分成两类
    ，初始化num1=num2=0

    1. 如果当前位是1，就将nums[i]异或到num1上。
    2. 如果当前位是0，就将nums[i]异或到num2上。

    这样一来，两个只出现一次的数就会被分别异或到num1和num2上，而其他数也会被分别异或到这两个数上。而由于其他数都出现了两次，
    所以最终它们就会被异或成0，num1和num2就是那两个只出现一次的数。
     */
    public int[] singleNumber(int[] nums) {
        int two = 0;
        for (int num : nums) {
            two ^= num;
        }
        // 剩下两个单独的数异或的和 求最后的1位，该位两个单独的相异
        int last1 = two & (-two);
        int type1 = 0;
        int type2 = 0;
        for (int num : nums) {
            // 当前位为1的一类， 重复的类会过滤调
            if ((num & last1) != 0) {
                type1 ^= num;
            } else {
                // 当前位不是1的一类，重复的类会过滤调
                type2 ^= num;
            }
        }
        return new int[] {type1, type2};
    }
}
