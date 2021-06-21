package Basic.Enum;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Junlin Chen
 * @Date: 2021/06/12 18:38
 * @Describe: EnumMap EnumSet
 */
public class Herb {
    public enum Type {ANNUAL, PER,BIE};

    private final String name;
    private final Type type;

    public Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    public String toString(){
        return name;
    }
    /*
    有一个花园，里面有这么多的植物，根据 枚举植物的类型 排好序输出  不用ordinal方法
     */
    public static void main(String[] args) {
        Herb[] garden = new Herb[5];
        garden[0] = new Herb("水仙",Herb.Type.ANNUAL);
        garden[1] = new Herb("玫瑰",Herb.Type.PER);
        garden[2] = new Herb("玫瑰",Herb.Type.PER);
        garden[3] = garden[2];
        garden[4] = new Herb("白盒",Herb.Type.BIE);
        Map<Type, Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);
        for (Herb.Type t:Herb.Type.values()){
            herbsByType.put(t,new HashSet<Herb>());
        }
        for (Herb b :garden){
            herbsByType.get(b.type).add(b); // Set判断对象插入
        }
        System.out.println(herbsByType);
    }
}
