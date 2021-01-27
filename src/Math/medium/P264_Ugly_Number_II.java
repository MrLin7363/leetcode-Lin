package Math.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/27
  *@Describe:
 */

import java.util.HashSet;
import java.util.PriorityQueue;

public class P264_Ugly_Number_II {
    // [0-1690] 的丑数按升序，预计算   5 + 12
    // hash + heap
    class Ugly{
        public int[] nums=new int[1690];
        Ugly(){
            HashSet<Long> seen=new HashSet<>(); // 注意long类型否则溢出
            PriorityQueue<Long> heap=new PriorityQueue<>();//升序堆
            seen.add(1L); // 1是丑数
            heap.add(1L);
            int[] primes=new int[]{2,3,5};
            long curUgly,newUgly;
            for (int i = 0; i < 1690; i++) {
                curUgly=heap.poll();
                nums[i]=(int)curUgly;
                for (int j:primes){
                    newUgly=curUgly*j;
                    if (!seen.contains(newUgly)){
                        seen.add(newUgly);
                        heap.add(newUgly);
                    }
                }
            }
        }
    }
    public  int nthUglyNumber(int n) {
//         return new Ugly().nums[n-1];
        return new UglyDP().nums[n-1];
    }

    public static void main(String[] args) {
        new P264_Ugly_Number_II().nthUglyNumber(10);
    }
    // DP三指针 丑数 42 + 54
    class UglyDP{
        public int[] nums=new int[1690];
        UglyDP(){
            nums[0]=1;
            int ugly,i2=0,i3=0,i5=0;
            for (int i = 1; i < 1690; i++) {
                ugly=Math.min(Math.min(nums[i2]*2 , nums[i3]*3) ,nums[i5] *5 );
                nums[i]=ugly;
                //全都加1的话就会把这个重复数给过滤掉了
                if (ugly==nums[i2]*2) i2++;
                if (ugly==nums[i3]*3) i3++;
                if (ugly==nums[i5]*5) i5++;
            }
        }
    }
}
