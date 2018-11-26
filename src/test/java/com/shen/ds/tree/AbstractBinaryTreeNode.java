package com.shen.ds.tree;

public class AbstractBinaryTreeNode {
    public void preorderTraversal(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.getData() + "\t");
            preorderTraversal(treeNode.getLeftNode());
            preorderTraversal(treeNode.getRightNode());
        }
    }

    public void inOrderTraversal(TreeNode treeNode) {
        if (treeNode != null) {
            inOrderTraversal(treeNode.getLeftNode());
            System.out.print(treeNode.getData() + "\t");
            inOrderTraversal(treeNode.getRightNode());
        }
    }

    public void postOrderTraversal(TreeNode treeNode) {
        if (treeNode != null) {
            postOrderTraversal(treeNode.getLeftNode());
            postOrderTraversal(treeNode.getRightNode());
            System.out.print(treeNode.getData() + "\t");
        }
    }
    public void createBinaryTree(int[] data) {
        if (data.length == 0) {
            return;
        }
        TreeNode[] treeNodes = new TreeNode[data.length];
        treeNodes[0] = new TreeNode(null, null, data[0]);
        for (int i = 1; i < data.length; i++) {
            TreeNode currentNode = new TreeNode(null, null, data[i]);
            treeNodes[i] = currentNode;
            //计算获取父节点的值
            int index = (i - 1) / 2;
            TreeNode parentTreeNode = treeNodes[index];
            if (i % 2 == 1) {
                parentTreeNode.setLeftNode(currentNode);
            } else {
                parentTreeNode.setRightNode(currentNode);
            }
        }
    }

    public void print(AbstractBinaryTreeNode treeNode,TreeNode root) {
        System.out.println("先序遍历：");
        treeNode.preorderTraversal(root);
        System.out.println("\n中序遍历：");
        treeNode.inOrderTraversal(root);
        System.out.println("\n后序遍历：");
        treeNode.postOrderTraversal(root);
    }
}
