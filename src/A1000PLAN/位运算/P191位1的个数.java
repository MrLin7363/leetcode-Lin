package A1000PLAN.位运算;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P191位1的个数 {
    /*
    Ologn=1的个数 Brian Kernighan 算法x=x & (x−1)，该运算将 xxx 的二进制表示的最后一个1变成0
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /*
    Ok=32 循环检查二进制位 1的个数
     */
    public int hammingWeight2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            // &相同为1,  1<<左移32位分别匹配
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

}
