package A1000PLAN.深广遍历.图.bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 *desc:
 *@author lin
 *@since 2023/11/9
 **/
public class P433最小基因变化 {
    /*
    1.每个字符种类共 3*8=24中，每一种都用广度遍历去尝试
    bank->hashset
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(endGene)) {
            return -1;
        }
        char[] gene = new char[] {'A', 'C', 'G', 'T'};
        int step = 1;
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(startGene);
        visited.add(startGene);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (poll.charAt(j) != gene[k]) {
                            StringBuilder sb = new StringBuilder(poll);
                            sb.setCharAt(j, gene[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && set.contains(next)) {
                                if (next.equals(endGene)) {
                                    return step;
                                }
                                queue.add(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int i = new P433最小基因变化().minMutation("AACCGGTT", "AAACGGTA",
            new String[] {"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        System.out.println(i);
    }
}
