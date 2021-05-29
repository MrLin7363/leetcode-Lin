package Basic.Lambda;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 13:57
 * @Describe:
 */
public class City {
    private String state;
    private Integer population;

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
