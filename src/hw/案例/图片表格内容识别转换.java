 

package hw.案例;

import java.util.HashMap;

/**
 * desc: 无题目说明
 *
 * @author junlin
 * @since 2022/1/14
 **/
public class 图片表格内容识别转换 {

    static HashMap<Integer, Integer> colsMaxWidth = new HashMap<>(); // 每列的最大
    static String[][] matrix;

    private static String[] transformationTable(String[] tableInfo, int lineNum, int colNum) {
        matrix = new String[lineNum][colNum];
        buildModel(tableInfo, colNum);
        String[] result = new String[lineNum + 2];
        // 一行行的绘制文本
        result[0] = drawLine(0, colNum, true);
        result[result.length - 1] = drawLine(0, colNum, true);
        for (int i = 0; i < lineNum; i++) {
            result[i + 1] = drawLine(i, colNum, false);
        }
        return result;
    }

    private static String drawLine(int lineNo, int colNum, boolean topOrBottom) {
        String placeHolder = topOrBottom ? "-" : " ";
        String splitStr = topOrBottom ? "+" : "|";
        StringBuilder line = new StringBuilder(splitStr);
        for (int i = 0; i < colNum; i++) {
            int width = colsMaxWidth.containsKey(i + 1) ? colsMaxWidth.get(i + 1) + 2 : 2;
            if (!topOrBottom && matrix[lineNo][i] != null) {
                // 总共需要的空格
                int needPlace = width - matrix[lineNo][i].length();
                int leftPlace = needPlace / 2;
                // 如果需要的空格不是偶数，那么右侧多一个空格，因为要居中表示
                int rightPlace = (needPlace % 2) == 0 ? needPlace / 2 : needPlace / 2 + 1;
                for (int j = 0; j < leftPlace; j++) {
                    line.append(placeHolder);
                }
                line.append(matrix[lineNo][i]);
                for (int j = 0; j < rightPlace; j++) {
                    line.append(placeHolder);
                }
            } else {
                for (int j = 0; j < width; j++) {
                    line.append(placeHolder);
                }
            }
            line.append(splitStr);
        }
        return line.toString();
    }

    private static void buildModel(String[] tableInfo, int colNum) {
        for (int i = 0; i < tableInfo.length; i++) {
            String[] parsed = tableInfo[i].split(" ");
            if (parsed.length == 3) {
                int lineNo = Integer.parseInt(parsed[0].substring("line".length(), "line".length() + 1));
                int colNo = Integer.parseInt(parsed[1].substring("col".length(), "col".length() + 1));
                String content = parsed[2];
                matrix[lineNo - 1][colNo - 1] = content;
                if (!colsMaxWidth.containsKey(colNo)) {
                    colsMaxWidth.put(colNo, content.length());
                } else if (colsMaxWidth.get(colNo) < content.length()) {
                    colsMaxWidth.put(colNo, content.length());
                }
            }
        }
    }
}
