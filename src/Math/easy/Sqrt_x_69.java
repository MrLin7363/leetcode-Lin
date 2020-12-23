package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/14 0:21
 * @Describe:
 */
public class Sqrt_x_69 {
    /*
    二分法
    mid*mid<=x
     */
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


     /*
    牛顿迭代法 官方版本，非递归
     */
     public int mySqrt2(int x) {
         if (x==0) return 0;
         double C=x,x0=x;
         while (true){
             double xi=0.5*(x0+C)/x0;
             //  1e-7是10的-7次方,差值小于这个数可以返回了
             if (Math.abs(x0-xi)< 1e-7 )
                 break;
             x0=xi;
         }
         return (int)x0;
     }

      /*
    牛顿迭代法 递归
     */
     private int s;
    public int mySqrtN(int x) {
        s=x;
        if (x==0) return 0;
        return (int)sqrts(x);
    }
    private double sqrts(double x){
        double res=(x+s/x)/2;
        if (res==x)
            return x;
        else return sqrts(res);
    }
}
