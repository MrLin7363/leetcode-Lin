package Basic.泛型;


import java.io.Serializable;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/23 13:11
 * @Describe:
 */
public class ArrayAlg {
    // 返回类型为T  的泛型方法，并且T 实现了Comparable Serializable  做比较用   约束传入的参数
    public static <T extends Comparable & Serializable> T getMin(T...a){
        return a[a.length/2];
    }

    public static void main(String[] args) {
        String middle = ArrayAlg.getMin("]ohnM", "Qs", "Public");
        System.out.println(middle);
    }

    /*
    // 第一个T是方法参数限定，表示（）内的T必须是T类型的
// 第二个T是方法的返回类型
// 第三个T和第一个T对应
public static <T extends Comparable & Serializable> T getMin(T...a){
     */
}
