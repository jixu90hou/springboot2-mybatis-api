package com.shen.test;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        Map<String,Integer> map=new ConcurrentHashMap(1);
        map.put("a1",1);
        map.put("a2",2);
        System.out.println(map.size());
        Set<Map.Entry<String,Integer>> entries= map.entrySet();
       for (Map.Entry<String,Integer> entry:entries){
           System.out.println(entry.getKey()+"\t"+entry.getValue());
       }
    }
}
