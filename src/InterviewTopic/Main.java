package InterviewTopic;

import java.util.*;

/**
 * @author: Junlin Chen
 * @Date: 2021/03/15 23:09
 * @Describe:
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = sc.nextInt();
        int n=sc.nextInt();
        int[][] mat=new int[n][2];
        for(int i = 0; i < n; i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            mat[i][0]=x;
            mat[i][1]=y;
        }
        int z=sc.nextInt();
        System.out.println(test(z,mat));
    }
    static HashSet<Integer> set=new HashSet<>();
    public static int test(int index,int[][] mat){
            ArrayDeque<Integer> list=new ArrayDeque<>();
            list.add(index);
            int level=0;
            set.add(index);
            while (!list.isEmpty()){
                int dd=list.pollLast();
                if (list.size()==0){
                    level++;
                }
                for (int j = 0; j < mat.length; j++) {
                    if (mat[j][0] == dd&&!set.contains(mat[j][1])) {
                        set.add(mat[j][1]);
                        list.add(mat[j][1]);
                    }
                }

            }
        return level*2;
    }
}
