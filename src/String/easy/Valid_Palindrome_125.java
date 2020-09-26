package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/10 18:45
 * @Describe: 只用判断字母，其他不用判断，默认空字符串是回文
 * 1、用前后两个指针向中间遍历，如果遇不相等则不是回文,结束条件是最后相遇的字母也相等,偶数情况下则是两个下标分别过去一位，字母也相等  O n  O 1
 * 2、分别两次遍历，再字符串判断 O n^2  O n   //  或者不用遍历利用StringBuffer反转，然后两个比较，但是得先去掉不是字母的部分
 */
public class Valid_Palindrome_125 {
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()|| s.length()==1) return true;
        //定义两个下标
        int i=0,j=s.length()-1;
        char[] str=s.toCharArray();
        //如果不相等返回false
        while (i<=j){
            //若不是字母,这里如果有数字也要判断，数字和字母不一样就错，,找到是字母的,这里如果用while 那如果超过数组长度要break所以用if
            if (!Character.isLetterOrDigit(str[i]) ) i++;
            else if (!Character.isLetterOrDigit(str[j]) ) j--;
            else if(Character.toLowerCase(str[i++])!=Character.toLowerCase(str[j--]))
                return false;
        }
        return true;
    }

    public static boolean isPalindrome2(String s){
        //将非字母数字的换成空格
        String actual=s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        //注意这里不能用StringBuilder和String直接比， 因为StringBuilder没有重写equals方法，依旧比较地址
        String rev=new StringBuilder(actual).reverse().toString();
        return rev.equals(actual);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }
}
