package String.easy;

import java.util.HashMap;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/12 10:27
 * @Describe: 判断第一个字符能否由第二个字符串中取字符组成，只能用一次
 * 1、两层for循环，标志使用过，效率太低  O n^2
 * 2、两个for循环 ， O n
 * java,用数组存储字符,'a'存储在map[0]中,初始值都为0。
 * 1.首先遍历一遍magazine字符串,将字符串中的字符在指定位置+1,获取字典。
 * 2.再遍历一遍ransomNote字符串,判断数组中该字符的数量是否大于0，如果大于0则自减,否则就返回false
 * -97  -'a' 都能让小写字母回到整形数字 0-26
 */
public class Ransom_Note_383 {
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];
        for(int i = 0; i < magazine.length(); i++) {
            map[magazine.charAt(i) - 97]++;
        }
        for(int i = 0; i < ransomNote.length(); i++) {
            if(map[ransomNote.charAt(i) - 97] > 0) {
                map[ransomNote.charAt(i) - 97]--;
            }else{
                return false;
            }
        }
        return true;
    }
    /*
    效率贼低
     */
    public static boolean canConstruct2(String ransomNote, String magazine) {
        if (magazine.length()==0 && ransomNote.length()!=0)
            return false;
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        // 初始化hashmap
        for (int j=0;j<magazine.length();j++)
            hashMap.put( j , false);
        for (int i = 0; i < ransomNote.length(); i++) {
            for (int j=0;j<magazine.length();j++) {
                if(j == magazine.length()-1 && ransomNote.charAt(i) != magazine.charAt(j)){
                    return false;
                }
                // 有匹配的字符串
                if ( ransomNote.charAt(i) == magazine.charAt(j)) {
                    // 如果没被用过
                    if (hashMap.get(j) == false) {
                        hashMap.put(j, true);
                        break;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        canConstruct(new String("aa"),new String(""));
    }
}
