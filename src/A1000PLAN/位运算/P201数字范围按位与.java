package A1000PLAN.位运算;

/**
 *desc:
 *@author lin
 *@since 2023/11/25
 **/
public class P201数字范围按位与 {
    /*
    对所有数字执行按位与运算的结果是所有对应二进制字符串的公共前缀再用零补上后面的剩余位
    -> 给定两个整数，我们要找到它们对应的二进制字符串的公共前缀

    1. BK算法去掉最后的1
     */
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return right;
    }

    /*
    2.同步右移动直到相等，记录次数，再次左移即可
     */
    public int rangeBitwiseAnd2(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count++;
        }
        return right << count;
    }
}
