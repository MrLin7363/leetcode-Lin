package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/21 17:56
 * @Describe: 用String判断
 */
public class Palindrome_Number_9 {
    public static boolean isPalindrome(int x) {
        if(x<0 || x%10==0 && x!=0){
            return false;
        }
        int revernumber=0;
        while(x>revernumber){
            revernumber=revernumber*10+x%10;
            x/=10;
        }
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

    public static void main(String[] args) {
//        System.out.println(isPalindrome(121));
    }
}
