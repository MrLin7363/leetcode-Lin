package hw.基础学习.基础特性;

public class StringTest {
    public static void main(String[] args) {
        /*
        多个String类型相加，如果是常量相加，编译完就会优化出最佳结果，如d，如果是变量，就会创建Stringbuilder对象拼接
         */
        String s1 = "a";
        String s2 = s1 + "b"; // 对象
        String s3 = "a" + "b";
        System.out.println(s2 == "ab"); // false
        System.out.println(s3 == "ab"); // true

        String s = "a" + "b" + "c" + "d";
        System.out.println(s == "abcd"); // true

        String s4 = "a";
        String s5 = "a".intern();
        System.out.println(s1 == s4); // t
        System.out.println(s4 == s5); // t

        String a = "1";
        String b = "2";
        String c = a + b; // String c = (new StringBulider()).append(a).append(b).toString();  对象
        String d = "1" + "2";
        String f = "12";

        System.out.println(c == d); // false
        System.out.println(f == d); // true
        System.out.println(c == f); // false

        String e = "123" + 456; // int + string -> string
        String r = "123" + 2147483647; // int + string -> string
        String t = "123" + 456.011d; // double + string -> string
        String zz = "123" + 456.011f; // float + string -> string
        System.out.println(r.getClass());
        System.out.println(r);
        System.out.println(t.getClass());
        System.out.println(t);
        System.out.println(zz);
        System.out.println(Integer.MAX_VALUE);

        outer:
        while (true) {
            break outer;
        }

        String str = "HelloWorld";
        String str1 = new StringBuilder("Hello").append("World").toString();
        System.out.println(str1 == str1.intern()); // false   str1是对象，str1.intern是常量池对象
        System.out.println(str == str1.intern()); // true   str1去常量池找到str相等

        String str2 = new StringBuilder("Hello").append("World").toString();
        System.out.println(str2 == str2.intern()); // false   str2是对象，str2.intern是常量池对象
        System.out.println(str == str2.intern()); // true


        System.out.println('a'); // a
        System.out.println('a' + 1); // 98
        System.out.println("hello" + 'a' + 1); // helloa1
        System.out.println('a' + 1 + "hello"); // 98hello
        System.out.println("1 + 1 = " + 1 + 1); // 1 + 1 = 11
        System.out.println(1 + 1 + "= 1 + 1"); // 2= 1 + 1


        String ss="a$b$c";
        String[] $s = ss.split("$");
        System.out.println(ss.split("$"));
    }
}
