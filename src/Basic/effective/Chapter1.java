package Basic.effective;

import java.util.ArrayList;


/**
 * @author: chenjunlin
 * @since: 2021/06/10
 * @descripe:
 */
public class Chapter1 {

    public static void main(String[] args) {
        try {
            try {
                ArrayList list = null;
                Object o = new Object();
                list.equals(o);
            } catch (Exception e) {
                System.out.println("asd");
                throw e;
            }
        } catch (Exception e) {
            System.out.println("12w");
//            throw e;
        }
    }

}
