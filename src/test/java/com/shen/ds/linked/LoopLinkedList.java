package com.shen.ds.linked;

import java.util.Objects;

public class LoopLinkedList<T> implements MyList<T> {
    private Node lastNode;
    private Node root;
    private int printIndex=0;
    private int count = 0;
    //  private Node next;

    @Override
    public int add(T t) {
        Objects.requireNonNull(t);
        if (root == null) {
            root = new Node(t);
            lastNode = root;
        } else {
            Node next = new Node(t);
            lastNode.setNext(next);
            lastNode = next;
        }
        lastNode.setNext(root);
        return count++;
    }

    private void print(Node node) {
        System.out.println(node.getData());
        if (node.getNext() != null) {
            // System.out.println(node.getNext().getData());
            if(++printIndex==count){
                return;
            }
            print(node.getNext());

        }
    }

    @Override
    public MyList<T> queryAll() {
        return null;
    }

    public void print() {
        if (root != null) {
            print(root);
        }
    }
    @Override
    public T getFirst() {
        return (T) this.root.getData();
    }

    public Node getNode(T t) {
       return getNode(t, root);
    }
    private Node getNode(T t, Node node) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(t)) {
            return node;
        } else {
            return getNode(t, node.getNext());
        }
    }

    @Override
    public int delete(T t) {
        return 0;
    /*    Objects.requireNonNull(t);
        if(count==0){
            try {
                throw new IllegalAccessException("集合为空，不能删除操作");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Node node=getNode(t);
        Node next=node.getNext();
        if(node.getNext()!=null){
            node.getNext().setNext();
        }
        node.setNext();*/
    }

    @Override
    public int size() {
        return this.count;
    }

    public static void main(String[] args) {
        LoopLinkedList linkedList = new LoopLinkedList();
        linkedList.add("zhangming1");
        linkedList.add("zhangming2");
        linkedList.add("zhangming3");
        linkedList.add("zhangming4");
        linkedList.print();
        System.out.println("第一个节点：" + linkedList.getFirst());
        System.out.println("数量：" + linkedList.size());
    }
}
