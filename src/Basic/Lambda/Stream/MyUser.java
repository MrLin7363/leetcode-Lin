package Basic.Lambda.Stream;

/**
 * @author: chenjunlin
 * @since: 2021/05/24
 * @descripe:
 */
public class MyUser {

    private String name;
    private Integer age;
    private String desc;

    public MyUser(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MyUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
