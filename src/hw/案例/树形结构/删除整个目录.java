 

package hw.案例.树形结构;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/17
 **/
public class 删除整个目录 {

    /*
    思路：
    1.构建树
    2.DFS删除树，是先删除完子目录，再删除根目录
     */
    private static String delAllDirectorys(String[] dirTreeLines) {
        Node node = buildTree(dirTreeLines);
        StringBuilder dfs = dfs(new StringBuilder(), node);
        return dfs.toString();
    }

    private static StringBuilder dfs(StringBuilder res, Node node) {
        for (Node child : node.children) {
            dfs(res, child);
        }
        return res.append(node.name);
    }

    private static String dfsDelete(String result,Node node) {
        String tmpResult = result;
        for (Node child : node.children) {
            tmpResult = dfsDelete( tmpResult,child);
        }
        tmpResult = tmpResult + node.name;
        return tmpResult;
    }

    private static Node buildTree(String[] dirTreeLines) {
        Map<Integer, List<Node>> map = new HashMap<>();
        for (int i = 0; i < dirTreeLines.length; i++) {
            String dirTree = dirTreeLines[i];
            // 根目录
            if (!dirTree.contains("|-")) {
                Node node = new Node(0, dirTree);
                List<Node> nodeList = map.getOrDefault(0, new ArrayList<>());
                nodeList.add(node);
                map.put(0, nodeList);
                continue;
            }

            // 子目录，需要添加 父子节点关系
            int level = (dirTree.lastIndexOf("|-")) / 2 + 1;
            // 有父节点且父节点不为空
            if (map.get(level - 1) != null && map.get(level - 1).size() > 0) {
                Node node = new Node(level, dirTree);
                List<Node> nodeList = map.getOrDefault(level, new ArrayList<>());
                // 如果当前层级已有该节点则跳过
                if (nodeList.contains(node)) {
                    continue;
                }
                // 添加当前节点
                nodeList.add(node);
                map.put(level, nodeList);
                // 添加关系
                map.get(level - 1).get(map.get(level - 1).size() - 1).children.add(node);
            }
        }
        return map.get(0).get(0);
    }

    public static void main(String[] args) {
        String[] res = new String[10];
        res[0] = "|-B";
        res[1] = "A";
        res[2] = "|-B";
        res[3] = "|-|-C";
        res[4] = "|-|-D";
        res[5] = "|-|-D";
        res[6] = "|-|-|-|-D";
        res[7] = "|-|-E";
        res[8] = "|-|-|-F";
        res[9] = "|-lib32";
        System.out.println(delAllDirectorys(res));
        String dirTree = "|-D";
        System.out.println(dirTree.lastIndexOf("|-") / 2 + 1);
        System.out.println(dirTree.indexOf("|-"));
        System.out.println(dirTree.lastIndexOf("|-"));
        System.out.println((int) -1 - (int) -1);
        System.out.println((dirTree.lastIndexOf("|-") - dirTree.indexOf("|-")) / 2);
    }

    private static class Node {
        private int level;

        private String name;

        private List<Node> children = new ArrayList<>();

        public Node(int level, String name) {
            this.level = level;
            int index = name.lastIndexOf("|-");
            if (index == -1) {
                this.name = name;
            } else {
                this.name = name.substring(index + 2);
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.level, this.name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Node node = (Node) obj;
            return this.level == node.level && this.name.equals(node.name);
        }
    }
}
