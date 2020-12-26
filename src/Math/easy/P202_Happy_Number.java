package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/26
  *@Describe: 快乐数不会越来越大，会有一个环
 */

import java.util.HashSet;
import java.util.Set;

public class P202_Happy_Number {
    /*

     */

    /*
    哈希判断环 82 + 67
     */
    public boolean isHappy3(int n) {
        Set<Integer> seen=new HashSet<>();
        // 如果遇到环停止
        while (n!=1 && !seen.contains(n)){
            seen.add(n);
            n=getNext(n);
        }
        return n==1;
    }
    public int getNext(int n){
        int sum=0;
        while (n>0){
            int tail=n%10;
            n=n/10;
            sum+=tail*tail;
        }
        return sum;
    }
}
