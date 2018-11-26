package com.shen;

import java.util.ArrayList;
import java.util.Iterator;

public class TestExample {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("zhangming1");
        list.add("zhangming2");
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
