 

package hw.基础学习;

import java.util.HashSet;
import java.util.Set;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/4
 **/
public class HashCodeStudy {

    private static class Person {

        private String name;

        public void setName(String name) {
            this.name = name;
        }
        /*
        用于集合中比较,如果加上这个方法，那么name相同的两个hashcode相同
         */
        public int hashCode() {
            int i = this.name.hashCode();
            System.out.println(i);
            return i;
        }

        /*
        这里重写了equals方法，里面用到了这个类的属性，如果hashcode()不重写，可能两个name相同的类添加进了hashset里，
        但是equals方法却比较相同
         */
        public boolean equals(Object obj) {
            if (obj instanceof HashCodeStudy.Person) {
                HashCodeStudy.Person temp = (HashCodeStudy.Person) obj;
                return this.name.equals(temp.name);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        HashCodeStudy.Person person = new Person();
        person.setName("person");
        Set<HashCodeStudy.Person> hashSet = new HashSet<>();
        hashSet.add(person);
        HashCodeStudy.Person person2 = new Person();
        person2.setName("person");
        System.out.println(person.equals(person2));
        if (hashSet.contains(person2)) {
            System.out.println("person2");
        }else{
            hashSet.add(person2);
        }
        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());

        System.out.println(new HashCodeStudy.Car().hashCode());
        System.out.println(new HashCodeStudy.Car().hashCode());

    }

    private static class Car {

        private String name;

        public boolean equals(Object obj) {
            if (obj instanceof HashCodeStudy.Car) {
                HashCodeStudy.Car temp = (HashCodeStudy.Car) obj;
                return this.name.equals(temp.name);
            }
            return false;
        }
    }

}
