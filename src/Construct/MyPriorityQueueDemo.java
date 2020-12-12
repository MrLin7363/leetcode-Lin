package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/12
  *@Describe: 优先队列示例
  参考 P 23 题
 */

import java.util.PriorityQueue;

public class MyPriorityQueueDemo {
    static class Person implements Comparable<Person>{
        public int age;
        Person(int age){
            this.age=age;
        }
        public int compareTo(Person other){
            return age-other.age;
        }
    }
    public static void main (String[] args) {
        // 会去比较Person 里的compareTo方法，取出队列中最小的
        PriorityQueue<Person> q=new PriorityQueue<Person>();
        Person a=new Person(10);
        Person b=new Person(20);
        Person c=new Person(5);
        q.offer(a);
        q.offer(c);
        q.offer(b);
        System.out.println(q.peek().age);
    }

}
