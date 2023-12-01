package A1000PLAN.数组.字符相加类;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P67二进制求和 {
    /*
    字符相加
     */
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0) {
            int c1 = i >= 0 ? a.charAt(i--) - '0' : 0;
            int c2 = j >= 0 ? b.charAt(j--) - '0' : 0;
            int cur = (c1 + c2 + carry) % 2;
            carry = c1 + c2 + carry >= 2 ? 1 : 0;
            sb.append(cur);
        }
        if (carry > 0) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        new P67二进制求和().addBinary("1", "11");
    }
}
