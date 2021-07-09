package Basic.Collections;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author: chenjunlin
 * @since: 2021/05/25
 * @descripe:
 */
public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<String> strings=new TreeSet<>();
        strings.add("chen2222");
        strings.add("lin");
        strings.add("jun");
        System.out.println(strings);
        NavigableSet<String> sort=new TreeSet<>(
                Comparator.comparing(s->s.length()<4)
        );
        sort.addAll(strings);
        System.out.println(sort);
    }
}
