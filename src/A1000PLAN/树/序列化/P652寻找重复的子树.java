package A1000PLAN.树.序列化;

import Construct.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/19
 **/
public class P652寻找重复的子树 {

    /*
    2. On^2 和第1中是一样的
    */
    private Map<String, TreeNode> seen = new HashMap<>();

    private Set<TreeNode> repeat = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        recur(root);
        return new ArrayList<>(repeat);
    }

    private String recur(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        sb.append(recur(node.left));
        sb.append(")(");
        sb.append(recur(node.right));
        sb.append(")");
        String serial = sb.toString();
        if (seen.containsKey(serial)) {
            // 注意易错：这里要返回seen中的，因为先递归，如果先add(node),回溯第二次找到结点的时候又重复添加了相同结构的树的根节点
            // add seen.get 相当于回溯过程中找之前是否有重复的，就不会重复添加了
            repeat.add(seen.get(serial));
            // repeat.add(node);
        } else {
            seen.put(serial, node);
        }
        return serial;
    }


    /*
    3. 最优版：三元组优化
    对于每一个不同的子树，在DFS时可以生成唯一的序号idx；
判断子树是否重复：因为自底向上遍历，所以对于每个节点，可以获得其左右子树对应的唯一序号idx，这个节点的val和其左右子树的序号idx
可以生成字符串标志flag"root_val,left_idx,right_idx",如果字符串在哈希Map中出现过，就说明当前子树重复；
如果字符串没在哈希Map中出现过，就将<字符串，未使用过的idx>存入哈希Map。递归返回值：当前节点对应二叉树的唯一序号idx
注意:如果dfs不返回idx而是返回当前子树字符串标志flag，而每个子树flag就从"root_val,left_idx,right_idx"变成了"root_val,left_flag,right_flag",
这样就是官方题解的方法1，每个字符串标志就是当前二叉树的序列化。第n个节点生成字符串的时间从O(1)变成O(n),那么总时间复杂度就变成了O(n2)O(n^2)O(n
     */
    private Map<String, Pair<TreeNode, Integer>> hashMap = new HashMap<>();

    private Set<TreeNode> repeats = new HashSet<>();

    private int idx = 0;

    public List<TreeNode> findDuplicateSubtrees3(TreeNode root) {
        recursion(root);
        return new ArrayList<>(repeats);
    }

    private int recursion(TreeNode node) {
        // 空结点编号为0
        if (node == null) {
            return 0;
        }
        int[] tri = new int[] {node.val, recursion(node.left), recursion(node.right)};
        String hash = Arrays.toString(tri);
        if (hashMap.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = hashMap.get(hash);
            // 因为自底向上，所以这里要记录之前就出现的根节点，否则会add两次
            repeats.add(pair.getKey());
            return pair.getValue();
        } else {
            hashMap.put(hash, new Pair<>(node, ++idx));
            return idx;
        }
    }

    /*
    1. On^2 推荐好记  序列化：根左右  返回列表，单个的，多个结点的根
     */
    private Map<String, Integer> map = new HashMap<>();

    private List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append("(");
        sb.append(dfs(node.left));
        sb.append(")(");
        sb.append(dfs(node.right));
        sb.append(")");
        String serial = sb.toString();
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        // 如果第3次重复则不再加入
        if (map.get(serial) == 2) {
            ans.add(node);
        }
        return serial;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);
        new P652寻找重复的子树().findDuplicateSubtrees2(root);
    }
}
