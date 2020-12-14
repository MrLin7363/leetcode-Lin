package MyTest;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/6
  *@Describe:
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Big2 {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String sdf=dateFormat.format(new Date());
        String sdf2=dateFormat.format(new Date());
        try {
            Date date=dateFormat.parse(sdf);
            System.out.println(!date.equals(dateFormat.parse(sdf2)));
        }catch (Exception e){
        }
       /* BigDecimal b=new BigDecimal(1);
        BigDecimal c=new BigDecimal(1);
        if (b.equals(c))
            System.out.println("yes");*/
    }
}
