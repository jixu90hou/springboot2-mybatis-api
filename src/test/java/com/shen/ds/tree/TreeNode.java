package com.shen.ds.tree;

public class TreeNode {
    private TreeNode leftNode;
    private TreeNode rightNode;
    private int data;
    private boolean isDelete;
    private TreeNode parent;

    public TreeNode(TreeNode leftNode, TreeNode rightNode, int data) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
        this.isDelete=false;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "leftNode=" + leftNode + ", rightNode=" + rightNode + ", data=" + data + ", isDelete=" + isDelete + '}';
    }

    public TreeNode(TreeNode leftNode, TreeNode rightNode, int data, boolean isDelete) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.data = data;
        this.isDelete = isDelete;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
