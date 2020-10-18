package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/18
  *@Describe:[2-9] 电话号码字母交集
 */

import java.util.*;

public class Letter_Combinations_of_a_Phone_Number_17 {
    public List<String> letterCombinations(String digits) {
        ArrayList<String> resList=new ArrayList<>();
        Map<Integer,String[]> table=new HashMap<Integer,String[]>(){{
            put(2,new String[]{"a","b","c"});
            put(3,new String[]{"d","e","f"});
            put(4,new String[]{"g","h","i"});
            put(5,new String[]{"j","k","l"});
            put(6,new String[]{"m","n","o"});
            put(7,new String[]{"p","q","r","s"});
            put(8,new String[]{"t","u","v"});
            put(9,new String[]{"w","x","y","z"});
        }};
        if (digits.length()==0 || digits.length()==1){
            return new ArrayList<>(Arrays.asList(digits));
        }
        StringBuilder sb=new StringBuilder(); // 依次求两个数字字母的交集
        for (int i = 0; i < digits.length()-1; i++) {
            sb.append(table.get(digits.charAt(i)));
        }
        // 求子串交集

        return resList;
    }
}
