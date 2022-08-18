package hw.基础学习.基础特性;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayTest {
    public static void main(String[] args) {
        //         String[]s=new Object[0]; 编译错
        // 数组可以转
        Object[] o = new String[2];
        Number[] n = new Integer[2];
        Object[] ss = new Object[0];
        //         Number[]n2=new int[2]; 编译错

        // 集合不可以转，因为有泛型
        List<Integer> iList = new ArrayList<>();
        List<Number> nList = new ArrayList<>();
        //        nList=iList; 编译错
        //        iList=nList; 编译错

        long l = 1;
        int i = 1;
        //        int ii=l+i; 编译错
        long ll = l + i;
        String sss = null;
        switch (sss) { // 运行异常

        }
    }
}
