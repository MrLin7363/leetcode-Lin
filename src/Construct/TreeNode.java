package Construct;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/30 20:12
 * @Describe:
 */
public class TreeNode  {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}