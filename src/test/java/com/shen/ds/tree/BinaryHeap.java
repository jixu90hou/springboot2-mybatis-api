package com.shen.ds.tree;

import java.util.Arrays;

public class BinaryHeap extends AbstractBinaryTreeNode {
    public static void main(String[] args) {
        BinaryHeap binaryHeap = new BinaryHeap();
       /* binaryHeap.createMaxBinaryHeap(90);
        binaryHeap.createMaxBinaryHeap(80);
        binaryHeap.createMaxBinaryHeap(70);
        binaryHeap.createMaxBinaryHeap(60);
        binaryHeap.createMaxBinaryHeap(40);
        binaryHeap.createMaxBinaryHeap(30);
        binaryHeap.createMaxBinaryHeap(20);
        binaryHeap.createMaxBinaryHeap(10);
        binaryHeap.createMaxBinaryHeap(50);
        binaryHeap.createMaxBinaryHeap(35);
        binaryHeap.print();*/
        Integer[] array = {90, 80, 70, 60, 40, 30, 20, 10, 50, 85};
        binaryHeap.init(array);
        System.out.println();
        binaryHeap.print();
        // binaryHeap.adjust();
        int count = binaryHeap.size();
        for (int i=0;i<count;i++){
            System.out.println();
            binaryHeap.delete();
            binaryHeap.print();
        }

      /*  binaryHeap.delete(80);
        binaryHeap.delete(70);
        binaryHeap.delete(60);
        binaryHeap.delete(40);
        binaryHeap.delete(30);
        binaryHeap.delete(20);
        binaryHeap.delete(10);
        binaryHeap.delete(50);
        binaryHeap.delete(35);*/
        System.out.println("\n删除后的数据：");
      //  binaryHeap.print();

        // int[] array = {90, 85, 70, 60, 80, 30, 20, 10, 50, 40,65};
        /*B
        binaryHeap.createMaxBinaryHeap(80);
        binaryHeap.createMaxBinaryHeap(70);
        binaryHeap.createMaxBinaryHeap(60);
        binaryHeap.createMaxBinaryHeap(40);
        binaryHeap.createMaxBinaryHeap(30);
        binaryHeap.createMaxBinaryHeap(20);
        binaryHeap.createMaxBinaryHeap(10);
        binaryHeap.createMaxBinaryHeap(50);
        binaryHeap.createMaxBinaryHeap(85);

        binaryHeap.print(binaryHeap, binaryHeap.root);*/

    }

    private Integer[] data = new Integer[8];
    private int index = 0;
    private int count = 0;
    private TreeNode root;

    public void print() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                System.out.print(data[i] + "\t");
            }
        }
    }

    public void init(Integer[] array) {
        this.data = array;
        this.count = array.length;
    }

    public void createMaxBinaryHeap(int value) {
        index = count++;
        if (index >= data.length) {
            resize();
        }
        if (data[0] == null) {
            data[0] = value;
        } else {
            data[index] = value;
        }
        while (true) {
            int parentIndex = (index - 1) / 2;
            int parent = data[parentIndex];
            if (parent < data[index]) {
                data[parentIndex] = data[index];
                data[index] = parent;
                if (index == parentIndex) {
                    break;
                }
                index = parentIndex;
            } else {
                break;
            }
        }

    }

    public int getIndex(int value) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void build() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
            }
        }
    }

    public int size() {
        return this.count;
    }

    public void delete() {
        int index = 0;
        //用最后一个值替换掉当前节点
        int temp = data[index];
        data[index] = data[count - 1];
        data[count - 1] = temp;
        while (true) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            boolean existLeft = count - 1 > leftIndex;
            boolean existRight = count - 1 > rightIndex;
            if (existLeft && existRight) {
                if (data[leftIndex] > data[rightIndex] && data[leftIndex] > data[index]) {
                    swap(index, leftIndex);
                    index = leftIndex;
                } else if (data[rightIndex] > data[leftIndex] && data[rightIndex] > data[index]) {
                    swap(index, rightIndex);
                    index = rightIndex;
                } else {
                    break;
                }
            } else if (existRight && data[rightIndex] > data[index]) {
                swap(index, rightIndex);
                index = rightIndex;
            } else if (existLeft && data[leftIndex] > data[index]) {
                swap(index, leftIndex);
                index = leftIndex;
            } else {
                break;
            }
        }
        count--;
    }

    public void adjust() {
        int index = 0;
        int nextIndex = 0;
        int hi=0;
        while (true) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            boolean existLeft = count - 1 > leftIndex;
            boolean existRight = count - 1 > rightIndex;
            if (existLeft && existRight) {
                if (data[leftIndex] > data[rightIndex] && data[leftIndex] > data[index]) {
                    swap(index, leftIndex);
                    index = leftIndex;
                } else if (data[rightIndex] > data[leftIndex] && data[rightIndex] > data[index]) {
                    swap(index, rightIndex);
                    index = rightIndex;
                } else {
                    //上一次是左边的，这一次该是右边节点了
                    if (nextIndex % 2 == 0) {
                        Math.pow(2,10);
                        index = leftIndex;
                        nextIndex = rightIndex;
                    } else {
                        index = rightIndex;
                        nextIndex = leftIndex;
                    }

                }
            } else if (existRight && data[rightIndex] > data[index]) {
                swap(index, rightIndex);
                index = rightIndex;
            } else if (existLeft && data[leftIndex] > data[index]) {
                swap(index, leftIndex);
                index = leftIndex;
            } else {
                break;
            }
        }

    }

    public void swap(int indexA, int indexB) {
        int temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    private void resize() {
        this.data = Arrays.copyOf(data, data.length * 2);
    }

    public void setMaxBinary(TreeNode currentNode) {
        if (currentNode.getParent().getData() > currentNode.getData()) {
            TreeNode temp = currentNode.getParent();

            currentNode.setParent(currentNode);

        }
    }

}
