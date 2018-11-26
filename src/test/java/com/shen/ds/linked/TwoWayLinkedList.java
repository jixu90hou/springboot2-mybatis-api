package com.shen.ds.linked;

import com.shen.ds.entity.User;

import java.util.Objects;

/**
 * 循环链表
 */
public class TwoWayLinkedList<T> implements MyList<T>{
    private TwoWayNode lastNode;
    private TwoWayNode  root;
    private int printIndex=0;
    private int count = 0;

    public static void main(String[] args) {
        TwoWayLinkedList linkedList = new TwoWayLinkedList();
        linkedList.add("zhangming1");
        linkedList.add("zhangming2");
        linkedList.add("zhangming3");
        linkedList.add("zhangming4");
        linkedList.print();
        linkedList.delete("zhangming1");
        linkedList.delete("zhangming4");
        linkedList.delete("zhangming2");
        linkedList.delete("zhangming3");

        System.out.println("===========删除后的数据==============");
        linkedList.print();

        TwoWayLinkedList<User> list=new TwoWayLinkedList<>();
        list.add(new User("zhang1",21));
        list.add(new User("zhang2",22));
        list.add(new User("zhang3",23));
        User fistUser=list.getFirst();
        System.out.println(fistUser.getName());
    }
    @Override
    public int add(T t) {
        Objects.requireNonNull(t);
        if (root == null) {
            root = new TwoWayNode(t);
            lastNode = root;
        } else {
            TwoWayNode next = new TwoWayNode(t);
            lastNode.setNextNode(next);
            next.setPreNode(lastNode);
            lastNode = next;
        }
        return count++;
    }

    @Override
    public T getFirst() {
        return (T) root.data;
    }

    public TwoWayNode getNode(T t) {
        return getNextNode(t, root);
    }
    private void print(TwoWayNode node) {
        System.out.println(node.getData());
        if (node.getNextNode() != null) {
            // System.out.println(node.getNext().getData());
            print(node.getNextNode());

        }
    }

    public void print() {
        if (root != null) {
            print(root);
        }
    }    private TwoWayNode getNextNode(T t, TwoWayNode node) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(t)) {
            return node;
        } else {
            return getNextNode(t, node.getNextNode());
        }
    }
    private TwoWayNode getPreNode(T t, TwoWayNode node) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(t)) {
            return node;
        } else {
            return getPreNode(t, node.getPreNode());
        }
    }
    @Override
    public int delete(T t) {
        TwoWayNode twoWayNode=getNode(t);
        if(twoWayNode==null){
            try {
                throw new IllegalAccessException("不存在该元素:"+t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        TwoWayNode preNode=twoWayNode.getPreNode();
        TwoWayNode nextNode=twoWayNode.getNextNode();
        if(preNode!=null){
            preNode.setNextNode(nextNode);
        }
        if(nextNode!=null){
            nextNode.setPreNode(preNode);
        }
        if (root.equals(twoWayNode)){
            root=nextNode;
        }
        return this.count--;
    }

    @Override
    public MyList<T> queryAll() {
        if(root==null){
            return null;
        }
        if(root.getNextNode()!=null){

        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    static class TwoWayNode {
        //数据
        private Object data;
        //前置节点
        private TwoWayNode preNode;
        //后置节点
        private TwoWayNode nextNode;

        public TwoWayNode(Object data, TwoWayNode preNode, TwoWayNode nextNode) {
            this.data = data;
            this.preNode = preNode;
            this.nextNode = nextNode;
        }

        public TwoWayNode(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public TwoWayNode getPreNode() {
            return preNode;
        }

        public TwoWayNode getNextNode() {
            return nextNode;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public void setPreNode(TwoWayNode preNode) {
            this.preNode = preNode;
        }

        public void setNextNode(TwoWayNode nextNode) {
            this.nextNode = nextNode;
        }
    }
}
