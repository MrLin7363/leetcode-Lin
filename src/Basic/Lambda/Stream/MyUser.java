package Basic.Lambda.Stream;

/**
 * @author: chenjunlin
 * @since: 2021/05/24
 * @descripe:
 */
public class MyUser {

    private String name;
    private Integer count;
    private String desc;

    public MyUser(String name, Integer count, String desc) {
        this.name = name;
        this.count = count;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MyUser(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
