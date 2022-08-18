 

package hw.案例.树形结构;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/17
 **/
public class 文件树 {
    /**
     * 全路径排序  方法
     */
    public static List<String> getTreeFormat(List<String> stringList) {
        // 路径排好序
        stringList.sort(String::compareTo);
        // 分隔每一个路径，二维数组记录
        String[][] splits = new String[stringList.size()][];
        for (int i = 0; i < stringList.size(); i++) {
            splits[i]=stringList.get(i).split("/");
        }
        List<String> result = new ArrayList<>();
        for (String[] arr:splits){
            int blank=0;
            for (int i = 0; i < arr.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < blank; j++) {
                    sb.append(" ");
                }
                blank++;
                sb.append(arr[i]);
                // 注意这里 要 toString不然就是String 和 Stringbuild比较
                if (!result.contains(sb.toString())){
                    result.add(sb.toString());
                }
            }
        }
        result.forEach(System.out::println);
        return result;
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        result.add("usr");
        StringBuilder stringBuilder = new StringBuilder("usr");
        System.out.println(result.contains(stringBuilder));
        String ss= "usr/";
        String[] split = ss.split("/");
        getTreeFormat(Arrays.asList("usr/uu/qwd","etc/","root","usr"));
    }
}
