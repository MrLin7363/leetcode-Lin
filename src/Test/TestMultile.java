package Test;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/21
  *@Describe:
 */

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Stream;

public class TestMultile {

    public static void main(String[] args) {
        new ThreadLocal<>();
        new HashMap();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println("qwe");
            }
        };
        BigDecimal ss=null;
        if (ss!=null || ss.compareTo(BigDecimal.ZERO)>0)
        System.out.println("asd");
    }
}
