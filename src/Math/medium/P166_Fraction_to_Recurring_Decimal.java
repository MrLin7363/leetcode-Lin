package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe: 分数转循环小数
 */

import java.util.HashMap;
import java.util.Map;

public class P166_Fraction_to_Recurring_Decimal {
    /*
    长除法，对循环小数部分加()  100 + 96
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator==0) return "0";
        StringBuilder fraction = new StringBuilder();
        // 对boolean类型 异或 ， 一负一正(一 true 一 false)取负号
        if ( numerator<0 ^ denominator<0 ){
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend=Math.abs(Long.valueOf(numerator));
        long divisor=Math.abs(Long.valueOf(denominator));
        fraction.append(dividend/divisor);//整数部分
        long remainder=dividend % divisor;// 第一个余数
        if (remainder==0){
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long,Integer> map=new HashMap<>();
        while (remainder!=0){
            if (map.containsKey(remainder)){
                // 在循环开始的前面加入 (
                fraction.insert(map.get(remainder),"(");
                // 结束加 ）
                fraction.append(")");
                // 循环体找到结束
                break;
            }
            map.put(remainder,fraction.length());
            remainder*=10;
            fraction.append(remainder/divisor);
            remainder%=divisor;
        }
        return fraction.toString();
    }

}
