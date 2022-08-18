 

package hw.基础学习;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/22
 **/
public class LazyStudy {

    private static final LazyStudy helper = new LazyStudy();

    static {
        System.out.println("static");
    }

    public static LazyStudy getHelper() {
        return helper;
    }

    private static class Holder {
        static HashCodeStudy helper = new HashCodeStudy();
    }

    public static HashCodeStudy getInstance() {
        return Holder.helper;
    }

    public static void main(String[] args) {
        LazyStudy lazyStudy = new LazyStudy();
        System.out.println("Asd");
    }

}
