package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/8
  *@Describe: 61+6
 */

import java.util.HashMap;
import java.util.Map;

public class Sum4_II_454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res=0;
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum=A[i]+B[j];
//                map.put(sum,map.getOrDefault(sum,0)+1);  可替代下面if else
                if (map.containsKey(sum)) map.put(sum,map.get(sum)+1);
                else
                    map.put(sum,1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD=-(C[i]+D[j]);
                if (map.containsKey(sumCD)) res+=map.get(sumCD);
            }
        }
        return res;
    }
    //48+6
    public static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int res=0;
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum=A[i]+B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD=-(C[i]+D[j]);
                if (map.containsKey(sumCD)) res+=map.get(sumCD);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        fourSumCount2(new int[]{1,2},new int[]{-2,-1},new int[]{-1,2},new int[]{0,2});
    }
}
