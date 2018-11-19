package com.winterchen.ds.queue;

import java.util.Arrays;

public class MyQueue<T> {
    //默认环形数组为16个
    private Object[] array;
    //尾部指针
    private int tail = -1;
    //头指针
    private int head = -1;
    private int size;

    @Override
    public String toString() {
        return "MyQueue{" + "array=" + Arrays.toString(array) + ", tail=" + tail + ", head=" + head + ", size=" + size + '}';
    }

    public MyQueue(int size) {
        this.array = new Object[size];
        this.size = size;
    }

    /**
     * 入队
     *
     * @param t
     */
    public void enqueue(T t) {
        if (head == -1) {
            head++;
        } else if (isQueueFull()) {
            throw new RuntimeException("队列已满");
        }
        if (tail + 1 == size) {
            //到数组的最后一个元素了，
            tail = -1;
        }
        array[++tail] = t;
    }

    //算法规则：tail-head<0 则加size,结果+1，如果最终结果等于size则队列已满
    public boolean isQueueFull() {
        int distance = tail - head;
        if (distance < 0) {
            distance = distance + size;
        }
        if (distance + 1 == size && getNext(tail)!=null) {
            return true;
        }
        return false;
    }

    public boolean isQueueEmpty() {
        if (head == tail + 1 && getNext(head) == null) {
            return true;
        }
        return false;
    }

    public T getNext(int index) {
        if (index + 1 == size) {
            return (T) array[0];
        } else {
            return (T) array[index + 1];
        }
    }

    /**
     * 出队
     */
    public T dequeue() {
        if (isQueueEmpty()) {
            throw new RuntimeException("队列已空");
        }
        T value = (T) array[head];
        array[head] = null;
        if (++head == size) {
            head = 0;
        }
        return value;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(4);
        myQueue.enqueue(10);
        myQueue.enqueue(8);
        myQueue.enqueue(6);
        myQueue.enqueue(4);
        System.out.println("10,8,6,4入队：" + myQueue);
        System.out.println(myQueue.dequeue());
        System.out.println("出队后的元素：" + myQueue);
        myQueue.enqueue(2);
        myQueue.dequeue();

        System.out.println("2再次入队：" + myQueue);
        myQueue.enqueue(2);
        System.out.println("出队后的元素：" + myQueue);
        myQueue.dequeue();
        System.out.println(myQueue);
        myQueue.dequeue();
        myQueue.dequeue();

        System.out.println(myQueue);
        System.out.println("出队后的元素：" + myQueue);
        myQueue.enqueue(2);
        System.out.println("出队后的元素：" + myQueue);
    }
}
