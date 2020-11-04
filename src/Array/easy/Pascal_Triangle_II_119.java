package Array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/04 19:07
 * @Describe: 与上一题区别只是不用记住每一层的节点
 */
public class Pascal_Triangle_II_119 {
    /*
    效率最高，下面可以不看
     */
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> pre =new ArrayList<>();
        List<Integer> cur =new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j==0||j==i){ // 第一或者最后
                    cur.add(1);
                }else {
                    cur.add(pre.get(j)+pre.get(j-1));
                }
            }
                pre=cur;
        }
        return cur;
    }


        public List<Integer> getRow2(int numRows) {
        List<Integer> pre =new ArrayList<>();
        List<Integer> cur =new ArrayList<>();
        cur.add(1);
        if (numRows<1){
            return cur;
        }
        // 两次遍历
        for (int i = 2; i <= numRows+1; i++) {
            cur=new ArrayList<>();
            cur.add(1);// 开头元素
            // 取上一行
            for (int j = 1; j < i-1; j++) {
                Integer value=pre.get(j)+pre.get(j-1);
                cur.add(value);
            }
            cur.add(1); // 结尾元素
            pre=cur;
        }
        return cur;
    }
    public List<Integer> getRow(int numRows) {
        List<List<Integer>> result=new ArrayList<>(numRows);
        List<Integer> first=new ArrayList<>(1);
        first.add(1);
        result.add(first);
        if (numRows<1){
            return result.get(numRows);
        }
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
        return result.get(numRows-1);
    }
}
