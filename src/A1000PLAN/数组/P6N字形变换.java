package A1000PLAN.数组;

/**
 *desc:
 *@author lin
 *@since 2023/11/22
 **/
public class P6N字形变换 {
    /*
    填充二维数组，再遍历
    一个周期是一壹+斜行  向下填充r个字符，右上填充r-2个字符，一个周期为 t=r+r-2 个字符,每个周期占用 r-2+1 列
    因此有 n/t 个周期， 矩阵列数= n+t-1/t *(r-1) 多余的一两列不需考虑 n+t-1是多考虑一个周期，有可能n<t或者n有余数
    如果当前字符下标i满足 i/t<r-1 则向下移动，否则右上移动
     */
    public String convert(String s, int numRows) {
        int circle = numRows + numRows - 2;
        int n = s.length();
        if (numRows == 1 || numRows >= n) {
            return s;
        }
        int cols = ((n + circle - 1) / circle) * (numRows - 1);
        char[][] martrix = new char[numRows][cols];
        // 填充
        int row = 0;
        int col = 0;
        for (int i = 0; i < n; i++) {
            martrix[row][col] = s.charAt(i);
            // 下移动
            if (i % circle < numRows - 1) {
                row++;
            } else {
                row--;
                col++;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < martrix.length; i++) {
            for (int j = 0; j < martrix[0].length; j++) {
                if (martrix[i][j] != 0) {
                    sb.append(martrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new P6N字形变换().convert("A", 1);
        new P6N字形变换().convert("PAYPALISHIRING", 4);
        new P6N字形变换().convert("PAYPALISHIRING", 3);
    }
}
