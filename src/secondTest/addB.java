package secondTest;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/30
  *@Describe:
 */

public class addB {
    public String addBinary(String a, String b) {
        StringBuilder res=new StringBuilder();
        int l1=a.length(),l2=b.length();
        int sum=0,carry=0;
        for (int i = l1-1,j=l2-1; i >=0 || j>=0; i--,j--) {
            sum=carry;
            sum+=(i>=0)?a.charAt(i)-'0':0;
            sum+=(j>=0)?b.charAt(j)-'0':0;
            res.append(sum%2);
            carry=sum/2;
        }
        res.append(carry==1?carry:"");
        return res.reverse().toString();
    }

    public boolean isPalindrome(String s) {
        int j=s.length()-1,i=0;
        while (i<j){
            if (i<j&&!Character.isLetterOrDigit(s.charAt(i))){
                i++;
            }
            else if (i<j&&!Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }
            else if (Character.toLowerCase(s.charAt(i++))!=Character.toLowerCase(s.charAt(j--))){
                return false;
            }
        }
        return true;
    }
    public String countAndSay(int n) {
        StringBuilder ss=new StringBuilder();
        if (n==1){
            return "1";
        }
        int prev=0,cur=1;
        String str=countAndSay(n-1);
        for (cur = 1; cur < str.length(); cur++) {
            if (str.charAt(prev)!=str.charAt(cur)){
                int count=cur-prev;
                ss.append(count).append(str.charAt(prev));
                prev=cur;
            }
        }
        // 最后cur for循环后是位于length位置的，所以最后一段肯定会相加
        int count=cur-prev;
        ss.append(count).append(str.charAt(prev));
        return ss.toString();
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";
        String str=strs[0];
        int len=strs.length;
        for (int i = 0; i <len ; i++) {
            while (strs[i].indexOf(str)!=0){
                str=str.substring(0,str.length()-1);
            }
        }
        return str.isEmpty()?"":str;
    }
    public int lengthOfLastWord(String s) {
        if (s.length()==0) return 0;
        int len=s.length();
        int i=len-1;
        while (i>=0&&s.charAt(i)==' '){
            i--;
        }
        if (i<0) return 0;
        int end =i;
        int start=i;
        while (start>=0&&s.charAt(start)!=' '){
            start--;
        }
        return end-start;
    }
    public String reverseStr(String s, int k) {
        char[] a=s.toCharArray();
        int len=s.length();
        for (int start = 0; start < len; start+=2*k) {
            int i=start,j=Math.min(i+k-1,len-1);
            while (i<j){
                char temp=a[i];
                a[i++]=a[j];
                a[j--]=temp;
            }
        }
        return new String(a);
    }
        public static void main(String[] args) {
        new addB().isPalindrome(" --  abcccccccba   ");
    }
}
