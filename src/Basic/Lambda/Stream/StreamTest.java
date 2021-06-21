package Basic.Lambda.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: chenjunlin
 * @since: 2021/05/24
 * @descripe:
 */
public class StreamTest {
    public static void main(String[] args) {
        Map<String,String> nameDescMap = new HashMap<>();
        MyUser myUser1=new MyUser("chen",1,"chen1");
        MyUser myUser3=new MyUser("chen",1,"chen2");
        MyUser myUser4=new MyUser("chen",1,"chen3");
        MyUser myUser5=new MyUser("jun",1,"jun2");
        MyUser myUser6=new MyUser("lin",1,"lin3");
        List<MyUser> users=new ArrayList<>();
        users.add(myUser1);
        users.add(myUser4);
        users.add(myUser3);
        users.add(myUser5);
        users.add(myUser6);
        // 这个方法的取key,key不能有重复的，如果有报错
//        nameDescMap = users.stream().collect(Collectors.toMap(MyUser::getName,
//                MyUser::getDesc));
        // 这个会取第一个key作map，如果后面有重复的，则跳过
        nameDescMap = users.stream().collect(Collectors.toMap(MyUser::getName,
                MyUser::getDesc,(k,v)-> k
                ));
        nameDescMap.forEach( (k,v)-> System.out.println("key="+k+"  &&value="+v));
    }
}
