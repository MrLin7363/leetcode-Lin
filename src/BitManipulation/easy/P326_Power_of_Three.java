package BitManipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P326_Power_of_Three {





    public boolean isPowerOfThree4(int n) {
        if (n < 1 ) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
