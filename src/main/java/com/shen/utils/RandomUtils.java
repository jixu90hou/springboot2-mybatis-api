package com.shen.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomUtils {
    private static int countItem=1000;
    private static int[]a = new int[countItem+5];
    {
        for (int i = 1; i <= countItem; i++)
            a[i] = i;
    }
    private static Random rand = new Random(47);

    public static List<Integer> random() {
        List<Integer> ans = new ArrayList<Integer>();
        int index = 1;
        for (int i = 1; i <= countItem; i++) {
            int ind = index + rand.nextInt(countItem-index+1);
            int tmp = a[index];
            a[index] = a[ind];
            a[ind] = tmp;
            ans.add(a[index]);
            index++;
        }

        return ans;
    }

    public static void main(String[] args) {
        /*List<Integer> list = random();
        System.out.println(list.size());*/
        Random random=new Random();
        int num = random.nextInt(1000)%(1000-0+1) + 0;
        System.out.println("num:"+num);
       /* for (int i : list)
            System.out.println(i);
        Set<Integer> set = new HashSet<Integer>(list);
        for (int i = 1; i <= 10000; i++) {
            if (set.contains(i) == false) {
                System.out.println(false);
                break;
            }
        }*/
    }
}

