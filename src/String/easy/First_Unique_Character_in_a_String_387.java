package String.easy;

import java.net.Inet4Address;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/12 16:40
 * @Describe: 第一个不重复的字符的下标 - hashtable
 * 一遍过，用hashtable
 */
public class First_Unique_Character_in_a_String_387 {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashtable=new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            hashtable.put(c,hashtable.getOrDefault(c,0)+1);
        }
        //遍历map
        for (int i=0;i<s.length();i++){
            if (hashtable.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}
