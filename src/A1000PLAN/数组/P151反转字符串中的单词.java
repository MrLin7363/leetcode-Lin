package A1000PLAN.数组;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/22
 **/
public class P151反转字符串中的单词 {
    /*
    双指针-最优解O1空间：同时从右往左，卻单词添加进sb, sb算一个O1空间吧
     */
    public String reverseWords(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;

        // 去掉首尾空格
        while (left < len && s.charAt(left) == ' ') {
            left++;
        }
        while (right > left && s.charAt(right) == ' ') {
            right--;
        }

        int index = right;
        StringBuffer sb = new StringBuffer();
        while (left <= right) {
            while (index >= left && s.charAt(index) != ' ') {
                index--;
            }
            sb.append(s.substring(index + 1, right + 1));
            if (index > left) {
                sb.append(" ");
            }

            // 过滤空格
            while (index >= left && s.charAt(index) == ' ') {
                index--;
            }
            right = index;
        }

        return sb.toString();
    }

    public String reverseWords2(String s) {
        s = s.trim();
        // 分隔多个空格
        String[] split = s.split("\\s+");
        List<String> list = Arrays.asList(split);
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public static void main(String[] args) {
        new P151反转字符串中的单词().reverseWords("a good   example");
        new P151反转字符串中的单词().reverseWords("the sky is blue");
    }
}
