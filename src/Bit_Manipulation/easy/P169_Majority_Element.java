package Bit_Manipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/3
  *@Describe:
 */

import java.util.HashMap;
import java.util.Map;

public class P169_Majority_Element {

    /*
    投票算法  99 + 64
     */
    public int majorityElement2(int[] nums) {
        int candidate=0;
        int count=0;
        for (int num:nums){
            if (count==0){
                candidate=num;
            }
            count+=num==candidate?1:-1;
        }
        return candidate;
    }
    /*
    hashmap 47 + 17
     */
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> counts=countNums(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer,Integer> entry:counts.entrySet()){
            if (majorityEntry==null || entry.getValue()>majorityEntry.getValue()){
                majorityEntry=entry;
            }
        }
        return majorityEntry.getKey();
    }
    public Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num:nums){
            counts.put(num,counts.getOrDefault(num,0)+1);
        }
        return counts;
    }
}
