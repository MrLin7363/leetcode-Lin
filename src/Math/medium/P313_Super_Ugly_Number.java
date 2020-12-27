package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P313_Super_Ugly_Number {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] nums=new int[n];

        return 0;
    }
    class UglyDP{
        public int[] nums=new int[1690];
        UglyDP(){
            nums[0]=1;
            int ugly,i2=0,i3=0,i5=0;
            for (int i = 1; i < 1690; i++) {
                ugly=Math.min(Math.min(nums[i2]*2 , nums[i3]*3) ,nums[i5] *5 );
                nums[i]=ugly;
                //全都加1的话就会把这个重复数给过滤掉了
                if (ugly==nums[i2]*2) i2++;
                if (ugly==nums[i3]*3) i3++;
                if (ugly==nums[i5]*5) i5++;
            }
        }
    }
}