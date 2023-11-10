

package A1000PLAN.深广遍历.图;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/11
 **/
public class P733图像渲染 {
    // 上右下左
    static int[] row = new int[] {-1, 0, 1, 0};

    static int[] clo = new int[] {0, 1, 0, -1};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int origin = image[sr][sc];
        dfs(image, sr, sc, color, origin);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int color, int origin) {
        if (i < 0 || j < 0 || i > image.length - 1 || j > image[0].length - 1 || image[i][j] != origin) {
            return;
        }
        // 已经访问过
        if (image[i][j] == color) {
            return;
        }
        image[i][j] = color;
        for (int k = 0; k < 4; k++) {
            int sr = i + row[k];
            int sc = j + clo[k];
            dfs(image, sr, sc, color, origin);
        }
    }
}
