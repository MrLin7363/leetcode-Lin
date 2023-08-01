package Array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ArrayMain {
    public static void main(String[] args) {

    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayDeque<>(), n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> path, int n, int k, int begin) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.offerLast(i);
            dfs(res, path, n, k, i + 1);
            path.pollLast();
        }
    }
}
