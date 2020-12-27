package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

public class P168_Excel_Sheet_Column_Title {
    // 100 + 34 十进制转二十六进制
    public String convertToTitle(int n) {
        StringBuilder sb=new StringBuilder();
        while (n>0){
            int c=n%26;
            if (c==0){
                c=26;
                n-=1;
            }
            // 每次插入头部
            sb.insert(0,(char)('A'+c-1));
            n/=26;
        }
        return sb.toString();
    }
}
