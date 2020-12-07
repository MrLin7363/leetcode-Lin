package Tree.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/7
  *@Describe: 先调用 serialize 再调用 deserialize
 */

import Construct.TreeNode;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class P297_Serialize_Deserialize_Binary_Tree {
    /*
    先序遍历，根据 , 反序列化  11 + 11
     */
    // 序列化成字符串
    public String serialize(TreeNode root) {
        return reserialize(root,"");
    }

    // 反序列化为树
    public TreeNode deserialize(String data) {
        String[] data_array=data.split(",");
        Deque<String> data_list=new LinkedList<>(Arrays.asList(data_array));
        return redeserialize(data_list);
    }

    // 序列化递归函数  , 分割
    public String reserialize(TreeNode root,String str){
        if (root==null){
            str+="None,";
        }else{
            str+=root.val+",";
            str=reserialize(root.left,str);
            str=reserialize(root.right,str);
        }
        return str;
    }
    // 先序回归造树
    public TreeNode redeserialize(Deque<String> strList){
        if (strList.getFirst().equals("None")){
            strList.removeFirst();
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(strList.getFirst()));
        strList.removeFirst();
        root.left=redeserialize(strList);
        root.right=redeserialize(strList);
        return root;
    }

}
