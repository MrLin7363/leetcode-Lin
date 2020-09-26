package Test;


/**
 * @author: Junlin Chen
 * @Date: 2020/06/22 18:03
 * @Describe:
 */
public class MyService {

     public MyStorage myStorage=new MyStorage();

     public void addOrder(Integer integer){
         myStorage.push(integer);
     }
     public void getOrder(){
         myStorage.pop();
     }

    public static void main(String[] args) {
        MyService myService=new MyService();
        myService.addOrder(1);
        myService.addOrder(2);
        myService.addOrder(3);
        myService.getOrder();
        myService.getOrder();
        myService.getOrder();
    }
}
