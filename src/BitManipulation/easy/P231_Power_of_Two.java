package BitManipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P231_Power_of_Two {
    /*
        位运算 -将二进制中最右边的 1 设置为 0  99 +28
     */
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        return (n& (n-1)) == 0;
    }
    /*
    位运算 -获取二进制中最右边的 1   99 + 28
     */
    public boolean isPowerOfTwo2(int n) {
        if (n == 0) return false;
        long x = (long) n; // -2147483648 防溢出
        return (x & (-x)) == x;
    }
        // 迭代，不考虑算法 logn  99 + 12 k
    public boolean isPowerOfTwo3(int n) {
        if (n==0) return false;
        while (n%2==0) n/=2;
        return n==1;
    }
}
