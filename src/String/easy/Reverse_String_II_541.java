package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/12 18:56
 * @Describe:
 */
public class Reverse_String_II_541 {
    public static String reverseStr(String s, int k) {
        char[] chars=s.toCharArray();
        int i,j;
        int len=s.length();
        //这个在每次遍历后j=i+k-1不能少，前面的只是初始化
        for (i=0,j=i+k-1;i<len;i+=2*k,j=i+k-1){
            //如果剩余字符少于 k 个，则将剩余字符全部反转,即是i到数组末尾反转。
            if (j>=len){
                reverseRange(chars,i,len-1);
            }
            //正常情况
            //如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
            else reverseRange(chars,i,j);
        }
        //这里不能用chars.toString()，会返回这个对象
        return String.valueOf(chars);
    }
    /*
    反转区间内的字符串
     */
    private static void reverseRange(char[] chars,int left,int rigth) {
        while (left<rigth){
            char c=chars[rigth];
            chars[rigth--]=chars[left];
            chars[left++]=c;
        }
    }
    public static void main(String[] args) {
        String s=reverseStr(new String("abcde"),2);
        for (int i=0;i<s.length();i++){
            System.out.print(s.charAt(i));
        }
    }
    /*
    ----------------------------------------------------------------------------------
    官方答案  int i = start, j = Math.min(start + k - 1, a.length - 1);
     */
        public String reverseStr2(String s, int k) {
            char[] a = s.toCharArray();
            for (int start = 0; start < a.length; start += 2 * k) {
                int i = start, j = Math.min(start + k - 1, a.length - 1);
                //交换
                while (i < j) {
                    char tmp = a[i];
                    a[i++] = a[j];
                    a[j--] = tmp;
                }
            }
            return new String(a);
        }

}
