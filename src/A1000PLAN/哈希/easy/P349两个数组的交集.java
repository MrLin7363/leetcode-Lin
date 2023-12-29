package A1000PLAN.哈希.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/8
 **/
public class P349两个数组的交集 {
    // 两个set
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        for(int i:nums1){
            if(!set.contains(i)){
                set.add(i);
            }
        }
        Set<Integer> resSet=new HashSet<>();
        for(int i:nums2){
            if(set.contains(i)){
                resSet.add(i);
            }
        }
        return resSet.stream().mapToInt(Integer::intValue).toArray();
    }
}
