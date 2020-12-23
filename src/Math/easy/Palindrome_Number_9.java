package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/21 17:56
 * @Describe: 用String判断
 */
public class Palindrome_Number_9 {
    // 反转后半段判断
    public static boolean isPalindrome(int x) {
        // 负数或者 0 不是回文
        if(x<0 || x%10==0 && x!=0){
            return false;
        }
        int revernumber=0;
        // 反转中间的后半段
        while(x>revernumber){
            revernumber=revernumber*10+x%10;
            x/=10;
        }
        // 除于10就是奇数的比较
        return x==revernumber || x==revernumber/10;


        /*String s=String.valueOf(x);
        StringBuffer res=new StringBuffer();
        for (int i = s.length()-1; i >= 0 ; i--) {
            res.append(s.charAt(i));
        }
        if(res.toString().equals(s)){
        return true;
        }
        return false;*/
    }

}
