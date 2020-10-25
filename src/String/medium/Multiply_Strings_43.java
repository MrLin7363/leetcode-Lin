package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/24
  *@Describe: 字符串乘法
  1. 乘数每一位依次与被乘数所有位乘法，每一次乘完一轮都相加
  2. 优化竖式：每一位乘完后，按照位置规律加起来，从乘法最右边的那位确认
              int n2=num2.charAt(i)-'0'; 注意 -'0' 不然时拿ascall码相加
 */

public class Multiply_Strings_43 {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        // 保存计算结果
        String res="";
        // num2 每一位与num1相乘
        for (int i = num2.length()-1; i >= 0 ; i--) {
            // 保存num2与第i位相乘的结果
            StringBuilder temp= new StringBuilder();
            int carry=0;
            // 补0
            for (int j = 0; j <num2.length()-1-i ; j++) {
                temp.append(0);
            }
            int n2=num2.charAt(i)-'0';
            // num2 第i位与num1所有位相乘
            for (int j = num1.length()-1; j >=0 || carry!= 0 ; j--) {
                int n1= j<0 ? 0 : num1.charAt(j)-'0';
                int value=(n1*n2+carry)%10;
                carry=(n1*n2+carry)/10;
                temp.append(value);
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res=addStrings(res,temp.reverse().toString());
        }
        return res;
    }
    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    private static String addStrings(String s1,String s2){
        int carry=0;
        StringBuilder sb=new StringBuilder();
        for (int i = s1.length()-1, j = s2.length()-1; i>=0 || j>=0 || carry>0 ; i--,j--) {
            int v1= i<0 ? 0 : s1.charAt(i)-'0';
            int v2= j<0 ? 0 : s2.charAt(j)-'0';
            int value=(v1+v2+carry)%10;
            sb.append(value);  //  这里i , j 都遍历完，只剩carry时，也能把carry赋值到末尾
            carry=(v1+v2+carry)/10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        multiply2(new String("9"),new String("99"));
    }
    /*
    优化竖式
     */
    public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        int n=num1.length();
        int m= num2.length();
        int[] res=new int[n+m];
        for (int i = n-1; i >=0 ; i--) {
            int v1=num1.charAt(i)-'0';
            for (int j =  m-1; j >=0 ; j--) {
                int v2=num2.charAt(j)-'0';
                int sum=(res[i+j+1]+v1*v2);
                res[i+j+1]=sum%10; // 值
                res[i+j]=sum/10; // 进位
            }
        }
        // 去掉前面多余的0
        StringBuilder result=new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (!(i==0 && res[i]==0)) result.append(res[i]); // 因为第二位不会为0，后面的中间为0的话也要添加进去
        }
        return result.toString();
    }
}
