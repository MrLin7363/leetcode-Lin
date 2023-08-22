package A1000PLAN.贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/16
 **/
public class P406根据身高重建队列 {
    /*
    输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
解释：
编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
  */
    /*
     1.根据身高升序，相同情况，根据前面几个人降序
     2.使用队列记录进队，如果前面有几个高的，就需要在原有队列腾出几个空值，而队列里的元素不用动，因为一定比后面进队的矮
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        int n = people.length;
        // 这里不初始化int[] 可以判断ans[i]==null
        int[][] ans = new int[n][];

        for (int[] peo : people) {
            int space = peo[1] + 1;
            for (int i = 0; i < n; i++) {
                // 找到空位置腾出来
                if (ans[i] == null) {
                    space--;
                    // 腾完位置，插入
                    if (space == 0) {
                        ans[i] = peo;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new P406根据身高重建队列().reconstructQueue2(new int[][] {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
    }

    /*
    身高降序，相同身高前面几个升序
    队列不断插入， 如果前面有4个高的，就找到队列第5个位置插入，如果队列没有4个，就插入在队列末尾
    因为后面进来都是矮的，队列插在哪里不影响 前面有几个比当前高的数值
     */
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person : people) {
            // 这里插入中间，后面的元素自动后移
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
