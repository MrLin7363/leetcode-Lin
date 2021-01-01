package Bit_Manipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/28
  *@Describe:
 */

import java.util.ArrayList;
import java.util.List;

public class P342_Power_of_Four {

    // 用这个 log 运算所有n次幂共用  99 + 26
    public boolean isPowerOfFour2(int n) {
        return Math.log10(n)/Math.log10(4) %1==0;
//        return Math.log(n)/Math.log(4) %1==0;  log 是以e为底
//        return Math.log(n) / Math.log(2) % 2 == 0;
    }
    // 位运算 99 + 94     (101010...10)^2 用十六进制表示为 ：(aaaaaaaa)^16
    public boolean isPowerOfFour3(int n) {
        return n>0 && (n & (n - 1)) == 0  // 判断是不是2的幂
                && (n & 0xaaaaaaaa) == 0; // 判断是不是2的偶数幂 如 8 12 而不是 6 10
    }
    // 不考虑 99 + 40
    public boolean isPowerOfFour4(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
    }
    // 迭代 99 + 56
    public boolean isPowerOfThree5(int n) {
        if (n < 1 ) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    // 预计算，共有15种可能  10 + 11
    public boolean isPowerOfFour1(int n) {
        int count = 15;
        List<Integer> nums = new ArrayList();
        int lastNum = 1;
        nums.add(lastNum);
        // 4^0   --  4^15
        for (int i = 1; i < count + 1; ++i) {
            lastNum = lastNum * 4;
            nums.add(lastNum);
        }
        return nums.contains(n);
    }
}
