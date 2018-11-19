package com.winterchen.ds.tree;

public class BinaryTreeNode extends AbstractBinaryTreeNode implements BinaryTree {
    private TreeNode root;

    @Override
    public String toString() {
        return "BinaryTreeNode{" + "root=" + root + '}';
    }

    public BinaryTreeNode(TreeNode rootTree) {
        this.root = rootTree;
    }

    public BinaryTreeNode() {
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6,20,30,40};
        BinaryTreeNode binaryTree = new BinaryTreeNode();
        binaryTree.createBinaryTree(array);
        TreeNode root=binaryTree.getRoot();
        System.out.println(binaryTree);
        binaryTree.print(binaryTree,root);
    }

    @Override
    public boolean add(int data) {
        return false;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void createBinaryTree(int[] data) {
        if (data.length == 0) {
            return;
        }
        TreeNode[] treeNodes = new TreeNode[data.length];
        treeNodes[0] = new TreeNode(null, null, data[0]);
        this.root=treeNodes[0];
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

    @Override
    public TreeNode get(String key) {
        return null;
    }

}
