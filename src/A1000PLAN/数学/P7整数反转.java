package A1000PLAN.数学;

/**
 *desc:
 *@author lin
 *@since 2023/11/23
 **/
public class P7整数反转 {
    /*
    数字逐步转，过程判断是否溢出
    https://leetcode.cn/problems/reverse-integer/solutions/211865/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/

    其实判断条件可以简化的，因为x本身会被int限制，当x为正数并且位数和Integer.MAX_VALUE的位数相等时首位最大只能为2，
    所以逆转后不会出现res = Integer.MAX_VALUE / 10 && tmp > 2的情况，自然也不需要判断res==214748364 && tmp>7了，反之负数情况也一样
    如 2147483649 反转后一定是2在前面
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int cur = x % 10;
            // if (res>214748364 || (res==214748364 && tmp>7)) {   不需要判断最后一位
            // if (res<-214748364 || (res==-214748364 && tmp<-8)) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res * 10 + cur;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
