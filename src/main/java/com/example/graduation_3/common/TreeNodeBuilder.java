package com.example.graduation_3.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cy
 * @data 2020/5/7 - 17:58
 */
public class TreeNodeBuilder {

    public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topPid){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if(n1.getPid().equals(topPid)) {
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                if(n1.getId().equals(n2.getPid())) {
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }

    public void demo(List<TreeNode> treeNodes,TreeNode a){
        for (TreeNode p :
                treeNodes) {
            if (p.getPid().equals(a.getId())){
                a.getChildren().add(p);
                demo(treeNodes,p);
            }
        }

    }
}
