package wangzheng;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/02 15:55
 * @Describe: 组合输出
 */
public class Combination {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] array = new String[]{
                "1", "2", "3", "4"
        };
        listAll(Arrays.asList(array),"");

    }

    public static void listAll(List<String> candidate, String prefix){
        System.out.println(prefix);

        for(int i=0; i<candidate.size(); i++){
            List<String> temp = new LinkedList<String>(candidate);//new LinkedList<String>(candidate)---copy candidate
            listAll(temp, prefix+temp.remove(i));
        }
    }
}
