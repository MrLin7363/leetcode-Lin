package Bit_Manipulation.easy;
    
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
    分治算法  99 + 78
     */
    public int majorityElement(int[] nums) {
        return majorityElementRec(nums,0,nums.length-1);
    }
    public int majorityElementRec(int[] nums,int low,int high){
        if (low==high){
            return nums[low];
        }
        int mid=(high-low)/2+low;
        int left = majorityElementRec(nums,low,mid);
        int right = majorityElementRec(nums,mid+1,high);
        if (left==right){
            return left;
        }
        int leftCount=countInRange(nums,left,low,high);
        int rightCount = countInRange(nums, right, low, high);
        return leftCount>rightCount ? left:right;
    }
    public int countInRange(int[] nums,int num,int low,int high){
        int count=0;
        for (int i = low; i <= high; i++) {
            if (nums[i]==num){
                count++;
            }
        }
        return count;
    }
    /*
    hashmap 47 + 17
     */
    public int majorityElement3(int[] nums) {
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
