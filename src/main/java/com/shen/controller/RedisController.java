package com.shen.controller;

import com.shen.queue.Message;
import com.shen.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;
    public static DelayQueue<Message> queue = new DelayQueue<Message>();
    public static final int itemCount=200000;
    public static ConcurrentHashMap map = new ConcurrentHashMap(itemCount);
    public static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    volatile long beginTime = 0;
    ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();
    ScheduledExecutorService service2 = Executors
            .newSingleThreadScheduledExecutor();
    public static AtomicInteger count = new AtomicInteger();

    public RedisController() {
        for (int i = 0; i < 1; i++) {
            consume();
        }

        setData();
    }

    public void setData() {
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        /*service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("======run setRedisData========");
                setRedisData();
            }
        }, 6, 5, TimeUnit.SECONDS);*/
        service2.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("======run setQueueData========");
                setQueueData();
            }
        }, 6, 5, TimeUnit.SECONDS);
    }

    public void consume() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        map.put(message.getBody(), message.getId());
                        // System.out.println(message);
                        int index = count.incrementAndGet();
                      //  System.out.println("======"+"\t"+message+"\t"+queue.size());

                        if (index % 1000 == 0) {
                        System.out.println(message+"\t"+queue.size());
                        }
                        // queue.offer(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @GetMapping("/init")
    public Object init() {
        System.out.println("============init============");
        beginTime = System.currentTimeMillis();
        //setData1();
        return "success";
    }
    public void setQueueData(){
        for (int i = 0; i < itemCount; i++) {
            String key = "item-" + i;
            Message m1 = new Message(1, key, 5000);
            queue.offer(m1);
        }
    }
    public void setRedisData() {
        for (int i = 0; i < itemCount; i++) {
            String key = "item-" + i;
            redisUtils.set(key, i, 5);
        }
    }
    public void setData1() {
        for (int i = 0; i < itemCount; i++) {
            String key = "item-" + i;
            Message m1 = new Message(1, key, 5000);
            queue.offer(m1);
            redisUtils.set(key, i, 5);
        }
    }
    @GetMapping("/list")
    public Object list() {
        return "success";
    }

    @GetMapping("/test")
    public Object test() {
        testRedis();
        return "success";
    }

    private void testRedis() {
        redisUtils.set("test", "test1");
        Object value = redisUtils.get("test");
        System.out.println("value:" + value);
    }

}
