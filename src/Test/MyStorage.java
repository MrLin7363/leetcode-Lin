package Test;

import java.util.ArrayList;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/22 18:13
 * @Describe:
 */
public class MyStorage {

    public static ArrayList<Integer> myOrders=new ArrayList<>();

    public void push(Integer orderNumber){
        myOrders.add(orderNumber);
    }

    public Integer pop(){
        Integer o=myOrders.get(myOrders.size()-1);
        myOrders.remove(o);
        System.out.println(o);
        return o;
    }


}
