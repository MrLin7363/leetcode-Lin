package Basic.Lambda.Stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: chenjunlin
 * @since: 2021/05/24
 * @descripe:
 */
public class StreamObjectTest {
    public static void main(String[] args) {
        Map<String,String> nameDescMap = new HashMap<>();
        MyUser myUser1=new MyUser("chen",18,"chen1");
        MyUser myUser3=new MyUser("zhaoyun",14,"chen2");
        MyUser myUser4=new MyUser("chen",14,"chen3");
        MyUser myUser5=new MyUser("linchong",15,"jun2");
        MyUser myUser6=new MyUser("zhaoyun",16,"lin3");
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

        // 统计年龄List,distinct只能用于单属性的情况
        List<Integer> ageList = users.stream().map(MyUser::getAge).distinct().collect(Collectors.toList());
        ageList.forEach(age-> System.out.println(age));

        List<City> cityList=Arrays.asList(new City(100000,users),new City(2000,users));
        // flatMap 把两个城市下所有用户统计成一个流
        List<MyUser> userList = cityList.stream().flatMap(city->city.getUserList().stream()).collect(Collectors.toList());
        userList.forEach(user-> System.out.println(user));
        // flatMap 把两个城市下所有用户数量统计成一个流
        int sum = cityList.stream().flatMapToInt(city -> IntStream.of(city.getPopulation())).sum();
        System.out.println(sum);
        // 统计每个城市下的人口数量List
        List<Integer> populationList = cityList.stream().flatMap(city -> Stream.of(city.getPopulation())).collect(Collectors.toList());
        populationList.forEach(po-> System.out.println(po));


    }
}
