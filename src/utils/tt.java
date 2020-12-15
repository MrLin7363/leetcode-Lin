package utils;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/9
  *@Describe:
 */

import java.math.BigDecimal;

public class tt {

    public static void main(String[] args) {
        BigDecimal sum_hold=new BigDecimal(9900000);
        BigDecimal fet_rate=new BigDecimal(3.20); // 只要有小数位，除数会有问题0.0320000000000000017763568394002504646778106689453125
        BigDecimal yearsday=new BigDecimal(360);
        BigDecimal diffdays=new BigDecimal(22);
        BigDecimal res;
        // 百分比除于100 数据大的话，后面一些数据会有数值
        System.out.println(fet_rate.divide(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_UP));
        System.out.println(sum_hold.multiply(fet_rate.divide(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_UP)));
        System.out.println(sum_hold.multiply(fet_rate.divide(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_UP)).multiply(diffdays));
        System.out.println(sum_hold.multiply(fet_rate.divide(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_UP))
                .multiply(diffdays).divide(yearsday,BigDecimal.ROUND_HALF_EVEN));
        res=sum_hold.multiply(fet_rate.divide(new BigDecimal(100)).setScale(4,BigDecimal.ROUND_HALF_UP)).multiply(diffdays)
                .divide(yearsday,BigDecimal.ROUND_HALF_EVEN);
        System.out.println(res);
    }
}
