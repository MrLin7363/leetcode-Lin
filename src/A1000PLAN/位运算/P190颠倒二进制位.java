package A1000PLAN.位运算;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P190颠倒二进制位 {
    /*
    https://leetcode.cn/problems/reverse-bits/solutions/246370/zhi-qi-ran-zhi-qi-suo-yi-ran-wei-yun-suan-jie-fa-x/?envType=study-plan-v2&envId=top-interview-150
    1. 取模求和
    与反转十进制整数使用取模除十累加的方法类似
    十进制：ans = ans * 10 + n % 10; n = n / 10;
    二进制：ans = ans * 2 + n % 2; n = n / 2;
     */
    public int reverseBits(int n) {
        int res = 0;
        // 二进制要考虑前导0，所以干脆循环二进制最大长度
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }
}
