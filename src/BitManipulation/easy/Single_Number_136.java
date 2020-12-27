package BitManipulation.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/14 18:41
 * @Describe: 找单数
 */
public class Single_Number_136 {

    /*
    位运算
     */
    public int singleNumber2(int[] nums) {
        int a=0;
        for (int i:nums){
            a^=i;
        }
        return a;
    }

    /*
    Map方式
     */
    public static int singleNumber(int[] nums) {
        Map<Integer,Integer> hashtable=new HashMap<>();
        for (int i:nums){
            // getOrDefault  有这个key就使用这个key对应的值,没有就用默认值
            hashtable.put(i,hashtable.getOrDefault(i,0)+1);
        }
        for (int i:hashtable.keySet()){
            if (hashtable.get(i)==1)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{4,1,2,1,2};
        singleNumber(nums);
    }
}
