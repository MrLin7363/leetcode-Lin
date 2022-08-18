 

package hw.案例;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/26
 **/
public class 字符串编码校验 {

    List<List<Integer>> ans = new ArrayList<>(); // 存的是每次存的校验码的长度

    // 返回去掉校验码的字符串的个数
    public int check(String ss) {
        dfs(new ArrayList<>(), ss, 0);
        if (ans.isEmpty() || ans.size()>1){
            return -1;
        }
        return ans.get(0).stream().mapToInt(v->v).sum();
    }

    private void dfs(List<Integer> path, String sb, int begin) {
        // 正常结束条件：循环到后面
        if (begin == sb.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 终止条件：首字符不是数字，或者是前导0
        if (!Character.isDigit(sb.charAt(begin)) || sb.charAt(begin) == '0') {
            return;
        }
        // 最多四个数字
        for (int i = 1; i <= 4; i++) {
            // 如果不是数字
            if (!Character.isDigit(sb.charAt(begin + i - 1))) {
                break;
            }
            String preNumString = sb.substring(begin, begin + i);
            int preNum = Integer.valueOf(preNumString);
            int end = begin + i + preNum;
            if (end > sb.length()) {
                break;
            }
            path.add(preNum);
            dfs(path, sb, end);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        字符串编码校验 sss = new 字符串编码校验();
        System.out.println(sss.check("9computer012"));
    }


        /*
    有一种校验码机制，用于数据传输中的数据完整性检查，规则如下：

l  在字符串中插入一些数字作为校验码，每个数字之后跟随对应个数的字符；

l  要求有校验码（校验码大于零并且无前导零），并且正确匹配、无歧义：

如，"helloworld" 在插入校验码之后可以为 "5hello5world"，即 5 + "hello" + 5 + "world"；

但是，有些字符串在进行校验时会产生歧义，比如 "109something" 可以校验为 10 + "9something" 或者 1 + "0" + 9 + "something"，故这类编码方式是有歧义的。


现给出一个字符串 encodedString，请判断这个字符串是否符合上述规则：

l  如果是，则返回去掉校验码后的字符串长度；

l  如果不是，则返回 -1。
     */
}
