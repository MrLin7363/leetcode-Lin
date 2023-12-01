package A1000PLAN.排序.二分.边界类;

/**
 *desc:
 *@author lin
 *@since 2023/11/27
 **/
public class P275H指数II {
    // 左闭右开:搜索右边界 - 推荐
    public int hIndex3(int[] citations) {
        int n = citations.length;
        int left = 1;
        int right = n + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (citations[ n - mid] >= mid) {
                left = mid + 1;  // mid确定，往右搜索[mid,right)
            } else {
                right = mid;
            }
        }
        return right - 1;
    }

    /*
    O logn  左闭右闭-搜索右边界
     [0,n]  其实0可以不管，因为0篇论文的引用次数>=0  从[1,n]开始
    这里比较特殊，n是右边的闭区间，因为论文数从1开始
    https://leetcode.cn/problems/h-index-ii/solutions/2504326/tu-jie-yi-tu-zhang-wo-er-fen-da-an-si-ch-d15k/
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 1;
        int right = n;
        while (left <= right) {
            // 偶数偏左偏右不影响
            int mid = (right + left + 1) / 2;
            // 当前位置合法,往右区间搜索
            // 比如下标是0,也就是有一篇论文>=ci[0]就行;
            if (citations[n - mid] >= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 因为找到右边界时一定是 left=mid+1
        return right;
    }


    public static void main(String[] args) {
        new P275H指数II().hIndex(new int[] {0});
        new P275H指数II().hIndex3(new int[] {0, 1, 3, 5, 6});
    }

    /*
    O n
     */
    public int hIndex2(int[] citations) {
        int h = 0;
        int i = citations.length - 1;
        // 找到了一篇被引用了至少 h+1次的论文
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
