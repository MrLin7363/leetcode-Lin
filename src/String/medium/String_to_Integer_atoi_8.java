package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/18
  *@Describe:
  1.遍历匹配，字符-'0'转换为数值
  2.AC自动机
 */

import java.util.HashMap;
import java.util.Map;

public class String_to_Integer_atoi_8 {
    public static void main(String[] args) {
        myAtoi("+-12");
        myAtoiByAC("-91283472332");
    }
    public static int myAtoi(String s) {
        int i=0;
        int sign=1; // 正负
        int result=0;
        int len=s.length();
        if (len==0) return 0;
        // 跳过空格
        while (i<len && s.charAt(i)==' ')
            i++;
        // 判断正负,只用算第一个
        if (i<len && (s.charAt(i)=='+' || s.charAt(i)=='-'))
            sign=s.charAt(i++)=='-'? -1:1;
        // 判断是否溢出，非数字不会进去循环
        while (i<len && s.charAt(i)>='0' && s.charAt(i)<='9'){
            // 溢出
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && s.charAt(i)-'0' >Integer.MAX_VALUE%10)) // 7代替%10也可
                return sign==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            result = result*10+ (s.charAt(i++)-'0');
        }
        return result*sign;
    }

    /*
    2.有限AC自动机
     */
    public static int myAtoiByAC(String s) {
        Automation automation=new Automation();
        for (int i = 0; i < s.length(); i++) {
            if (automation.get(s.charAt(i))=="end")
                break;
        }
        return automation.sign * automation.result;
    }
    static class Automation{
        public int sign=1;
        public int result=0;
        private String state="start"; // 状态机初始化
        private Map<String,String[]> table=new HashMap<String, String[]>(){
            {
                put("start", new String[]{"start", "signed", "in_number", "end"});
                put("signed", new String[]{"end", "end", "in_number", "end"});
                put("in_number", new String[]{"end", "end", "in_number", "end"});
                put("end", new String[]{"end", "end", "end", "end"});
            }
        };// 状态机
        // 执行自动机
        public String get(char c){
            state=table.get(state)[getCol(c)];
            if ("in_number".equals(state)){
                // 判断溢出
                if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && c-'0' >Integer.MAX_VALUE%10)){
                    result= sign==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    state="end";
                }
                else {
                    result=result * 10 + c - '0';
                }
            }else if ("signed".equals(state)){
                sign= c=='-' ? -1 : 1;
            }
            return state; // end 时结束
        }
        // 获取自动机下一步跳转序号
        private int getCol(char c){
            if (c==' ')// 空格
                return 0;
            if (c=='+' || c=='-') // +-
                return 1;
            if (c-'0'>=0 && c-'0'<=9) // 数字
                return 2;
            return 3;
        }


    }
}
