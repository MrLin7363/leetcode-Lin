package hw.基础学习.基础特性;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FloatTest {
    public static void main(String[] args) {
        for (float flt = (float) 1000000000; flt < 1000000010; flt++) {
            System.out.println(flt); // 代码测试flt不比 1000000010 小
        }

        // 静止使用浮点数 ==  equals
        Float f1=1.2f;
        float f2=1.3f-0.1f; // 浮点数加减后=1.1999999丢失精度
        System.out.println(f2);//
        Float f3=Float.valueOf(f2);
        System.out.println(f1==f2); // false  如果f2=1.2f 都是true
        System.out.println(f1.equals(f2)); // false
        System.out.println(f1.equals(f3)); // false
        double naN = Double.NaN;// 禁止尝试与NaN进行比较运算，相等操作使用Double或Float的 isNaN() 方法
        float naN1 = Float.NaN;

        int i = Integer.parseInt("2");
        float flt2 = (float) 1000000000;
        System.out.println(flt2);
        flt2++;
        System.out.println(flt2);
        System.out.println(flt2 < 1000000010);
        // 输出不确定，超过了7位有效数字   判定这两个值100=10010相等 所以循环体不执行
        for (float flt = (float) 1000000000; flt < 1000000010; flt++) {
            System.out.println(flt);
        }
        // 7位可以输出，精度保证
//        for (float flt = (float) 123456789; flt < 1234567891; flt++) {
//            System.out.println(flt);
//        }
        double dou=12345678;
        System.out.println(++dou);
//        for (double dou = (double) 12345678; dou < 1234567891; dou++) {
//            System.out.println(dou);
//        }
        float a = 1234567f; // 保留7位，超过7位加e算精度损失

        double b=0.12345678901234567890; // 保留16位

        float c=0.12345678901234567890f; // 保留7位

        System.out.println(a);

        System.out.println(b);

        System.out.println(c);


        try {
            String str = "ABCDE";
            byte[] bs = str.getBytes(StandardCharsets.UTF_8);
            FileOutputStream file = new FileOutputStream("test.txt", true);
            file.write(bs);
            file.close();
        } catch(IOException e) {
            System.out.println(e.toString());
        }


    }
}
