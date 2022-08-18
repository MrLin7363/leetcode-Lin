package hw.基础学习.基础特性;

import java.util.HashSet;

public class Email {
    public String address;
    public Email(String address) {
        this.address = address;
    }
    public int hashCode() {
        int result = address.hashCode();
        return result;
    }
    public static void main(String[] args) {
        HashSet<Email> set = new HashSet<>();
        Email email = new Email("huawei.com");
        set.add(email);
        email.address = "silong.com"; // 修改地址值，导致hashcode值变化
        System.out.println(set.contains(email)); // false
        set.remove(email); // 泄漏，不报错
    }
}
