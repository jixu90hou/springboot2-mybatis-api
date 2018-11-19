package com.winterchen.ds.tree;

public class DynamicBinaryTreeNode extends AbstractBinaryTreeNode {
    private TreeNode root;

    public void createTreeNode(int value, TreeNode parentNode) {
        TreeNode node = new TreeNode(null, null, value);
        //如果插入的值大于根节点，就匹配右节点值
        if (value > parentNode.getData()) {
            if (parentNode.getRightNode() == null) {
                parentNode.setRightNode(node);
                node.setParent(parentNode);
            } else {
                createTreeNode(value, parentNode.getRightNode());
            }
        } else {
            //否则就匹配左节点值
            if (parentNode.getLeftNode() == null) {
                parentNode.setLeftNode(node);
                node.setParent(parentNode);
            } else {
                createTreeNode(value, parentNode.getLeftNode());
            }
        }
    }

/*
    public void delete(int value) {
        //如果删除的是根节点
        if (root.getData() == value) {
            if (root.getRightNode() != null) {
                root = root.getRightNode();
            } else if (root.getLeftNode() != null) {
                root = root.getLeftNode();
            } else {
                root = null;
            }
        } else {

        }
    }
*/

    public boolean delete(int value) {
        //TreeNode temp = treeNode;
        TreeNode findNode = getTreeNode(value);
        TreeNode newNode = null;
        if (findNode != null && findNode.getData() == value) {
            if (findNode.getRightNode() != null) {
                newNode = findNode.getRightNode();
                TreeNode lastLastNode = getLeftLastNode(newNode);
                lastLastNode.setLeftNode(findNode.getLeftNode());
            } else if (findNode.getLeftNode() != null) {
                newNode = findNode.getLeftNode();
            }
            if (newNode != null) {
                findNode.setData(newNode.getData());
                findNode.setLeftNode(newNode.getLeftNode());
                findNode.setRightNode(newNode.getRightNode());
            } else {
                TreeNode parent = findNode.getParent();
                if (findNode.equals(parent.getLeftNode())) {
                    parent.setLeftNode(null);
                } else {
                    parent.setRightNode(null);
                }
            }
            return true;
        }
        return false;
    }

    private TreeNode getTreeNode(int value, TreeNode node) {
        if (node == null) {
            return null;
        }
        int data = node.getData();
        if (data == value) {
            return node;
        } else {
            TreeNode leftNode = getTreeNode(value, node.getLeftNode());
            if (leftNode != null) {
                return leftNode;
            }
            TreeNode rightNode = getTreeNode(value, node.getRightNode());
            if (rightNode != null) {
                return rightNode;
            }
        }
        return null;
    }

    public TreeNode getTreeNode(int value) {
        return getTreeNode(value, root);
    }

    public TreeNode getLeftLastNode(TreeNode treeNode) {
        for (; ; ) {
            if (treeNode.getLeftNode() == null) {
                return treeNode;
            }else{
                treeNode=treeNode.getLeftNode();
            }
        }
    }

    public TreeNode getRightLastNode(TreeNode treeNode) {
        for (; ; ) {
            if (treeNode.getRightNode() == null) {
                return treeNode;
            }
        }
    }

    public void createTreeNode(int value) {
        if (this.root == null) {
            this.root = new TreeNode(null, null, value);
        } else {
            createTreeNode(value, root);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public static void main(String[] args) {
        DynamicBinaryTreeNode treeNode = new DynamicBinaryTreeNode();
        treeNode.createTreeNode(40);
        treeNode.createTreeNode(30);
        treeNode.createTreeNode(80);
        treeNode.createTreeNode(10);
        treeNode.createTreeNode(60);
        treeNode.createTreeNode(90);
        treeNode.createTreeNode(35);
        treeNode.createTreeNode(70);
        TreeNode root = treeNode.getRoot();
        System.out.println("========================");
        treeNode.print(treeNode,root);

        System.out.println("\n查找内容：");
        TreeNode findTreeNode = treeNode.getTreeNode(30);
        System.out.println(findTreeNode);
        System.out.println("\n删除元素：");
        boolean isDelete = treeNode.delete(35);
        boolean isDelete1 = treeNode.delete(70);

        TreeNode root1 = treeNode.getRoot();
        System.out.println("========================");
        treeNode.print(treeNode,root);

    }


}
