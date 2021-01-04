package Bit_Manipulation.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/2
  *@Describe:
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P137_Single_Number_II {
    public int singleNumber(int[] nums) {
        return 0;
    }
    /*
    hashSet  62 + 95
     */
    public int singleNumber2(int[] nums) {
        Set<Long> hashSet=new HashSet<>();
        long sumSet=0,sumArray=0;
        for (int n:nums){
            sumArray+=n;
            hashSet.add((long)n);
        }
        for (Long s:hashSet){
            sumSet+=s;
        }
        return (int)((3*sumSet-sumArray) /2); // 注意先/2 再转为int
    }

    /*
    hashmap 32 = 47
     */
    public int singleNumber3(int[] nums) {
        Map<Integer,Integer> hashtable=new HashMap<>();
        for (int i:nums){
            // getOrDefault  有这个key就使用这个key对应的值,没有就用默认值
            hashtable.put(i,hashtable.getOrDefault(i,0)+1);
        }
        for (int i:hashtable.keySet()){
            if (hashtable.get(i)==1)
                return i;
        }
        return -1;
    }
}
