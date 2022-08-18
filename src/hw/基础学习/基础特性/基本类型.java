package hw.基础学习.基础特性;

import java.util.Hashtable;

public class 基本类型 {

    public static void main(String[] args) {
        String st1 = "123";
        String s2 = st1 + 132;

        char[] sss=new char[3];
        sss[0]='\u1112';
        System.out.println(sss[0]);

        String str="\1238";
        // \123 代表 'S'
        System.out.println(str);
        String str2="\11212";
        // \000 代表 'J'
        System.out.println(str2);
        String str3="\u1534143";
        // ሴ 代表 'ሴ'
        System.out.println(str3);
        char ch=10;
        // 原类型上相加，没有new
        ch+=ch;
        // 类型提升至int  编译错误
        // ch=ch+ch;

        byte bb=-128;
        Byte vbb=127;
        float t=101231.0f;
        double dd=123212.1023123123111111111;

        long l1,l2,l3;
        l1=10;
        l2=1;//l2=0会有运行异常
        l3=l1%l2;


        int i=1;
        i+=1.8;
        System.out.println(i);// 2 向下取整

        char ccc='c';
        System.out.println(ccc+1);

        float ff=1;
        // float ff123=1.2;  报错，不加f默认为double，损失精度
        Float wqe=12f; // 引用类型要加后缀
        double ddd=2;
        Double asd=2.0;
        Double qweqw=2d; // 如果不是小数类型且是int范围内，引用类型必须加d
//        Double asd=2;
        long l=2341;
        Long ll=123l;
        Boolean bl=null; // 引用类型可以为null，基本类型不可

        Double bbbbb=10.287;
        Float sf=1f;
        float df=1;


        // 损失精度的转型，全都向0靠拢
        double fst = 1.4d;
        double snd = 0.99d;
        double trd = -1.99d;
        System.out.println((int)fst);//1
        System.out.println((int)snd);//0
        System.out.println((int)trd);//-1
        System.out.println((float)trd);//-1.99   1.99还是在float范围内，不损失精度
        float f=123.2f;
        float ff1=-0.1f;
        System.out.println((int)f);//123
        System.out.println((int)ff1);//0

        char x='他';
        float v = x + 1.2f;
        double v1 = x + 1.2;
        int i1 = x + 1;
        int ii=1;
        double v2 = ii + 1.1;
        System.out.println(ii+1.1);

//        Short ss=null;
//        switch (ss){} // 报错

    }

}
