package Array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/20 19:45
 * @Describe:
 */
public class Two_Sum_1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(i,nums[i]);
        }
        for (int i=0;i<nums.length;i++){
            int complement=target-nums[i];
            if(map.containsValue(complement)&&map.get(complement)!=i){
                return new int[]{i,map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
