package Bit_Manipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/1
  *@Describe:
 */

public class P190_Reverse_Bits {
    /*
    分治合并：位运算  100 + 80
     */
    public int reverseBits(int n) {
        // >>>无符号右移 避免右移后左边有符号位
        n = (n >>> 16) | (n << 16);
        // 0xff00ff00: 1111 1111 0000 0000 1111 1111 0000 0000  将前面8位保留，后面的清0再右移
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        // 0xf0f0f0f0: 1111 0000 1111 0000 1111 0000 1111 0000
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        // 0xcccccccc: 1100
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        // 0xaaaaaaaa: 1010
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
    /*
    逐位反转 98 + 38
     */
    public int reverseBits2(int n) {
        int res=0;
        for (int i = 0; i < 32; i++) {
            res|=(n&(1<<i))!=0 ?  1<< (31-i):0;
//            res+=(n&(1<<i))!=0 ?  1<< (31-i):0;
//            res^=(n&(1<<i))!=0 ?  1<< (31-i):0;
        }
        return res;
    }

    /*
    逐位颠倒，取模求和  98 + 39
     */
    public int reverseBits3(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // 新二进制左移一位，加上原先二进制的最右边一位，用n%2会溢出
            res= (res<<1) + (n&1);
            // 原先二进制右移1位
            n>>=1;
        }
        return res;
    }
}
