package Basic.Lambda.Stream;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 13:57
 * @Describe:
 */
public class City {
    private String state;
    private Integer population;
    private List<MyUser> userList; // 城市下的人

    public City(Integer population,List<MyUser> userList) {
        this.userList=userList;
        this.population=population;
    }
    public List<MyUser> getUserList() {
        return userList;
    }

    public void setUserList(List<MyUser> userList) {
        this.userList = userList;
    }

    public City(String state, Integer population) {
        this.state = state;
        this.population = population;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
