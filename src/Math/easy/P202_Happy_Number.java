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
    快慢指针 82 + 92
     */
    public boolean isHappy2(int n) {
        int slow=n;
        int fast=getNext(n);
        while (slow!=1 && slow!=fast ){
            slow=getNext(slow);
            fast=getNext(getNext(fast));
        }
        return slow==1;
    }
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
