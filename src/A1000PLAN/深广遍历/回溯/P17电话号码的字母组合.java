package A1000PLAN.深广遍历.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc:
 *
 * @author 
 * @since 2023/9/20
 **/
public class P17电话号码的字母组合 {
    /*
    初始化map记录数字和字母的关系
     */
    private List<String> res = new ArrayList<>();

    private Map<Character, List<Character>> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        dfs(digits, 0, "");
        return res;
    }

    private void dfs(String digits, int index, String path) {
        if (path.length() == digits.length()) {
            res.add(path);
            return;
        }
        char digit = digits.charAt(index);
        List<Character> characters = map.get(digit);
        for (Character letter : characters) {
            // 用 String类型可以省去StringBuffer的append和remove回溯操作
            dfs(digits, index + 1, path + letter);
        }
    }

    // private void dfs(String digits, int index, String path) {
    //     if (path.length() == digits.length()) {
    //         res.add(path);
    //         return;
    //     }
    //     for (int i = index; i < digits.length(); i++) {
    //         char digit = digits.charAt(i);
    //         List<Character> characters = map.get(digit);
    //         for (Character letter : characters) {
    //             dfs(digits, i + 1, path + letter);
    //         }
    //     }
    // }

    public static void main(String[] args) {
        new P17电话号码的字母组合().letterCombinations("");
        new P17电话号码的字母组合().letterCombinations("2");
        new P17电话号码的字母组合().letterCombinations("23");
    }
}
