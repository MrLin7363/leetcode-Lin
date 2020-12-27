package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/24
  *@Describe: 翻倍除法
 */

public class P29_Divide_Two_Integers {
    // 100/3
    // 100>3 100>6 100>12 100>24 100>48 100>96 100<192 ---- 使用了 2^5 = 32 个3，还剩 100 - 96 = 4 需要被除
    // 4>3 4<6                                         ---- 使用了 2^0 = 1  个3，还剩 4   - 3  = 1 需要被除
    // 1<3                                             ---- 被除数小于除数，递归结束，总计使用了 33 个 3
    // 简单概括为： 60/8 = (60-32)/8 + 4 = (60-32-16)/8 + 2 + 4 = 1 + 2 + 4 = 7
    /*
    long 类型，不转为负数，正数  好理解 100 + 71
     */
    public int divide2(int dividend, int divisor) {
        if(dividend == 0) return 0;
        // 当除数为1，直接返回被除数
        if (divisor==1) return dividend;
        if (divisor==-1){
            // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
            if ( dividend==Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }else{ // 其余返回相反数
                return -dividend;
            }
        }
        // 把被除数与除数调整为正数,为防止被除数Integer.MIN_VALUE转换为正数会溢出，使用long类型保存参数
        if (dividend < 0 && divisor < 0) {
            return div(-(long) dividend, -(long) divisor);
        } else if (dividend < 0 || divisor < 0) {
            return -div(Math.abs((long) dividend), Math.abs((long) divisor));
        } else {
            return div((long) dividend, (long) divisor);
        }
    }
    public int div(long dividend,long divisor){
        // 如果被除数小于除数，负数情况相反，结果明显为0
        if (dividend<divisor){
            return 0;
        }
        int count = 1; // 使用了多少个divisor
        long sum = divisor; // 记录用了count个divisor的和
        while (sum+sum<=dividend ){
            // 每次翻倍
            sum<<=1;
            count<<=1;
        }
        return count+div(dividend-sum ,divisor);
    }

    /*
    int 转化为负数避免溢出运算，建议看long类型的相反操作  100 + 71
     */
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        // 当除数为1，直接返回被除数
        if (divisor==1) return dividend;
        if (divisor==-1){
            // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
            if ( dividend==Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }else{ // 其余返回相反数
                return -dividend;
            }
        }
        int sign=1;
        if ( (dividend>0 && divisor<0) || ( dividend<0&&divisor>0 )){
            sign=-1;
        }
        // 如果被除数是 MIN 会溢出
        dividend= dividend>0 ? -dividend:dividend;
        divisor= divisor > 0 ? -divisor:divisor;
        // 都改为负号是因为int 的范围是[2^31, 2^31-1]，如果a是-2^32，函数转为正数时将会溢出
        // 如 50/100=0  如果被除数小于除数，因为转了负数，所以这里符号相反
        if (dividend>divisor){
            return 0;
        }
        // 转化为负数进行计算
        int ans=div(dividend,divisor);
        return sign==-1?-ans:ans;
    }
    public int div(int dividend,int divisor){
        // 如果被除数小于除数，负数情况相反，结果明显为0
        if (dividend>divisor){
            return 0;
        }
        int count = 1; // 使用了多少个divisor
        int sum = divisor; // 记录用了count个divisor的和
        while (sum+sum>=dividend && sum+sum<0){
            // 每次翻倍
            sum<<=1;
            count<<=1;
        }
        return count+div(dividend-sum ,divisor);
    }

        public static void main(String[] args) {
        //2147483647
        System.out.println(Integer.MAX_VALUE);
        //-2147483648 最小值比最大值 绝对值大一，可以利用这点判断溢出问题
        System.out.println(Integer.MIN_VALUE);
    }
}
