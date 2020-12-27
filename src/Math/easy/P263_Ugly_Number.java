package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P263_Ugly_Number {
    /*
    只含因数2，3，5   %  100 + 35
     */
    public boolean isUgly(int num) {
        if (num<1) return false;
        int[] factor=new int[]{2,3,5};
        for(int i:factor){
            while (num%i==0){
                num=num/i;
            }
        }
        return num==1?true:false;
    }
}
