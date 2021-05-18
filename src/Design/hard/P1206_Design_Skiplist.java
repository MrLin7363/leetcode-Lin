package Design.hard;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/13
  *@Describe: 跳表
  随机函数的算法可以重写按redis的做还没有试
 */

import java.util.Random;

public class P1206_Design_Skiplist {
    /*
    的实现 75+68
     */
    static class Skiplist {
        class Node {
            int val;
            Node right,down;

            public Node(Node r, Node d, int val) {
                right = r;
                down = d;
                this.val = val;
            }
        }
        public Skiplist() {
            this.head=new Node(null, null, 0);
            this.rand = new Random();
            this.maxLevel=64;
            nodeList=new Node[maxLevel];
        }
        /*
        头结点默认为0值的结点,头结点之间通过down连接
        每一行都有一个头结点，类似于前驱哨兵结点
         */
        Node head;
        Random rand;
        int maxLevel; // 最大层数
        Node[] nodeList;// 添加结点时，路径的记录
        public boolean search(int target) {
            // 先往右再往下，缩小区间
            for (Node p=head;p!=null;p=p.down) {
                while (p.right!=null&&p.right.val<target){
                    p=p.right;
                }
                if (p.right!=null&&p.right.val==target){
                    return true;
                }
            }
            return false;
        }

        public void add(int num) {
            int lv = -1;
            // 遍历每一层的哨兵头结点
            for (Node p = head; p != null; p = p.down) {
                while (p.right != null && p.right.val < num) {
                    p = p.right;
                }
                // 记录每次向下进行的路径结点
                nodeList[++lv] = p;
            }
            boolean insertUp = true;
            Node downNode = null;
            // lv-- 每次向上一层
            while (insertUp && lv >= 0) {
                Node insert = nodeList[lv--]; // 新添加结点前一个结点
                insert.right = new Node(insert.right, downNode, num);
                downNode = insert.right; // downNode就是新添加的结点
                insertUp = (rand.nextInt() & 1) == 0; // 按一定的几率是否继续向上添加一行
            }
            // 是否在当前已有行的最上面再增加一行，最上面一行哨兵结点就是头结点
            // 最后往上增加链表层数
            if (insertUp) {
                head = new Node(new Node(null, downNode, num), head, 0);
            }
        }
        /*
        在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可
         */
        public boolean erase(int num) {
            boolean exists = false;
            // 遍历每一层找到相同值的结点，只删除一个
            for (Node p = head; p != null; p = p.down) {
                while (p.right != null && p.right.val < num) {
                    p = p.right;
                }
                if (p.right != null && p.right.val == num) {
                    exists = true;
                    p.right = p.right.right;
                }
            }
            return exists;
        }
    }


    public static void main(String[] args) {
        Skiplist skiplist=new Skiplist();
        System.out.println(skiplist.search(2));
        skiplist.add(50);
        skiplist.add(50);
        skiplist.add(60);
        skiplist.erase(50);
        System.out.println(skiplist.search(50));
    }


}
