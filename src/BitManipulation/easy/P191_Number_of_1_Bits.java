package BitManipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P191_Number_of_1_Bits {
    // 逐个把最右边1置为 0  100 + 51
    public int hammingWeight2(int n) {
        int sum=0;
        while (n!=0){
            sum++;
            n&=n-1;
        }
        return sum;
    }
        // 循环和位移动 100 +25
    public int hammingWeight(int n) {
        int mask=1;
        int bitsOf1=0; // 一的个数
        for (int i = 0; i < 32; i++) {
            // 每次与如果不等于0说明，这个对应mask 的位置值 为 1
            if ( (mask&n) !=0){
                bitsOf1++;
            }
            mask<<=1;
        }
        return bitsOf1;
    }
}
