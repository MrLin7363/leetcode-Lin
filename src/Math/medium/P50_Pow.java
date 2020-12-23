package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/22
  *@Describe:
 */

public class P50_Pow {
    public double myPow(double x, int n) {
        for (int i = 0; i*i < n; i++) {
            x=x*x;
        }
        return x;
    }
}
