package Basic.Effective;

import java.util.ArrayList;


/**
 * @author: chenjunlin
 * @since: 2021/06/10
 * @descripe:
 */
public class MyException {

    public static void main(String[] args) {
        try {
            try {
                ArrayList list = null;
                Object o = new Object();
                list.equals(o);
            } catch (java.lang.Exception e) {
                System.out.println("asd");
                throw e;
            }
        } catch (java.lang.Exception e) {
            System.out.println("12w");
//            throw e;
        }
    }

}
