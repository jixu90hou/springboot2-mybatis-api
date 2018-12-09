package com.shen.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMany {
    private final static ExecutorService executorService= Executors.newFixedThreadPool(100);
    private static final int count=20000000;

    public static List<String> generate(){
        System.out.println("*************生成随机数开始*************");
        Random random=new Random();
        int max=1000;
        int min=0;
        List<String> numList=new ArrayList<>(count);
        for (int i=0;i<count;i++){
            int num = random.nextInt(max)%(max-min+1) + min;
            numList.add("item-"+num);
        }
        System.out.println("*************生成随机数完成*************");
        return numList;
    }
    public static void main(String[] args) {

        CountDownLatch latch=new CountDownLatch(count);
       List<String> valueList=generate();
        Map<String, String> parameters = new HashMap<String, String>();
        for (String value:valueList){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    send(parameters, value);
                    latch.countDown();
                }
            });
        }
      /*  for (int i=0;i<100000;i++){
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    send(parameters, finalI);
                    latch.countDown();
                }
            });
        }*/
        try {
            latch.await();
            System.out.println("===============完成==================");
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void send(Map<String, String> parameters, String value) {
        String url="http://172.16.10.50:8080/redis/getItem?item=";
        String result = sendGet(url+value, parameters);
        System.out.println("url+value:"+(url+value));
        System.out.println("result:"+result);
    }
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            String full_url=url;
            // System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            // 遍历所有的响应头字段
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
