package String.easy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/12 16:40
 * @Describe: 第一个不重复的字符的下标 - hashtable
 * 一遍过
 */
public class First_Unique_Character_in_a_String_387 {
    /*public int firstUniqChar(String s) {
        HashMap<Integer,Integer> hashtable=new HashMap<>();
        for (int i=0;i<s.length();i++){
            if (!hashtable.containsKey(s.charAt(i))){
                hashtable.put(i,hashtable.get(s.charAt(i)+1));
            }
        }
        // 遍历map
        for (Map.Entry<Integer, Integer> entry : hashtable.entrySet()) {
            if (entry.getValue()==1){
                return entry.getKey();
            }
        }
    }*/
}
