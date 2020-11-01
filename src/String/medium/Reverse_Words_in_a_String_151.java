package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/1
  *@Describe:
  1.双端队列（栈）
  2.语言特性
  3.先反转整个字符串，再逐个反转单词
 */

import sun.swing.StringUIClientPropertyKey;

import java.util.*;

public class Reverse_Words_in_a_String_151 {
    /*
     双端队列
     */
    public String reverseWords(String s) {
        int left=0,right=s.length()-1;
        // 去掉头尾多余空格
        while (left<=right && s.charAt(left)==' ')
            left++;
        while (left<=right && s.charAt(right)==' ')
            right--;
        Deque<String> de=new ArrayDeque<>();
        StringBuilder sb=new StringBuilder();
        while (left<=right){
            char c=s.charAt(left);
            if (c == ' ' && sb.length()!=0){ // 字符串已存在，其下一位为空格，当前为找到单词
                de.offerFirst(sb.toString());
                sb.setLength(0);
                System.out.println(sb.toString());
            }else if (c != ' '){
                sb.append(c);
            }
            left++;
        }
        de.offerFirst(sb.toString()); // 添加最后一个单词
        return String.join(" ",de);
    }

    /*
     * @Description: 最快吧
     */
    public static  String reverseWords3(String s) {
        // 去掉开头和中间多余的空格
        StringBuilder sb = trimSpaces(s);
        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);
        // 翻转每个单词
        reverseEachWord(sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        reverseWords3("  hello world  ");
    }
    private static StringBuilder trimSpaces(String s){
        int left = 0, right = s.length() - 1;
        while (left<=right && s.charAt(left)==' ')
            left++;
        while (left<=right && s.charAt(right)==' ')
            right--;
        // 去中间多余
        StringBuilder sb=new StringBuilder();
        while (left<=right){
            char c=s.charAt(left);
            if (c != ' '){
                sb.append(c);
            }else if (sb.charAt(sb.length()-1) !=' '){ // 如果前一位不等于空格,注意是sb
                sb.append(c); //添加空格
            }
            left++;
        }
        return sb;
    }

    private static void reverseEachWord(StringBuilder s){
        int n=s.length();
        int start=0,end=0;
        while (start<n){
            // 循环至每个单词的末尾
            while (end<n && s.charAt(end)!=' ')
                end++;
            // 反转单词
            reverse(s,start,end-1);
            start=end+1;
            end++;
        }
    }
    public static void reverse(StringBuilder s,int left, int right){
        while (left<right){
            char temp=s.charAt(left);
            s.setCharAt(left++,s.charAt(right));
            s.setCharAt(right--,temp);
        }
    }
    /*
    API 运用
     */
    public String reverseWords2(String s) {
        s=s.trim();// 去除头尾空格,注意需要返回
        List<String> list= Arrays.asList(s.split("\\s+")); // \\s表示 空格,回车,换行等空白符,+代表多个
        Collections.reverse(list);
        return String.join(" ",list);
    }
}
