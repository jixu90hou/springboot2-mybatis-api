package com.shen.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.shen.queue.Message;
import com.shen.utils.LRULinkedHashMap;
import com.shen.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;
    public static DelayQueue<Message> queue = new DelayQueue<Message>();
    public static final int itemCount = 200000;
    public static final int slotCount = 100000;
    public static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    public static final ExecutorService singleService = Executors.newSingleThreadScheduledExecutor();
    //private LRULinkedHashMap<String,Integer> lruLinkedHashMap=new LRULinkedHashMap(1000);
 //   private Map<String, Integer> lruLinkedHashMap = new ConcurrentHashMap<>(1000);

    Cache<String, Integer> lruLinkedHashMap = CacheBuilder.newBuilder()
            .expireAfterWrite(100, TimeUnit.SECONDS)
            .maximumSize(1000).build();
    volatile long beginTime = 0;
    /* ScheduledExecutorService service = Executors
             .newSingleThreadScheduledExecutor();
     ScheduledExecutorService service2 = Executors
             .newSingleThreadScheduledExecutor();*/
    public static AtomicInteger count = new AtomicInteger();

    public RedisController() {
         consume();
        //   for (int i = 0; i < 8; i++) {
        // consume();
        //   }

        // setData();
    }

  /*  public void setData() {
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("======run setRedisData========");
                setRedisData();
            }
        }, 6, 5, TimeUnit.SECONDS);
        service2.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("======run setQueueData========");
                setQueueData();
            }
        }, 6, 5, TimeUnit.SECONDS);
    }*/

    public void consume() {
        singleService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message message = queue.take();
                        Object value = redisUtils.get(message.getBody());
                        lruLinkedHashMap.put(message.getBody(), message.getId());
                        // System.out.println(message);
                       int index = count.incrementAndGet();
                        //  System.out.println("======"+"\t"+message+"\t"+queue.size());

                        //if (index % 10000 == 0) {
                        System.out.println(message + "\t" + queue.size() + "\tvalue:" + value + "\t" + Thread.currentThread().getName());
                      //  }

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
        setData1();
        //  setRedisData();
        return "success";
    }

    @GetMapping("/getItem")
    public Object getItem(@RequestParam String item) {
        if (item == null) {
            return "no";
        }
      /*  try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Integer value = lruLinkedHashMap.getIfPresent(item);
        System.out.println("value:" + value + "\t" + lruLinkedHashMap.size());
        if (value == null) {
            value = (Integer) redisUtils.get(item);
            lruLinkedHashMap.put(item, value);
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String key=item.split("-")[1];
                queue.offer(new Message(Integer.valueOf(key), item, 5000));
            }
        });
        return value;
    }

    public void setQueueData() {
        for (int i = 0; i < itemCount; i++) {
            String key = "item-" + i;
            Message m1 = new Message(1, key, 5000);
            queue.offer(m1);
        }
    }

    public void setRedisData() {
        for (int i = 0; i < itemCount; i++) {
            int finalI = i;
         /*   executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String key = "item-" + finalI;
                    System.out.println(finalI);
                    redisUtils.set(key, finalI, 10);
                }
            });*/
            String key = "item-" + finalI;
            System.out.println(finalI);
            redisUtils.set(key, finalI);
        }
    }

    public void setData1() {
        for (int i = 0; i < itemCount; i++) {
            String key = "item-" + i;
            Message m1 = new Message(1, key, 5000);
            queue.offer(m1);
            if (i % slotCount == 0) {
                System.out.println("提供：" + m1);
            }
            //  redisUtils.set(key, i, 5);
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
