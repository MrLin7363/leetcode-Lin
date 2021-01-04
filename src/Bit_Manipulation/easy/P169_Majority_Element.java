package Bit_Manipulation.easy;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/3
  *@Describe:
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class P169_Majority_Element {


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
