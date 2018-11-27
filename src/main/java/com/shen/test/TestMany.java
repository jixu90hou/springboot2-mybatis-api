package com.shen.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.shen.utils.HttpUtils.sendGet;

public class TestMany {
    private final static ExecutorService executorService= Executors.newFixedThreadPool(1000);
    public static void main(String[] args) {
        int count=10000000;
        CountDownLatch latch=new CountDownLatch(count);
        Map<String, String> parameters = new HashMap<String, String>();
        for (int i=0;i<100000;i++){
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    send(parameters, finalI);
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
            System.out.println("===============完成==================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void send(Map<String, String> parameters, int i) {
        String url="http://localhost:8080/redis/getItem?item=";
        String value="item-"+i;
        String result = sendGet(url+value, parameters);
        System.out.println("result:"+result);
    }
}
