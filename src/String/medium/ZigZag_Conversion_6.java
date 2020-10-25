package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/25
  *@Describe: 字符串按Z型排列
1.按行排序：通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。  ON ON

我们可以使用  min{ numRows,len(s) }个列表来表示 Z 字形图案中的非空行。

从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。

只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。

2.按行访问
按照与逐行读取 Z 字形图案相同的顺序访问字符串。   ON ON
首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...
P   A   H   N
A P L S I I G
Y   I   R
每一次循环 上面的是 4
 */

import java.util.ArrayList;
import java.util.List;

public class ZigZag_Conversion_6 {
    public String convert(String s, int numRows) {
        if (numRows==1) return s;
        // 初始化共有几行
        List<StringBuilder> rows=new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow=0;
        boolean goingDown=false;// 方向
        // 给每一行赋值
        for (char c:s.toCharArray()){
            // 赋值
            rows.get(curRow).append(c);
            // 方向是否变换，第0行到倒数第1行前，都是方向正，每次行前进1；最后一行则方向-1，直到第1行变换方向
            if (curRow==0 || curRow==rows.size()-1)
                goingDown =!goingDown; // 变换方向
            curRow+=goingDown?1:-1; //正+1
        }
        StringBuilder res=new StringBuilder();
        for (StringBuilder row:rows) res.append(row);
        return res.toString();
    }

    /*
    P   A   H   N
    A P L S I I G
    Y   I   R
    i=2 时循环到第2行； 环=4。j每次+4。
     */
    public static String convert2(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2; // 如上图，一个循环=4， PAYP  ,  ALIS , HIRI , NG
        // 每一行
        for (int i = 0; i < numRows; i++) {
            // j每一次循环，要确定两个数字，一个是循环次数中的第一个字母，一个是 Z 中间连接的字母
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));  // 第2行时，j =0，确定A
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) // 中间行的要额外确定多一个字母
                    // 第2行时，j =0，确定P
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        convert2("PAYPALISHIRING",3);
    }
}
