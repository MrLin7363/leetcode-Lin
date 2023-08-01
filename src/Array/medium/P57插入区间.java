package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/8
  *@Describe: 插入区间
 */

import java.util.ArrayList;
import java.util.List;

public class P57插入区间 {
    /*
    36 + 99
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left=newInterval[0];
        int right=newInterval[1];
        boolean isFirst=false;
        List<int[]> ans=new ArrayList<>();
        for (int[] interval:intervals){
            // 当前区间在插入区间的右侧且无交集
            if (interval[0]>right){
                // 插入区间在第一个
                if (!isFirst){
                    ans.add(new int[]{left,right});
                    isFirst=true;
                }
                ans.add(interval);
            }else if (interval[1]<left){ // 当前区间在插入区间的左侧且无交集
                ans.add(interval);
            }else{
                // 与插入区间有交集，计算它们的并集
                left=Math.min(interval[0],left);
                right=Math.max(interval[1],right);
            }
        }
        if (!isFirst){
            ans.add(new int[]{left, right});
        }
        return ans.toArray(new int[ans.size()][2]);
        // 上面一行可以代替下面的全部
      /*  int[][] res=new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            res[i]=ans.get(i);
        }
        return res;*/
    }
}
