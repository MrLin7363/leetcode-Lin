package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/12 18:19
 * @Describe: 就是反转特定字符串，双指针，如果遇到元音字母下标停止，交换两个位置
 */
public class Reverse_Vowels_of_a_String_345 {
    /*
     双指针法
     */
    private static char[] vowels={'a','A','e','E','i','I','o','O','u','U'};
    public String reverseVowels(String s) {
        if (s.length()==0) return "";
        //这里作为-1，是因为下面如果头尾元素第一次就要换的话，++i，--j后是当前位置
        int i=-1,j=s.length();
        char[] chars=s.toCharArray();
        char temp;
        while (i<j){
            while ( !isVowel(chars[++i]) )
                if (i==s.length()-1) break;
            while ( !isVowel(chars[--j])  )
                if (j==0) break;
            if (i>=j) break;
            temp=chars[i];
            chars[i]=chars[j];
            chars[j]=temp;
        }
        return String.valueOf(chars);
    }
    //判断是不是元音
    private boolean isVowel(char c){
        for (char i:vowels){
            if (i==c)
                return true;
        }
        return false;
    }
}
