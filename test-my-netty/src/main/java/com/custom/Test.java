package com.custom;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] arg){

        // 用来判断和否为正数。。。
//        int val = -2;
//        System.out.println( (val & -val) == val);
        System.out.println(  1 << 4);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
}
