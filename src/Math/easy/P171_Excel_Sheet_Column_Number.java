package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P171_Excel_Sheet_Column_Number {
    // 有点像二十六转十，但不是。 100 + 69
    // 如 ZY 则是 26*26 + 25 =701   不是Y * 26^0  Z*26^1
    public int titleToNumber(String s) {
        int ans = 0;
        for(int i=0;i<s.length();i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }

}
