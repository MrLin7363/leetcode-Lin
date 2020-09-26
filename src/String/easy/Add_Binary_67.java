package String.easy;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/29 19:32
 * @Describe:
 */
public class Add_Binary_67 {
    public String addBinary(String a, String b) {
        StringBuilder s=new StringBuilder();
        int carry=0,sum=0;
        //和正常竖式加法一样，从最后一位相加，不过因为是append，所以最后要反转数组才行
        for (int i = a.length()-1, j=b.length()-1; i >=0 || j>=0 ; i--, j--) {
            //记下进位,和当前运算相加
            sum=carry;
            //sum+=(i>=0? a.charAt(i)-'0' : 0); 其实是纪录每一次的相加结果，如果j<0或i<0的话则将其当做0来处理
            sum+=i>=0? a.charAt(i)-'0' : 0;
            sum+=j>=0? b.charAt(j)-'0' : 0;
            //记下相加后下面的每一位
            s.append(sum%2);
            //计算进位
            carry=sum/2;
        }
        //如果最后还有进位要加上去
        s.append(carry==1?carry:"");
        //反转数组
        return s.reverse().toString();
    }

    /*
    位与，受限于JAVA在Integer.parseInt时内存溢出，否则，下面的位运算解决是可以的
     */
    public String addBinary2(String a, String b) {
        int num1=Integer.parseInt(a,2);
        int num2=Integer.parseInt(b,2);
        while (num2!=0){
            //无进位和，异或
            int temp=num1^num2;
            //进位和，要向左移动一位
            num2=(num1&num2)<<1;
            num1=temp;
        }
        return Integer.toBinaryString(num1);
    }
}
