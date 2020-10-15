package String.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/14
  *@Describe: 以空格为分割连续的字符串个数
  1.用函数实现，先去掉头尾多余空格， \\s+ 去掉多个分隔符
  2.前一个字符为空格，或者是第一个字符
 */

public class Number_of_Segments_in_a_String_434 {
    public int countSegments(String s) {
        String trimed=s.trim();
        if(trimed.equals(""))
            return 0;
        return trimed.split("\\s+").length;
    }

    public int countSegments2(String s) {
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            if ((i==0 || s.charAt(i-1)==' ') && s.charAt(i)!=' ' )
                count++;
        }
        return count;
    }

}
