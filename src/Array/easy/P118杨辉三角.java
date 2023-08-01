package Array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/02 23:43
 * @Describe:
 */
public class P118杨辉三角 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList<>(numRows);
        if (numRows<1){
            return result;
        }
        List<Integer> first=new ArrayList<>(1);
        first.add(1);
        result.add(first);
        // 两次遍历
        for (int i = 2; i <= numRows; i++) {
            // 新建一行
            List<Integer> list=new ArrayList<>(i);
            list.add(1);// 开头元素
            // 取上一行
            List<Integer> preList=result.get(i-2);
            for (int j = 1; j < i-1; j++) {
                Integer value=preList.get(j)+preList.get(j-1);
                list.add(value);
            }
            list.add(1); // 结尾元素
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        generate(2);
    }
}
