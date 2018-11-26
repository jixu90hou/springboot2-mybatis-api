package com.shen.ds.linked;

public interface MyList<T> {
    int add(T t);

    T getFirst();

    int delete(T t);
    int size();
    MyList<T> queryAll();
}
