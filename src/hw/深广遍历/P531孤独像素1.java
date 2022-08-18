package hw.深广遍历;

/**
 * desc: 第2不用做，烂题
 * 这题太简单
 *
 * @author junlin
 * @since 2022/2/7
 **/
public class P531孤独像素1 {

    public int findLonelyPixel(char[][] picture) {
        int[] rows = new int[picture.length];
        int[] cols = new int[picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

}
