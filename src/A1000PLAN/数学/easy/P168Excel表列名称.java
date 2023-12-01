package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P168Excel表列名称 {
    /*
    简洁版-二十六进制
    和正常 0~25 的 26 进制相比，本质上就是每一位多加了 1。假设 A == 0，B == 1，
    那么 AB = 26 * 0 + 1 * 1，而现在 AB = 26 * (0 + 1) + 1 * (1 + 1)，
    所以只要在处理每一位的时候减 1，就可以按照正常的 26 进制来处理
     */
    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    /*
    二十六进制求余
    65='A'
     */
    public String convertToTitle2(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            int c = (columnNumber - 1) % 26 + 1;
            sb.append((char) (c - 1 + 'A'));
            columnNumber = (columnNumber - c) / 26;
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        new P168Excel表列名称().convertToTitle(701);
        new P168Excel表列名称().convertToTitle(26);
        new P168Excel表列名称().convertToTitle(28);
        System.out.println((char) 65);
        System.out.println((char) 66);
    }
}
