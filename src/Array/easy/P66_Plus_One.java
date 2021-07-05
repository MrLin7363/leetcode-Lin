package Array.easy;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/25
  *@Describe:
 */

public class P66_Plus_One {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            //如果末位有一个不是0则可以直接返回,否则继续循环，往前面位数加1
            if (digits[i] != 0) {
                return digits;
            }
        }
        //默认所有都为0,再在前面加一个1
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

}
