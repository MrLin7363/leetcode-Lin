package Basic.Lambda.Stream;


/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 13:35
 * @Describe:
 */
public class People {
    private Integer id;
    private String name;

    public People(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
